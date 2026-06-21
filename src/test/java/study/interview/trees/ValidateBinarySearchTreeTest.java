package study.interview.trees;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import study.interview.commons.TreeNode;

class ValidateBinarySearchTreeTest {

    @Test
    void test_emptyTree_returnsTrue() {
        ValidateBinarySearchTree test = new ValidateBinarySearchTree();

        assertTrue(test.isValidBST(null));
    }

    @Test
    void test_singleNode_returnsTrue() {
        ValidateBinarySearchTree test = new ValidateBinarySearchTree();

        TreeNode root = new TreeNode(1);

        assertTrue(test.isValidBST(root));
    }

    @Test
    void test_validSimpleBST_returnsTrue() {
        ValidateBinarySearchTree test = new ValidateBinarySearchTree();

        /*
         *     2
         *    / \
         *   1   3
         */
        TreeNode root = new TreeNode(2, new TreeNode(1), new TreeNode(3));

        assertTrue(test.isValidBST(root));
    }

    @Test
    void test_invalidRightChildLessThanRoot_returnsFalse() {
        ValidateBinarySearchTree test = new ValidateBinarySearchTree();

        /*
         *     5
         *    / \
         *   1   4
         *      / \
         *     3   6
         *
         * 4 is in the right subtree of 5,
         * so it must be greater than 5.
         */
        TreeNode root =
                new TreeNode(5, new TreeNode(1), new TreeNode(4, new TreeNode(3), new TreeNode(6)));

        assertFalse(test.isValidBST(root));
    }

    @Test
    void test_invalidBecauseOfAncestorBound_returnsFalse() {
        ValidateBinarySearchTree test = new ValidateBinarySearchTree();

        /*
         *       5
         *      / \
         *     1   6
         *        / \
         *       4   7
         *
         * 4 is less than 6, but it is in the right subtree of 5.
         * Therefore it must be greater than 5.
         */
        TreeNode root =
                new TreeNode(5, new TreeNode(1), new TreeNode(6, new TreeNode(4), new TreeNode(7)));

        assertFalse(test.isValidBST(root));
    }

    @Test
    void test_duplicateOnLeft_returnsFalse() {
        ValidateBinarySearchTree test = new ValidateBinarySearchTree();

        /*
         *     2
         *    /
         *   2
         *
         * BST is strict: left must be < root.
         */
        TreeNode root = new TreeNode(2, new TreeNode(2), null);

        assertFalse(test.isValidBST(root));
    }

    @Test
    void test_duplicateOnRight_returnsFalse() {
        ValidateBinarySearchTree test = new ValidateBinarySearchTree();

        /*
         *     2
         *      \
         *       2
         *
         * BST is strict: right must be > root.
         */
        TreeNode root = new TreeNode(2, null, new TreeNode(2));

        assertFalse(test.isValidBST(root));
    }

    @Test
    void test_validLargerBST_returnsTrue() {
        ValidateBinarySearchTree test = new ValidateBinarySearchTree();

        /*
         *          8
         *        /   \
         *       3     10
         *      / \      \
         *     1   6      14
         *        / \     /
         *       4   7   13
         */
        TreeNode root =
                new TreeNode(
                        8,
                        new TreeNode(
                                3,
                                new TreeNode(1),
                                new TreeNode(6, new TreeNode(4), new TreeNode(7))),
                        new TreeNode(10, null, new TreeNode(14, new TreeNode(13), null)));

        assertTrue(test.isValidBST(root));
    }

    @Test
    void test_minMaxIntegerValues_valid() {
        ValidateBinarySearchTree test = new ValidateBinarySearchTree();

        /*
         *       0
         *      / \
         * MIN     MAX
         */
        TreeNode root =
                new TreeNode(0, new TreeNode(Integer.MIN_VALUE), new TreeNode(Integer.MAX_VALUE));

        assertTrue(test.isValidBST(root));
    }
}
