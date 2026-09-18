package study.lld.projects.meetingscheduler;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

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
