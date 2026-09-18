package study.lld.projects.meetingscheduler;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

class FirstAvailableRoomWithRightOccupancy implements RoomFinderStrategy {
    private final MeetingRoomDao meetingRoomDao;

    FirstAvailableRoomWithRightOccupancy(MeetingRoomDao meetingRoomDao) {
        this.meetingRoomDao = meetingRoomDao;
    }

    @Override
    public List<MeetingRoom> findAvailableRooms(
            Instant start, Instant end, List<Person> attendees, String description) {
        List<MeetingRoom> rooms = meetingRoomDao.listAll();
        return rooms.stream()
                .filter(r -> r.maxOccupancy >= attendees.size())
                .filter(r -> r.isFree(start, end))
                .collect(Collectors.toList());
    }
}
