package study.neetcode.interview.trees;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;
import study.neetcode.interview.commons.TreeNode;

class BinaryTreeLevelOrderTraversalTest {

    @Test
    void test_emptyTree_returnsEmptyList() {
        BinaryTreeLevelOrderTraversal test = new BinaryTreeLevelOrderTraversal();

        assertEquals(List.of(), test.levelOrder(null));
    }

    @Test
    void test_singleNode_returnsOneLevel() {
        BinaryTreeLevelOrderTraversal test = new BinaryTreeLevelOrderTraversal();

        TreeNode root = new TreeNode(1);

        assertEquals(List.of(List.of(1)), test.levelOrder(root));
    }

    @Test
    void test_leetcodeExample() {
        BinaryTreeLevelOrderTraversal test = new BinaryTreeLevelOrderTraversal();

        /*
         *        3
         *       / \
         *      9   20
         *         /  \
         *        15   7
         */
        TreeNode root =
                new TreeNode(
                        3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)));

        assertEquals(List.of(List.of(3), List.of(9, 20), List.of(15, 7)), test.levelOrder(root));
    }

    @Test
    void test_completeBinaryTree() {
        BinaryTreeLevelOrderTraversal test = new BinaryTreeLevelOrderTraversal();

        /*
         *          1
         *        /   \
         *       2     3
         *      / \   / \
         *     4   5 6   7
         */
        TreeNode root =
                new TreeNode(
                        1,
                        new TreeNode(2, new TreeNode(4), new TreeNode(5)),
                        new TreeNode(3, new TreeNode(6), new TreeNode(7)));

        assertEquals(
                List.of(List.of(1), List.of(2, 3), List.of(4, 5, 6, 7)), test.levelOrder(root));
    }

    @Test
    void test_leftSkewedTree() {
        BinaryTreeLevelOrderTraversal test = new BinaryTreeLevelOrderTraversal();

        /*
         *      1
         *     /
         *    2
         *   /
         *  3
         */
        TreeNode root = new TreeNode(1, new TreeNode(2, new TreeNode(3), null), null);

        assertEquals(List.of(List.of(1), List.of(2), List.of(3)), test.levelOrder(root));
    }

    @Test
    void test_rightSkewedTree() {
        BinaryTreeLevelOrderTraversal test = new BinaryTreeLevelOrderTraversal();

        /*
         *  1
         *   \
         *    2
         *     \
         *      3
         */
        TreeNode root = new TreeNode(1, null, new TreeNode(2, null, new TreeNode(3)));

        assertEquals(List.of(List.of(1), List.of(2), List.of(3)), test.levelOrder(root));
    }

    @Test
    void test_sparseTree_preservesLeftToRightOrderWithinLevel() {
        BinaryTreeLevelOrderTraversal test = new BinaryTreeLevelOrderTraversal();

        /*
         *          1
         *        /   \
         *       2     3
         *        \   /
         *         5 6
         */
        TreeNode root =
                new TreeNode(
                        1,
                        new TreeNode(2, null, new TreeNode(5)),
                        new TreeNode(3, new TreeNode(6), null));

        assertEquals(List.of(List.of(1), List.of(2, 3), List.of(5, 6)), test.levelOrder(root));
    }
}
