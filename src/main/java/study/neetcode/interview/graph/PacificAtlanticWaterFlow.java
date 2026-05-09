package study.neetcode.interview.graph;

import java.util.*;

public class PacificAtlanticWaterFlow {

    //    Problem: Pacific Atlantic Water Flow
    //
    //    You are given an m x n matrix heights.
    //
    //    Water can flow from a cell to another cell if the neighbor’s height is less than or equal
    // to the current cell’s height.
    //
    //    Return all cells from which water can flow to both:
    //
    //    Pacific ocean: top row or left column
    //    Atlantic ocean: bottom row or right column

    //    heights = [
    //              [1, 2, 2, 3, 5],
    //              [3, 2, 3, 4, 4],
    //              [2, 4, 5, 3, 1],
    //              [6, 7, 1, 4, 5],
    //              [5, 1, 1, 2, 4]
    //            ]
    //
    //    solution = [
    //              [0,4],
    //              [1,3],
    //              [1,4],
    //              [2,2],
    //              [3,0],
    //              [3,1],
    //              [4,0]
    //            ]
    boolean[][] nodesReachableFromAtlantic;
    boolean[][] nodesReachableFromPacific;

    public List<List<Integer>> waterflow(int[][] heights) {
        nodesReachableFromAtlantic = new boolean[heights.length][heights[0].length];
        nodesReachableFromPacific = new boolean[heights.length][heights[0].length];

        // pacific traversals
        for (int j = 0; j < heights[0].length; j++) {
            dfsVisit(heights, 0, j, nodesReachableFromPacific, Integer.MIN_VALUE);
        }
        for (int i = 0; i < heights.length; i++) {
            dfsVisit(heights, i, 0, nodesReachableFromPacific, Integer.MIN_VALUE);
        }

        // atlantic traversals
        for (int j = 0; j < heights[0].length; j++) {
            dfsVisit(heights, heights.length - 1, j, nodesReachableFromAtlantic, Integer.MIN_VALUE);
        }
        for (int i = 0; i < heights.length; i++) {
            dfsVisit(
                    heights,
                    i,
                    heights[0].length - 1,
                    nodesReachableFromAtlantic,
                    Integer.MIN_VALUE);
        }

        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < heights.length; i++) {
            for (int j = 0; j < heights[0].length; j++) {
                if (nodesReachableFromAtlantic[i][j] && nodesReachableFromPacific[i][j]) {
                    result.add(List.of(i, j));
                }
            }
        }
        return result;
    }

    private void dfsVisit(
            int[][] heights, int i, int j, boolean[][] isReachable, int previousValue) {
        if (i < 0 || j < 0 || i >= heights.length || j >= heights[0].length) return;
        if (heights[i][j] < previousValue) return; // water flow is reversed
        if (isReachable[i][j]) {
            return;
        }

        isReachable[i][j] = true;

        dfsVisit(heights, i, j - 1, isReachable, heights[i][j]);
        dfsVisit(heights, i, j + 1, isReachable, heights[i][j]);
        dfsVisit(heights, i - 1, j, isReachable, heights[i][j]);
        dfsVisit(heights, i + 1, j, isReachable, heights[i][j]);
    }
}
