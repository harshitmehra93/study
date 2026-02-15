package study.neetcode.coreskills.graph;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.util.stream.IntStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import study.model.Graph;
import study.model.GraphException;
import study.model.GraphNodeBase;

public class UndirectedIntegerGraphTest {
    private Graph graph;

    @BeforeEach
    void setUp() {
        graph = new UndirectedIntegerGraph();
    }

    @Test
    public void testGraph() {
        assertEquals(0, graph.getSize());
    }

    @Test
    public void testArgConstructor() {
        graph = new UndirectedIntegerGraph(10);
        assertEquals(10, graph.getSize());
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
        GraphNodeBase node = graph.getNode(1);
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

        GraphNodeBase node1 = graph.getNode(1);
        assertNotNull(node1);
        assertEquals(1, node1.getValue());

        GraphNodeBase node2 = graph.getNode(1);
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
        assertThrows(GraphException.class, () -> graph.addEdge(2, 1));
        assertEquals(2, graph.getSize());
        GraphNodeBase node1 = graph.getNode(1);
        GraphNodeBase node2 = graph.getNode(2);

        assertTrue(node1.getAdjacencyList().contains(node2));
        assertTrue(node2.getAdjacencyList().contains(node1));
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

        GraphNodeBase node1 = graph.getNode(1);
        GraphNodeBase node2 = graph.getNode(2);

        assertEquals(2, graph.getSize());

        // node 1 validation
        assertNotNull(node1);
        assertEquals(1, node1.getValue());
        Set<GraphNodeBase> adjListOfNode1 = node1.getAdjacencyList();
        assertNotNull(adjListOfNode1);
        assertEquals(1, adjListOfNode1.size());
        assertTrue(adjListOfNode1.contains(node2));

        // node 2 validation
        assertNotNull(node2);
        assertEquals(2, node2.getValue());
        Set<GraphNodeBase> adjListOfNode2 = node2.getAdjacencyList();
        assertNotNull(adjListOfNode2);
        assertEquals(1, adjListOfNode1.size());
        assertTrue(adjListOfNode2.contains(node1));
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

        var adjListNode1 = node1.getAdjacencyList();
        var adjListNode2 = node2.getAdjacencyList();
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
        // 1-2-3-4
        //   |
        //   5

        // Act
        graph.removeEdge(1, 2);
        // 1 2-3-4
        //   |
        //   5

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

        var adjListNode1 = node1.getAdjacencyList();
        var adjListNode2 = node2.getAdjacencyList();
        var adjListNode3 = node3.getAdjacencyList();
        var adjListNode4 = node4.getAdjacencyList();
        var adjListNode5 = node5.getAdjacencyList();
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
        assertFalse(adjListNode2.contains(node1));
        assertTrue(adjListNode2.contains(node5));
        assertTrue(adjListNode2.contains(node3));

        // node 3
        assertEquals(2, adjListNode3.size());
        assertTrue(adjListNode3.contains(node2));
        assertTrue(adjListNode3.contains(node4));

        // node 4
        assertEquals(1, adjListNode4.size());
        assertTrue(adjListNode4.contains(node3));

        // node 5
        assertEquals(1, adjListNode5.size());
        assertTrue(adjListNode5.contains(node2));
    }

    @Test
    void equality() {
        var node1 = new IntegerGraphNode(1);
        var node2 = new IntegerGraphNode(1);
        assertTrue(node1.equals(node2));
        assertTrue(node1.hashCode() == node2.hashCode());

        Set<IntegerGraphNode> set = new HashSet<>();
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
        assertSetIsSorted(neighbours);
    }

    @Test
    void getNeighbours_happy_3() {
        final int GRAPH_SIZE = 100;
        buildRandomGraph(GRAPH_SIZE);

        var node = getRandomNode(GRAPH_SIZE);
        var neighbours = graph.getNeighbours(node.getValue());
        assertSetIsSorted(neighbours);
    }

    @Test
    void bfs_happy() {
        buildRandomGraph(20);
        List<IntegerGraphNode> bfsOrder = graph.bfs(getRandomNode(10));
        System.out.println(bfsOrder);
    }

    @Test
    void bfs_happy_2() {
        build20NodeGraph();
        IntegerGraphNode node = (IntegerGraphNode) graph.getNode(3);

        List<IntegerGraphNode> result = graph.bfs(node);

        assertEquals(10, result.size());
        List<Integer> expectedList = List.of(3, 8, 11, 1, 12, 18, 2, 5, 19, 14);
        assertListsAreSame(result, expectedList);
    }

    @Test
    void bfs_happy_3() {
        build20NodeGraph();
        IntegerGraphNode node = (IntegerGraphNode) graph.getNode(9);

        List<IntegerGraphNode> result = graph.bfs(node);

        assertEquals(1, result.size());
        List<Integer> expectedList = List.of(9);
        assertListsAreSame(result, expectedList);
    }

    @Test
    void emptyGraph_findPath_throws() {
        assertThrows(GraphException.class, () -> graph.findPath(1, 2));
    }

    @Test
    void noPathExists_findPath_returnsEmptyList() {
        graph.addNode(1);
        graph.addNode(2);
        assertEquals(0, graph.findPath(1, 2).size());
    }

    @Test
    void pathExists_findPath_returnsPath() {
        graph.addNode(1);
        graph.addNode(2);
        graph.addEdge(1, 2);

        List<IntegerGraphNode> path = graph.findPath(1, 2);

        assertEquals(2, path.size());
        assertEquals(2, path.get(0).getValue());
        assertEquals(1, path.get(1).getValue());
    }

    @Test
    void pathExists_findPath_happy() {
        build20NodeGraph();

        List<IntegerGraphNode> path = graph.findPath(5, 19);

        assertListsAreSame(path, List.of(19, 5));
    }

    @Test
    void pathExists_findPath_happy_2() {
        build20NodeGraph();

        List<IntegerGraphNode> path = graph.findPath(5, 8);

        assertListsAreSame(path, List.of(8, 12, 5));
    }

    @Test
    void pathExists_findPath_happy_3() {
        build20NodeGraph();

        List<IntegerGraphNode> path = graph.findPath(14, 3);

        assertListsAreSame(path, List.of(3, 8, 18, 2, 14));
    }

    @Test
    void NoPathExists_throws() {
        build20NodeGraph();

        List<IntegerGraphNode> path = graph.findPath(9, 10);

        assertListsAreSame(path, List.of());
    }

    private static void assertListsAreSame(
            List<IntegerGraphNode> result, List<Integer> expectedList) {
        assertEquals(result.size(), expectedList.size());
        Iterator<IntegerGraphNode> resultIt = result.iterator();
        Iterator<Integer> expectedIt = expectedList.iterator();
        while (resultIt.hasNext()) {
            var expected = expectedIt.next();
            var actual = resultIt.next().getValue();
            assertEquals(expected, actual);
        }
    }

    private void buildRandomGraph(int GRAPH_MAX_SIZE) {
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
        for (int i = 0; i < GRAPH_MAX_SIZE; i++) addRandomEdge(GRAPH_MAX_SIZE);
        printGraph();
    }

    /*
     *    14
     *    |
     *    2 -- 18 -- 1
     *    |     |    |
     *    |    19    |
     *    |     |    8 -- 3
     *    |     5  / |  /
     *    |     | /  11
     *    2 --- 12
     *
     *    9   10
     * */

    private void build20NodeGraph() {
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
    }

    private void printGraph() {
        Set<IntegerGraphNode> nodes = graph.getGraphNodes();
        nodes.stream()
                .forEach(
                        n -> {
                            System.out.println(n.getValue() + " -> " + n.getAdjacencyList());
                        });
    }

    private void addRandomEdge(int MAX_INT) {
        GraphNodeBase node1 = getRandomNode(MAX_INT);
        GraphNodeBase node2 = getRandomNode(MAX_INT);
        try {
            graph.addEdge(node1, node2);
        } catch (GraphException g) {
        }
    }

    private IntegerGraphNode getRandomNode(int SET_SIZE) {
        IntegerGraphNode node = null;
        for (int i = (int) (Math.random() * SET_SIZE); i < SET_SIZE; i++) {
            try {
                node = (IntegerGraphNode) graph.getNode(i);
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

    private void assertSetIsSorted(Set neighbours) {
        Iterator<IntegerGraphNode> it = neighbours.iterator();
        isSorted(it);
    }

    private void assertListIsSorted(List neighbours) {
        Iterator<IntegerGraphNode> it = neighbours.iterator();
        isSorted(it);
    }

    private void isSorted(Iterator<IntegerGraphNode> it) {
        var node1 = it.hasNext() ? it.next() : null;
        while (it.hasNext()) {
            var node2 = it.next();
            assertTrue(node1.getValue() <= node2.getValue());
            node1 = node2;
        }
    }
}
