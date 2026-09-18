package study.lld.projects.meetingscheduler;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class Main {

    /*
     Meeting room
    - Id
    - MeetingRoomCalendar
    - List<Meetings>
    - maxOccupancy
    - AV capabilities
    - Building
    - Floor
    - Office
    - bookMeeting(Meeting)

    MeetingRoomCalendar
    - List<Meeting>
    - isFree(start time, end time)
    - bookMeeting(Meeting, start, end)

    Meeting
    - Id
    - List<People>
    - MeetingRoom
    - Description
    - start
    - end
     */

    public static void main(String[] args) {
        MeetingRoomDao meetingRoomDao = new InMemoryMeetingRoomDao();
        meetingRoomDao.save(new MeetingRoom(3));
        meetingRoomDao.save(new MeetingRoom(2));
        RoomFinderStrategy roomFinderStrategy =
                new FirstAvailableRoomWithRightOccupancy(meetingRoomDao);
        NotificationService notificationService = new NotificationServiceImpl();
        notificationService.addNotifier(new EmailNotifier());
        MeetingBookingService meetingBookingService =
                new MeetingBookingService(roomFinderStrategy, notificationService);

        Person a = new Person("Harshit", "a@gmail.com", "address1");
        Person b = new Person("Pulkit", "b@gmail.com", "address2");
        Person c = new Person("Vinita", "c@gmail.com", "address3");
        meetingBookingService.bookMeeting(
                Instant.ofEpochMilli(0L), Instant.ofEpochMilli(50L), List.of(a, b, c), "sync up");
        meetingBookingService.bookMeeting(
                Instant.ofEpochMilli(50L), Instant.ofEpochMilli(100L), List.of(a, b, c), "sync up");
        meetingBookingService.bookMeeting(
                Instant.ofEpochMilli(0L), Instant.ofEpochMilli(5L), List.of(a, b, c), "sync up");
    }
}

class MeetingRoom {
    private static final AtomicInteger counter = new AtomicInteger(0);
    int id;
    private final MeetingRoomCalendar calendar;
    final int maxOccupancy;
    public final ReentrantLock LOCK;

    MeetingRoom(int maxOccupancy) {
        id = counter.getAndIncrement();
        this.maxOccupancy = maxOccupancy;
        this.calendar = new MeetingRoomCalendar();
        LOCK = new ReentrantLock();
    }

    public boolean tryBooking(Meeting meeting) {
        LOCK.lock();
        try {
            return calendar.bookMeeting(meeting);
        } finally {
            LOCK.unlock();
        }
    }

    public boolean isFree(Instant start, Instant end) {
        LOCK.lock();
        try {
            return calendar.isFree(start, end);
        } finally {
            LOCK.unlock();
        }
    }
}

class MeetingRoomCalendar {

    List<Meeting> scheduledMeetings;

    public MeetingRoomCalendar() {
        scheduledMeetings = new ArrayList<>();
    }

    boolean isFree(Instant start, Instant end) {
        if (scheduledMeetings.isEmpty()) return true;
        // we can use binary search to find an interval starting just

        return isOverlappingMeeting(start, end);
    }

    private boolean isOverlappingMeeting(Instant start, Instant end) {
        for (int i = 0; i < scheduledMeetings.size(); i++) {
            Meeting meeting = scheduledMeetings.get(i);
            if (start.isBefore(meeting.end) && end.isAfter(meeting.start)) {
                return false;
            }
        }
        return true;
    }

    boolean bookMeeting(Meeting meeting) {
        if (!isFree(meeting.start, meeting.end)) {
            return false;
        }
        scheduledMeetings.add(meeting);
        return true;
    }
}

class Person {
    static final AtomicInteger counter = new AtomicInteger(0);
    private final int id;
    String name;
    String email;
    String address;

    public Person(String name, String email, String address) {
        this.id = counter.getAndIncrement();
        this.name = name;
        this.email = email;
        this.address = address;
    }
}

class Meeting {
    public static AtomicInteger counter = new AtomicInteger(0);
    private final int id;
    private MeetingRoom meetingRoom;
    final List<Person> attendees;
    final String description;
    final Instant start;
    final Instant end;

    Meeting(List<Person> attendees, String description, Instant start, Instant end) {
        this.id = counter.getAndIncrement();
        this.meetingRoom = null;
        this.attendees = attendees;
        this.description = description;
        this.start = start;
        this.end = end;
    }

    public void setMeetingRoom(MeetingRoom room) {
        this.meetingRoom = room;
    }
}

interface RoomFinderStrategy {
    List<MeetingRoom> findAvailableRooms(
            Instant start, Instant end, List<Person> attendees, String description);
}

interface MeetingRoomDao {
    MeetingRoom find(int id);

    void save(MeetingRoom room);

    List<MeetingRoom> listAll();
}

class InMemoryMeetingRoomDao implements MeetingRoomDao {
    Map<Integer, MeetingRoom> meetingRoomDaoMap = new HashMap<>();

    @Override
    public MeetingRoom find(int id) {
        return meetingRoomDaoMap.get(id);
    }

    @Override
    public void save(MeetingRoom room) {
        meetingRoomDaoMap.put(room.id, room);
    }

    @Override
    public List<MeetingRoom> listAll() {
        return meetingRoomDaoMap.values().stream().toList();
    }
}

class FirstAvailableRoomWithRightOccupancy implements RoomFinderStrategy {
    private final MeetingRoomDao meetingRoomDao;

    FirstAvailableRoomWithRightOccupancy(MeetingRoomDao meetingRoomDao) {
        this.meetingRoomDao = meetingRoomDao;
    }

    public List<MeetingRoom> findAvailableRooms(
            Instant start, Instant end, List<Person> attendees, String description) {
        List<MeetingRoom> rooms = meetingRoomDao.listAll();
        return rooms.stream()
                .filter(r -> r.maxOccupancy >= attendees.size())
                .filter(r -> r.isFree(start, end))
                .collect(Collectors.toList());
    }
}

interface NotificationService {
    void notifyAttendees(Meeting meeting);

    void addNotifier(Notifier notifier);
}

class NotificationServiceImpl implements NotificationService {
    List<Notifier> notifiers = new ArrayList<>();

    @Override
    public void notifyAttendees(Meeting meeting) {
        notifiers.forEach(n -> n.notify(meeting));
    }

    @Override
    public void addNotifier(Notifier notifier) {
        notifiers.add(notifier);
    }
}

interface Notifier {
    void notify(Meeting meeting);
}

class EmailNotifier implements Notifier {

    @Override
    public void notify(Meeting meeting) {
        for (Person person : meeting.attendees) {
            System.out.println(
                    "Sending Email to "
                            + person.name
                            + " at "
                            + person.email
                            + "about meeting"
                            + meeting.description);
        }
    }
}

class MeetingBookingService {
    private final RoomFinderStrategy roomFinderStrategy;
    private final NotificationService notificationService;

    MeetingBookingService(
            RoomFinderStrategy roomFinderStrategy, NotificationService notificationService) {
        this.roomFinderStrategy = roomFinderStrategy;
        this.notificationService = notificationService;
    }

    void bookMeeting(Instant start, Instant end, List<Person> attendees, String description) {
        Objects.requireNonNull(start, "start");
        Objects.requireNonNull(end, "end");
        Objects.requireNonNull(attendees, "attendees");
        Objects.requireNonNull(description, "description");
        if (start.isAfter(end)) throw new RuntimeException("invalid start and end time");
        if (attendees.isEmpty()) throw new RuntimeException("attendees should not be 0");

        List<MeetingRoom> rooms =
                roomFinderStrategy.findAvailableRooms(start, end, attendees, description);
        if (rooms.isEmpty()) {
            throw new RuntimeException("No rooms available");
        }

        Meeting meeting = new Meeting(attendees, description, start, end);
        boolean meetingBooked = false;
        for (MeetingRoom room : rooms) {
            if (room.tryBooking(meeting)) {
                meetingBooked = true;
                meeting.setMeetingRoom(room);
                break;
            }
        }
        if (!meetingBooked) {
            throw new RuntimeException("No rooms available");
        }

        // notify about meetings
        notificationService.notifyAttendees(meeting);
    }
}
/*

There are 10 steps to attempt a LLD question

1. Understand what needs to be built from interviewer
2. Define key Use cases and APIs
3. Data Model and Persistence
4. Class design
5. Apply Design Patterns
6. Sequence of main flow
7. Concurrency
8. Edge cases and error handling
9. Code, or at least skeleton
10. Extensions and scalability.
 */
