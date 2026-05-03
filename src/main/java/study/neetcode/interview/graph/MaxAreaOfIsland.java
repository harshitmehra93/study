package study.neetcode.interview.graph;

public class MaxAreaOfIsland {
    //    Max Area of Island
    //    You are given an m x n grid of integers:
    //
    //            1 = land
    //            0 = water
    //
    //    An island is formed by connecting land cells horizontally or vertically.
    //
    //    Return the maximum area of an island in the grid.
    //
    //    The area of an island is the number of land cells in that connected component.
    //
    //            Function signature
    public int maxAreaOfIsland(char[][] grid) {
        int max = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    max = Math.max(max, discoverAndCount(grid, i, j));
                }
            }
        }
        for (char[] arr : grid) {
            for (char ch : arr) {
                System.out.print(ch + " ");
            }
            System.out.println();
        }
        return max;
    }

    private int discoverAndCount(char[][] grid, int i, int j) {
        if (i < 0 || j < 0) return 0;
        if (i >= grid.length || j >= grid[0].length) return 0;
        if (grid[i][j] != '1') return 0;

        grid[i][j] = 'X';
        int left = discoverAndCount(grid, i, j - 1);
        int right = discoverAndCount(grid, i, j + 1);
        int up = discoverAndCount(grid, i - 1, j);
        int down = discoverAndCount(grid, i + 1, j);

        return 1 + left + right + up + down;
    }
}
