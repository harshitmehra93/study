package study.neetcode.interview.trees;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import study.neetcode.interview.commons.TreeNode;

class LowestCommonAncestorTest {

    @Test
    void test_nodesOnDifferentSides_returnsRoot() {
        LowestCommonAncestor test = new LowestCommonAncestor();

        /*
         *        3
         *       / \
         *      5   1
         *     / \ / \
         *    6  2 0  8
         *      / \
         *     7   4
         *
         * p = 5, q = 1
         * LCA = 3
         */
        TreeNode root = buildExampleTree();
        TreeNode p = root.left; // 5
        TreeNode q = root.right; // 1

        assertSame(root, test.lowestCommonAncestor(root, p, q));
    }

    @Test
    void test_oneNodeIsAncestor_returnsAncestor() {
        LowestCommonAncestor test = new LowestCommonAncestor();

        /*
         * p = 5, q = 4
         * LCA = 5
         */
        TreeNode root = buildExampleTree();
        TreeNode p = root.left; // 5
        TreeNode q = root.left.right.right; // 4

        assertSame(p, test.lowestCommonAncestor(root, p, q));
    }

    @Test
    void test_lcaDeepInsideLeftSubtree_returnsNodeTwo() {
        LowestCommonAncestor test = new LowestCommonAncestor();

        /*
         * p = 7, q = 4
         * LCA = 2
         */
        TreeNode root = buildExampleTree();
        TreeNode p = root.left.right.left; // 7
        TreeNode q = root.left.right.right; // 4
        TreeNode expected = root.left.right; // 2

        assertSame(expected, test.lowestCommonAncestor(root, p, q));
    }

    @Test
    void test_lcaWhenOneNodeIsRoot_returnsRoot() {
        LowestCommonAncestor test = new LowestCommonAncestor();

        /*
         * p = root, q = 4
         * LCA = root
         *
         * This catches path length assumptions.
         * path(root) is shorter than path(q).
         */
        TreeNode root = buildExampleTree();
        TreeNode p = root; // 3
        TreeNode q = root.left.right.right; // 4

        assertSame(root, test.lowestCommonAncestor(root, p, q));
    }

    @Test
    void test_pathOfQShorterThanPathOfP_doesNotThrow() {
        LowestCommonAncestor test = new LowestCommonAncestor();

        /*
         * p = 4, q = 5
         * LCA = 5
         *
         * pathOfP = [3,5,2,4]
         * pathOfQ = [3,5]
         *
         * Your current loop:
         * for (int i = 0; i < pathOfP.size(); i++)
         *
         * can throw IndexOutOfBoundsException if it indexes pathOfQ beyond its size.
         */
        TreeNode root = buildExampleTree();
        TreeNode p = root.left.right.right; // 4
        TreeNode q = root.left; // 5

        assertSame(q, test.lowestCommonAncestor(root, p, q));
    }

    @Test
    void test_sameNode_returnsThatNode() {
        LowestCommonAncestor test = new LowestCommonAncestor();

        TreeNode root = buildExampleTree();
        TreeNode p = root.left; // 5

        assertSame(p, test.lowestCommonAncestor(root, p, p));
    }

    @Test
    void test_singleNodeTree_returnsRoot() {
        LowestCommonAncestor test = new LowestCommonAncestor();

        TreeNode root = new TreeNode(1);

        assertSame(root, test.lowestCommonAncestor(root, root, root));
    }

    @Test
    void test_lcaInRightSubtree_returnsNodeThree() {
        LowestCommonAncestor test = new LowestCommonAncestor();

        /*
         *        1
         *       / \
         *      2   3
         *         / \
         *        4   5
         *
         * p = 4, q = 5
         * LCA = 3
         */
        TreeNode root =
                new TreeNode(1, new TreeNode(2), new TreeNode(3, new TreeNode(4), new TreeNode(5)));

        TreeNode p = root.right.left; // 4
        TreeNode q = root.right.right; // 5
        TreeNode expected = root.right; // 3

        assertSame(expected, test.lowestCommonAncestor(root, p, q));
    }

    private TreeNode buildExampleTree() {
        /*
         *        3
         *       / \
         *      5   1
         *     / \ / \
         *    6  2 0  8
         *      / \
         *     7   4
         */
        return new TreeNode(
                3,
                new TreeNode(5, new TreeNode(6), new TreeNode(2, new TreeNode(7), new TreeNode(4))),
                new TreeNode(1, new TreeNode(0), new TreeNode(8)));
    }
}
