package study.neetcode.interview.trees;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import study.neetcode.interview.trees.commons.TreeNode;

class KthSmallestElementInBSTTest {

    @Test
    void test_k1_returnsSmallestElement() {
        KthSmallestElementInBST test = new KthSmallestElementInBST();

        /*
         *        3
         *       / \
         *      1   4
         *       \
         *        2
         *
         * Inorder: [1, 2, 3, 4]
         */
        TreeNode root = new TreeNode(3, new TreeNode(1, null, new TreeNode(2)), new TreeNode(4));

        assertEquals(1, test.kthSmallest(root, 1));
    }

    @Test
    void test_k2_returnsSecondSmallest() {
        KthSmallestElementInBST test = new KthSmallestElementInBST();

        /*
         *        3
         *       / \
         *      1   4
         *       \
         *        2
         *
         * Inorder: [1, 2, 3, 4]
         */
        TreeNode root = new TreeNode(3, new TreeNode(1, null, new TreeNode(2)), new TreeNode(4));

        assertEquals(2, test.kthSmallest(root, 2));
    }

    @Test
    void test_givenExample_k3_returnsThree() {
        KthSmallestElementInBST test = new KthSmallestElementInBST();

        /*
         *          5
         *         / \
         *        3   6
         *       / \
         *      2   4
         *     /
         *    1
         *
         * Inorder: [1, 2, 3, 4, 5, 6]
         */
        TreeNode root =
                new TreeNode(
                        5,
                        new TreeNode(3, new TreeNode(2, new TreeNode(1), null), new TreeNode(4)),
                        new TreeNode(6));

        assertEquals(3, test.kthSmallest(root, 3));
    }

    @Test
    void test_kEqualsNumberOfNodes_returnsLargest() {
        KthSmallestElementInBST test = new KthSmallestElementInBST();

        /*
         *      2
         *     / \
         *    1   3
         *
         * Inorder: [1, 2, 3]
         */
        TreeNode root = new TreeNode(2, new TreeNode(1), new TreeNode(3));

        assertEquals(3, test.kthSmallest(root, 3));
    }

    @Test
    void test_singleNode_k1_returnsRoot() {
        KthSmallestElementInBST test = new KthSmallestElementInBST();

        TreeNode root = new TreeNode(10);

        assertEquals(10, test.kthSmallest(root, 1));
    }

    @Test
    void test_leftSkewedBST() {
        KthSmallestElementInBST test = new KthSmallestElementInBST();

        /*
         *        5
         *       /
         *      4
         *     /
         *    3
         *   /
         *  2
         * /
         *1
         *
         * Inorder: [1, 2, 3, 4, 5]
         */
        TreeNode root =
                new TreeNode(
                        5,
                        new TreeNode(
                                4,
                                new TreeNode(3, new TreeNode(2, new TreeNode(1), null), null),
                                null),
                        null);

        assertEquals(4, test.kthSmallest(root, 4));
    }

    @Test
    void test_rightSkewedBST() {
        KthSmallestElementInBST test = new KthSmallestElementInBST();

        /*
         * 1
         *  \
         *   2
         *    \
         *     3
         *      \
         *       4
         *
         * Inorder: [1, 2, 3, 4]
         */
        TreeNode root =
                new TreeNode(
                        1, null, new TreeNode(2, null, new TreeNode(3, null, new TreeNode(4))));

        assertEquals(3, test.kthSmallest(root, 3));
    }

    @Test
    void test_largerBST_middleValue() {
        KthSmallestElementInBST test = new KthSmallestElementInBST();

        /*
         *          8
         *        /   \
         *       3     10
         *      / \      \
         *     1   6      14
         *        / \     /
         *       4   7   13
         *
         * Inorder: [1, 3, 4, 6, 7, 8, 10, 13, 14]
         */
        TreeNode root =
                new TreeNode(
                        8,
                        new TreeNode(
                                3,
                                new TreeNode(1),
                                new TreeNode(6, new TreeNode(4), new TreeNode(7))),
                        new TreeNode(10, null, new TreeNode(14, new TreeNode(13), null)));

        assertEquals(7, test.kthSmallest(root, 5));
    }
}
