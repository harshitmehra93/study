package study.neetcode.interview.graph;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Set;

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

    public int minimumEffortPath(int[][] heights) {
        // init DJ EffortMatrix
        int[][] effort = new int[heights.length][heights[0].length];
        for (int i = 0; i < heights.length; i++) {
            for (int j = 0; j < heights[0].length; j++) {
                effort[i][j] = Integer.MAX_VALUE;
            }
        }
        effort[0][0] = 0;

        // Djikstras
        // init PQ and set source effort as 0
        // add source to PQ
        // For each step extracted from DJ
        // if visited continue
        // mark visited
        // relax unvisited adj nodes
        // if relax is successful then add to PQ
        Comparator<Step> effortComp = Comparator.comparingInt(Step::effort);
        PriorityQueue<Step> Q = new PriorityQueue<>(effortComp);
        Q.offer(new Step(0, 0, effort[0][0]));
        boolean[][] visited = new boolean[heights.length][heights[0].length];
        while (!Q.isEmpty()) {
            var step = Q.poll();
            if (visited[step.i][step.j]) continue;

            visited[step.i][step.j] = true;

            if (step.i == heights.length - 1 && step.j == heights[0].length - 1) {
                return step.effort;
            }

            relax(step.i - 1, step.j, step, effort, step.effort(), heights, visited, Q);
            relax(step.i + 1, step.j, step, effort, step.effort(), heights, visited, Q);
            relax(step.i, step.j - 1, step, effort, step.effort(), heights, visited, Q);
            relax(step.i, step.j + 1, step, effort, step.effort(), heights, visited, Q);
        }

        // Return effor of E[heights.len][heights[0].len]
        return effort[heights.length - 1][heights[0].length - 1];
    }

    private void relax(
            int i,
            int j,
            Step previousStep,
            int[][] effort,
            int previousBestEffort,
            int[][] heights,
            boolean[][] visited,
            PriorityQueue<Step> Q) {
        if (i < 0 || j < 0 || i >= heights.length || j >= heights[0].length) return;
        if (visited[i][j]) return;

        int effortToReachIJ = Math.abs(heights[previousStep.i][previousStep.j] - heights[i][j]);
        int newMax = Math.max(effortToReachIJ, previousBestEffort);

        if (newMax < effort[i][j]) {
            effort[i][j] = newMax;
            Q.offer(new Step(i, j, newMax));
        }
    }

    record Step(int i, int j, int effort) {}

    //    public int minimumEffortPath(int[][] heights) {
    //        return visitBackTrack(0, 0, heights, 0, new HashSet<Index>());
    //    }

    private int visitBackTrack(int i, int j, int[][] heights, int maxEffort, Set<Index> visited) {
        if (isOutsideBounds(i, j, heights)) return -1;
        if (i == heights.length - 1 && j == heights[0].length - 1) return maxEffort;
        var index = new Index(i, j);
        if (visited.contains(index)) return -1;

        visited.add(index);

        int up = tryDirection(i - 1, j, heights, maxEffort, visited, heights[i][j]);
        int down = tryDirection(i + 1, j, heights, maxEffort, visited, heights[i][j]);
        int left = tryDirection(i, j - 1, heights, maxEffort, visited, heights[i][j]);
        int right = tryDirection(i, j + 1, heights, maxEffort, visited, heights[i][j]);

        visited.remove(index);

        int best = Integer.MAX_VALUE;

        if (up != -1) best = Math.min(best, up);
        if (down != -1) best = Math.min(best, down);
        if (left != -1) best = Math.min(best, left);
        if (right != -1) best = Math.min(best, right);

        return best == Integer.MAX_VALUE ? -1 : best;
    }

    private static boolean isOutsideBounds(int i, int j, int[][] heights) {
        return i < 0 || j < 0 || i >= heights.length || j >= heights[0].length;
    }

    private int tryDirection(
            int i, int j, int[][] heights, int maxEffort, Set<Index> visited, int current) {
        if (isOutsideBounds(i, j, heights)) return -1;
        int effort = Math.abs(heights[i][j] - current);
        return visitBackTrack(i, j, heights, Math.max(maxEffort, effort), visited);
    }

    record Index(int i, int j) {}
}
