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
        // init effort
        int rowLen = heights.length;
        int colLen = heights[0].length;
        int[][] effort = new int[rowLen][colLen];
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                effort[i][j] = Integer.MAX_VALUE;
            }
        }

        // init PQ with comparator of step with minimum effort
        // set source step as 0 effort
        // add step to PQ
        // For each step in PQ
        // if target is reached then return effort of target index
        // see if the index is visited
        // if yes continue
        // for each node reachable from the current node see if its not visited
        // if not visited then relax the edge
        // if relax is successful then add to PQ
        Comparator<Step> stepComp = Comparator.comparingInt(Step::effort);
        PriorityQueue<Step> q = new PriorityQueue<>(stepComp);
        q.offer(new Step(0, 0, 0));
        boolean[][] visited = new boolean[rowLen][colLen];
        while (!q.isEmpty()) {
            var step = q.poll();
            int i = step.i;
            int j = step.j;
            if (i == rowLen - 1 && j == colLen - 1) {
                return step.effort;
            }
            if (visited[i][j]) continue;

            visited[i][j] = true;

            relax(i - 1, j, step.effort, visited, heights, heights[i][j], effort, q);
            relax(i + 1, j, step.effort, visited, heights, heights[i][j], effort, q);
            relax(i, j - 1, step.effort, visited, heights, heights[i][j], effort, q);
            relax(i, j + 1, step.effort, visited, heights, heights[i][j], effort, q);
        }
        return effort[rowLen - 1][colLen - 1];
    }

    private void relax(
            int i,
            int j,
            int currentMaxEffort,
            boolean[][] visited,
            int[][] heights,
            int previousHeight,
            int[][] effort,
            PriorityQueue<Step> q) {
        if (i < 0 || j < 0 || i >= heights.length || j >= heights[0].length) return;
        if (visited[i][j]) return;

        int effortToReachIJ = Math.abs(heights[i][j] - previousHeight);
        int newMaxEffortToReachIJ = Math.max(currentMaxEffort, effortToReachIJ);
        if (effort[i][j] > newMaxEffortToReachIJ) {
            effort[i][j] = newMaxEffortToReachIJ;
            q.offer(new Step(i, j, newMaxEffortToReachIJ));
        }
    }

    // relax
    // record max effort of V
    // get max Effort of U
    // see if U>V then update V with new max

    // create a step record with i and j and best known effort to reach it
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
