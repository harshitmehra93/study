package study.neetcode.coreskills.graph;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import study.model.Graph;
import study.model.GraphException;
import study.model.GraphNodeBase;

import static org.junit.jupiter.api.Assertions.*;

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
        assertThrows(GraphException.class,()->graph.addNode(1));
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
    void removeEdge_happy(){
        // Arrange
        graph.addNode(1);
        graph.addNode(2);
        graph.addEdge(1,2);

        // Act
        graph.removeEdge(1,2);

        // Assert
        assertEquals(2,graph.getSize());

        var node1 = graph.getNode(1);
        var node2 = graph.getNode(2);
        assertNotNull(node1);
        assertNotNull(node2);

        var adjListNode1 = node1.getAdjacencyList();
        var adjListNode2 = node2.getAdjacencyList();
        assertNotNull(adjListNode1);
        assertNotNull(adjListNode2);
        assertEquals(0,adjListNode1.size());
        assertEquals(0,adjListNode2.size());
        assertFalse(adjListNode1.contains(node2));
        assertFalse(adjListNode2.contains(node1));
    }

    @Test
    void removeEdge_happy_2(){
        // Arrange
        graph.addNode(1);
        graph.addNode(2);
        graph.addNode(3);
        graph.addNode(4);
        graph.addNode(5);
        graph.addEdge(1,2);
        graph.addEdge(2,3);
        graph.addEdge(3,4);
        graph.addEdge(2,5);
        // 1-2-3-4
        //   |
        //   5

        // Act
        graph.removeEdge(1,2);
        // 1 2-3-4
        //   |
        //   5

        // Assert
        assertEquals(5,graph.getSize());

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
        assertEquals(0,adjListNode1.size());
        assertFalse(adjListNode1.contains(node2));

        // node 2
        assertEquals(2,adjListNode2.size());
        assertFalse(adjListNode2.contains(node1));
        assertTrue(adjListNode2.contains(node5));
        assertTrue(adjListNode2.contains(node3));

        // node 3
        assertEquals(2,adjListNode3.size());
        assertTrue(adjListNode3.contains(node2));
        assertTrue(adjListNode3.contains(node4));

        // node 4
        assertEquals(1,adjListNode4.size());
        assertTrue(adjListNode4.contains(node3));

        // node 5
        assertEquals(1,adjListNode5.size());
        assertTrue(adjListNode5.contains(node2));
    }

    @Test
    void equality(){
        var node1 = new IntegerGraphNode(1);
        var node2 = new IntegerGraphNode(1);
        assertTrue(node1.equals(node2));
        assertTrue(node1.hashCode()==node2.hashCode());

        Set<IntegerGraphNode> set = new HashSet<>();
        set.add(node1);
        set.add(node2);
        assertEquals(1,set.size());
    }
}
