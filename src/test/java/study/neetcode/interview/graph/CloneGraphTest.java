package study.neetcode.interview.graph;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import org.junit.jupiter.api.Test;

class CloneGraphTest {

    @Test
    public void cloneGraphTest() {
        // Build graph:
        // 1 -- 2
        // |    |
        // 4 -- 3

        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);

        node1.setNeighbours(Arrays.asList(node2, node4));
        node2.setNeighbours(Arrays.asList(node1, node3));
        node3.setNeighbours(Arrays.asList(node2, node4));
        node4.setNeighbours(Arrays.asList(node1, node3));

        CloneGraph cg = new CloneGraph();

        Node cloneNode = cg.cloneGraph(node1);

        // Basic checks
        assertNotNull(cloneNode);
        assertNotSame(node1, cloneNode);
        assertEquals(node1.val, cloneNode.val);

        // Deep check
        assertCloneIsCorrect(node1, cloneNode, new HashSet<>());
    }

    private void assertCloneIsCorrect(Node original, Node clone, Set<Node> visited) {
        if (visited.contains(original)) return;

        visited.add(original);

        assertNotSame(original, clone);
        assertEquals(original.val, clone.val);
        assertEquals(original.getNeighbours().size(), clone.getNeighbours().size());

        for (int i = 0; i < original.getNeighbours().size(); i++) {
            assertCloneIsCorrect(
                    original.getNeighbours().get(i), clone.getNeighbours().get(i), visited);
        }
    }
}
