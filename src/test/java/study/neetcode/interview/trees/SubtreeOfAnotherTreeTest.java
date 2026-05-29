package study.neetcode.interview.trees;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import study.neetcode.interview.trees.commons.TreeNode;

class SubtreeOfAnotherTreeTest {

    @Test
    void test_emptySubtree_returnsTrue() {
        SubtreeOfAnotherTree test = new SubtreeOfAnotherTree();

        TreeNode root = new TreeNode(1);

        assertTrue(test.isSubtree(root, null));
    }

    @Test
    void test_emptyRootNonEmptySubtree_returnsFalse() {
        SubtreeOfAnotherTree test = new SubtreeOfAnotherTree();

        TreeNode subRoot = new TreeNode(1);

        assertFalse(test.isSubtree(null, subRoot));
    }

    @Test
    void test_bothEmpty_returnsTrue() {
        SubtreeOfAnotherTree test = new SubtreeOfAnotherTree();

        assertTrue(test.isSubtree(null, null));
    }

    @Test
    void test_givenExample_returnsTrue() {
        SubtreeOfAnotherTree test = new SubtreeOfAnotherTree();

        /*
         * root:
         *        3
         *       / \
         *      4   5
         *     / \
         *    1   2
         *
         * subRoot:
         *      4
         *     / \
         *    1   2
         */
        TreeNode root =
                new TreeNode(3, new TreeNode(4, new TreeNode(1), new TreeNode(2)), new TreeNode(5));

        TreeNode subRoot = new TreeNode(4, new TreeNode(1), new TreeNode(2));

        assertTrue(test.isSubtree(root, subRoot));
    }

    @Test
    void test_sameValuesButDifferentStructure_returnsFalse() {
        SubtreeOfAnotherTree test = new SubtreeOfAnotherTree();

        /*
         * root:
         *        3
         *       / \
         *      4   5
         *     / \
         *    1   2
         *       /
         *      0
         *
         * subRoot:
         *      4
         *     / \
         *    1   2
         */
        TreeNode root =
                new TreeNode(
                        3,
                        new TreeNode(4, new TreeNode(1), new TreeNode(2, new TreeNode(0), null)),
                        new TreeNode(5));

        TreeNode subRoot = new TreeNode(4, new TreeNode(1), new TreeNode(2));

        assertFalse(test.isSubtree(root, subRoot));
    }

    @Test
    void test_subtreeIsLeaf_returnsTrue() {
        SubtreeOfAnotherTree test = new SubtreeOfAnotherTree();

        /*
         * root:
         *      1
         *     / \
         *    2   3
         *
         * subRoot:
         *    3
         */
        TreeNode root = new TreeNode(1, new TreeNode(2), new TreeNode(3));

        TreeNode subRoot = new TreeNode(3);

        assertTrue(test.isSubtree(root, subRoot));
    }

    @Test
    void test_valueExistsButSubtreeDoesNotMatch_returnsFalse() {
        SubtreeOfAnotherTree test = new SubtreeOfAnotherTree();

        /*
         * root:
         *      1
         *     / \
         *    2   3
         *
         * subRoot:
         *    3
         *     \
         *      4
         */
        TreeNode root = new TreeNode(1, new TreeNode(2), new TreeNode(3));

        TreeNode subRoot = new TreeNode(3, null, new TreeNode(4));

        assertFalse(test.isSubtree(root, subRoot));
    }

    @Test
    void test_entireTreeMatches_returnsTrue() {
        SubtreeOfAnotherTree test = new SubtreeOfAnotherTree();

        TreeNode root = new TreeNode(1, new TreeNode(2), new TreeNode(3));

        TreeNode subRoot = new TreeNode(1, new TreeNode(2), new TreeNode(3));

        assertTrue(test.isSubtree(root, subRoot));
    }

    @Test
    void test_duplicateValues_onlyOneBranchMatches_returnsTrue() {
        SubtreeOfAnotherTree test = new SubtreeOfAnotherTree();

        /*
         * root:
         *          1
         *        /   \
         *       2     2
         *      /     / \
         *     3     3   4
         *
         * subRoot:
         *       2
         *      / \
         *     3   4
         */
        TreeNode root =
                new TreeNode(
                        1,
                        new TreeNode(2, new TreeNode(3), null),
                        new TreeNode(2, new TreeNode(3), new TreeNode(4)));

        TreeNode subRoot = new TreeNode(2, new TreeNode(3), new TreeNode(4));

        assertTrue(test.isSubtree(root, subRoot));
    }
}
