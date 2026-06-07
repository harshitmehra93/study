package study.neetcode.interview.intervals;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class MinimumNumberOfArrowsToBurstBalloonsTest {

    @Test
    void example1() {
        MinimumNumberOfArrowsToBurstBalloons solution = new MinimumNumberOfArrowsToBurstBalloons();

        int[][] points = {
            {10, 16},
            {2, 8},
            {1, 6},
            {7, 12}
        };

        assertEquals(2, solution.findMinArrowShots(points));
    }

    @Test
    void sortByStartBreaksBecauseArrowPositionMayNotBeInsideLaterInterval() {
        MinimumNumberOfArrowsToBurstBalloons solution = new MinimumNumberOfArrowsToBurstBalloons();

        int[][] points = {
            {1, 10},
            {2, 3}
        };

        assertEquals(1, solution.findMinArrowShots(points));
    }

    @Test
    void allBalloonsOverlap() {
        MinimumNumberOfArrowsToBurstBalloons solution = new MinimumNumberOfArrowsToBurstBalloons();

        int[][] points = {
            {1, 10},
            {2, 9},
            {3, 8},
            {4, 7}
        };

        assertEquals(1, solution.findMinArrowShots(points));
    }

    @Test
    void noBalloonsOverlap() {
        MinimumNumberOfArrowsToBurstBalloons solution = new MinimumNumberOfArrowsToBurstBalloons();

        int[][] points = {
            {1, 2},
            {3, 4},
            {5, 6},
            {7, 8}
        };

        assertEquals(4, solution.findMinArrowShots(points));
    }

    @Test
    void touchingIntervalsCanUseSameArrow() {
        MinimumNumberOfArrowsToBurstBalloons solution = new MinimumNumberOfArrowsToBurstBalloons();

        int[][] points = {
            {1, 2},
            {2, 3},
            {3, 4}
        };

        assertEquals(2, solution.findMinArrowShots(points));
    }

    @Test
    void singleBalloonNeedsOneArrow() {
        MinimumNumberOfArrowsToBurstBalloons solution = new MinimumNumberOfArrowsToBurstBalloons();

        int[][] points = {{5, 10}};

        assertEquals(1, solution.findMinArrowShots(points));
    }

    @Test
    void emptyInputNeedsZeroArrows() {
        MinimumNumberOfArrowsToBurstBalloons solution = new MinimumNumberOfArrowsToBurstBalloons();

        int[][] points = {};

        assertEquals(0, solution.findMinArrowShots(points));
    }

    @Test
    void mixedGroupsOfOverlaps() {
        MinimumNumberOfArrowsToBurstBalloons solution = new MinimumNumberOfArrowsToBurstBalloons();

        int[][] points = {
            {1, 5},
            {2, 6},
            {7, 10},
            {8, 11},
            {12, 14}
        };

        assertEquals(3, solution.findMinArrowShots(points));
    }

    @Test
    void sameStartDifferentEnds() {
        MinimumNumberOfArrowsToBurstBalloons solution = new MinimumNumberOfArrowsToBurstBalloons();

        int[][] points = {
            {1, 10},
            {1, 5},
            {1, 3}
        };

        assertEquals(1, solution.findMinArrowShots(points));
    }

    @Test
    void sameEndDifferentStarts() {
        MinimumNumberOfArrowsToBurstBalloons solution = new MinimumNumberOfArrowsToBurstBalloons();

        int[][] points = {
            {1, 5},
            {2, 5},
            {3, 5}
        };

        assertEquals(1, solution.findMinArrowShots(points));
    }

    @Test
    void handlesLargeCoordinatesWithoutComparatorOverflow() {
        MinimumNumberOfArrowsToBurstBalloons solution = new MinimumNumberOfArrowsToBurstBalloons();

        int[][] points = {
            {-2147483648, -2147483647},
            {2147483646, 2147483647}
        };

        assertEquals(2, solution.findMinArrowShots(points));
    }

    @Test
    void sortByStartBreaksWhenFirstIntervalIsWideAndLaterIntervalsDoNotShareItsEnd() {
        MinimumNumberOfArrowsToBurstBalloons solution = new MinimumNumberOfArrowsToBurstBalloons();

        int[][] points = {
            {1, 10},
            {2, 3},
            {4, 5}
        };

        assertEquals(2, solution.findMinArrowShots(points));
    }
}
