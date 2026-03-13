package study.neetcode.coreskills.graph;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import study.model.Graph;
import study.model.GraphException;
import study.model.GraphNode;
import study.utils.GraphUtils;

public abstract class UndirectedIntegerGraphTest {
    protected Graph<Integer> graph;

    @BeforeEach
    void setUp() {
        setupTargetGraph();
    }

    abstract void setupTargetGraph();

    @Test
    public void testGraph() {
        assertEquals(0, graph.getSize());
    }

    //    @Test
    //    public void testArgConstructor() {
    //        graph = new UndirectedGraph(10);
    //        assertEquals(10, graph.getSize());
    //    }

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
        assertThrows(GraphException.class, () -> graph.addEdge(2, 1));
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
    void bfs_happy() {
        graph = GraphUtils.buildRandomGraph(graph, 20);
        List<GraphNode<Integer>> bfsOrder =
                graph.bfs(GraphUtils.getRandomNode(graph, 10).getValue());
        System.out.println(bfsOrder);
    }

    @Test
    void bfs_happy_2() {
        graph = GraphUtils.build20NodeGraph(graph);

        List<GraphNode<Integer>> result = graph.bfs(3);

        assertEquals(10, result.size());
        List<Integer> expectedList = List.of(3, 8, 11, 1, 12, 18, 2, 5, 19, 14);
        GraphUtils.assertListsAreSame(result, expectedList);
    }

    @Test
    void bfs_happy_3() {
        graph = GraphUtils.build20NodeGraph(graph);

        List<GraphNode<Integer>> result = graph.bfs(9);

        assertEquals(1, result.size());
        List<Integer> expectedList = List.of(9);
        GraphUtils.assertListsAreSame(result, expectedList);
    }

    @Test
    void bfs_happy_4() {
        graph = GraphUtils.buildSmallTree(graph);

        var result = graph.bfs(1);

        GraphUtils.assertListsAreSame(result, List.of(1, 2, 3, 4, 5));
    }

    @Test
    void emptyGraph_findShortestPath_throws() {
        assertThrows(GraphException.class, () -> graph.findShortestPath(1, 2));
    }

    @Test
    void noPathExists_findShortestPath_returnsNull() {
        graph.addNode(1);
        graph.addNode(2);
        assertNull(graph.findShortestPath(1, 2));
    }

    @Test
    void pathExists_findShortestPath_returnsPath() {
        graph.addNode(1);
        graph.addNode(2);
        graph.addEdge(1, 2);

        List<GraphNode<Integer>> path = graph.findShortestPath(1, 2);

        GraphUtils.assertListsAreSame(path, List.of(2, 1));
    }

    @Test
    void pathExists_sameNode_returnsSingleNodeList() {
        graph = GraphUtils.build20NodeGraph(graph);

        List<GraphNode<Integer>> path = graph.findShortestPath(8, 8);

        GraphUtils.assertListsAreSame(path, List.of(8));
    }

    @Test
    void NoPathExists_findShortestPath_returnsNull() {
        graph = GraphUtils.build20NodeGraph(graph);

        assertNull(graph.findShortestPath(9, 10));
    }

    @Test
    void pathExists_findShortestPath_happy() {
        graph = GraphUtils.build20NodeGraph(graph);

        List<GraphNode<Integer>> path = graph.findShortestPath(5, 19);

        GraphUtils.assertListsAreSame(path, List.of(19, 5));
    }

    @Test
    void pathExists_findShortestPath_happy_2() {
        graph = GraphUtils.build20NodeGraph(graph);

        List<GraphNode<Integer>> path = graph.findShortestPath(5, 8);

        GraphUtils.assertListsAreSame(path, List.of(8, 12, 5));
    }

    @Test
    void pathExists_findShortestPath_happy_3() {
        graph = GraphUtils.build20NodeGraph(graph);

        List<GraphNode<Integer>> path = graph.findShortestPath(14, 3);

        GraphUtils.assertListsAreSame(path, List.of(3, 8, 12, 2, 14));
    }

    @Test
    void dfs_nodeDoesNotExist_throws() {
        assertThrows(GraphException.class, () -> graph.dfs(1));
    }

    @Test
    void dfs_nodeExist_returnsList() {
        graph.addNode(1);

        List<GraphNode<Integer>> result = graph.dfs(1);

        GraphUtils.assertListsAreSame(result, List.of(1));
    }

    @Test
    void dfs_happy() {
        graph.addNode(1);
        graph.addNode(2);
        graph.addEdge(1, 2);

        var result = graph.dfs(1);

        GraphUtils.assertListsAreSame(result, List.of(1, 2));
    }

    @Test
    void dfs_happy_2() {
        graph = GraphUtils.buildSmallTree(graph);

        var result = graph.dfs(1);

        GraphUtils.assertListsAreSame(result, List.of(1, 2, 4, 5, 3));
    }

    @Test
    void dfs_happy_3() {
        graph = GraphUtils.buildSmallTree(graph);
        graph.addEdge(5, 1); // make a cycle

        var result = graph.dfs(1);

        GraphUtils.assertListsAreSame(result, List.of(1, 2, 4, 5, 3));
    }

    @Test
    void clearTest(){
        graph = GraphUtils.buildSmallTree(graph);
        assertEquals(6,graph.getSize());
        GraphNode<Integer> a = graph.getNode(1);

        GraphNode<Integer> b = graph.getNode(2);
        GraphNode<Integer> c = graph.getNode(3);
        List<GraphNode<Integer>> expectedNeighbours = List.of(b, c);
        Set<GraphNode<Integer>> neighbours = graph.getNeighbours(a.getValue());
        assertEquals(2, neighbours.size());
        for(GraphNode<Integer> nei : neighbours){
            assertTrue(expectedNeighbours.contains(nei));
        }

        graph.clear();
        assertEquals(0,graph.getSize());
        assertThrows(GraphException.class, ()->graph.getNode(1));
    }
}

class GraphWithAdjacencyListTest extends UndirectedIntegerGraphTest {
    void setupTargetGraph() {
        graph = new UndirectedGraph<Integer>();
    }
}

class GraphWithAdjacencyMatrixTest extends UndirectedIntegerGraphTest {
    void setupTargetGraph() {
        graph = new UndirectedGraphWithAdjMatrix<Integer>();
    }
}
