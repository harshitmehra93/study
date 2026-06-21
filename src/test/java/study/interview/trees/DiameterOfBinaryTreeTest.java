package study.interview.trees;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import study.interview.commons.TreeNode;

class DiameterOfBinaryTreeTest {

    @Test
    void test_emptyTree_returnsZero() {
        DiameterOfBinaryTree test = new DiameterOfBinaryTree();

        assertEquals(0, test.diameterOfBinaryTree(null));
    }

    @Test
    void test_singleNode_returnsZero() {
        DiameterOfBinaryTree test = new DiameterOfBinaryTree();

        TreeNode root = new TreeNode(1);

        /*
         * One node has no edges.
         */
        assertEquals(0, test.diameterOfBinaryTree(root));
    }

    @Test
    void test_twoNodes_returnsOne() {
        DiameterOfBinaryTree test = new DiameterOfBinaryTree();

        /*
         *  1
         * /
         *2
         *
         * Longest path: 2 -> 1
         * Edges = 1
         */
        TreeNode root = new TreeNode(1, new TreeNode(2), null);

        assertEquals(1, test.diameterOfBinaryTree(root));
    }

    @Test
    void test_leetcodeExample_returnsThree() {
        DiameterOfBinaryTree test = new DiameterOfBinaryTree();

        /*
         *        1
         *       / \
         *      2   3
         *     / \
         *    4   5
         *
         * Longest path: 4 -> 2 -> 1 -> 3
         * Edges = 3
         */
        TreeNode root =
                new TreeNode(1, new TreeNode(2, new TreeNode(4), new TreeNode(5)), new TreeNode(3));

        assertEquals(3, test.diameterOfBinaryTree(root));
    }

    @Test
    void test_diameterDoesNotPassThroughRoot() {
        DiameterOfBinaryTree test = new DiameterOfBinaryTree();

        /*
         *            1
         *           /
         *          2
         *         / \
         *        3   4
         *       /     \
         *      5       6
         *
         * Longest path: 5 -> 3 -> 2 -> 4 -> 6
         * Edges = 4
         *
         * This checks the important rule:
         * diameter may not pass through root.
         */
        TreeNode root =
                new TreeNode(
                        1,
                        new TreeNode(
                                2,
                                new TreeNode(3, new TreeNode(5), null),
                                new TreeNode(4, null, new TreeNode(6))),
                        null);

        assertEquals(4, test.diameterOfBinaryTree(root));
    }

    @Test
    void test_leftSkewedTree_returnsHeightMinusOne() {
        DiameterOfBinaryTree test = new DiameterOfBinaryTree();

        /*
         *      1
         *     /
         *    2
         *   /
         *  3
         * /
         *4
         *
         * Longest path: 4 -> 3 -> 2 -> 1
         * Edges = 3
         */
        TreeNode root =
                new TreeNode(
                        1, new TreeNode(2, new TreeNode(3, new TreeNode(4), null), null), null);

        assertEquals(3, test.diameterOfBinaryTree(root));
    }

    @Test
    void test_balancedTree_returnsFour() {
        DiameterOfBinaryTree test = new DiameterOfBinaryTree();

        /*
         *          1
         *        /   \
         *       2     3
         *      / \   / \
         *     4   5 6   7
         *
         * Longest path can be:
         * 4 -> 2 -> 1 -> 3 -> 6
         * Edges = 4
         */
        TreeNode root =
                new TreeNode(
                        1,
                        new TreeNode(2, new TreeNode(4), new TreeNode(5)),
                        new TreeNode(3, new TreeNode(6), new TreeNode(7)));

        assertEquals(4, test.diameterOfBinaryTree(root));
    }

    @Test
    void test_deeperOneSideWithBranch_returnsFive() {
        DiameterOfBinaryTree test = new DiameterOfBinaryTree();

        /*
         *            1
         *           / \
         *          2   3
         *         /
         *        4
         *       / \
         *      5   6
         *     /
         *    7
         *
         * Longest path: 7 -> 5 -> 4 -> 2 -> 1 -> 3
         * Edges = 5
         */
        TreeNode root =
                new TreeNode(
                        1,
                        new TreeNode(
                                2,
                                new TreeNode(
                                        4, new TreeNode(5, new TreeNode(7), null), new TreeNode(6)),
                                null),
                        new TreeNode(3));

        assertEquals(5, test.diameterOfBinaryTree(root));
    }
}
