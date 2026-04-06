package study.neetcode.coreskills.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

public class SingleSourceShortestPathTest {
    @Test
    void test_bellman() {
        // cormen Figure 24.4
        // Bellmand works with directed graph which can contain cycles and negative weights
        Graph<String> graph = new DirectedGraph<>();
        graph.addNode("s");
        graph.addNode("t");
        graph.addNode("x");
        graph.addNode("z");
        graph.addNode("y");
        graph.addEdge("s", "t", 6);
        graph.addEdge("s", "y", 7);
        graph.addEdge("t", "x", 5);
        graph.addEdge("t", "z", -4);
        graph.addEdge("t", "y", 8);
        graph.addEdge("x", "t", -2);
        graph.addEdge("z", "x", 7);
        graph.addEdge("z", "s", 2);
        graph.addEdge("y", "x", -3);
        graph.addEdge("y", "z", 9);

        SingleSourceShortestPath singleSourceShortestPath = new SingleSourceShortestPath();

        var s = graph.getNode("s");
        var t = graph.getNode("t");
        var x = graph.getNode("x");
        var z = graph.getNode("z");
        var y = graph.getNode("y");
        assertEquals(
                List.of(s, y, x, t),
                singleSourceShortestPath.getShortestPathWithBellman(s, t, graph));
        assertEquals(
                List.of(s, y, x), singleSourceShortestPath.getShortestPathWithBellman(s, x, graph));
        assertEquals(
                List.of(s, y, x, t, z),
                singleSourceShortestPath.getShortestPathWithBellman(s, z, graph));
        assertEquals(
                List.of(s, y), singleSourceShortestPath.getShortestPathWithBellman(s, y, graph));
        assertEquals(2, singleSourceShortestPath.distanceFromSource.get(t));
        assertEquals(4, singleSourceShortestPath.distanceFromSource.get(x));
        assertEquals(-2, singleSourceShortestPath.distanceFromSource.get(z));
        assertEquals(7, singleSourceShortestPath.distanceFromSource.get(y));
        assertEquals(0, singleSourceShortestPath.distanceFromSource.get(s));
    }

    @Test
    void topologicalSort() {
        Graph<String> graph = new DirectedGraph<>();
        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addNode("D");
        graph.addNode("E");
        graph.addNode("F");
        graph.addNode("G");
        graph.addNode("H");
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("A", "D");
        graph.addEdge("B", "E");
        graph.addEdge("B", "F");
        graph.addEdge("C", "G");
        graph.addEdge("C", "H");

        SingleSourceShortestPath singleSourceShortestPath = new SingleSourceShortestPath();
        singleSourceShortestPath.topologicalSort(graph);

        var A = graph.getNode("A");
        var B = graph.getNode("B");
        var C = graph.getNode("C");
        var D = graph.getNode("D");
        var E = graph.getNode("E");
        var F = graph.getNode("F");
        var G = graph.getNode("G");
        var H = graph.getNode("H");
        assertEquals(
                List.of(E, F, B, G, H, C, D, A), singleSourceShortestPath.topologicalSortResult);
    }

    @Test
    void dijkstras() {
        Graph<String> graph = new DirectedGraph<>();
        // Dijkstras works with postive weighted edged graphs with no cycles
        // i.e. DAG with no negative weighted edges
        graph.addNode("s");
        graph.addNode("t");
        graph.addNode("x");
        graph.addNode("z");
        graph.addNode("y");
        graph.addEdge("s", "t", 10);
        graph.addEdge("s", "y", 5);
        graph.addEdge("t", "x", 1);
        graph.addEdge("t", "y", 2);
        graph.addEdge("x", "z", 4);
        graph.addEdge("z", "x", 6);
        graph.addEdge("z", "s", 7);
        graph.addEdge("y", "x", 9);
        graph.addEdge("y", "z", 2);
        graph.addEdge("y", "t", 3);

        SingleSourceShortestPath singleSourceShortestPath = new SingleSourceShortestPath();
        var s = graph.getNode("s");
        var t = graph.getNode("t");
        var x = graph.getNode("x");
        var z = graph.getNode("z");
        var y = graph.getNode("y");
        assertEquals(
                List.of(s, y, t, x),
                singleSourceShortestPath.getShortestPathWithDijskras(s, x, graph));
        assertEquals(
                List.of(s, y, t, x),
                singleSourceShortestPath.getShortestPathWithBellman(s, x, graph));
    }
}
