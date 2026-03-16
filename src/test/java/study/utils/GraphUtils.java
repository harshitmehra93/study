package study.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;
import study.model.exceptions.GraphException;
import study.model.GraphNode;
import study.neetcode.coreskills.graph.Graph;

public class GraphUtils {
    /*
     *        1
     *       / \
     *      2   3
     *     / \
     *    4   5
     * */
    public static Graph<Integer> buildSmallTree(Graph<Integer> graph) {
        graph.addNode(1);
        graph.addNode(2);
        graph.addNode(3);
        graph.addNode(4);
        graph.addNode(5);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(2, 5);
        return graph;
    }

    public static void assertListsAreSame(
            List<GraphNode<Integer>> result, List<Integer> expectedList) {
        assertEquals(expectedList.size(), result.size());
        Iterator<GraphNode<Integer>> resultIt = result.iterator();
        Iterator<Integer> expectedIt = expectedList.iterator();
        while (resultIt.hasNext()) {
            assertEquals(expectedIt.next(), resultIt.next().getValue());
        }
    }

    public static Graph<Integer> buildRandomGraph(Graph<Integer> graph, int GRAPH_MAX_SIZE) {
        graph.clear();
        IntStream s =
                IntStream.generate(() -> (int) (Math.random() * GRAPH_MAX_SIZE))
                        .limit(GRAPH_MAX_SIZE);
        s.forEach(
                i -> {
                    try {
                        graph.addNode(i);
                    } catch (GraphException g) {
                    }
                });
        for (int i = 0; i < GRAPH_MAX_SIZE; i++) addRandomEdge(graph, GRAPH_MAX_SIZE);
        printGraph(graph);
        return graph;
    }

    /*
     *
     *
     *          ----- 18 - 1
     *        /     /    \ |
     *        |     19    \|
     *        |     |      8 --- 3
     *        |     |    / |   /
     *        |     5   /  |  /
     *        |     |  /   11
     *  14 -- 2 --- 12
     *
     *        9   10
     * */
    public static Graph<Integer> build20NodeGraph(Graph<Integer> graph) {
        graph.clear();
        for (int i = 1; i < 20; i++) graph.addNode(i);
        graph.addEdge(1, 18);
        graph.addEdge(1, 8);
        graph.addEdge(8, 3);
        graph.addEdge(8, 11);
        graph.addEdge(8, 12);
        graph.addEdge(8, 18);
        graph.addEdge(3, 11);
        graph.addEdge(12, 2);
        graph.addEdge(12, 5);
        graph.addEdge(2, 18);
        graph.addEdge(2, 14);
        graph.addEdge(5, 19);
        graph.addEdge(18, 19);
        return graph;
    }

    public static void printGraph(Graph graph) {
        Set<GraphNode<Integer>> nodes = graph.getGraphNodes();
        nodes.stream()
                .forEach(
                        n -> {
                            System.out.println(n.getValue() + " -> " + n.getAdjacencyList());
                        });
    }

    public static void addRandomEdge(Graph<Integer> graph, int MAX_INT) {
        GraphNode node1 = getRandomNode(graph, MAX_INT);
        GraphNode node2 = getRandomNode(graph, MAX_INT);
        try {
            graph.addEdge(node1, node2);
        } catch (GraphException g) {
        }
    }

    public static GraphNode<Integer> getRandomNode(Graph<Integer> graph, int SET_SIZE) {
        GraphNode<Integer> node = null;
        for (int i = (int) (Math.random() * SET_SIZE); i < SET_SIZE; i++) {
            try {
                node = graph.getNode(i);
            } catch (GraphException g) {
                if (i == SET_SIZE - 1) {
                    i = 0; // restart from 0 if node is not found
                }
                continue;
            }
            break;
        }
        return node;
    }

    public static void assertSetIsSorted(Set neighbours) {
        Iterator<GraphNode<Integer>> it = neighbours.iterator();
        isSorted(it);
    }

    public static void assertListIsSorted(List neighbours) {
        Iterator<GraphNode<Integer>> it = neighbours.iterator();
        isSorted(it);
    }

    public static void isSorted(Iterator<GraphNode<Integer>> it) {
        var node1 = it.hasNext() ? it.next() : null;
        while (it.hasNext()) {
            var node2 = it.next();
            assertTrue(node1.getValue() <= node2.getValue());
            node1 = node2;
        }
    }
}
