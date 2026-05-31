package study.neetcode.interview.trees;

import study.neetcode.interview.trees.commons.TreeNode;

/*
Lowest Common Ancestor of Binary Tree

Given the root of a binary tree and two nodes p and q, return their lowest common ancestor.

The lowest common ancestor is the lowest node in the tree that has both p and q as descendants.

A node can be a descendant of itself.

Example
        3
       / \
      5   1
     / \ / \
    6  2 0  8
      / \
     7   4

Input:

p = 5
q = 1

Output:

3

Because 5 is in the left subtree and 1 is in the right subtree.

Another input:

p = 5
q = 4

Output:

5

Because 5 is an ancestor of 4.

Method signature
public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q)
 */
public class LowestCommonAncestor {
    public TreeNode lowestCommonAncestor(TreeNode node, TreeNode p, TreeNode q) {
        if (node == null) return null;
        if (node == p || node == q) return node;

        var leftSearch = lowestCommonAncestor(node.left, p, q);
        var rightSearch = lowestCommonAncestor(node.right, p, q);

        if (leftSearch != null && rightSearch != null) return node;
        return leftSearch == null ? rightSearch : leftSearch;
    }
}
