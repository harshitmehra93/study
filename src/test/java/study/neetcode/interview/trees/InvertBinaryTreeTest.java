package study.neetcode.interview.trees;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import study.neetcode.interview.commons.TreeNode;

class InvertBinaryTreeTest {

    @Test
    void test_emptyTree_returnsNull() {
        InvertBinaryTree test = new InvertBinaryTree();

        assertNull(test.invertTree(null));
    }

    @Test
    void test_singleNode_returnsSameNode() {
        InvertBinaryTree test = new InvertBinaryTree();

        TreeNode root = new TreeNode(1);

        TreeNode inverted = test.invertTree(root);

        assertNotNull(inverted);
        assertEquals(1, inverted.val);
        assertNull(inverted.left);
        assertNull(inverted.right);
    }

    @Test
    void test_leetcodeExample() {
        InvertBinaryTree test = new InvertBinaryTree();

        /*
         * Original:
         *       4
         *     /   \
         *    2     7
         *   / \   / \
         *  1   3 6   9
         *
         * Expected:
         *       4
         *     /   \
         *    7     2
         *   / \   / \
         *  9   6 3   1
         */
        TreeNode root =
                new TreeNode(
                        4,
                        new TreeNode(2, new TreeNode(1), new TreeNode(3)),
                        new TreeNode(7, new TreeNode(6), new TreeNode(9)));

        TreeNode inverted = test.invertTree(root);

        assertEquals(4, inverted.val);

        assertEquals(7, inverted.left.val);
        assertEquals(9, inverted.left.left.val);
        assertEquals(6, inverted.left.right.val);

        assertEquals(2, inverted.right.val);
        assertEquals(3, inverted.right.left.val);
        assertEquals(1, inverted.right.right.val);
    }

    @Test
    void test_leftHeavyTree_becomesRightHeavy() {
        InvertBinaryTree test = new InvertBinaryTree();

        /*
         * Original:
         *      1
         *     /
         *    2
         *   /
         *  3
         *
         * Expected:
         *  1
         *   \
         *    2
         *     \
         *      3
         */
        TreeNode root = new TreeNode(1, new TreeNode(2, new TreeNode(3), null), null);

        TreeNode inverted = test.invertTree(root);

        assertEquals(1, inverted.val);
        assertNull(inverted.left);
        assertEquals(2, inverted.right.val);
        assertNull(inverted.right.left);
        assertEquals(3, inverted.right.right.val);
    }

    @Test
    void test_rightHeavyTree_becomesLeftHeavy() {
        InvertBinaryTree test = new InvertBinaryTree();

        /*
         * Original:
         *  1
         *   \
         *    2
         *     \
         *      3
         *
         * Expected:
         *      1
         *     /
         *    2
         *   /
         *  3
         */
        TreeNode root = new TreeNode(1, null, new TreeNode(2, null, new TreeNode(3)));

        TreeNode inverted = test.invertTree(root);

        assertEquals(1, inverted.val);
        assertEquals(2, inverted.left.val);
        assertNull(inverted.right);
        assertEquals(3, inverted.left.left.val);
        assertNull(inverted.left.right);
    }

    @Test
    void test_invertingTwice_returnsOriginalStructure() {
        InvertBinaryTree test = new InvertBinaryTree();

        TreeNode root =
                new TreeNode(1, new TreeNode(2, new TreeNode(4), new TreeNode(5)), new TreeNode(3));

        TreeNode invertedOnce = test.invertTree(root);
        TreeNode invertedTwice = test.invertTree(invertedOnce);

        assertEquals(1, invertedTwice.val);
        assertEquals(2, invertedTwice.left.val);
        assertEquals(4, invertedTwice.left.left.val);
        assertEquals(5, invertedTwice.left.right.val);
        assertEquals(3, invertedTwice.right.val);
    }
}
