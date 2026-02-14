package study.neetcode.coreskills.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;
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
    public void AddEdge_happy() {
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
    void emptyGraph_removeEdge_throws2() {
        assertThrows(GraphException.class, () -> graph.removeEdge(1, 2));
    }
}
