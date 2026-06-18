package study.neetcode.interview.matrix;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class Search2DMatrixTest {

    private final Search2DMatrix solution = new Search2DMatrix();

    @Test
    void returnsTrueWhenTargetExistsInMiddleOfMatrix() {
        int[][] matrix = {
            {1, 3, 5, 7},
            {10, 11, 16, 20},
            {23, 30, 34, 60}
        };

        assertTrue(solution.searchMatrix(matrix, 16));
    }

    @Test
    void returnsFalseWhenTargetFallsBetweenRows() {
        int[][] matrix = {
            {1, 3, 5, 7},
            {10, 11, 16, 20},
            {23, 30, 34, 60}
        };

        assertFalse(solution.searchMatrix(matrix, 13));
    }

    @Test
    void findsFirstAndLastElements() {
        int[][] matrix = {
            {1, 3, 5, 7},
            {10, 11, 16, 20},
            {23, 30, 34, 60}
        };

        assertTrue(solution.searchMatrix(matrix, 1));
        assertTrue(solution.searchMatrix(matrix, 60));
    }

    @Test
    void returnsFalseWhenTargetIsOutsideMatrixRange() {
        int[][] matrix = {
            {1, 3, 5, 7},
            {10, 11, 16, 20},
            {23, 30, 34, 60}
        };

        assertFalse(solution.searchMatrix(matrix, 0));
        assertFalse(solution.searchMatrix(matrix, 61));
    }

    @Test
    void searchesSingleRowMatrix() {
        int[][] matrix = {{1, 4, 9, 15}};

        assertTrue(solution.searchMatrix(matrix, 9));
        assertFalse(solution.searchMatrix(matrix, 10));
    }

    @Test
    void searchesSingleColumnMatrix() {
        int[][] matrix = {{1}, {4}, {9}, {15}};

        assertTrue(solution.searchMatrix(matrix, 4));
        assertFalse(solution.searchMatrix(matrix, 8));
    }

    @Test
    void searchesSingleCellMatrix() {
        int[][] matrix = {{7}};

        assertTrue(solution.searchMatrix(matrix, 7));
        assertFalse(solution.searchMatrix(matrix, 3));
    }
}
