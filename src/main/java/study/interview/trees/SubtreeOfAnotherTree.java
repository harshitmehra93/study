package study.interview.trees;

import study.interview.commons.TreeNode;

/*
Subtree of Another Tree

Given the roots of two binary trees root and subRoot, return true if there is a subtree of root with the same structure and node values as subRoot.

A subtree of root means some node in root and all of its descendants.

Example
root:
        3
       / \
      4   5
     / \
    1   2

subRoot:
      4
     / \
    1   2

Output:

true

Because the subtree rooted at node 4 in root matches subRoot.

Example 2
root:
        3
       / \
      4   5
     / \
    1   2
       /
      0

subRoot:
      4
     / \
    1   2

Output:

false

The values look close, but the structure differs because the 2 node in root has an extra child 0.

Method signature
public boolean isSubtree(TreeNode root, TreeNode subRoot)
 */
public class SubtreeOfAnotherTree {
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (subRoot == null) return true;
        return findSameSubTree(root, subRoot);
    }

    private boolean findSameSubTree(TreeNode p, TreeNode targetRoot) {
        if (p == null) return false;
        if (isSameTree(p, targetRoot)) return true;
        return findSameSubTree(p.left, targetRoot) || findSameSubTree(p.right, targetRoot);
    }

    private boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;

        if (p == null || q == null) return false;
        if (p.val != q.val) return false;

        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
