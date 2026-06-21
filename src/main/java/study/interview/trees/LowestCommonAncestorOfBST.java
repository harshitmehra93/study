package study.interview.trees;

import study.interview.commons.TreeNode;

/*
Lowest Common Ancestor of a BST

Given a binary search tree root and two nodes p and q, return their lowest common ancestor.

The lowest common ancestor is the lowest node in the tree that has both p and q as descendants.

A node can be a descendant of itself.

BST property

For every node:

left subtree values < node.val < right subtree values
Example
        6
       / \
      2   8
     / \ / \
    0  4 7  9
      / \
     3   5
Input
p = 2
q = 8

Output:

6

Because 2 is on the left side of 6, and 8 is on the right side of 6.

Another input
p = 2
q = 4

Output:

2

Because 2 is an ancestor of 4, and a node can be ancestor of itself.

Method signature
public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q)
 */
public class LowestCommonAncestorOfBST {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        int min = Math.min(p.val, q.val);
        int max = Math.max(p.val, q.val);
        return findFirstNodeInRange(root, min, max);
    }

    private TreeNode findFirstNodeInRange(TreeNode node, int min, int max) {
        if (node == null) return null;
        int current = node.val;
        if (current == max || current == min) return node;
        if (current > min && current < max) return node;
        return current < min
                ? findFirstNodeInRange(node.right, min, max)
                : findFirstNodeInRange(node.left, min, max);
    }
}
