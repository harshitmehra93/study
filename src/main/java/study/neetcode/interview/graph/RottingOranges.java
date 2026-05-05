package study.neetcode.interview.graph;

import java.util.ArrayDeque;
import java.util.Queue;

public class RottingOranges {
    private int freshOranges;
    private boolean[][] visited;
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
        Queue<Cell> q = new ArrayDeque<>();
        freshOranges = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) q.offer(new Cell(i, j));
                if (grid[i][j] == 1) freshOranges++;
            }
        }
        Queue<Cell> childQ = new ArrayDeque<>();
        int minutes = 0;
        while (!q.isEmpty() && freshOranges > 0) {
            int size = q.size();

            for (int k = 0; k < size; k++) {
                Cell cell = q.poll();
                rotAdjacentAndAddToQ(grid, cell, q);
            }

            minutes++;
        }
        if (freshOranges != 0) return -1;
        return minutes;
    }

    private void rotAdjacentAndAddToQ(int[][] grid, Cell cell, Queue<Cell> q) {
        addToQIfWithinBoundsAndIsFresh(grid, q, cell.i, cell.j - 1);
        addToQIfWithinBoundsAndIsFresh(grid, q, cell.i, cell.j + 1);
        addToQIfWithinBoundsAndIsFresh(grid, q, cell.i - 1, cell.j);
        addToQIfWithinBoundsAndIsFresh(grid, q, cell.i + 1, cell.j);
    }

    void addToQIfWithinBoundsAndIsFresh(int[][] grid, Queue<Cell> q, int i, int j) {
        if (isOutsideBounds(grid, i, j)) return;
        if (grid[i][j] == 1) {
            grid[i][j] = 2;
            q.offer(new Cell(i, j));
            freshOranges--;
        }
    }

    private static boolean isOutsideBounds(int[][] grid, int i, int j) {
        return i < 0 || j < 0 || i >= grid.length || j >= grid[0].length;
    }

    record Cell(int i, int j) {}
}
