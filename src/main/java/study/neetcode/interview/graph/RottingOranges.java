package study.neetcode.interview.graph;

public class RottingOranges {
    //    Problem: Rotting Oranges
    //
    //    You are given an m x n grid:
    //
    //            0 = empty cell
    // 1 = fresh orange
    // 2 = rotten orange
    //
    //    Every minute, any fresh orange adjacent up/down/left/right to a rotten orange becomes
    // rotten.
    //
    //    Return the minimum number of minutes until no fresh orange remains.
    //
    //    If impossible, return -1.

    public int orangesRotting(int[][] grid) {
        // find is there any unreachable cluster if yes then return -1, if there are no fresh
        // oranges return 0, -> O(2*m*n)
        int freshOranges = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    freshOranges++;
                    if (!adjacentRottenOrangeExists(grid, i, j)) {
                        return -1;
                    }
                }
            }
        }

        if (freshOranges == 0) return 0;

        // For each rotten orange, rot adjacent fresh oranges for each iteration count 1
        int minutes = 0;
        while (freshOrangesExist(grid)) { // O(2((m*n)^2))
            boolean visited[][] = new boolean[grid.length][grid[0].length];
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == 2 && !visited[i][j]) {
                        visited[i][j] = true;
                        rotAdjacent(grid, i, j, visited);
                    }
                }
            }
            minutes++;
        }

        return minutes;
    }

    private boolean freshOrangesExist(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    return true;
                }
            }
        }
        return false;
    }

    private void rotAdjacent(int[][] grid, int i, int j, boolean[][] visited) {
        markRottenifNotOutsideBoundsOfGridAndIsFreshOrangeAndIsNotVisited(grid, i, j - 1, visited);
        markRottenifNotOutsideBoundsOfGridAndIsFreshOrangeAndIsNotVisited(grid, i, j + 1, visited);
        markRottenifNotOutsideBoundsOfGridAndIsFreshOrangeAndIsNotVisited(grid, i - 1, j, visited);
        markRottenifNotOutsideBoundsOfGridAndIsFreshOrangeAndIsNotVisited(grid, i + 1, j, visited);
    }

    private void markRottenifNotOutsideBoundsOfGridAndIsFreshOrangeAndIsNotVisited(
            int[][] grid, int i, int j, boolean[][] visited) {
        if (isOutsideBoundsOfGrid(grid, i, j)) return;
        if (grid[i][j] == 1 && !visited[i][j]) {
            grid[i][j] = 2;
            visited[i][j] = true;
        }
    }

    private boolean adjacentRottenOrangeExists(int[][] grid, int i, int j) {
        boolean visited[][] = new boolean[grid.length][grid[0].length];
        return isRottenOrangeReachable(grid, i, j, visited);
    }

    public boolean isRottenOrangeReachable(int[][] grid, int i, int j, boolean[][] visited) {
        if (isOutsideBoundsOfGrid(grid, i, j)) return false;
        if (visited[i][j]) return false;
        if (grid[i][j] == 0) return false;

        visited[i][j] = true;
        if (grid[i][j] == 2) return true;
        return isRottenOrangeReachable(grid, i, j - 1, visited)
                || isRottenOrangeReachable(grid, i, j + 1, visited)
                || isRottenOrangeReachable(grid, i - 1, j, visited)
                || isRottenOrangeReachable(grid, i + 1, j, visited);
    }

    private static boolean isOutsideBoundsOfGrid(int[][] grid, int i, int j) {
        return i < 0 || j < 0 || i >= grid.length || j >= grid[0].length;
    }
}
