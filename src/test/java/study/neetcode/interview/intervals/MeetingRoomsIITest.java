package study.neetcode.interview.intervals;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class MeetingRooms2Test {

    @Test
    void exampleNeedsTwoRooms() {
        MeetingRooms2 solver = new MeetingRooms2();

        int[][] intervals = {
            {0, 30},
            {5, 10},
            {15, 20}
        };

        assertEquals(2, solver.minMeetingRooms(intervals));
    }

    @Test
    void noOverlapNeedsOneRoom() {
        MeetingRooms2 solver = new MeetingRooms2();

        int[][] intervals = {
            {7, 10},
            {2, 4}
        };

        assertEquals(1, solver.minMeetingRooms(intervals));
    }

    @Test
    void touchingMeetingsReuseSameRoom() {
        MeetingRooms2 solver = new MeetingRooms2();

        int[][] intervals = {
            {1, 2},
            {2, 3},
            {3, 4}
        };

        assertEquals(1, solver.minMeetingRooms(intervals));
    }

    @Test
    void allOverlapNeedsAllRooms() {
        MeetingRooms2 solver = new MeetingRooms2();

        int[][] intervals = {
            {1, 10},
            {2, 9},
            {3, 8},
            {4, 7}
        };

        assertEquals(4, solver.minMeetingRooms(intervals));
    }

    @Test
    void multipleRoomsFreedOverTime() {
        MeetingRooms2 solver = new MeetingRooms2();

        int[][] intervals = {
            {1, 4},
            {2, 5},
            {7, 9},
            {8, 10}
        };

        assertEquals(2, solver.minMeetingRooms(intervals));
    }

    @Test
    void staggeredMeetingsNeedThreeRooms() {
        MeetingRooms2 solver = new MeetingRooms2();

        int[][] intervals = {
            {1, 5},
            {2, 6},
            {3, 7},
            {8, 9}
        };

        assertEquals(3, solver.minMeetingRooms(intervals));
    }

    @Test
    void singleMeetingNeedsOneRoom() {
        MeetingRooms2 solver = new MeetingRooms2();

        int[][] intervals = {{1, 10}};

        assertEquals(1, solver.minMeetingRooms(intervals));
    }

    @Test
    void emptyMeetingsNeedZeroRooms() {
        MeetingRooms2 solver = new MeetingRooms2();

        int[][] intervals = {};

        assertEquals(0, solver.minMeetingRooms(intervals));
    }
}
