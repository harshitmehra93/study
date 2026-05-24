package study.neetcode.interview.graph;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SwimInRisingWaterTest {

    @Test
    void test_givenSmallExample() {
        SwimInRisingWater test = new SwimInRisingWater();

        int[][] grid = {
            {0, 2},
            {1, 3}
        };

        assertEquals(3, test.swimInWater(grid));
    }

    @Test
    void test_givenLargeExample() {
        SwimInRisingWater test = new SwimInRisingWater();

        int[][] grid = {
            {0, 1, 2, 3, 4},
            {24, 23, 22, 21, 5},
            {12, 13, 14, 15, 16},
            {11, 17, 18, 19, 20},
            {10, 9, 8, 7, 6}
        };

        assertEquals(16, test.swimInWater(grid));
    }

    @Test
    void test_singleCell_returnsCellValueOrZeroDependingOnProblemConvention() {
        SwimInRisingWater test = new SwimInRisingWater();

        int[][] grid = {{0}};

        assertEquals(0, test.swimInWater(grid));
    }

    @Test
    void test_mustAvoidHighWall() {
        SwimInRisingWater test = new SwimInRisingWater();

        int[][] grid = {
            {0, 100, 100},
            {1, 2, 100},
            {100, 3, 4}
        };

        /*
         * Best path:
         * 0 -> 1 -> 2 -> 3 -> 4
         *
         * Path cost = max cell value on path = 4.
         */
        assertEquals(4, test.swimInWater(grid));
    }

    @Test
    void test_directPathLooksShortButHasHighCell() {
        SwimInRisingWater test = new SwimInRisingWater();

        int[][] grid = {
            {0, 50, 2},
            {1, 3, 4},
            {6, 5, 7}
        };

        /*
         * Going right immediately hits 50.
         * Better path avoids it:
         * 0 -> 1 -> 3 -> 4 -> 7
         *
         * Path cost = 7.
         */
        assertEquals(7, test.swimInWater(grid));
    }

    @Test
    void test_pathCostIsMaxCellValueNotSum() {
        SwimInRisingWater test = new SwimInRisingWater();

        int[][] grid = {
            {0, 1, 2},
            {5, 4, 3},
            {6, 7, 8}
        };

        /*
         * Path:
         * 0 -> 1 -> 2 -> 3 -> 8
         *
         * Answer is max cell value on path = 8.
         * Not sum.
         */
        assertEquals(8, test.swimInWater(grid));
    }

    @Test
    void test_onePossiblePathOnlyAlongIncreasingSnake() {
        SwimInRisingWater test = new SwimInRisingWater();

        int[][] grid = {
            {0, 1, 2},
            {9, 8, 3},
            {6, 5, 4}
        };

        /*
         * Best path:
         * 0 -> 1 -> 2 -> 3 -> 4
         *
         * Answer = 4.
         */
        assertEquals(4, test.swimInWater(grid));
    }

    @Test
    void test_highStartStillRequiresStartTime() {
        SwimInRisingWater test = new SwimInRisingWater();

        int[][] grid = {
            {5, 6},
            {7, 8}
        };

        /*
         * You start on cell 5, so time must be at least 5.
         * Destination is 8, so answer is 8.
         */
        assertEquals(8, test.swimInWater(grid));
    }
}
