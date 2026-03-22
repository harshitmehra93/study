package study.neetcode.coreskills.graph;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.*;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import study.model.Edge;
import study.model.GraphNode;
import study.model.exceptions.GraphException;
import study.utils.GraphUtils;

public class DirectedIntegerGraphTest {

    private Graph<Integer> graph;

    @BeforeEach
    void setup() {
        graph = new DirectedGraph<>();
    }

    @Test
    void createGraph() {
        assertEquals(0, graph.getSize());
    }

    @Test
    public void addNullNode_shouldThrow() {
        assertThrows(GraphException.class, () -> graph.addNode(null));
    }

    @Test
    public void addGraphNode() {
        graph.addNode(1);
        assertEquals(1, graph.getSize());
    }

    @Test
    public void addGraphNode_getNode() {
        graph.addNode(1);

        assertEquals(1, graph.getSize());
        GraphNode node = graph.getNode(1);
        assertNotNull(node);
        assertEquals(1, node.getValue());
    }

    @Test
    public void addDuplicate_throws() {
        graph.addNode(1);
        assertThrows(GraphException.class, () -> graph.addNode(1));
    }

    @Test
    public void addTwoGraphNodes_getNodes() {
        graph.addNode(1);
        graph.addNode(2);

        assertEquals(2, graph.getSize());

        GraphNode node1 = graph.getNode(1);
        assertNotNull(node1);
        assertEquals(1, node1.getValue());

        GraphNode node2 = graph.getNode(1);
        assertNotNull(node2);
        assertEquals(1, node2.getValue());
    }

    @Test
    public void emptyGraph_AddEdge_nullNodes_shouldThrow() {
        assertThrows(GraphException.class, () -> graph.addEdge(null, 2));
    }

    @Test
    public void emptyGraph_AddEdge_shouldThrow() {
        assertThrows(GraphException.class, () -> graph.addEdge(1, 2));
    }

    @Test
    public void existingEdge_addAgain_shouldThrow() {
        graph.addNode(1);
        graph.addNode(2);
        graph.addEdge(1, 2);

        assertThrows(GraphException.class, () -> graph.addEdge(1, 2));
        assertDoesNotThrow(() -> graph.addEdge(2, 1));
        assertEquals(2, graph.getSize());
        GraphNode<Integer> node1 = graph.getNode(1);
        GraphNode<Integer> node2 = graph.getNode(2);

        assertTrue(graph.getNeighbours(node1.getValue()).contains(node2));
        assertTrue(graph.getNeighbours(node2.getValue()).contains(node1));
    }

    @Test
    void sameNode_addEdge_shouldThrow() {
        graph.addNode(1);
        assertThrows(GraphException.class, () -> graph.addEdge(1, 1));
    }

    @Test
    public void addEdge_happy() {
        graph.addNode(1);
        graph.addNode(2);

        graph.addEdge(1, 2);

        GraphNode<Integer> node1 = graph.getNode(1);
        GraphNode<Integer> node2 = graph.getNode(2);

        assertEquals(2, graph.getSize());

        // node 1 validation
        assertNotNull(node1);
        assertEquals(1, node1.getValue());
        Set<GraphNode<Integer>> adjListOfNode1 = graph.getNeighbours(node1.getValue());
        assertNotNull(adjListOfNode1);
        assertEquals(1, adjListOfNode1.size());
        assertTrue(adjListOfNode1.contains(node2));

        // node 2 validation
        assertNotNull(node2);
        assertEquals(2, node2.getValue());
        Set<GraphNode<Integer>> adjListOfNode2 = graph.getNeighbours(node2.getValue());
        assertNotNull(adjListOfNode2);
        assertEquals(0, adjListOfNode2.size());
        assertFalse(adjListOfNode2.contains(node1));
    }

    @Test
    void emptyGraph_removeEdge_throws() {
        assertThrows(GraphException.class, () -> graph.removeEdge(1, 2));
    }

    @Test
    void nullNode_removeEdge_throws() {
        assertThrows(GraphException.class, () -> graph.removeEdge(null, 2));
    }

    @Test
    void nonExistentEdge_removeEdge_throws() {
        graph.addNode(1);
        graph.addNode(2);

        assertThrows(GraphException.class, () -> graph.removeEdge(1, 2));
        assertThrows(GraphException.class, () -> graph.removeEdge(2, 1));
    }

    @Test
    void removeEdge_happy() {
        // Arrange
        graph.addNode(1);
        graph.addNode(2);
        graph.addEdge(1, 2);

        // Act
        graph.removeEdge(1, 2);

        // Assert
        assertEquals(2, graph.getSize());

        var node1 = graph.getNode(1);
        var node2 = graph.getNode(2);
        assertNotNull(node1);
        assertNotNull(node2);

        var adjListNode1 = graph.getNeighbours(node1.getValue());
        var adjListNode2 = graph.getNeighbours(node2.getValue());
        assertNotNull(adjListNode1);
        assertNotNull(adjListNode2);
        assertEquals(0, adjListNode1.size());
        assertEquals(0, adjListNode2.size());
        assertFalse(adjListNode1.contains(node2));
        assertFalse(adjListNode2.contains(node1));
    }

    @Test
    void removeEdge_happy_2() {
        // Arrange
        graph.addNode(1);
        graph.addNode(2);
        graph.addNode(3);
        graph.addNode(4);
        graph.addNode(5);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(2, 5);
        // 1->2->3->4
        //    |
        //    \/
        //     5

        // Act
        graph.removeEdge(1, 2);
        // 1 2->3->4
        //    |
        //    \/
        //     5

        // Assert
        assertEquals(5, graph.getSize());

        var node1 = graph.getNode(1);
        var node2 = graph.getNode(2);
        var node3 = graph.getNode(3);
        var node4 = graph.getNode(4);
        var node5 = graph.getNode(5);
        assertNotNull(node1);
        assertNotNull(node2);
        assertNotNull(node3);
        assertNotNull(node4);
        assertNotNull(node5);

        var adjListNode1 = graph.getNeighbours(node1.getValue());
        var adjListNode2 = graph.getNeighbours(node2.getValue());
        var adjListNode3 = graph.getNeighbours(node3.getValue());
        var adjListNode4 = graph.getNeighbours(node4.getValue());
        var adjListNode5 = graph.getNeighbours(node5.getValue());
        assertNotNull(adjListNode1);
        assertNotNull(adjListNode2);
        assertNotNull(adjListNode3);
        assertNotNull(adjListNode4);
        assertNotNull(adjListNode5);

        // node 1
        assertEquals(0, adjListNode1.size());
        assertFalse(adjListNode1.contains(node2));

        // node 2
        assertEquals(2, adjListNode2.size());
        assertTrue(adjListNode2.contains(node5));
        assertTrue(adjListNode2.contains(node3));

        // node 3
        assertEquals(1, adjListNode3.size());
        assertTrue(adjListNode3.contains(node4));

        // node 4
        assertEquals(0, adjListNode4.size());

        // node 5
        assertEquals(0, adjListNode5.size());
    }

    @Test
    void equality() {
        var node1 = new GraphNode(1);
        var node2 = new GraphNode(1);
        assertTrue(node1.equals(node2));
        assertTrue(node1.hashCode() == node2.hashCode());

        Set<GraphNode> set = new HashSet<>();
        set.add(node1);
        set.add(node2);
        assertEquals(1, set.size());
    }

    @Test
    void nullNode_getNeighbours_throws() {
        assertThrows(GraphException.class, () -> graph.getNeighbours(null));
    }

    @Test
    void NonExistentNode_getNeighbours_throws() {
        assertThrows(GraphException.class, () -> graph.getNeighbours(1));
    }

    @Test
    void getNeighbours_happy() {
        graph.addNode(1);
        graph.addNode(2);

        var neightbours = graph.getNeighbours(1);

        assertNotNull(neightbours);
        assertEquals(0, neightbours.size());
    }

    @Test
    void getNeighbours_happy_2() {
        graph.addNode(1);
        graph.addNode(2);
        graph.addNode(3);
        graph.addNode(4);
        graph.addNode(5);

        graph.addEdge(1, 4);
        graph.addEdge(1, 2);
        graph.addEdge(1, 5);
        graph.addEdge(1, 3);

        var neighbours = graph.getNeighbours(1);

        assertNotNull(neighbours);
        System.out.println(neighbours);
        assertEquals(4, neighbours.size());
        GraphUtils.assertSetIsSorted(neighbours);
    }

    @Test
    void getNeighbours_happy_3() {
        final int GRAPH_SIZE = 100;
        graph = GraphUtils.buildRandomGraph(graph, GRAPH_SIZE);

        var node = GraphUtils.getRandomNode(graph, GRAPH_SIZE);
        var neighbours = graph.getNeighbours(node.getValue());
        GraphUtils.assertSetIsSorted(neighbours);
    }

    @Test
    void test_getEdge() {
        graph.addNode(1);
        graph.addNode(2);
        graph.addEdge(1, 2);

        assertEquals(2, graph.getSize());
        assertTrue(graph.getNeighbours(1).contains(graph.getNode(2)));

        Edge<Integer> edge = graph.getEdge(1, 2).get();
        assertEquals(1, edge.vertice1.getValue());
        assertEquals(2, edge.vertice2.getValue());
        assertEquals(1, graph.getEdges().size());

        graph.removeEdge(1, 2);

        assertTrue(graph.getEdge(1, 2).isEmpty());
        assertEquals(0, graph.getEdges().size());
    }

    @Test
    void getEdge_nulls() {
        graph.addNode(1);
        graph.addNode(2);
        graph.addEdge(1, 2);

        assertThrows(GraphException.class, () -> graph.getEdge(null, null));
    }

    @Test
    void getEdge_doesNotExist() {
        graph.addNode(1);
        graph.addNode(2);

        assertTrue(graph.getEdge(1, 2).isEmpty());
    }

    @Test
    void bfs_happy() {
        graph = GraphUtils.build20NodeGraph(graph);
        /*
         *
         *
         *          ----> 18 <- 1
         *        /      |  |\  |
         *        |     \|   \  |
         *        |     19    \ |/
         *        |     ^       8 ----> 3
         *        |     |     / |     /
         *        |     5    /  |/  |/
         *        |     ^   /     11
         *        |     | |/
         * 14 <-- 2 <-- 12
         *
         *        9   10
         * */

        List<GraphNode<Integer>> result = graph.bfs(8);

        assertEquals(9, result.size());
        List<Integer> expectedList = List.of(8, 3, 11, 12, 18, 2, 5, 19, 14);
        GraphUtils.assertListsAreSame(result, expectedList);
    }

    @Test
    void dfs_nodeDoesNotExist_throws() {
        DfsTraversal<Integer> traversal = new DfsTraversal<>();
        traversal.dfsTraversalIterative(graph);
        assertEquals(0, traversal.result.size());
    }

    @Test
    void dfs_nodeExist_returnsList() {
        graph.addNode(1);

        DfsTraversal<Integer> traversal = new DfsTraversal<>();
        traversal.dfsTraversalIterative(graph);

        GraphUtils.assertListsAreSame(traversal.result, List.of(1));
    }

    @Test
    void dfs_happy() {
        graph.addNode(1);
        graph.addNode(2);
        graph.addEdge(1, 2);

        DfsTraversal<Integer> traversal = new DfsTraversal<>();
        traversal.dfsTraversalIterative(graph);

        GraphUtils.assertListsAreSame(traversal.result, List.of(1, 2));
    }

    @Test
    void dfs_happy_2() {
        graph = GraphUtils.buildSmallTree(graph);

        DfsTraversal<Integer> traversal = new DfsTraversal<>();
        traversal.dfsTraversalIterative(graph);

        GraphUtils.assertListsAreSame(traversal.result, List.of(1, 2, 4, 5, 3));
    }

    @Test
    void dfs_happy_3() {
        graph = GraphUtils.buildSmallTree(graph);
        graph.addEdge(5, 1); // make a cycle

        DfsTraversal<Integer> traversal = new DfsTraversal<>();
        traversal.dfsTraversalIterative(graph);

        GraphUtils.assertListsAreSame(traversal.result, List.of(1, 2, 4, 5, 3));
    }

    @Test
    void connectedComponents() {
        graph.addNode(1);
        graph.addNode(2);
        graph.addNode(3);
        graph.addNode(4);
        graph.addNode(5);
        graph.addNode(6);
        graph.addNode(7);
        graph.addNode(8);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        //        graph.addEdge(5,2);
        graph.addEdge(5, 6);
        graph.addEdge(6, 7);
        graph.addEdge(7, 8);
        /*
         *   1 -> 2       5 -> 6
         *        |            |
         *        \/           \/
         *   4 <- 3       8 <- 7
         *
         * */

        DfsTraversal<Integer> dfsTraversal = new DfsTraversal<>();
        dfsTraversal.dfsTraversal(graph);

        var result =
                dfsTraversal.connectedComponents.entrySet().stream()
                        .collect(
                                Collectors.groupingBy(
                                        entry -> entry.getValue(),
                                        Collectors.mapping(
                                                entry -> entry.getKey(), Collectors.toList())));
        assertEquals(2, result.size());
        var connectedComponent1 = result.get(1);
        assertTrue(connectedComponent1.contains(graph.getNode(1)));
        assertTrue(connectedComponent1.contains(graph.getNode(2)));
        assertTrue(connectedComponent1.contains(graph.getNode(3)));
        assertTrue(connectedComponent1.contains(graph.getNode(4)));

        var connectedComponent2 = result.get(2);
        assertTrue(connectedComponent2.contains(graph.getNode(5)));
        assertTrue(connectedComponent2.contains(graph.getNode(6)));
        assertTrue(connectedComponent2.contains(graph.getNode(7)));
        assertTrue(connectedComponent2.contains(graph.getNode(8)));
    }

    @Test
    void topologicalSort() {
        Graph<String> graph = new DirectedGraph<String>();
        graph.addNode("undershorts");
        graph.addNode("pants");
        graph.addNode("belt");
        graph.addNode("shirt");
        graph.addNode("tie");
        graph.addNode("jacket");
        graph.addNode("socks");
        graph.addNode("shoes");
        graph.addNode("watch");
        graph.addEdge("undershorts", "pants");
        graph.addEdge("pants", "belt");
        graph.addEdge("belt", "jacket");
        graph.addEdge("shirt", "belt");
        graph.addEdge("shirt", "tie");
        graph.addEdge("tie", "jacket");
        graph.addEdge("undershorts", "shoes");
        graph.addEdge("pants", "shoes");
        graph.addEdge("socks", "shoes");

        DfsTraversal<String> traversal = new DfsTraversal<>();
        traversal.topologicalSort(graph);

        // [GraphNode(undershorts), GraphNode(socks), GraphNode(watch), GraphNode(pants),
        // GraphNode(shoes), GraphNode(shirt), GraphNode(belt), GraphNode(tie), GraphNode(jacket)]
        LinkedList<GraphNode<String>> result = traversal.topologicalSortResult;
        assertEquals("undershorts", result.get(0).getValue());
        assertEquals("socks", result.get(1).getValue());
        assertEquals("watch", result.get(2).getValue());
        assertEquals("pants", result.get(3).getValue());
        assertEquals("shoes", result.get(4).getValue());
        assertEquals("shirt", result.get(5).getValue());
        assertEquals("belt", result.get(6).getValue());
        assertEquals("tie", result.get(7).getValue());
        assertEquals("jacket", result.get(8).getValue());

        GraphUtils.printGraph(graph);
    }

    @Test
    void simplePathTest() {
        Graph<String> G = simplePathsSetup();

        DfsTraversal traversal = new DfsTraversal();
        traversal.topologicalSort(G);

        // expected - p, n, o, s, m, r, y, v, x, w, z, u, q, t.
        assertListsAreSame(
                traversal.topologicalSortResult,
                List.of("p", "n", "o", "s", "m", "r", "y", "v", "x", "w", "z", "u", "q", "t"));
    }

    Graph<String> simplePathsSetup() {
        Graph graph = new DirectedGraph<String>();
        // cormen page 615 fig 22.8
        graph.addNode("m");
        graph.addNode("t");
        graph.addNode("x");
        graph.addNode("n");
        graph.addNode("q");
        graph.addNode("u");
        graph.addNode("o");
        graph.addNode("r");
        graph.addNode("y");
        graph.addNode("v");
        graph.addNode("s");
        graph.addNode("p");
        graph.addNode("z");
        graph.addNode("w");

        graph.addEdge("m", "x");
        graph.addEdge("m", "q");
        graph.addEdge("m", "r");

        graph.addEdge("q", "t");

        graph.addEdge("n", "q");
        graph.addEdge("n", "u");
        graph.addEdge("n", "o");

        graph.addEdge("u", "t");

        graph.addEdge("r", "u");
        graph.addEdge("r", "y");

        graph.addEdge("y", "v");

        graph.addEdge("o", "r");
        graph.addEdge("o", "s");

        graph.addEdge("v", "x");
        graph.addEdge("v", "w");

        graph.addEdge("s", "r");

        graph.addEdge("p", "o");
        graph.addEdge("p", "s");
        graph.addEdge("p", "z");

        graph.addEdge("w", "z");

        return graph;
    }

    public <T extends Comparable> void assertListsAreSame(
            List<GraphNode<T>> result, List<T> expectedList) {
        assertEquals(expectedList.size(), result.size());
        Iterator<GraphNode<T>> resultIt = result.iterator();
        Iterator<T> expectedIt = expectedList.iterator();
        while (resultIt.hasNext()) {
            assertEquals(expectedIt.next(), resultIt.next().getValue());
        }
    }
}
