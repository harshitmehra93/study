package study.neetcode.coreskills.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import study.model.*;
import study.utils.GraphUtils;

public class EdgeClassificationTest {

    private Graph<Integer> graph;
    private DfsTraversal<Integer> dfsTraversal;

    @BeforeEach
    void setup() {
        graph = new DirectedGraph<>();
        dfsTraversal = new DfsTraversal();
    }

    @Test
    void test() {
        graph = GraphUtils.buildSmallTree(graph);
        /*
         *        1
         *       / \
         *      2   3
         *     / \
         *    4   5
         * */
        dfsTraversal = new DfsTraversal();

        dfsTraversal.dfsTraversal(graph);

        System.out.println(dfsTraversal.result);
        GraphUtils.assertListsAreSame(dfsTraversal.result, List.of(1, 2, 4, 5, 3));
    }

    @Test
    void dfs_nodeDoesNotExist_throws() {
        graph.clear();

        dfsTraversal.dfsTraversal(graph);

        assertEquals(0, dfsTraversal.result.size());
    }

    @Test
    void dfs_nodeExist_returnsList() {
        graph.addNode(1);

        dfsTraversal.dfsTraversal(graph);

        GraphUtils.assertListsAreSame(dfsTraversal.result, List.of(1));
    }

    @Test
    void dfs_happy() {
        graph.addNode(1);
        graph.addNode(2);
        graph.addEdge(1, 2);

        dfsTraversal.dfsTraversal(graph);

        GraphUtils.assertListsAreSame(dfsTraversal.result, List.of(1, 2));
    }

    @Test
    void dfs_happy_2() {
        graph = GraphUtils.buildSmallTree(graph);

        dfsTraversal.dfsTraversal(graph);

        GraphUtils.assertListsAreSame(dfsTraversal.result, List.of(1, 2, 4, 5, 3));
    }

    @Test
    void dfs_happy_3() {
        graph = GraphUtils.buildSmallTree(graph);
        graph.addEdge(5, 1); // make a cycle

        dfsTraversal.dfsTraversal(graph);

        GraphUtils.assertListsAreSame(dfsTraversal.result, List.of(1, 2, 4, 5, 3));
    }

    @Test
    void test_2() {
        graph = GraphUtils.buildSmallTree(graph);
        graph.addEdge(3, 5);
        /*
         *          1
         *        |  |
         *      |/   \|
         *      2    3
         *     / \    |
         *    |/  \|  |/
         *    4      5
         * */

        dfsTraversal.dfsTraversal(graph);
        dfsTraversal.printResult();

        GraphUtils.assertListsAreSame(dfsTraversal.result, List.of(1, 2, 4, 5, 3));

        assertEquals(
                EdgeType.TREE_EDGE, dfsTraversal.classification.get(graph.getEdge(1, 3).get()));
        assertEquals(
                EdgeType.TREE_EDGE, dfsTraversal.classification.get(graph.getEdge(1, 2).get()));
        assertEquals(
                EdgeType.CROSS_EDGE, dfsTraversal.classification.get(graph.getEdge(3, 5).get()));
        assertEquals(
                EdgeType.TREE_EDGE, dfsTraversal.classification.get(graph.getEdge(2, 5).get()));
        assertEquals(
                EdgeType.TREE_EDGE, dfsTraversal.classification.get(graph.getEdge(2, 4).get()));
    }

    @Test
    void test_3() {
        graph = GraphUtils.buildSmallTree(graph);
        graph.addEdge(3, 5);
        /*
         *         1
         *       /  \
         *      2    3
         *     / \  /
         *    4   5
         * */

        dfsTraversal.dfsTraversal(graph);
        dfsTraversal.printResult();

        var classification = dfsTraversal.classification;

        assertEquals(5, classification.keySet().size());
        assertEquals(EdgeType.TREE_EDGE, classification.get(graph.getEdge(1, 3).get()));
        assertEquals(EdgeType.TREE_EDGE, classification.get(graph.getEdge(1, 2).get()));
        assertEquals(EdgeType.CROSS_EDGE, classification.get(graph.getEdge(3, 5).get()));
        assertEquals(EdgeType.TREE_EDGE, classification.get(graph.getEdge(2, 5).get()));
        assertEquals(EdgeType.TREE_EDGE, classification.get(graph.getEdge(2, 4).get()));
    }

    @Test
    void test_4() {
        graph.addNode(1);
        graph.addNode(2);
        graph.addNode(4);
        graph.addNode(5);
        graph.addNode(8);
        graph.addEdge(1, 2);
        graph.addEdge(2, 4);
        graph.addEdge(5, 1);
        graph.addEdge(1, 8);
        graph.addEdge(2, 5);
        graph.addEdge(5, 8);
        /*
         *         1
         *       / | \
         *      2 -5- 8
         *     /
         *    4
         * */

        DfsTraversal dfsTraversal = new DfsTraversal();
        dfsTraversal.dfsTraversal(graph);
        dfsTraversal.printResult();
        var classification = dfsTraversal.classification;

        assertEquals(EdgeType.TREE_EDGE, classification.get(graph.getEdge(1, 2).get()));
        assertEquals(EdgeType.TREE_EDGE, classification.get(graph.getEdge(2, 4).get()));
        assertEquals(EdgeType.TREE_EDGE, classification.get(graph.getEdge(2, 5).get()));
        assertEquals(EdgeType.BACK_EDGE, classification.get(graph.getEdge(5, 1).get()));
        assertEquals(EdgeType.TREE_EDGE, classification.get(graph.getEdge(5, 8).get()));
        assertEquals(EdgeType.FORWARD_EDGE, classification.get(graph.getEdge(1, 8).get()));
    }

    @Test
    void test_5() {
        graph.addNode(1);
        graph.addNode(2);
        graph.addNode(4);
        graph.addNode(5);
        graph.addNode(8);
        graph.addEdge(1, 2);
        graph.addEdge(2, 4);
        graph.addEdge(1, 5);
        graph.addEdge(1, 8);
        graph.addEdge(4, 5);
        /*
         *         1
         *       / | \
         *      2  5  8
         *      | /
         *      4
         * */

        DfsTraversal dfsTraversal = new DfsTraversal();
        dfsTraversal.dfsTraversal(graph);
        dfsTraversal.printResult();
        var classification = dfsTraversal.classification;

        assertEquals(5, classification.keySet().size());
        assertEquals(EdgeType.TREE_EDGE, classification.get(graph.getEdge(1, 2).get()));
        assertEquals(EdgeType.TREE_EDGE, classification.get(graph.getEdge(2, 4).get()));
        assertEquals(EdgeType.TREE_EDGE, classification.get(graph.getEdge(4, 5).get()));
        assertEquals(EdgeType.FORWARD_EDGE, classification.get(graph.getEdge(1, 5).get()));
        assertEquals(EdgeType.TREE_EDGE, classification.get(graph.getEdge(1, 8).get()));
    }
}
