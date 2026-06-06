package study.neetcode.interview.intervals;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class NonOverlappingIntervalsTest {

    @Test
    void removesOneOverlappingInterval() {
        NonOverlappingIntervals solver = new NonOverlappingIntervals();

        int[][] intervals = {
            {1, 2},
            {2, 3},
            {3, 4},
            {1, 3}
        };

        assertEquals(1, solver.eraseOverlapIntervals(intervals));
    }

    @Test
    void removesTwoDuplicateIntervals() {
        NonOverlappingIntervals solver = new NonOverlappingIntervals();

        int[][] intervals = {
            {1, 2},
            {1, 2},
            {1, 2}
        };

        assertEquals(2, solver.eraseOverlapIntervals(intervals));
    }

    @Test
    void noOverlapWhenIntervalsOnlyTouch() {
        NonOverlappingIntervals solver = new NonOverlappingIntervals();

        int[][] intervals = {
            {1, 2},
            {2, 3},
            {3, 4}
        };

        assertEquals(0, solver.eraseOverlapIntervals(intervals));
    }

    @Test
    void removesLargerEndWhenOverlapHappens() {
        NonOverlappingIntervals solver = new NonOverlappingIntervals();

        int[][] intervals = {
            {1, 100},
            {11, 22},
            {1, 11},
            {2, 12}
        };

        assertEquals(2, solver.eraseOverlapIntervals(intervals));
    }

    @Test
    void nestedIntervals() {
        NonOverlappingIntervals solver = new NonOverlappingIntervals();

        int[][] intervals = {
            {1, 10},
            {2, 3},
            {4, 5},
            {6, 7}
        };

        assertEquals(1, solver.eraseOverlapIntervals(intervals));
    }

    @Test
    void allIntervalsOverlap() {
        NonOverlappingIntervals solver = new NonOverlappingIntervals();

        int[][] intervals = {
            {1, 5},
            {2, 6},
            {3, 7},
            {4, 8}
        };

        assertEquals(3, solver.eraseOverlapIntervals(intervals));
    }

    @Test
    void singleInterval() {
        NonOverlappingIntervals solver = new NonOverlappingIntervals();

        int[][] intervals = {{1, 2}};

        assertEquals(0, solver.eraseOverlapIntervals(intervals));
    }

    @Test
    void emptyIntervals() {
        NonOverlappingIntervals solver = new NonOverlappingIntervals();

        int[][] intervals = {};

        assertEquals(0, solver.eraseOverlapIntervals(intervals));
    }

    @Test
    void updatesPreviousWhenNoOverlapBeforeLaterOverlap() {
        NonOverlappingIntervals solver = new NonOverlappingIntervals();

        int[][] intervals = {
            {1, 2},
            {3, 100},
            {4, 5}
        };

        assertEquals(1, solver.eraseOverlapIntervals(intervals));
    }
}
