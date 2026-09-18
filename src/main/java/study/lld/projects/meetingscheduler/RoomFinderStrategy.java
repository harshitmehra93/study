package study.lld.projects.meetingscheduler;

import java.time.Instant;
import java.util.List;

interface RoomFinderStrategy {
    List<MeetingRoom> findAvailableRooms(
            Instant start, Instant end, List<Person> attendees, String description);
}
