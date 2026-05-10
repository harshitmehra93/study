package study.neetcode.interview.graph;

/*
**Number of Enclaves**

You are given an `m x n` binary matrix `grid`, where:

- `0` represents sea
- `1` represents land

A move consists of walking from one land cell to another land cell in one of the four directions: up, down, left, or right.

Return the number of land cells in `grid` for which we cannot walk off the boundary of the grid.

In other words: count land cells that are **not connected to any boundary land cell**.

Example:

```text
Input:
grid = [
  [0,0,0,0],
  [1,0,1,0],
  [0,1,1,0],
  [0,0,0,0]
]

Output: 3
```

The three land cells in the middle form an enclave because they cannot reach the boundary.
 */
public class NumberOfEnclaves {

    private boolean[][] visited;

    public int numberOfEnclaves(int[][] grid) {
        visited = new boolean[grid.length][grid[0].length];

        // top
        for (int j = 0; j < grid[0].length; j++) {
            dfsVisit(grid, 0, j);
        }

        // right
        for (int i = 0; i < grid.length; i++) {
            dfsVisit(grid, i, grid[0].length - 1);
        }

        // bottom
        for (int j = 0; j < grid[0].length; j++) {
            dfsVisit(grid, grid.length - 1, j);
        }

        // left
        for (int i = 0; i < grid.length; i++) {
            dfsVisit(grid, i, 0);
        }

        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) count++;
            }
        }
        return count;
    }

    private void dfsVisit(int[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length) return;
        if (grid[i][j] != 1) return;
        if (visited[i][j]) return;

        visited[i][j] = true;

        dfsVisit(grid, i, j - 1);
        dfsVisit(grid, i, j + 1);
        dfsVisit(grid, i - 1, j);
        dfsVisit(grid, i + 1, j);
    }
}
