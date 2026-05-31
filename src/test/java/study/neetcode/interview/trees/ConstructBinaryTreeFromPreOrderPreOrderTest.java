package study.neetcode.interview.trees;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import study.neetcode.interview.trees.commons.TreeNode;

class ConstructBinaryTreeFromPreOrderPreOrderTest {

    @Test
    void test_emptyArrays_returnsNull() {
        ConstructBinaryTreeFromPreOrderPreOrder test =
                new ConstructBinaryTreeFromPreOrderPreOrder();

        assertNull(test.buildTree(new int[] {}, new int[] {}));
    }

    @Test
    void test_singleNode() {
        ConstructBinaryTreeFromPreOrderPreOrder test =
                new ConstructBinaryTreeFromPreOrderPreOrder();

        TreeNode root = test.buildTree(new int[] {1}, new int[] {1});

        assertEquals(1, root.val);
        assertNull(root.left);
        assertNull(root.right);
    }

    @Test
    void test_leetcodeExample() {
        ConstructBinaryTreeFromPreOrderPreOrder test =
                new ConstructBinaryTreeFromPreOrderPreOrder();

        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};

        TreeNode root = test.buildTree(preorder, inorder);

        /*
         *        3
         *       / \
         *      9   20
         *         /  \
         *        15   7
         */
        assertEquals(3, root.val);

        assertEquals(9, root.left.val);
        assertNull(root.left.left);
        assertNull(root.left.right);

        assertEquals(20, root.right.val);
        assertEquals(15, root.right.left.val);
        assertEquals(7, root.right.right.val);
    }

    @Test
    void test_leftSkewedTree() {
        ConstructBinaryTreeFromPreOrderPreOrder test =
                new ConstructBinaryTreeFromPreOrderPreOrder();

        /*
         *      1
         *     /
         *    2
         *   /
         *  3
         *
         * Preorder: root, left, right -> [1, 2, 3]
         * Inorder: left, root, right -> [3, 2, 1]
         */
        int[] preorder = {1, 2, 3};
        int[] inorder = {3, 2, 1};

        TreeNode root = test.buildTree(preorder, inorder);

        assertEquals(1, root.val);
        assertEquals(2, root.left.val);
        assertEquals(3, root.left.left.val);

        assertNull(root.right);
        assertNull(root.left.right);
        assertNull(root.left.left.left);
        assertNull(root.left.left.right);
    }

    @Test
    void test_rightSkewedTree() {
        ConstructBinaryTreeFromPreOrderPreOrder test =
                new ConstructBinaryTreeFromPreOrderPreOrder();

        /*
         *  1
         *   \
         *    2
         *     \
         *      3
         *
         * Preorder: [1, 2, 3]
         * Inorder:  [1, 2, 3]
         */
        int[] preorder = {1, 2, 3};
        int[] inorder = {1, 2, 3};

        TreeNode root = test.buildTree(preorder, inorder);

        assertEquals(1, root.val);
        assertNull(root.left);

        assertEquals(2, root.right.val);
        assertNull(root.right.left);

        assertEquals(3, root.right.right.val);
        assertNull(root.right.right.left);
        assertNull(root.right.right.right);
    }

    @Test
    void test_completeTree() {
        ConstructBinaryTreeFromPreOrderPreOrder test =
                new ConstructBinaryTreeFromPreOrderPreOrder();

        /*
         *          1
         *        /   \
         *       2     3
         *      / \   / \
         *     4   5 6   7
         *
         * Preorder: root, left, right
         * Inorder: left, root, right
         */
        int[] preorder = {1, 2, 4, 5, 3, 6, 7};
        int[] inorder = {4, 2, 5, 1, 6, 3, 7};

        TreeNode root = test.buildTree(preorder, inorder);

        assertEquals(1, root.val);

        assertEquals(2, root.left.val);
        assertEquals(4, root.left.left.val);
        assertEquals(5, root.left.right.val);

        assertEquals(3, root.right.val);
        assertEquals(6, root.right.left.val);
        assertEquals(7, root.right.right.val);
    }

    @Test
    void test_sparseTree() {
        ConstructBinaryTreeFromPreOrderPreOrder test =
                new ConstructBinaryTreeFromPreOrderPreOrder();

        /*
         *          1
         *        /   \
         *       2     3
         *        \   /
         *         4 5
         *
         * Preorder: [1, 2, 4, 3, 5]
         * Inorder:  [2, 4, 1, 5, 3]
         */
        int[] preorder = {1, 2, 4, 3, 5};
        int[] inorder = {2, 4, 1, 5, 3};

        TreeNode root = test.buildTree(preorder, inorder);

        assertEquals(1, root.val);

        assertEquals(2, root.left.val);
        assertNull(root.left.left);
        assertEquals(4, root.left.right.val);

        assertEquals(3, root.right.val);
        assertEquals(5, root.right.left.val);
        assertNull(root.right.right);
    }
}
