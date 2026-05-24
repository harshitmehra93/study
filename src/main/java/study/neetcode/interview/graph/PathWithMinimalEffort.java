package study.neetcode.interview.graph;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
Path With Minimum Effort

Problem statement

You are given a 2D grid heights.

You start at the top-left cell:

[0, 0]

You want to reach the bottom-right cell:

[rows - 1, cols - 1]

You can move in four directions:

up, down, left, right

The effort of moving from one cell to another is:

absolute difference between their heights

The effort of a path is the maximum edge effort along that path.

Return the minimum possible effort required to travel from top-left to bottom-right.

Example
heights = [
  [1, 2, 2],
  [3, 8, 2],
  [5, 3, 5]
]

Output:

2

One good path is:

1 -> 3 -> 5 -> 3 -> 5

Efforts:

|1-3| = 2
|3-5| = 2
|5-3| = 2
|3-5| = 2

Maximum effort on this path is 2.
 */
public class PathWithMinimalEffort {

    private int[][] maxEfforts;
    private boolean[][] visited;

    public int minimumEffortPath(int[][] heights) {
        int rowLen = heights.length;
        int colLen = heights[0].length;

        maxEfforts = new int[rowLen][colLen];
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                maxEfforts[i][j] = Integer.MAX_VALUE;
            }
        }

        Comparator<ProposedStep> comp = Comparator.comparingInt(ProposedStep::proposedMaxEffort);
        PriorityQueue<ProposedStep> q = new PriorityQueue<>(comp);
        maxEfforts[0][0] = 0;
        q.offer(new ProposedStep(0, 0, 0));
        visited = new boolean[rowLen][colLen];
        while (!q.isEmpty()) {
            var finalisedStep = q.poll();
            int i = finalisedStep.i;
            int j = finalisedStep.j;
            if (i == rowLen - 1 && j == colLen - 1) {
                return finalisedStep.proposedMaxEffort;
            }
            if (visited[i][j]) continue;

            relax(i - 1, j, finalisedStep, heights, q);
            relax(i + 1, j, finalisedStep, heights, q);
            relax(i, j - 1, finalisedStep, heights, q);
            relax(i, j + 1, finalisedStep, heights, q);
        }
        return maxEfforts[rowLen - 1][colLen - 1];
    }

    private void relax(
            int i, int j, ProposedStep parentStep, int[][] heights, PriorityQueue<ProposedStep> q) {
        if (i < 0 || j < 0 || i >= heights.length || j >= heights[0].length) return;
        if (visited[i][j]) return;
        int effortOnThisEdge = Math.abs(heights[i][j] - heights[parentStep.i][parentStep.j]);
        int newMaxEffortToReachIJ = Math.max(parentStep.proposedMaxEffort(), effortOnThisEdge);
        if (maxEfforts[i][j] > newMaxEffortToReachIJ) {
            maxEfforts[i][j] = newMaxEffortToReachIJ;
            q.offer(new ProposedStep(i, j, newMaxEffortToReachIJ));
        }
    }

    record ProposedStep(int i, int j, int proposedMaxEffort) {}
}
