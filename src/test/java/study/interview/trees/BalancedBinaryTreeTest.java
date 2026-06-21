package study.interview.trees;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import study.interview.commons.TreeNode;

class BalancedBinaryTreeTest {

    @Test
    void test_emptyTree_returnsTrue() {
        BalancedBinaryTree test = new BalancedBinaryTree();

        assertTrue(test.isBalanced(null));
    }

    @Test
    void test_singleNode_returnsTrue() {
        BalancedBinaryTree test = new BalancedBinaryTree();

        TreeNode root = new TreeNode(1);

        assertTrue(test.isBalanced(root));
    }

    @Test
    void test_balancedLeetcodeExample_returnsTrue() {
        BalancedBinaryTree test = new BalancedBinaryTree();

        /*
         *      3
         *     / \
         *    9   20
         *       /  \
         *      15   7
         */
        TreeNode root =
                new TreeNode(
                        3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)));

        assertTrue(test.isBalanced(root));
    }

    @Test
    void test_leftHeavyUnbalanced_returnsFalse() {
        BalancedBinaryTree test = new BalancedBinaryTree();

        /*
         *        1
         *       /
         *      2
         *     /
         *    3
         *
         * At root:
         * left height = 2
         * right height = 0
         * difference = 2
         */
        TreeNode root = new TreeNode(1, new TreeNode(2, new TreeNode(3), null), null);

        assertFalse(test.isBalanced(root));
    }

    @Test
    void test_rightHeavyUnbalanced_returnsFalse() {
        BalancedBinaryTree test = new BalancedBinaryTree();

        /*
         *  1
         *   \
         *    2
         *     \
         *      3
         */
        TreeNode root = new TreeNode(1, null, new TreeNode(2, null, new TreeNode(3)));

        assertFalse(test.isBalanced(root));
    }

    @Test
    void test_balancedButNotPerfect_returnsTrue() {
        BalancedBinaryTree test = new BalancedBinaryTree();

        /*
         *        1
         *       / \
         *      2   3
         *     /
         *    4
         *
         * Root difference = 1
         * Node 2 difference = 1
         */
        TreeNode root = new TreeNode(1, new TreeNode(2, new TreeNode(4), null), new TreeNode(3));

        assertTrue(test.isBalanced(root));
    }

    @Test
    void test_unbalancedDeepSubtreeEvenIfRootLooksBalanced_returnsFalse() {
        BalancedBinaryTree test = new BalancedBinaryTree();

        /*
         *              1
         *            /   \
         *           2     3
         *          /       \
         *         4         5
         *        /
         *       6
         *
         * Root may look close, but subtree rooted at 2 is unbalanced.
         */
        TreeNode root =
                new TreeNode(
                        1,
                        new TreeNode(2, new TreeNode(4, new TreeNode(6), null), null),
                        new TreeNode(3, null, new TreeNode(5)));

        assertFalse(test.isBalanced(root));
    }

    @Test
    void test_largeBalancedTree_returnsTrue() {
        BalancedBinaryTree test = new BalancedBinaryTree();

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

        assertTrue(test.isBalanced(root));
    }
}
