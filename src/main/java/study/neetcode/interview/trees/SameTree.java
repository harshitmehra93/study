package study.neetcode.interview.trees;

import study.neetcode.interview.trees.commons.TreeNode;

/*
Same Tree

Given the roots of two binary trees p and q, return true if they are the same tree.

Two binary trees are considered the same if:

1. They have the same structure.
2. Corresponding nodes have the same value.
Example 1
p:
    1
   / \
  2   3

q:
    1
   / \
  2   3

Output:

true
Example 2
p:
    1
   /
  2

q:
    1
     \
      2

Output:

false

Same values, different structure.

Method signature
public boolean isSameTree(TreeNode p, TreeNode q)
 */
public class SameTree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;

        if (p.val != q.val) return false;

        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
