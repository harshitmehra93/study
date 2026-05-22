package study.neetcode.interview.graph;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GraphValidTreeTest {

    @Test
    void test_validTree_basic() {
        GraphValidTree test = new GraphValidTree();

        int n = 5;
        int[][] edges = {
            {0, 1},
            {0, 2},
            {0, 3},
            {1, 4}
        };

        assertTrue(test.validTree(n, edges));
    }

    @Test
    void test_invalidTree_hasCycle() {
        GraphValidTree test = new GraphValidTree();

        int n = 5;
        int[][] edges = {
            {0, 1},
            {1, 2},
            {2, 3},
            {1, 3},
            {1, 4}
        };

        assertFalse(test.validTree(n, edges));
    }

    @Test
    void test_invalidTree_disconnected() {
        GraphValidTree test = new GraphValidTree();

        int n = 5;
        int[][] edges = {
            {0, 1},
            {2, 3},
            {3, 4}
        };

        assertFalse(test.validTree(n, edges));
    }

    @Test
    void test_singleNode_noEdges_isValidTree() {
        GraphValidTree test = new GraphValidTree();

        int n = 1;
        int[][] edges = {};

        assertTrue(test.validTree(n, edges));
    }

    @Test
    void test_twoNodes_oneEdge_isValidTree() {
        GraphValidTree test = new GraphValidTree();

        int n = 2;
        int[][] edges = {{0, 1}};

        assertTrue(test.validTree(n, edges));
    }

    @Test
    void test_twoNodes_noEdge_isDisconnected() {
        GraphValidTree test = new GraphValidTree();

        int n = 2;
        int[][] edges = {};

        assertFalse(test.validTree(n, edges));
    }

    @Test
    void test_invalidTree_tooManyEdgesCycleMustExist() {
        GraphValidTree test = new GraphValidTree();

        int n = 4;
        int[][] edges = {
            {0, 1},
            {1, 2},
            {2, 3},
            {3, 0}
        };

        assertFalse(test.validTree(n, edges));
    }

    @Test
    void test_invalidTree_tooFewEdgesCannotBeConnected() {
        GraphValidTree test = new GraphValidTree();

        int n = 4;
        int[][] edges = {
            {0, 1},
            {2, 3}
        };

        assertFalse(test.validTree(n, edges));
    }

    @Test
    void test_validTree_chain() {
        GraphValidTree test = new GraphValidTree();

        int n = 5;
        int[][] edges = {
            {0, 1},
            {1, 2},
            {2, 3},
            {3, 4}
        };

        assertTrue(test.validTree(n, edges));
    }

    @Test
    void test_validTree_star() {
        GraphValidTree test = new GraphValidTree();

        int n = 5;
        int[][] edges = {
            {0, 1},
            {0, 2},
            {0, 3},
            {0, 4}
        };

        assertTrue(test.validTree(n, edges));
    }
}
