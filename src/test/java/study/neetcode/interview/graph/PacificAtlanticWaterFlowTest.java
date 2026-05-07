package study.neetcode.interview.graph;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;

class PacificAtlanticWaterFlowTest {

    @Test
    void test_givenExample() {
        PacificAtlanticWaterFlow test = new PacificAtlanticWaterFlow();

        int[][] heights =
                new int[][] {
                    {1, 2, 2, 3, 5},
                    {3, 2, 3, 4, 4},
                    {2, 4, 5, 3, 1},
                    {6, 7, 1, 4, 5},
                    {5, 1, 1, 2, 4}
                };

        int[][] expected =
                new int[][] {
                    {0, 4},
                    {1, 3},
                    {1, 4},
                    {2, 2},
                    {3, 0},
                    {3, 1},
                    {4, 0}
                };

        int[][] result = test.waterflow(heights);

        assertResultHasExpectedCells(expected, result);
    }

    @Test
    void test_memoizationFalseBug_cellCanReachBothButGetsMissed() {
        PacificAtlanticWaterFlow test = new PacificAtlanticWaterFlow();

        int[][] heights =
                new int[][] {
                    {1, 1, 1},
                    {1, 1, 2},
                    {2, 1, 1}
                };

        /*
         * Every cell can reach both oceans.
         *
         * Example:
         * [2,2] is Atlantic directly because it is on bottom/right border.
         * It can also reach Pacific:
         *
         * [2,2] = 1
         *   -> [2,1] = 1
         *   -> [1,1] = 1
         *   -> [0,1] = 1
         *
         * Flat movement is allowed, so it reaches the top row Pacific.
         *
         * Your memoized version can incorrectly miss [2,2].
         */
        int[][] expected =
                new int[][] {
                    {0, 0},
                    {0, 1},
                    {0, 2},
                    {1, 0},
                    {1, 1},
                    {1, 2},
                    {2, 0},
                    {2, 1},
                    {2, 2}
                };

        int[][] result = test.waterflow(heights);

        assertResultHasExpectedCells(expected, result);
    }

    private void assertResultHasExpectedCells(int[][] expected, int[][] result) {
        Set<String> expectedCells = toCellSet(expected);
        Set<String> resultCells = toCellSet(result);

        assertEquals(expectedCells, resultCells);
    }

    private Set<String> toCellSet(int[][] cells) {
        Set<String> set = new HashSet<>();
        for (int[] cell : cells) {
            set.add(cell[0] + "," + cell[1]);
        }
        return set;
    }
}
