package study.interview.matrix;

import java.util.ArrayList;
import java.util.List;

/*
Problem
Given an m x n matrix, return all elements of the matrix in spiral order.
Example:
Input:
[
 [1,2,3],
 [4,5,6],
 [7,8,9]
]

Output: [1,2,3,6,9,8,7,4,5]
 */
public class SpiralMatrix {
    List<Integer> result;

    public List<Integer> spiralOrder(int[][] matrix) {
        result = new ArrayList<>();
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        int i = 0, j = 0;
        while (true) {
            if (isInvalidIndex(matrix, i, j)) break;
            if (visited[i][j]) break;
            j = rightTraversal(matrix, i, j, visited);
            i = bottomTraversal(matrix, i + 1, j, visited);
            j = leftTraversal(matrix, i, j - 1, visited);
            i = upTraversal(matrix, i - 1, j, visited);
            j++;
        }
        return result;
    }

    private int upTraversal(int[][] matrix, int i, int j, boolean[][] visited) {
        if (isInvalidIndex(matrix, i, j)) return i;

        for (; i >= 0; i--) {
            if (visited[i][j]) return i + 1;
            result.add(matrix[i][j]);
            visited[i][j] = true;
        }
        return i + 1;
    }

    private int leftTraversal(int[][] matrix, int i, int j, boolean[][] visited) {
        if (isInvalidIndex(matrix, i, j)) return j;

        for (; j >= 0; j--) {
            if (visited[i][j]) return j + 1;
            result.add(matrix[i][j]);
            visited[i][j] = true;
        }
        return j + 1;
    }

    private int bottomTraversal(int[][] matrix, int i, int j, boolean[][] visited) {
        if (isInvalidIndex(matrix, i, j)) return i;

        for (; i < matrix.length; i++) {
            if (visited[i][j]) return i - 1;
            result.add(matrix[i][j]);
            visited[i][j] = true;
        }
        return i - 1;
    }

    private int rightTraversal(int[][] matrix, int i, int j, boolean[][] visited) {
        if (isInvalidIndex(matrix, i, j)) return j;

        for (; j < matrix[0].length; j++) {
            if (visited[i][j]) return j - 1;
            result.add(matrix[i][j]);
            visited[i][j] = true;
        }
        return j - 1;
    }

    private static boolean isInvalidIndex(int[][] matrix, int i, int j) {
        return i >= matrix.length || j >= matrix[0].length || i < 0 || j < 0;
    }
}
