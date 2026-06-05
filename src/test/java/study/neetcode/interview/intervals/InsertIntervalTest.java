package study.neetcode.interview.intervals;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

class InsertIntervalTest {

    @Test
    void insertsAndMergesSingleOverlap() {
        InsertInterval solver = new InsertInterval();

        int[][] intervals = {
            {1, 3},
            {6, 9}
        };
        int[] newInterval = {2, 5};

        int[][] expected = {
            {1, 5},
            {6, 9}
        };

        assertArrayEquals(expected, solver.insert(intervals, newInterval));
    }

    @Test
    void insertsAndMergesMultipleOverlaps() {
        InsertInterval solver = new InsertInterval();

        int[][] intervals = {
            {1, 2},
            {3, 5},
            {6, 7},
            {8, 10},
            {12, 16}
        };
        int[] newInterval = {4, 8};

        int[][] expected = {
            {1, 2},
            {3, 10},
            {12, 16}
        };

        assertArrayEquals(expected, solver.insert(intervals, newInterval));
    }

    @Test
    void insertsBeforeAllIntervals() {
        InsertInterval solver = new InsertInterval();

        int[][] intervals = {
            {3, 5},
            {6, 9}
        };
        int[] newInterval = {1, 2};

        int[][] expected = {
            {1, 2},
            {3, 5},
            {6, 9}
        };

        assertArrayEquals(expected, solver.insert(intervals, newInterval));
    }

    @Test
    void insertsAfterAllIntervals() {
        InsertInterval solver = new InsertInterval();

        int[][] intervals = {
            {1, 2},
            {3, 5}
        };
        int[] newInterval = {6, 8};

        int[][] expected = {
            {1, 2},
            {3, 5},
            {6, 8}
        };

        assertArrayEquals(expected, solver.insert(intervals, newInterval));
    }

    @Test
    void newIntervalCoversAllExistingIntervals() {
        InsertInterval solver = new InsertInterval();

        int[][] intervals = {
            {2, 3},
            {5, 7},
            {9, 10}
        };
        int[] newInterval = {1, 12};

        int[][] expected = {{1, 12}};

        assertArrayEquals(expected, solver.insert(intervals, newInterval));
    }

    @Test
    void existingIntervalsCoverNewInterval() {
        InsertInterval solver = new InsertInterval();

        int[][] intervals = {{1, 10}};
        int[] newInterval = {3, 5};

        int[][] expected = {{1, 10}};

        assertArrayEquals(expected, solver.insert(intervals, newInterval));
    }

    @Test
    void touchingIntervalsShouldMerge() {
        InsertInterval solver = new InsertInterval();

        int[][] intervals = {
            {1, 2},
            {5, 7}
        };
        int[] newInterval = {2, 5};

        int[][] expected = {{1, 7}};

        assertArrayEquals(expected, solver.insert(intervals, newInterval));
    }

    @Test
    void emptyIntervals() {
        InsertInterval solver = new InsertInterval();

        int[][] intervals = {};
        int[] newInterval = {4, 8};

        int[][] expected = {{4, 8}};

        assertArrayEquals(expected, solver.insert(intervals, newInterval));
    }
}
