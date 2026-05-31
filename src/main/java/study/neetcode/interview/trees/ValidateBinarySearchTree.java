package study.neetcode.interview.trees;

import study.neetcode.interview.trees.commons.TreeNode;

/*
Validate Binary Search Tree

Given the root of a binary tree, determine if it is a valid binary search tree.

A valid BST means:

For every node:
- all values in the left subtree are strictly less than node.val
- all values in the right subtree are strictly greater than node.val
- both left and right subtrees must also be valid BSTs
Example 1
    2
   / \
  1   3

Output:

true
Example 2
    5
   / \
  1   4
     / \
    3   6

Output:

false
 */
public class ValidateBinarySearchTree {
    public boolean isValidBST(TreeNode root) {
        return validateBst(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean validateBst(TreeNode node, long minValue, long maxValue) {
        if (node == null) return true;

        int current = node.val;
        if (current >= maxValue || current <= minValue) return false;

        return validateBst(node.left, minValue, current)
                && validateBst(node.right, current, maxValue);
    }
}
