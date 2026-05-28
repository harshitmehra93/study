package study.neetcode.interview.trees;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import study.neetcode.interview.trees.commons.TreeNode;

class SameTreeTest {

    @Test
    void test_bothTreesEmpty_returnsTrue() {
        SameTree test = new SameTree();

        assertTrue(test.isSameTree(null, null));
    }

    @Test
    void test_oneTreeEmpty_returnsFalse() {
        SameTree test = new SameTree();

        TreeNode p = new TreeNode(1);

        assertFalse(test.isSameTree(p, null));
        assertFalse(test.isSameTree(null, p));
    }

    @Test
    void test_singleNodeSameValue_returnsTrue() {
        SameTree test = new SameTree();

        TreeNode p = new TreeNode(1);
        TreeNode q = new TreeNode(1);

        assertTrue(test.isSameTree(p, q));
    }

    @Test
    void test_singleNodeDifferentValue_returnsFalse() {
        SameTree test = new SameTree();

        TreeNode p = new TreeNode(1);
        TreeNode q = new TreeNode(2);

        assertFalse(test.isSameTree(p, q));
    }

    @Test
    void test_sameBalancedTrees_returnsTrue() {
        SameTree test = new SameTree();

        /*
         *      1
         *     / \
         *    2   3
         */
        TreeNode p = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        TreeNode q = new TreeNode(1, new TreeNode(2), new TreeNode(3));

        assertTrue(test.isSameTree(p, q));
    }

    @Test
    void test_sameValuesDifferentStructure_returnsFalse() {
        SameTree test = new SameTree();

        /*
         * p:
         *      1
         *     /
         *    2
         *
         * q:
         *      1
         *       \
         *        2
         */
        TreeNode p = new TreeNode(1, new TreeNode(2), null);
        TreeNode q = new TreeNode(1, null, new TreeNode(2));

        assertFalse(test.isSameTree(p, q));
    }

    @Test
    void test_sameStructureDifferentDeepValue_returnsFalse() {
        SameTree test = new SameTree();

        /*
         * p:
         *      1
         *     / \
         *    2   3
         *
         * q:
         *      1
         *     / \
         *    2   4
         */
        TreeNode p = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        TreeNode q = new TreeNode(1, new TreeNode(2), new TreeNode(4));

        assertFalse(test.isSameTree(p, q));
    }

    @Test
    void test_largerIdenticalTrees_returnsTrue() {
        SameTree test = new SameTree();

        /*
         *        1
         *       / \
         *      2   3
         *     /   / \
         *    4   5   6
         */
        TreeNode p =
                new TreeNode(
                        1,
                        new TreeNode(2, new TreeNode(4), null),
                        new TreeNode(3, new TreeNode(5), new TreeNode(6)));

        TreeNode q =
                new TreeNode(
                        1,
                        new TreeNode(2, new TreeNode(4), null),
                        new TreeNode(3, new TreeNode(5), new TreeNode(6)));

        assertTrue(test.isSameTree(p, q));
    }
}
