package study.lld.projects.meetingscheduler;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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
