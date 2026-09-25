package study.lld.projects.library;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        /*
        Library Management System
        - PaymentService
        - BookService
        - UserService
        - BorrowBookService
        - CalculateFeeService
        - InvoicingService
        - searchBook(String title): List<Book>
        - borrowBook(int bookId, int userId): Invoice
        - isBookAvailableForBorrow(String title): boolean
        - returnBook(int bookId): Invoice
        - pay(Invoice, PaymentStrategy)

        BookService{
            - getBook(int id): Book
            - getAvailableBooksOfTitle(String title): List<Book>
            - setBookAsBorrowed(Book book, int userId)
            - setBookAsReturned(Book book)
        }
        BorrowBookService{
            - createBorrowTicket(int userId, int bookId, Instant scheduledReturnDate): BorrowBookTicket
            - updateReturnDate(BorrowBookTicket ticket, Instant returnDate): void

        }
        CalculateFeeService{
            int calculateFee(BorrowBookTicket ticket)
        }
        InvoiceService{
            - createInvoice(BorrowBookTicket)
            - updateBorrowFeeStatus(ticket,PaymentStatus)
            - updateLateFeeStatus(ticket,PaymentStatus)
            - updateBorrowFee(ticket,fee)
            - updateLateFee(ticket,fee)
        }
        PaymentService{
            pay(Invoice, PaymentStrategy)
        }


        Charges upfront / or later ? upfront and later both

        User
        - id
        - name

        Book
        - id
        - title
        - genre
        - type (hardcover, paperback)
        - borrowed
        - currentBorrowUserId <Not sure to have this here or get from ticket>
        - isAvailableToBorrow()

        BorrowBookTicket
        - id
        - userId
        - bookId
        - borrowDate
        - scheduledReturnDate
        - actualReturnDate

        Invoice
        - id
        - BorrowBookTicket
        - borrowingFee
        - lateFee
        - borrowFeePaymentStatus: boolean
        - lateFeePaymentStatus: boolean
         */
    }
}
/*
Library Management System
        - PaymentService
        - BookService
        - UserService
        - BorrowBookService
        - CalculateFeeService
        - InvoicingService
        - searchBook(String title): List<Book>
        - borrowBook(int bookId, int userId): Invoice
        - isBookAvailableForBorrow(String title): boolean
        - returnBook(int bookId): Invoice
        - pay(Invoice, PaymentStrategy)
 */
class LibraryService {
    PaymentService paymentService;
    BookService bookService;
    UserService userService;
    BorrowBookService borrowBookService;
    CalculateFeeService calculateFeeService;
    InvoicingService invoicingService;

    List<Book> searchBook(String title) {
        return bookService.getBooksOfTitle(title);
    }

    boolean isBookAvailableForBorrow(String title) {
        return bookService.isBookAvailableForBorrow(title);
    }

    BorrowBookTicket borrowBook(String title, int userId) {
        if (!bookService.isBookAvailableForBorrow(title)) {
            throw new RuntimeException("book not available");
        }
        Book book = bookService.getAnyAvailableBookOfTitle(title);
        boolean borrowed = false;
        // mutable boundary of only book
        try {
            bookService.setBookAsBorrowed(book);
            borrowed = true;
            return borrowBookService.createBorrowTicket(
                    userId, book.id, Instant.now().plus(2, ChronoUnit.DAYS));
        } catch (Exception e) {
            System.out.println("unexpected error while borrowing");
            if (borrowed) {
                book.markBookAsReturned();
            }
            throw e;
        }
    }

    Invoice returnBook(BorrowBookTicket ticket) {
        if (ticket.status == LifecycleStatus.RETURNED)
            throw new RuntimeException("already returned state");
        Book book = bookService.getBook(ticket.bookId);
        Instant previousReturnDate = ticket.actualReturnDate;

        boolean returnDateUpdated = false;
        boolean invoiceCreated = false;
        boolean returned = false;
        boolean ticketMarkedAsReturned = false;
        book.lock.lock();
        ticket.lock.lock();
        // This is a mutable boundary of two objects book and ticket. Locking on both
        // This double lock can be avoided with an atomic transaction on a database
        try {
            borrowBookService.updateReturnDate(ticket, Instant.now());
            returnDateUpdated = true;

            int borrowFee = calculateFeeService.calculateBorrowFee(ticket);
            int lateFee = calculateFeeService.calculateLateFee(ticket);
            Invoice invoice = invoicingService.createInvoice(ticket, borrowFee, lateFee);
            invoiceCreated = true;

            bookService.setBookAsReturned(book);
            returned = true;

            borrowBookService.markTicketAsReturned(ticket);
            ticketMarkedAsReturned = true;

            return invoice;
        } catch (Exception e) {
            System.out.println("Unexpected error while returning");
            if (returnDateUpdated) {
                borrowBookService.updateReturnDate(ticket, previousReturnDate);
            }
            if (returned) {
                bookService.setBookAsBorrowed(book);
            }
            if (ticketMarkedAsReturned) {
                borrowBookService.markTicketAsBorrowed(ticket);
            }
            throw e;
        } finally {
            ticket.lock.unlock();
            book.lock.unlock();
        }
    }

    void pay(Invoice invoice, PaymentStrategy paymentStrategy) {
        paymentService.pay(invoice, paymentStrategy);
    }
}
/*
- createInvoice(BorrowBookTicket)
            - updateBorrowFeeStatus(ticket,PaymentStatus)
            - updateLateFeeStatus(ticket,PaymentStatus)
            - updateBorrowFee(ticket,fee)
            - updateLateFee(ticket,fee)
 */
class InvoicingService {
    InvoiceDao invoiceDao;

    Invoice createInvoice(BorrowBookTicket ticket, int borrowFee, int lateFee) {
        return null;
    }

    void updateBorrowFee(Invoice invoice, int fee) {}

    void updateLateFee(Invoice invoice, int fee) {}
}

class InvoiceDao {}

interface CalculateFeeService {
    int calculateBorrowFee(BorrowBookTicket ticket);

    int calculateLateFee(BorrowBookTicket ticket);
}
/*
BorrowBookService{
            - createBorrowTicket(int userId, int bookId, Instant scheduledReturnDate): BorrowBookTicket
            - updateReturnDate(BorrowBookTicket ticket, Instant returnDate): void

        }
 */
class BorrowBookService {
    BorrowBookTicketDao borrowBookTicketDao;

    BorrowBookTicket createBorrowTicket(int userId, int bookId, Instant scheduledReturnDate) {
        //        borrowBookTicketDao.save(new BorrowBookTicket(userId,bookId,scheduledReturnDate));
        // other fields are set and saved
        return null;
    }

    BorrowBookTicket getTicketByBookId(int bookId) {
        return null;
    }

    void updateReturnDate(BorrowBookTicket ticket, Instant returnDate) {
        try {
            ticket.actualReturnDate = returnDate;
            borrowBookTicketDao.save(ticket);
        } catch (Exception e) {
            System.out.println("unexpected error while update");
            throw e;
        }
    }

    public void markTicketAsReturned(BorrowBookTicket ticket) {
        ticket.markReturned();
    }

    public void markTicketAsBorrowed(BorrowBookTicket ticket) {
        ticket.markBorrowed();
    }
}

class BorrowBookTicketDao {
    Map<Integer, BorrowBookTicket> tickets = new HashMap<>();

    BorrowBookTicket get(int id) {
        if (!tickets.containsKey(id)) throw new RuntimeException("ticket not found");
        return tickets.get(id);
    }

    void save(BorrowBookTicket ticket) {
        tickets.put(ticket.id, ticket);
    }
}

class UserService {
    User get(int userId) {
        return null;
    }
}
/*
BookService{
            - getBook(int id): Book
            - getAvailableBooksOfTitle(String title): List<Book>
            - setBookAsBorrowed(Book book, int userId)
            - setBookAsReturned(Book book)
        }
 */
class BookService {
    BookDao bookDao;

    Book getBook(int id) {
        return null;
    }

    List<Book> getAvailableBooksOfTitle(String title) {
        return null;
    }

    Book getAnyAvailableBookOfTitle(String title) {
        return null;
    }

    List<Book> getBooksOfTitle(String title) {
        return null;
    }

    void setBookAsBorrowed(Book book) {}

    void setBookAsReturned(Book book) {}

    public boolean isBookAvailableForBorrow(String title) {
        return getAvailableBooksOfTitle(title).stream().anyMatch(Book::isAvailableToBorrow);
    }
}

class BookDao {}

interface PaymentService {
    void pay(Invoice invoice, PaymentStrategy paymentStrategy);
}

enum PaymentStrategy {
    CASH,
    UPI
}

class Book {
    int id;
    String title;
    int borrowerUserId;
    boolean isAvailable;
    ReentrantLock lock = new ReentrantLock();

    public boolean isAvailableToBorrow() {
        lock.lock();
        try {
            return isAvailable;
        } finally {
            lock.unlock();
        }
    }

    public void markBookAsBorrowed(int userId) {
        lock.lock();
        try {
            if (!isAvailableToBorrow()) throw new RuntimeException("book not available to boorow");
            isAvailable = false;
            borrowerUserId = userId;
        } finally {
            lock.unlock();
        }
    }

    public void markBookAsReturned() {
        lock.lock();
        try {
            if (isAvailableToBorrow()) throw new RuntimeException("book is already returned");
            isAvailable = true;
            borrowerUserId = -1;
        } finally {
            lock.unlock();
        }
    }
}

class User {
    int id;
    String name;
}

class BorrowBookTicket {
    public LifecycleStatus status;
    int id;
    int borrowerUserId;
    int bookId;
    Instant borrowTime;
    Instant expectedReturnDate;
    Instant actualReturnDate;
    ReentrantLock lock = new ReentrantLock();

    public void markReturned() {
        lock.lock();
        try {
            status = LifecycleStatus.RETURNED;
        } finally {
            lock.unlock();
        }
    }

    public void markBorrowed() {
        lock.lock();
        try {
            status = LifecycleStatus.BORROWED;
        } finally {
            lock.unlock();
        }
    }

    public void updateReturnDate(Instant actualReturnDate) {
        lock.lock();
        try {
            this.actualReturnDate = actualReturnDate;
        } finally {
            lock.unlock();
        }
    }
}

enum LifecycleStatus {
    BORROWED,
    RETURNED
}

class Invoice {
    int id;
    int borrowBookTicketId;
    int borrowingFee;
    int lateFee;
    PaymentStatus borrowingFeePaymentStatus;
    PaymentStatus lateFeePaymentStatus;
}

enum PaymentStatus {
    PAID,
    PENDING
}
