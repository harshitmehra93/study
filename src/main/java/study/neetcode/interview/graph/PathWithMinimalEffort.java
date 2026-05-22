package study.neetcode.interview.graph;

import java.util.HashSet;
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
        return visit(0, 0, heights, 0, new HashSet<Index>());
    }

    private int visit(int i, int j, int[][] heights, int maxEffort, Set<Index> visited) {
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
        return visit(i, j, heights, Math.max(maxEffort, effort), visited);
    }

    record Index(int i, int j) {}
}
