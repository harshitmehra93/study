package study.neetcode.interview.trees;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import study.neetcode.interview.commons.TreeNode;

class LowestCommonAncestorOfBSTTest {

    @Test
    void test_lcaWhenNodesAreOnDifferentSides_returnsRoot() {
        LowestCommonAncestorOfBST test = new LowestCommonAncestorOfBST();

        /*
         *        6
         *       / \
         *      2   8
         *     / \ / \
         *    0  4 7  9
         *      / \
         *     3   5
         *
         * p = 2, q = 8
         * LCA = 6
         */
        TreeNode root = buildExampleTree();
        TreeNode p = root.left; // 2
        TreeNode q = root.right; // 8

        assertSame(root, test.lowestCommonAncestor(root, p, q));
    }

    @Test
    void test_lcaWhenOneNodeIsAncestor_returnsAncestor() {
        LowestCommonAncestorOfBST test = new LowestCommonAncestorOfBST();

        /*
         * p = 2, q = 4
         * LCA = 2
         */
        TreeNode root = buildExampleTree();
        TreeNode p = root.left; // 2
        TreeNode q = root.left.right; // 4

        assertSame(p, test.lowestCommonAncestor(root, p, q));
    }

    @Test
    void test_lcaInLeftSubtree() {
        LowestCommonAncestorOfBST test = new LowestCommonAncestorOfBST();

        /*
         * p = 3, q = 5
         * LCA = 4
         */
        TreeNode root = buildExampleTree();
        TreeNode p = root.left.right.left; // 3
        TreeNode q = root.left.right.right; // 5
        TreeNode expected = root.left.right; // 4

        assertSame(expected, test.lowestCommonAncestor(root, p, q));
    }

    @Test
    void test_lcaInRightSubtree() {
        LowestCommonAncestorOfBST test = new LowestCommonAncestorOfBST();

        /*
         * p = 7, q = 9
         * LCA = 8
         */
        TreeNode root = buildExampleTree();
        TreeNode p = root.right.left; // 7
        TreeNode q = root.right.right; // 9
        TreeNode expected = root.right; // 8

        assertSame(expected, test.lowestCommonAncestor(root, p, q));
    }

    @Test
    void test_singleNodeTree_returnsRoot() {
        LowestCommonAncestorOfBST test = new LowestCommonAncestorOfBST();

        TreeNode root = new TreeNode(1);

        assertSame(root, test.lowestCommonAncestor(root, root, root));
    }

    @Test
    void test_skewedRightTree() {
        LowestCommonAncestorOfBST test = new LowestCommonAncestorOfBST();

        /*
         * 1
         *  \
         *   2
         *    \
         *     3
         *      \
         *       4
         *
         * p = 3, q = 4
         * LCA = 3
         */
        TreeNode root =
                new TreeNode(
                        1, null, new TreeNode(2, null, new TreeNode(3, null, new TreeNode(4))));

        TreeNode p = root.right.right; // 3
        TreeNode q = root.right.right.right; // 4

        assertSame(p, test.lowestCommonAncestor(root, p, q));
    }

    private TreeNode buildExampleTree() {
        /*
         *        6
         *       / \
         *      2   8
         *     / \ / \
         *    0  4 7  9
         *      / \
         *     3   5
         */
        return new TreeNode(
                6,
                new TreeNode(2, new TreeNode(0), new TreeNode(4, new TreeNode(3), new TreeNode(5))),
                new TreeNode(8, new TreeNode(7), new TreeNode(9)));
    }
}
