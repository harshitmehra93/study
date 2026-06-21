package study.interview.trees;

import study.interview.commons.TreeNode;

/*
Diameter of Binary Tree

Given the root of a binary tree, return the length of the diameter of the tree.

The diameter of a binary tree is the length of the longest path between any two nodes in the tree.

This path may or may not pass through the root.

The length of a path is measured by the number of edges between nodes.

Example
        1
       / \
      2   3
     / \
    4   5

The longest path is:

4 -> 2 -> 5

or:

4 -> 2 -> 1 -> 3

Output:

3

Because the path 4 -> 2 -> 1 -> 3 has 3 edges.

Method signature
public int diameterOfBinaryTree(TreeNode root)
 */
public class DiameterOfBinaryTree {
    int maxDiameter = Integer.MIN_VALUE;

    public int diameterOfBinaryTree(TreeNode root) {
        computeMaxDepth(root);
        return maxDiameter == Integer.MIN_VALUE ? 0 : maxDiameter;
    }

    private int computeMaxDepth(TreeNode node) {
        if (node == null) return 0;

        int maxLeftDepth = computeMaxDepth(node.left);
        int maxRightDepth = computeMaxDepth(node.right);
        maxDiameter = Math.max(maxDiameter, maxLeftDepth + maxRightDepth);

        return 1 + Math.max(maxLeftDepth, maxRightDepth);
    }
}
