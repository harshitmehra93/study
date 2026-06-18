package study.neetcode.interview.matrix;

import java.util.HashSet;
import java.util.Set;

/*
Set Matrix Zeroes.
Problem
Given an m x n integer matrix, if an element is 0, set its entire row and column to 0.
You must modify the matrix in-place.
Example:
Input:
[
  [1,1,1],
  [1,0,1],
  [1,1,1]
]

Output:
[
  [1,0,1],
  [0,0,0],
  [1,0,1]
]
 */
public class SetMatrixZeroes {

    public void setZeroes(int[][] matrix) {
        Set<Integer> rowIndicesToZeroOut = new HashSet<>();
        Set<Integer> columnIndicesToZeroOut = new HashSet<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    rowIndicesToZeroOut.add(i);
                    columnIndicesToZeroOut.add(j);
                }
            }
        }

        for (int i : rowIndicesToZeroOut) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = 0;
            }
        }

        for (int j : columnIndicesToZeroOut) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][j] = 0;
            }
        }
    }
}
