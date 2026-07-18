package study.coreskills.graph;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

class BfsShortestPathTest {

    private final BfsShortestPath solution = new BfsShortestPath();

    @Test
    void computesDistancesParentsAndPathForRecallExample() {
        List<List<Integer>> graph =
                List.of(
                        List.of(1, 2),
                        List.of(0, 3),
                        List.of(0, 3, 4),
                        List.of(1, 2, 5),
                        List.of(2, 5),
                        List.of(3, 4));

        BfsShortestPath.SearchResult result = solution.search(graph, 0);

        assertArrayEquals(new int[] {0, 1, 1, 2, 2, 3}, result.distances());
        assertArrayEquals(new int[] {-1, 0, 0, 1, 2, 3}, result.parents());
        assertEquals(List.of(0, 1, 3, 5), solution.reconstructPath(result, 0, 5));
    }

    @Test
    void handlesCyclesWithoutChangingFirstDiscoveryParents() {
        List<List<Integer>> graph =
                List.of(List.of(1, 2), List.of(0, 2), List.of(0, 1, 3), List.of(2));

        BfsShortestPath.SearchResult result = solution.search(graph, 0);

        assertArrayEquals(new int[] {0, 1, 1, 2}, result.distances());
        assertArrayEquals(new int[] {-1, 0, 0, 2}, result.parents());
        assertEquals(List.of(0, 2, 3), solution.reconstructPath(result, 0, 3));
    }

    @Test
    void leavesDisconnectedVerticesUnreachable() {
        List<List<Integer>> graph = List.of(List.of(1), List.of(0), List.of(3), List.of(2));

        BfsShortestPath.SearchResult result = solution.search(graph, 0);

        assertArrayEquals(new int[] {0, 1, -1, -1}, result.distances());
        assertArrayEquals(new int[] {-1, 0, -1, -1}, result.parents());
        assertEquals(List.of(), solution.reconstructPath(result, 0, 3));
    }

    @Test
    void reconstructsSourceToItself() {
        List<List<Integer>> graph = List.of(List.of(1), List.of(0, 2), List.of(1));

        BfsShortestPath.SearchResult result = solution.search(graph, 1);

        assertArrayEquals(new int[] {1, 0, 1}, result.distances());
        assertArrayEquals(new int[] {1, -1, 1}, result.parents());
        assertEquals(List.of(1), solution.reconstructPath(result, 1, 1));
    }
}
