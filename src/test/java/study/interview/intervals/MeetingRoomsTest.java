package study.interview.intervals;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MeetingRoomsTest {

    @Test
    void overlappingMeetingsReturnsFalse() {
        MeetingRooms solver = new MeetingRooms();

        int[][] intervals = {
            {0, 30},
            {5, 10},
            {15, 20}
        };

        assertFalse(solver.canAttendMeetings(intervals));
    }

    @Test
    void nonOverlappingMeetingsReturnsTrue() {
        MeetingRooms solver = new MeetingRooms();

        int[][] intervals = {
            {7, 10},
            {2, 4}
        };

        assertTrue(solver.canAttendMeetings(intervals));
    }

    @Test
    void touchingMeetingsAreAllowed() {
        MeetingRooms solver = new MeetingRooms();

        int[][] intervals = {
            {1, 2},
            {2, 3},
            {3, 4}
        };

        assertTrue(solver.canAttendMeetings(intervals));
    }

    @Test
    void unsortedInputWithOverlapReturnsFalse() {
        MeetingRooms solver = new MeetingRooms();

        int[][] intervals = {
            {10, 20},
            {1, 5},
            {4, 8}
        };

        assertFalse(solver.canAttendMeetings(intervals));
    }

    @Test
    void unsortedInputWithoutOverlapReturnsTrue() {
        MeetingRooms solver = new MeetingRooms();

        int[][] intervals = {
            {10, 20},
            {1, 5},
            {6, 9}
        };

        assertTrue(solver.canAttendMeetings(intervals));
    }

    @Test
    void singleMeetingReturnsTrue() {
        MeetingRooms solver = new MeetingRooms();

        int[][] intervals = {{1, 10}};

        assertTrue(solver.canAttendMeetings(intervals));
    }

    @Test
    void emptyMeetingsReturnsTrue() {
        MeetingRooms solver = new MeetingRooms();

        int[][] intervals = {};

        assertTrue(solver.canAttendMeetings(intervals));
    }
}
