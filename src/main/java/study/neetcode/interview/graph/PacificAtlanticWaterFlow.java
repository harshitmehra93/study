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
    Boolean[][] atlanticMemo;
    Boolean[][] pacificMemo;

    public int[][] waterflow(int[][] heights) {
        atlanticMemo = new Boolean[heights.length][heights[0].length];
        pacificMemo = new Boolean[heights.length][heights[0].length];
        List<Pair> resultList = new ArrayList<>();
        for (int i = 0; i < heights.length; i++) {
            for (int j = 0; j < heights[0].length; j++) {
                boolean isPacificReachable =
                        isPacificReachable(i, j, heights, Integer.MAX_VALUE, new HashSet<>());
                boolean isAtlanticReachable =
                        isAtlanticReachable(i, j, heights, Integer.MAX_VALUE, new HashSet<>());
                if (isAtlanticReachable && isPacificReachable) {
                    resultList.add(new Pair(i, j));
                }
            }
        }
        int[][] result = new int[resultList.size()][2];
        for (int i = 0; i < result.length; i++) {
            var pair = resultList.get(i);
            result[i][0] = pair.i();
            result[i][1] = pair.j();
        }
        return result;
    }

    private boolean isPacificReachable(
            int i, int j, int[][] heights, int previousValue, Set<Pair> visited) {
        if (i < 0 || j < 0 || i >= heights.length || j >= heights[0].length) return false;
        Pair pair = new Pair(i, j);
        if (visited.contains(pair)) return false;
        if (heights[i][j] > previousValue) return false;
        if (i == 0 || j == 0) return true;
        if (pacificMemo[i][j] != null) return pacificMemo[i][j];

        visited.add(pair);
        int currentCell = heights[i][j];
        return pacificMemo[i][j] =
                isPacificReachable(i, j - 1, heights, currentCell, visited)
                        || isPacificReachable(i, j + 1, heights, currentCell, visited)
                        || isPacificReachable(i - 1, j, heights, currentCell, visited)
                        || isPacificReachable(i + 1, j, heights, currentCell, visited);
    }

    private boolean isAtlanticReachable(
            int i, int j, int[][] heights, int previousValue, Set<Pair> visited) {
        if (i < 0 || j < 0 || i >= heights.length || j >= heights[0].length) return false;
        Pair pair = new Pair(i, j);
        if (visited.contains(pair)) return false;
        if (heights[i][j] > previousValue) return false;
        if (i == heights.length - 1 || j == heights[0].length - 1) return true;
        if (atlanticMemo[i][j] != null) return atlanticMemo[i][j];

        visited.add(pair);
        int currentCell = heights[i][j];
        return atlanticMemo[i][j] =
                isAtlanticReachable(i, j - 1, heights, currentCell, visited)
                        || isAtlanticReachable(i, j + 1, heights, currentCell, visited)
                        || isAtlanticReachable(i - 1, j, heights, currentCell, visited)
                        || isAtlanticReachable(i + 1, j, heights, currentCell, visited);
    }

    record Pair(int i, int j) {}
}
