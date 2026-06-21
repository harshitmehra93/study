package study.interview.intervals;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

class MinimumIntervalToIncludeEachQueryTest {

    @Test
    void exampleOne() {
        MinimumIntervalToIncludeEachQuery solver = new MinimumIntervalToIncludeEachQuery();

        int[][] intervals = {
            {1, 4},
            {2, 4},
            {3, 6},
            {4, 4}
        };
        int[] queries = {2, 3, 4, 5};

        int[] expected = {3, 3, 1, 4};

        assertArrayEquals(expected, solver.minInterval(intervals, queries));
    }

    @Test
    void exampleTwo() {
        MinimumIntervalToIncludeEachQuery solver = new MinimumIntervalToIncludeEachQuery();

        int[][] intervals = {
            {2, 3},
            {2, 5},
            {1, 8},
            {20, 25}
        };
        int[] queries = {2, 19, 5, 22};

        int[] expected = {2, -1, 4, 6};

        assertArrayEquals(expected, solver.minInterval(intervals, queries));
    }

    @Test
    void noIntervalContainsAnyQuery() {
        MinimumIntervalToIncludeEachQuery solver = new MinimumIntervalToIncludeEachQuery();

        int[][] intervals = {
            {1, 2},
            {5, 6}
        };
        int[] queries = {3, 4, 7};

        int[] expected = {-1, -1, -1};

        assertArrayEquals(expected, solver.minInterval(intervals, queries));
    }

    @Test
    void exactPointIntervalIsSmallest() {
        MinimumIntervalToIncludeEachQuery solver = new MinimumIntervalToIncludeEachQuery();

        int[][] intervals = {
            {1, 10},
            {3, 7},
            {5, 5}
        };
        int[] queries = {5};

        int[] expected = {1};

        assertArrayEquals(expected, solver.minInterval(intervals, queries));
    }

    @Test
    void duplicateQueriesShouldPreserveAnswers() {
        MinimumIntervalToIncludeEachQuery solver = new MinimumIntervalToIncludeEachQuery();

        int[][] intervals = {
            {1, 4},
            {2, 4},
            {4, 4}
        };
        int[] queries = {4, 4, 2};

        int[] expected = {1, 1, 3};

        assertArrayEquals(expected, solver.minInterval(intervals, queries));
    }

    @Test
    void unsortedQueriesShouldReturnInOriginalOrder() {
        MinimumIntervalToIncludeEachQuery solver = new MinimumIntervalToIncludeEachQuery();

        int[][] intervals = {
            {1, 4},
            {2, 8},
            {10, 12}
        };
        int[] queries = {11, 3, 9};

        int[] expected = {3, 4, -1};

        assertArrayEquals(expected, solver.minInterval(intervals, queries));
    }

    @Test
    void touchingBoundariesAreIncluded() {
        MinimumIntervalToIncludeEachQuery solver = new MinimumIntervalToIncludeEachQuery();

        int[][] intervals = {{2, 5}};
        int[] queries = {2, 5, 6};

        int[] expected = {4, 4, -1};

        assertArrayEquals(expected, solver.minInterval(intervals, queries));
    }

    @Test
    void emptyIntervalsReturnsNegativeOneForAllQueries() {
        MinimumIntervalToIncludeEachQuery solver = new MinimumIntervalToIncludeEachQuery();

        int[][] intervals = {};
        int[] queries = {1, 2, 3};

        int[] expected = {-1, -1, -1};

        assertArrayEquals(expected, solver.minInterval(intervals, queries));
    }
}
