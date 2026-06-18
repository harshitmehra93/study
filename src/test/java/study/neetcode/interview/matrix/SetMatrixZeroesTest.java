package study.neetcode.interview.matrix;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

class SetMatrixZeroesTest {

    private final SetMatrixZeroes solution = new SetMatrixZeroes();

    @Test
    void zeroesSingleMiddleCellRowAndColumn() {
        int[][] matrix = {
            {1, 1, 1},
            {1, 0, 1},
            {1, 1, 1}
        };

        solution.setZeroes(matrix);

        assertArrayEquals(
                new int[][] {
                    {1, 0, 1},
                    {0, 0, 0},
                    {1, 0, 1}
                },
                matrix);
    }

    @Test
    void zeroesMultipleRowsAndColumnsFromOriginalZeroesOnly() {
        int[][] matrix = {
            {0, 1, 2, 0},
            {3, 4, 5, 2},
            {1, 3, 1, 5}
        };

        solution.setZeroes(matrix);

        assertArrayEquals(
                new int[][] {
                    {0, 0, 0, 0},
                    {0, 4, 5, 0},
                    {0, 3, 1, 0}
                },
                matrix);
    }

    @Test
    void leavesMatrixWithoutZeroesUnchanged() {
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6}
        };

        solution.setZeroes(matrix);

        assertArrayEquals(
                new int[][] {
                    {1, 2, 3},
                    {4, 5, 6}
                },
                matrix);
    }

    @Test
    void handlesZeroInFirstRowAndFirstColumn() {
        int[][] matrix = {
            {1, 0, 3},
            {4, 5, 6},
            {7, 8, 0}
        };

        solution.setZeroes(matrix);

        assertArrayEquals(
                new int[][] {
                    {0, 0, 0},
                    {4, 0, 0},
                    {0, 0, 0}
                },
                matrix);
    }

    @Test
    void handlesSingleRow() {
        int[][] matrix = {{1, 0, 3, 4}};

        solution.setZeroes(matrix);

        assertArrayEquals(new int[][] {{0, 0, 0, 0}}, matrix);
    }

    @Test
    void handlesSingleColumn() {
        int[][] matrix = {{1}, {0}, {3}};

        solution.setZeroes(matrix);

        assertArrayEquals(new int[][] {{0}, {0}, {0}}, matrix);
    }
}
