package study.interview.graph;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
Problem statement

You are given an n x n grid.

Each cell grid[i][j] represents the time when that cell becomes passable.

You start at the top-left cell:

[0, 0]

You want to reach the bottom-right cell:

[n - 1, n - 1]

At time t, you can move into a cell only if:

grid[i][j] <= t

You can move in four directions:

up, down, left, right

Return the minimum time t required to reach the bottom-right cell.

Example 1
grid = [
  [0, 2],
  [1, 3]
]

Output:

3
 */
public class SwimInRisingWater {

    private int[][] maxTimeToReach;
    private boolean[][] visited;

    public int swimInWater(int[][] grid) {
        int rowLen = grid.length;
        int colLen = grid[0].length;
        maxTimeToReach = new int[rowLen][colLen];
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                maxTimeToReach[i][j] = Integer.MAX_VALUE;
            }
        }
        maxTimeToReach[0][0] = grid[0][0];

        Comparator<ProposedStep> comp = Comparator.comparingInt(ProposedStep::proposedMaxTime);
        PriorityQueue<ProposedStep> q = new PriorityQueue<>(comp);
        q.offer(new ProposedStep(0, 0, grid[0][0]));
        visited = new boolean[rowLen][colLen];
        while (!q.isEmpty()) {
            var finalisedStep = q.poll();
            int i = finalisedStep.i;
            int j = finalisedStep.j;

            if (visited[i][j]) continue;

            visited[i][j] = true;

            if (i == rowLen - 1 && j == colLen - 1) {
                return finalisedStep.proposedMaxTime;
            }

            relax(i - 1, j, finalisedStep, grid, q);
            relax(i + 1, j, finalisedStep, grid, q);
            relax(i, j - 1, finalisedStep, grid, q);
            relax(i, j + 1, finalisedStep, grid, q);
        }
        return maxTimeToReach[rowLen - 1][colLen - 1];
    }

    private void relax(
            int i, int j, ProposedStep parentStep, int[][] grid, PriorityQueue<ProposedStep> q) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length) return;
        if (visited[i][j]) return;
        int newProposedMaxToReachIJ = Math.max(parentStep.proposedMaxTime, grid[i][j]);
        if (maxTimeToReach[i][j] > newProposedMaxToReachIJ) {
            maxTimeToReach[i][j] = newProposedMaxToReachIJ;
            q.offer(new ProposedStep(i, j, newProposedMaxToReachIJ));
        }
    }

    record ProposedStep(int i, int j, int proposedMaxTime) {}
}
