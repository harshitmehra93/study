package study.neetcode.interview.trees;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import study.neetcode.interview.trees.commons.TreeNode;

class CountGoodNodesInBinaryTreeTest {

    @Test
    void test_emptyTree_returnsZero() {
        CountGoodNodesInBinaryTree test = new CountGoodNodesInBinaryTree();

        assertEquals(0, test.goodNodes(null));
    }

    @Test
    void test_singleNode_returnsOne() {
        CountGoodNodesInBinaryTree test = new CountGoodNodesInBinaryTree();

        TreeNode root = new TreeNode(10);

        assertEquals(1, test.goodNodes(root));
    }

    @Test
    void test_givenExample_returnsFour() {
        CountGoodNodesInBinaryTree test = new CountGoodNodesInBinaryTree();

        /*
         *        3
         *       / \
         *      1   4
         *     /   / \
         *    3   1   5
         *
         * Good nodes: 3, 3, 4, 5
         */
        TreeNode root =
                new TreeNode(
                        3,
                        new TreeNode(1, new TreeNode(3), null),
                        new TreeNode(4, new TreeNode(1), new TreeNode(5)));

        assertEquals(4, test.goodNodes(root));
    }

    @Test
    void test_allIncreasingPath_allGood() {
        CountGoodNodesInBinaryTree test = new CountGoodNodesInBinaryTree();

        /*
         * 1
         *  \
         *   2
         *    \
         *     3
         *      \
         *       4
         *
         * Every node is >= max so far.
         */
        TreeNode root =
                new TreeNode(
                        1, null, new TreeNode(2, null, new TreeNode(3, null, new TreeNode(4))));

        assertEquals(4, test.goodNodes(root));
    }

    @Test
    void test_allDecreasingPath_onlyRootGood() {
        CountGoodNodesInBinaryTree test = new CountGoodNodesInBinaryTree();

        /*
         *      4
         *     /
         *    3
         *   /
         *  2
         * /
         *1
         *
         * Only root is good.
         */
        TreeNode root =
                new TreeNode(
                        4, new TreeNode(3, new TreeNode(2, new TreeNode(1), null), null), null);

        assertEquals(1, test.goodNodes(root));
    }

    @Test
    void test_equalToMaxSoFar_isGood() {
        CountGoodNodesInBinaryTree test = new CountGoodNodesInBinaryTree();

        /*
         *      3
         *     / \
         *    3   3
         *
         * Equal values count as good because node.val >= maxSoFar.
         */
        TreeNode root = new TreeNode(3, new TreeNode(3), new TreeNode(3));

        assertEquals(3, test.goodNodes(root));
    }

    @Test
    void test_negativeValues() {
        CountGoodNodesInBinaryTree test = new CountGoodNodesInBinaryTree();

        /*
         *       -1
         *       / \
         *     -2   0
         *     /
         *   -3
         *
         * Good nodes: -1, 0
         */
        TreeNode root = new TreeNode(-1, new TreeNode(-2, new TreeNode(-3), null), new TreeNode(0));

        assertEquals(2, test.goodNodes(root));
    }

    @Test
    void test_mixedTree() {
        CountGoodNodesInBinaryTree test = new CountGoodNodesInBinaryTree();

        /*
         *          5
         *        /   \
         *       3     7
         *      / \   / \
         *     6   2 6   8
         *
         * Good nodes:
         * 5 root
         * 6 on path 5->3->6
         * 7 on path 5->7
         * 8 on path 5->7->8
         *
         * Total = 4
         */
        TreeNode root =
                new TreeNode(
                        5,
                        new TreeNode(3, new TreeNode(6), new TreeNode(2)),
                        new TreeNode(7, new TreeNode(6), new TreeNode(8)));

        assertEquals(4, test.goodNodes(root));
    }
}
