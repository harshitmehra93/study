package study.lld.projects.meetingscheduler;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

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
