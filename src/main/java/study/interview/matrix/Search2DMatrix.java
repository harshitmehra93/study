package study.interview.matrix;

/*
Search a 2D Matrix
You are given an m x n integer matrix with these properties:
Each row is sorted in non-decreasing order.
The first integer of each row is greater than the last integer of the previous row.
Given an integer target, return true if target is in the matrix, otherwise return false.
You must write a solution with O(log(m * n)) time complexity.
Example:
Input:
matrix = [
  [1, 3, 5, 7],
  [10, 11, 16, 20],
  [23, 30, 34, 60]
]
target = 3

Output: true
 */
public class Search2DMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0) return false;

        int targetRow = getTargetRow(matrix, target, 0, matrix.length - 1);
        if (targetRow == -1) return false;
        return searchWithinRow(matrix[targetRow], target, 0, matrix[0].length - 1);
    }

    private boolean searchWithinRow(int[] row, int target, int low, int high) {
        if (low > high) return false;
        int middle = low + (high - low) / 2;
        if (target > row[middle]) {
            return searchWithinRow(row, target, middle + 1, high);
        }
        if (target < row[middle]) {
            return searchWithinRow(row, target, low, middle - 1);
        }
        return true;
    }

    private int getTargetRow(int[][] matrix, int target, int low, int high) {
        if (low > high) return -1;

        int middle = low + (high - low) / 2;

        int lastElement = matrix[middle][matrix[0].length - 1];

        if (target > lastElement) {
            return getTargetRow(matrix, target, middle + 1, high);
        }

        if (target >= matrix[middle][0]) return middle;

        return getTargetRow(matrix, target, low, middle - 1);
    }
}
