package study.neetcode.interview.matrix;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

class SpiralMatrixTest {

    private final SpiralMatrix solution = new SpiralMatrix();

    @Test
    void returnsSpiralOrderForSquareMatrix() {
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        List<Integer> result = solution.spiralOrder(matrix);

        assertEquals(List.of(1, 2, 3, 6, 9, 8, 7, 4, 5), result);
    }

    @Test
    void returnsSpiralOrderForWideMatrix() {
        int[][] matrix = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12}
        };

        List<Integer> result = solution.spiralOrder(matrix);

        assertEquals(List.of(1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7), result);
    }

    @Test
    void returnsSpiralOrderForTallMatrix() {
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9},
            {10, 11, 12}
        };

        List<Integer> result = solution.spiralOrder(matrix);

        assertEquals(List.of(1, 2, 3, 6, 9, 12, 11, 10, 7, 4, 5, 8), result);
    }

    @Test
    void returnsSingleRowLeftToRight() {
        int[][] matrix = {{1, 2, 3, 4}};

        List<Integer> result = solution.spiralOrder(matrix);

        assertEquals(List.of(1, 2, 3, 4), result);
    }

    @Test
    void returnsSingleColumnTopToBottom() {
        int[][] matrix = {{1}, {2}, {3}, {4}};

        List<Integer> result = solution.spiralOrder(matrix);

        assertEquals(List.of(1, 2, 3, 4), result);
    }

    @Test
    void returnsSingleCell() {
        int[][] matrix = {{42}};

        List<Integer> result = solution.spiralOrder(matrix);

        assertEquals(List.of(42), result);
    }

    @Test
    void handlesTwoByTwoMatrix() {
        int[][] matrix = {
            {1, 2},
            {3, 4}
        };

        List<Integer> result = solution.spiralOrder(matrix);

        assertEquals(List.of(1, 2, 4, 3), result);
    }
}
