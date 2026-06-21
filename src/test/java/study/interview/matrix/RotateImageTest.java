package study.interview.matrix;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

class RotateImageTest {

    private final RotateImage solution = new RotateImage();

    @Test
    void rotatesThreeByThreeMatrixClockwise() {
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        solution.rotate(matrix);

        assertArrayEquals(
                new int[][] {
                    {7, 4, 1},
                    {8, 5, 2},
                    {9, 6, 3}
                },
                matrix);
    }

    @Test
    void rotatesFourByFourMatrixClockwise() {
        int[][] matrix = {
            {5, 1, 9, 11},
            {2, 4, 8, 10},
            {13, 3, 6, 7},
            {15, 14, 12, 16}
        };

        solution.rotate(matrix);

        assertArrayEquals(
                new int[][] {
                    {15, 13, 2, 5},
                    {14, 3, 4, 1},
                    {12, 6, 8, 9},
                    {16, 7, 10, 11}
                },
                matrix);
    }

    @Test
    void rotatesTwoByTwoMatrixClockwise() {
        int[][] matrix = {
            {1, 2},
            {3, 4}
        };

        solution.rotate(matrix);

        assertArrayEquals(
                new int[][] {
                    {3, 1},
                    {4, 2}
                },
                matrix);
    }

    @Test
    void leavesSingleCellMatrixUnchanged() {
        int[][] matrix = {{42}};

        solution.rotate(matrix);

        assertArrayEquals(new int[][] {{42}}, matrix);
    }

    @Test
    void rotatesMatrixWithNegativeAndDuplicateValues() {
        int[][] matrix = {
            {-1, 0, -1},
            {2, 2, 3},
            {4, 5, 6}
        };

        solution.rotate(matrix);

        assertArrayEquals(
                new int[][] {
                    {4, 2, -1},
                    {5, 2, 0},
                    {6, 3, -1}
                },
                matrix);
    }
}
