package study.lld.projects.meetingscheduler;

import java.time.Instant;
import java.util.List;

public class Main {

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
