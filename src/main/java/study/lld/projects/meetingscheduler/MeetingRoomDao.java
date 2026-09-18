package study.lld.projects.meetingscheduler;

import java.util.List;

interface MeetingRoomDao {
    MeetingRoom find(int id);

    void save(MeetingRoom room);

    List<MeetingRoom> listAll();
}
