package study.lld.projects.moviebooking;

import static java.util.Objects.isNull;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.AllArgsConstructor;

public class Main {
    public static void main(String[] args) {
        /*
        Movie Booking System - Book my show

        Functional requirements
        User goes to the app and gets a list of movies in a city
        Each movie will have shows in different Multiplex
        Each Multiplex will have different shows of different movies
        A user can do a booking of a particular movies on a particular show in a theater


        Invariants
        - Concurrency of seat booking of a movies should be maintained.
        - Each Multiplex can have different shows of a movie
        - Multiplex can have multiple shows
        - Multiplex can have multiple Auditoriums
        - A Multiplex can have multiple Auditoriums
        - Auditorium will have a seat plan
        - A show knows its auditorium
        - A show knows its theater
        - A show knows its available seats which it gets from the auditorium
        - A show knows its timing
        - A show knows its movie
        - A booking will have a show and List<seats> along with payment
        -

        API
        listMovies()
            list all movies from movie dao
        List<Show> getShowsForMovies(Movie)
            list all shows for a movie from show dao
        List<Seat> getAvailableSeatsOfShow(Show)
            get Available seats of a movie from show Dao
        Booking createBooking(Show,List<Seat>)
            get available seats for a show
            validate that requested seats are available
            lock the seats for the show <- transactional boundary
            complete payment
            mark seats as booked
            create booking in booking dao
            unlock <- transactional boundary end
            return booking

        BookMyShow
        - MoviesService
        - ShowService
        - TheaterService

        Movie
        - id
        - name
        - runtime
        - genre

        Multiplex
        - id
        - name
        - List<Auditorium>

        Auditorium
        - id
        - List<Seat>
        - type - IMAX, 3D

        Seat
        - id
        - auditoriumId
        - type

        Show
        - id
        - movieId
        - multiplexId
        - auditoriumId
        - availableSeats<Seat>
        - bookedSeats<Seat>
        - startTiming
        - endTiming

        Booking
        - id
        - showId
        - List<Integer> seatIds
        - paymentMode
        - paymentAmount

         */
        MovieDao movieDao = new MovieDao();
        MovieService movieService = new MovieServiceImpl(movieDao);

        ShowDao showDao = new ShowDao();
        ShowSeatLockDao showSeatLockDao = new ShowSeatLockDao();
        ShowService showService = new ShowServiceImpl(showDao, showSeatLockDao);

        BookingDao bookingDao = new BookingDao();
        BookingService bookingService = new BookingServiceImpl(bookingDao);

        PaymentService paymentService = new PaymentServiceImpl();
        BookMyShow bms = new BookMyShow(movieService, showService, bookingService, paymentService);
    }
}

@AllArgsConstructor
class Movie {
    int id;
    String name;
    Duration runtime;
    String genre;
}

@AllArgsConstructor
class Multiplex {
    int id;
    List<Auditorium> auditoriums;
}

@AllArgsConstructor
class Auditorium {
    int id;
    List<Seat> seats;
    String type;
}

@AllArgsConstructor
class Seat {
    int id;
    String type;
    int auditoriumId;
    double tariff;
}

class Show {
    int id;
    int movieId;
    int multiplexId;
    int auditoriumId;
    Set<Seat> bookedSeats;
    Set<Seat> availableSeats;
    Map<Seat, Instant> seatLockEndTime;
    Instant startTime;
    Instant endTime;
}

class Booking {
    static AtomicInteger counter = new AtomicInteger(0);
    int id;
    int showId;
    List<Seat> bookedSeats;
    String paymentMode;
    double totalAmount;
    BookingStatus status;

    Booking(int showId, List<Seat> seats) {
        this.showId = showId;
        id = counter.getAndIncrement();
        this.bookedSeats = seats;
        status = BookingStatus.IN_PROGRESS;
    }
}

enum BookingStatus {
    IN_PROGRESS,
    COMPLETED,
    PAYMENT_SUCCESSFUL,
    FAILED,
    SEATS_BOOKED,
    PAYMENT_PENDING
}

class BookMyShow {
    private final MovieService movieService;
    private final ShowService showService;
    private final BookingService bookingService;
    private final PaymentService paymentService;

    BookMyShow(
            MovieService movieService,
            ShowService showService,
            BookingService bookingService,
            PaymentService paymentService) {
        this.movieService = movieService;
        this.showService = showService;
        this.bookingService = bookingService;
        this.paymentService = paymentService;
    }

    List<Movie> listMovies() {
        return movieService.listMovies();
    }

    List<Show> listShowForMovie(int movieId) {
        return showService.getShowsForMovie(movieId);
    }

    Set<Seat> getAvailableSeatsForShow(int showId) {
        return showService.getAvailableSeatsForShow(showId);
    }

    Booking createBooking(int showId, List<Seat> requestedSeats) {
        Set<Seat> availableSeats = showService.getAvailableSeatsForShow(showId);
        requestedSeats.forEach(
                s -> {
                    if (!availableSeats.contains(s))
                        throw new RuntimeException("seat not available " + s.id);
                });
        Booking booking = null;
        try {
            showService.lockSeatsForShow(
                    showId,
                    requestedSeats); // If lock fails then unlock the seats if any ges locked,
            // handled in finally
            booking =
                    bookingService.createBooking(
                            showId,
                            requestedSeats); // if create booking fails, then mark the booking
            // status as failed if the booking got persisted, else
            // ekip it, along with unlocking seats
            paymentService.completePaymentForBooking(
                    booking); // if payment fails, refund any amount debited, and mark the booking
            // as failed, along with above rollbackes
            bookingService.updateBookingStatus(booking, BookingStatus.PAYMENT_SUCCESSFUL);
            showService.bookSeats(
                    showId,
                    requestedSeats); // if booking seats fails, then un book any seats which got
            // booked, and mark the bookng as failed, and do the above
            // rollbacks
            bookingService.updateBookingStatus(booking, BookingStatus.SEATS_BOOKED);
            bookingService.updateBookingStatus(booking, BookingStatus.COMPLETED);
        } catch (RuntimeException e) {
            System.out.println("Unexpected error occurred");

            showService.unlockSeatsForShow(showId, requestedSeats);

            if (!isNull(booking)) {
                bookingService.updateBookingStatus(booking, BookingStatus.FAILED);
                if (booking.status == BookingStatus.PAYMENT_SUCCESSFUL
                        || booking.status == BookingStatus.SEATS_BOOKED) {
                    paymentService.refundPaymentForBooking(booking);
                }
                if (booking.status == BookingStatus.SEATS_BOOKED) {
                    showService.markSeatsAsAvailable(showId, requestedSeats);
                }
            }

            throw e;
        }
        return booking;
    }
}
/*
listMovies()
           list all movies from movie dao
       List<Show> getShowsForMovies(Movie)
           list all shows for a movie from show dao
       List<Seat> getAvailableSeatsOfShow(Show)
           get Available seats of a movie from show Dao
       Booking createBooking(Show,List<Seat>)
           get available seats for a show
           validate that requested seats are available
           lock the seats for the show <- transactional boundary
           complete payment
           mark seats as booked
           create booking in booking dao
           unlock <- transactional boundary end
           return booking
*/
interface PaymentService {
    void completePaymentForBooking(Booking booking);

    void refundPaymentForBooking(Booking booking);
}

class PaymentServiceImpl implements PaymentService {

    @Override
    public void completePaymentForBooking(Booking booking) {
        double total = 0;
        for (Seat seat : booking.bookedSeats) {
            total += seat.tariff;
        }
        booking.totalAmount = total;
        booking.paymentMode = "CASH";
        System.out.printf("paying %f with %s\n", booking.totalAmount, booking.paymentMode);
    }

    @Override
    public void refundPaymentForBooking(Booking booking) {
        System.out.println("refund booking amount " + booking.totalAmount);
    }
}

interface BookingService {
    Booking createBooking(int showId, List<Seat> seats);

    void updateBookingStatus(Booking booking, BookingStatus bookingStatus);
}

class BookingServiceImpl implements BookingService {
    private final BookingDao bookingDao;

    BookingServiceImpl(BookingDao bookingDao) {
        this.bookingDao = bookingDao;
    }

    @Override
    public Booking createBooking(int showId, List<Seat> seats) {
        Booking booking = new Booking(showId, seats);
        bookingDao.save(booking);
        return booking;
    }

    @Override
    public void updateBookingStatus(Booking booking, BookingStatus bookingStatus) {
        booking.status = bookingStatus;
        bookingDao.save(booking);
    }
}

class BookingDao {
    Map<Integer, Booking> bookings = new ConcurrentHashMap<>();

    public void save(Booking booking) {
        bookings.put(booking.id, booking);
    }
}

interface MovieService {
    List<Movie> listMovies();
}

class MovieServiceImpl implements MovieService {
    private final MovieDao movieDao;

    MovieServiceImpl(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    @Override
    public List<Movie> listMovies() {
        return movieDao.listMovies();
    }
}

class MovieDao {
    Map<Integer, Movie> movies = new ConcurrentHashMap<>();

    public List<Movie> listMovies() {
        return movies.values().stream().toList();
    }

    void save(Movie movie) {
        movies.put(movie.id, movie);
    }
}

interface ShowService {
    List<Show> getShowsForMovie(int movieId); // get shows from show dao

    Set<Seat> getAvailableSeatsForShow(
            int showId); // get available seats, this excludes seats which are locked

    void lockSeatsForShow(int showId, List<Seat> seats); // lock seats for show for 10 mins

    void unlockSeatsForShow(
            int showId, List<Seat> seats); // clear locked seats when booking completes

    void bookSeats(int showId, List<Seat> seats); // add to booked seats set

    void markSeatsAsAvailable(int showId, List<Seat> requestedSeats);
}

class ShowServiceImpl implements ShowService {
    private final ShowDao showDao;
    private final ShowSeatLockDao showSeatLockDao;

    ShowServiceImpl(ShowDao showDao, ShowSeatLockDao showSeatLockDao) {
        this.showDao = showDao;
        this.showSeatLockDao = showSeatLockDao;
    }

    @Override
    public List<Show> getShowsForMovie(int movieId) {
        return showDao.getShowsForMovie(movieId);
    }

    @Override
    public Set<Seat> getAvailableSeatsForShow(int showId) {
        Show show = showDao.get(showId);
        return show.availableSeats;
    }

    @Override
    public void lockSeatsForShow(int showId, List<Seat> seats) {
        showSeatLockDao.lockSeats(showDao.get(showId), seats);
    }

    @Override
    public void unlockSeatsForShow(int showId, List<Seat> seats) {
        showSeatLockDao.unlockSeats(showDao.get(showId), seats);
    }

    @Override
    public void bookSeats(int showId, List<Seat> seats) {
        Show show = showDao.get(showId);
        seats.forEach(
                s -> {
                    if (!show.availableSeats.contains(s)) {
                        throw new RuntimeException("seat is not available to book");
                    }
                });
        show.bookedSeats.addAll(seats);
        seats.forEach(s -> show.availableSeats.remove(s));
        showDao.update(show);
        unlockSeatsForShow(showId, seats);
    }

    @Override
    public void markSeatsAsAvailable(int showId, List<Seat> requestedSeats) {
        Show show = showDao.get(showId);
        requestedSeats.forEach(s -> show.bookedSeats.remove(s));
        show.availableSeats.addAll(requestedSeats);
        showDao.update(show);
    }
}

class ShowDao {
    Map<Integer, Show> shows = new ConcurrentHashMap<>();

    public Show get(int showId) {
        if (!shows.containsKey(showId)) throw new RuntimeException("show doesnt exist");
        return shows.get(showId);
    }

    public List<Show> getShowsForMovie(int movieId) {
        return shows.values().stream().filter(s -> s.movieId == movieId).toList();
    }

    public void update(Show show) {
        shows.put(show.id, show);
    }

    public void save(Show show) {
        shows.put(show.id, show);
    }
}

class ShowSeatLockDao {
    Map<ShowSeat, Instant> lockedSeats = new ConcurrentHashMap<>();

    public void lockSeats(Show show, List<Seat> seats) {
        for (Seat seat : seats) {
            ShowSeat showSeat = new ShowSeat(show.id, seat.id);
            if (lockedSeats.containsKey(showSeat)) {
                System.out.println("already locked seat");
                throw new RuntimeException("seat " + seat.id + " already locked");
            }
            lockedSeats.put(showSeat, Instant.now().plus(10, ChronoUnit.MINUTES));
        }
    }

    public void unlockSeats(Show show, List<Seat> seats) {
        for (Seat seat : seats) {
            ShowSeat showSeat = new ShowSeat(show.id, seat.id);
            lockedSeats.remove(showSeat);
        }
    }
}

class ShowSeat {
    Integer showId;
    Integer seatId;

    ShowSeat(int showId, int seatId) {
        this.showId = showId;
        this.seatId = seatId;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof ShowSeat showSeat) {
            return Objects.equals(showSeat.seatId, seatId)
                    && Objects.equals(showSeat.showId, seatId);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return showId.hashCode() + seatId.hashCode();
    }
}
