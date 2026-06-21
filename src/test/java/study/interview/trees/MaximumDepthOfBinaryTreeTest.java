package study.interview.trees;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import study.interview.commons.TreeNode;

class MaximumDepthOfBinaryTreeTest {

    @Test
    void test_emptyTree_returnsZero() {
        MaximumDepthOfBinaryTree test = new MaximumDepthOfBinaryTree();

        assertEquals(0, test.maxDepth(null));
    }

    @Test
    void test_singleNode_returnsOne() {
        MaximumDepthOfBinaryTree test = new MaximumDepthOfBinaryTree();

        TreeNode root = new TreeNode(1);

        assertEquals(1, test.maxDepth(root));
    }

    @Test
    void test_balancedTree() {
        MaximumDepthOfBinaryTree test = new MaximumDepthOfBinaryTree();

        /*
         *        1
         *      /   \
         *     2     3
         *    / \   / \
         *   4   5 6   7
         */
        TreeNode root =
                new TreeNode(
                        1,
                        new TreeNode(2, new TreeNode(4), new TreeNode(5)),
                        new TreeNode(3, new TreeNode(6), new TreeNode(7)));

        assertEquals(3, test.maxDepth(root));
    }

    @Test
    void test_unbalancedLeftHeavyTree() {
        MaximumDepthOfBinaryTree test = new MaximumDepthOfBinaryTree();

        /*
         *        1
         *       /
         *      2
         *     /
         *    3
         *   /
         *  4
         */
        TreeNode root =
                new TreeNode(
                        1, new TreeNode(2, new TreeNode(3, new TreeNode(4), null), null), null);

        assertEquals(4, test.maxDepth(root));
    }

    @Test
    void test_unbalancedRightHeavyTree() {
        MaximumDepthOfBinaryTree test = new MaximumDepthOfBinaryTree();

        /*
         *  1
         *   \
         *    2
         *     \
         *      3
         *       \
         *        4
         */
        TreeNode root =
                new TreeNode(
                        1, null, new TreeNode(2, null, new TreeNode(3, null, new TreeNode(4))));

        assertEquals(4, test.maxDepth(root));
    }

    @Test
    void test_leetcodeExample() {
        MaximumDepthOfBinaryTree test = new MaximumDepthOfBinaryTree();

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

        assertEquals(3, test.maxDepth(root));
    }

    @Test
    void test_mixedDepthTree_takesMaximumSide() {
        MaximumDepthOfBinaryTree test = new MaximumDepthOfBinaryTree();

        /*
         *        1
         *       / \
         *      2   3
         *         /
         *        4
         *       /
         *      5
         */
        TreeNode root =
                new TreeNode(
                        1,
                        new TreeNode(2),
                        new TreeNode(3, new TreeNode(4, new TreeNode(5), null), null));

        assertEquals(4, test.maxDepth(root));
    }
}
