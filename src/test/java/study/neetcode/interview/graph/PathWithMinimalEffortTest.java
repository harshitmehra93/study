package study.neetcode.interview.graph;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PathWithMinimalEffortTest {

    @Test
    void test_givenExample() {
        PathWithMinimalEffort test = new PathWithMinimalEffort();

        int[][] heights = {
            {1, 2, 2},
            {3, 8, 2},
            {5, 3, 5}
        };

        assertEquals(2, test.minimumEffortPath(heights));
    }

    @Test
    void test_flatGrid_returnsZero() {
        PathWithMinimalEffort test = new PathWithMinimalEffort();

        int[][] heights = {
            {1, 1, 1},
            {1, 1, 1},
            {1, 1, 1}
        };

        assertEquals(0, test.minimumEffortPath(heights));
    }

    @Test
    void test_singleCell_returnsZero() {
        PathWithMinimalEffort test = new PathWithMinimalEffort();

        int[][] heights = {{7}};

        assertEquals(0, test.minimumEffortPath(heights));
    }

    @Test
    void test_mustAvoidLargeClimb() {
        PathWithMinimalEffort test = new PathWithMinimalEffort();

        int[][] heights = {
            {1, 10, 6},
            {1, 2, 6},
            {1, 2, 3}
        };

        /*
         * Good path:
         * 1 -> 1 -> 1 -> 2 -> 2 -> 3
         *
         * max effort = 1
         */
        assertEquals(1, test.minimumEffortPath(heights));
    }

    @Test
    void test_directLookingPathIsNotBest() {
        PathWithMinimalEffort test = new PathWithMinimalEffort();

        int[][] heights = {
            {1, 100, 1},
            {1, 100, 1},
            {1, 1, 1}
        };

        /*
         * Avoid the 100 wall by going down and around.
         * All chosen steps can have effort 0.
         */
        assertEquals(0, test.minimumEffortPath(heights));
    }

    @Test
    void test_oneRow() {
        PathWithMinimalEffort test = new PathWithMinimalEffort();

        int[][] heights = {{1, 3, 6, 10}};

        /*
         * Only one path exists.
         * Efforts: 2, 3, 4
         * path effort = 4
         */
        assertEquals(4, test.minimumEffortPath(heights));
    }

    @Test
    void test_oneColumn() {
        PathWithMinimalEffort test = new PathWithMinimalEffort();

        int[][] heights = {{1}, {4}, {2}, {8}};

        /*
         * Only one path exists.
         * Efforts: 3, 2, 6
         * path effort = 6
         */
        assertEquals(6, test.minimumEffortPath(heights));
    }

    @Test
    void test_largerKnownExample() {
        PathWithMinimalEffort test = new PathWithMinimalEffort();

        int[][] heights = {
            {1, 2, 3},
            {3, 8, 4},
            {5, 3, 5}
        };

        assertEquals(1, test.minimumEffortPath(heights));
    }

    @Test
    void test_pathCostIsMaxNotSum() {
        PathWithMinimalEffort test = new PathWithMinimalEffort();

        int[][] heights = {
            {1, 2, 3},
            {2, 3, 4},
            {3, 4, 5}
        };

        /*
         * Many paths have multiple small steps.
         * We do NOT sum them.
         * Best path has every step effort <= 1.
         */
        assertEquals(1, test.minimumEffortPath(heights));
    }
}
