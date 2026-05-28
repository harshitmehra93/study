package study.neetcode.interview.trees;

import study.neetcode.interview.trees.commons.TreeNode;

/*
Invert Binary Tree

Given the root of a binary tree, invert the tree and return its root.

Invert means swap the left and right child of every node.

Example

Original tree:

      4
    /   \
   2     7
  / \   / \
 1   3 6   9

Inverted tree:

      4
    /   \
   7     2
  / \   / \
 9   6 3   1
Method signature
public TreeNode invertTree(TreeNode root)
 */
public class InvertBinaryTree {
    public TreeNode invertTree(TreeNode root) {
        invert(root);
        return root;
    }

    private void invert(TreeNode node) {
        if (node == null) return;

        var tmp = node.left;
        node.left = node.right;
        node.right = tmp;

        invert(node.left);
        invert(node.right);
    }
}
