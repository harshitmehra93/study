package study.interview.trees;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;
import study.interview.commons.TreeNode;

class BinaryTreeRightSideViewTest {

    @Test
    void test_emptyTree_returnsEmptyList() {
        BinaryTreeRightSideView test = new BinaryTreeRightSideView();

        assertEquals(List.of(), test.rightSideView(null));
    }

    @Test
    void test_singleNode_returnsRoot() {
        BinaryTreeRightSideView test = new BinaryTreeRightSideView();

        TreeNode root = new TreeNode(1);

        assertEquals(List.of(1), test.rightSideView(root));
    }

    @Test
    void test_givenExample_returnsRightSideView() {
        BinaryTreeRightSideView test = new BinaryTreeRightSideView();

        /*
         *        1
         *       / \
         *      2   3
         *       \   \
         *        5   4
         *
         * Right side view: [1, 3, 4]
         */
        TreeNode root =
                new TreeNode(
                        1,
                        new TreeNode(2, null, new TreeNode(5)),
                        new TreeNode(3, null, new TreeNode(4)));

        assertEquals(List.of(1, 3, 4), test.rightSideView(root));
    }

    @Test
    void test_leftSkewedTree_returnsAllNodes() {
        BinaryTreeRightSideView test = new BinaryTreeRightSideView();

        /*
         *      1
         *     /
         *    2
         *   /
         *  3
         *
         * Right side view: [1, 2, 3]
         */
        TreeNode root = new TreeNode(1, new TreeNode(2, new TreeNode(3), null), null);

        assertEquals(List.of(1, 2, 3), test.rightSideView(root));
    }

    @Test
    void test_rightSkewedTree_returnsAllNodes() {
        BinaryTreeRightSideView test = new BinaryTreeRightSideView();

        /*
         *  1
         *   \
         *    2
         *     \
         *      3
         *
         * Right side view: [1, 2, 3]
         */
        TreeNode root = new TreeNode(1, null, new TreeNode(2, null, new TreeNode(3)));

        assertEquals(List.of(1, 2, 3), test.rightSideView(root));
    }

    @Test
    void test_rightChildMissingButLeftVisibleAtLowerLevel() {
        BinaryTreeRightSideView test = new BinaryTreeRightSideView();

        /*
         *        1
         *       / \
         *      2   3
         *     /
         *    4
         *
         * Level 0: 1
         * Level 1: 3
         * Level 2: 4
         *
         * Right side view: [1, 3, 4]
         */
        TreeNode root = new TreeNode(1, new TreeNode(2, new TreeNode(4), null), new TreeNode(3));

        assertEquals(List.of(1, 3, 4), test.rightSideView(root));
    }

    @Test
    void test_completeTree_returnsRightmostAtEachLevel() {
        BinaryTreeRightSideView test = new BinaryTreeRightSideView();

        /*
         *          1
         *        /   \
         *       2     3
         *      / \   / \
         *     4   5 6   7
         *
         * Right side view: [1, 3, 7]
         */
        TreeNode root =
                new TreeNode(
                        1,
                        new TreeNode(2, new TreeNode(4), new TreeNode(5)),
                        new TreeNode(3, new TreeNode(6), new TreeNode(7)));

        assertEquals(List.of(1, 3, 7), test.rightSideView(root));
    }

    @Test
    void test_sparseTree_rightSideViewNotAlwaysRightChildChain() {
        BinaryTreeRightSideView test = new BinaryTreeRightSideView();

        /*
         *          1
         *        /   \
         *       2     3
         *        \
         *         5
         *        /
         *       6
         *
         * Right side view: [1, 3, 5, 6]
         *
         * Important: after node 3 has no children,
         * node 5 and then 6 become visible from the right.
         */
        TreeNode root =
                new TreeNode(
                        1,
                        new TreeNode(2, null, new TreeNode(5, new TreeNode(6), null)),
                        new TreeNode(3));

        assertEquals(List.of(1, 3, 5, 6), test.rightSideView(root));
    }
}
