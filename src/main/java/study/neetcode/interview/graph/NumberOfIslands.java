package study.neetcode.interview.graph;

public class NumberOfIslands {

    //    Number of Islands
    //
    //    You are given an m x n grid containing:
    //
    //            '1' = land
    //            '0' = water
    //
    //    An island is formed by connecting adjacent lands horizontally or vertically.
    //
    //    Return the number of islands.
    //
    //            Function signature
    //    int numIslands(char[][] grid);

    public int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    discover(grid, i, j);
                }
            }
        }
        for (char[] arr : grid) {
            for (char ch : arr) {
                System.out.print(ch + " ");
            }
            System.out.println();
        }
        return count;
    }

    private void discover(char[][] grid, int i, int j) {
        if (i >= grid.length || j >= grid[0].length) return;
        if (i < 0 || j < 0) return;

        if (grid[i][j] == '1') {
            grid[i][j] = 'X';
            discover(grid, i, j - 1);
            discover(grid, i, j + 1);
            discover(grid, i - 1, j);
            discover(grid, i + 1, j);
        }
    }
}
