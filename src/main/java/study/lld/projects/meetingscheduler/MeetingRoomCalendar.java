package study.lld.projects.meetingscheduler;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

class MeetingRoomCalendar {
    List<Meeting> scheduledMeetings;

    public MeetingRoomCalendar() {
        scheduledMeetings = new ArrayList<>();
    }

    boolean isFree(Instant start, Instant end) {
        if (scheduledMeetings.isEmpty()) return true;
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
