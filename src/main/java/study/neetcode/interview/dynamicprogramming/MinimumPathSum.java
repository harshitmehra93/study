package study.neetcode.interview.dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

public class MinimumPathSum {

    //    You are given an m x n grid filled with non-negative integers.
    //
    //    You start at the top-left corner (0, 0) and want to reach the bottom-right corner (m - 1,
    // n - 1).
    //
    //    You can only move:
    //
    //    Right (i, j+1)
    //    Down (i+1, j)
    Map<index, Integer> memo = new HashMap<>();

    public int minimumPathSum(int[][] grid) {
        memo = new HashMap<>();
        return helper(0, 0, grid);
    }

    int helper(int i, int j, int[][] grid) {
        int rowLength = grid.length;
        int columnLength = grid[0].length;
        if (i < 0 || j < 0 || i >= rowLength || j >= columnLength) return Integer.MAX_VALUE;
        if (i == rowLength - 1 && j == columnLength - 1)
            return grid[rowLength - 1][columnLength - 1];
        index pair = new index(i, j);
        if (memo.containsKey(pair)) return memo.get(pair);

        int right = helper(i, j + 1, grid);
        int down = helper(i + 1, j, grid);
        int min = Math.min(right, down);

        if (min == Integer.MAX_VALUE) {
            memo.put(pair, min);
            return min;
        }
        memo.put(pair, grid[i][j] + min);
        return memo.get(pair);
    }

    record index(int i, int j) {}
}
