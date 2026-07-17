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
    public List<Integer> spiralOrder(int[][] matrix) {
        int top = 0;
        int bottom = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;
        List<Integer> result = new ArrayList<>();

        while (left <= right && top <= bottom) {
            // top
            for (int j = left; j <= right; j++) {
                result.add(matrix[top][j]);
            }

            // right
            for (int i = top + 1; i <= bottom; i++) {
                result.add(matrix[i][right]);
            }

            // bottom
            if (top < bottom) {
                for (int j = right - 1; j >= left; j--) {
                    result.add(matrix[bottom][j]);
                }
            }

            // left
            if (left < right) {
                for (int i = bottom - 1; i > top; i--) {
                    result.add(matrix[i][left]);
                }
            }

            top++;
            bottom--;
            left++;
            right--;
        }
        return result;
    }
}
