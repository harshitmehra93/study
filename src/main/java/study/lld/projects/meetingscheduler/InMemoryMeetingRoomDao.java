package study.lld.projects.meetingscheduler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
