package study.neetcode.interview.trees;

import study.neetcode.interview.commons.TreeNode;

/*
Maximum Depth of Binary Tree

Given the root of a binary tree, return its maximum depth.

The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

Example
      3
     / \
    9   20
       /  \
      15   7

Output:

3

Because the longest root-to-leaf path is:

3 -> 20 -> 15

or:

3 -> 20 -> 7

Both have 3 nodes.

Method signature
public int maxDepth(TreeNode root)
 */
public class MaximumDepthOfBinaryTree {
    public int maxDepth(TreeNode node) {
        if (node == null) return 0;
        return 1 + Math.max(maxDepth(node.left), maxDepth(node.right));
    }
}
