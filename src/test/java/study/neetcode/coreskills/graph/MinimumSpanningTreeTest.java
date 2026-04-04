package study.neetcode.coreskills.graph;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;
import org.junit.jupiter.api.Test;
import study.model.Edge;

public class MinimumSpanningTreeTest {

    @Test
    void kruskalTest() {
        Graph<Integer> graph = new UndirectedGraph<>();
        graph.addNode(1);
        graph.addNode(2);
        graph.addNode(3);
        graph.addNode(4);
        graph.addNode(5);
        graph.addNode(6);
        graph.addNode(7);
        graph.addEdge(1, 2, 1);
        graph.addEdge(2, 3, 2);
        graph.addEdge(3, 4, 3);
        graph.addEdge(2, 5, 4);
        graph.addEdge(3, 5, 5);
        graph.addEdge(4, 7, 6);
        graph.addEdge(7, 6, 7);
        graph.addEdge(4, 6, 8);
        graph.addEdge(5, 7, 9);

        /*     1    2    3
         *    1 --- 2-- 3 --- 4
         *         4| /     / |8
         *          |/5    /6 |
         *          5 --- 7 -- 6
         *             9    7
         * */

        MinimumSpanningTree minimumSpanningTree = new MinimumSpanningTree();
        minimumSpanningTree.kruskalMst(graph);

        Set<Edge> expected =
                Set.of(
                        graph.getEdge(1, 2).get(),
                        graph.getEdge(2, 3).get(),
                        graph.getEdge(3, 4).get(),
                        graph.getEdge(2, 5).get(),
                        graph.getEdge(4, 7).get(),
                        graph.getEdge(7, 6).get());
        assertEquals(expected.size(), minimumSpanningTree.minimumSpanningTree.size());
        System.out.println(minimumSpanningTree.minimumSpanningTree);
        /*
         * [edge : (GraphNode(1), GraphNode(2)) weight=1,
         * edge : (GraphNode(2), GraphNode(3)) weight=2,
         * edge : (GraphNode(3), GraphNode(4)) weight=3,
         * edge : (GraphNode(2), GraphNode(5)) weight=4,
         * edge : (GraphNode(4), GraphNode(7)) weight=6,
         * edge : (GraphNode(7), GraphNode(6)) weight=7]
         */
        for (var expectedEdge : expected)
            assertTrue(minimumSpanningTree.minimumSpanningTree.contains(expectedEdge));
    }

    @Test
    void primTest() {
        Graph<String> graph = new UndirectedGraph<>();
        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addNode("D");
        graph.addNode("E");
        graph.addNode("F");
        graph.addEdge("A", "B", 1);
        graph.addEdge("B", "C", 2);
        graph.addEdge("C", "D", 3);
        graph.addEdge("D", "E", 4);
        graph.addEdge("E", "F", 5);
        graph.addEdge("F", "A", 6);
        graph.addEdge("B", "F", 7);
        graph.addEdge("C", "E", 8);
        graph.addEdge("B", "E", 9);
        graph.addEdge("C", "F", 10);

        /*
              B  --2--    C
          1/  |  \        | \3
        A     |7   \     8|  D
          6\  |      \9   | /4
              F   --5--  E

        w(C,F) = 10
         */

        MinimumSpanningTree minimumSpanningTree = new MinimumSpanningTree();
        minimumSpanningTree.primMst(graph, graph.getNode("A"));

        Set<Edge> expected =
                Set.of(
                        graph.getEdge("A", "B").get(),
                        graph.getEdge("B", "C").get(),
                        graph.getEdge("C", "D").get(),
                        graph.getEdge("D", "E").get(),
                        graph.getEdge("E", "F").get());
        assertEquals(expected.size(), minimumSpanningTree.minimumSpanningTree.size());
        System.out.println(minimumSpanningTree.minimumSpanningTree);
        for (var expectedEdge : expected)
            assertTrue(minimumSpanningTree.minimumSpanningTree.contains(expectedEdge));
    }

    @Test
    void primTest2() {
        Graph<String> graph = new UndirectedGraph<>();
        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addNode("D");
        graph.addNode("E");
        graph.addNode("F");
        graph.addEdge("A", "B", 1);
        graph.addEdge("B", "C", 6);
        graph.addEdge("C", "D", 7);
        graph.addEdge("D", "E", 9);
        graph.addEdge("E", "F", 8);
        graph.addEdge("F", "A", 10);
        graph.addEdge("B", "F", 2);
        graph.addEdge("C", "E", 5);
        graph.addEdge("B", "E", 3);
        graph.addEdge("C", "F", 4);

        /*
              B  --6--    C
          1/  |  \        | \7
        A     |2   \     5|  D
         10\  |      \3   | /9
              F   --8--  E

        w(C,F) = 4
         */

        MinimumSpanningTree minimumSpanningTree = new MinimumSpanningTree();
        minimumSpanningTree.primMst(graph, graph.getNode("A"));

        Set<Edge> expected =
                Set.of(
                        graph.getEdge("A", "B").get(),
                        graph.getEdge("B", "F").get(),
                        graph.getEdge("B", "E").get(),
                        graph.getEdge("F", "C").get(),
                        graph.getEdge("C", "D").get());
        assertEquals(expected.size(), minimumSpanningTree.minimumSpanningTree.size());
        System.out.println(minimumSpanningTree.minimumSpanningTree);
        for (var expectedEdge : expected)
            assertTrue(minimumSpanningTree.minimumSpanningTree.contains(expectedEdge));
    }
}
