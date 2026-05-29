package study.neetcode.interview.trees;

import study.neetcode.interview.trees.commons.TreeNode;

/*
Balanced Binary Tree

Given the root of a binary tree, return true if it is height-balanced.

A binary tree is height-balanced if, for every node:

abs(height(left subtree) - height(right subtree)) <= 1
Example 1
      3
     / \
    9   20
       /  \
      15   7

Output:

true
Example 2
        1
       /
      2
     /
    3

Output:

false

Because at node 1, the left subtree is much deeper than the right subtree.

Method signature
public boolean isBalanced(TreeNode root)
 */
public class BalancedBinaryTree {
    public boolean isBalanced(TreeNode root) {
        return computeMaxDepth(root) != -1;
    }

    private int computeMaxDepth(TreeNode node) {
        if (node == null) return 0;

        int leftMax = computeMaxDepth(node.left);
        int rightMax = computeMaxDepth(node.right);

        if (leftMax == -1 || rightMax == -1) return -1;
        if (Math.abs(leftMax - rightMax) > 1) return -1;

        return 1 + Math.max(leftMax, rightMax);
    }
}
