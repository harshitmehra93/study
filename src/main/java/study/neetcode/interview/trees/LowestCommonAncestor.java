package study.neetcode.interview.trees;

import java.util.ArrayList;
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
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        ArrayList<TreeNode> pathOfP = new ArrayList<>();
        findPathOfX(root, p, pathOfP, new ArrayList<>());

        ArrayList<TreeNode> pathOfQ = new ArrayList<>();
        findPathOfX(root, q, pathOfQ, new ArrayList<>());

        // Find last common TreeNode
        TreeNode lca = null;
        var minSize = Math.min(pathOfQ.size(),pathOfP.size());
        for (int i = 0; i < minSize; i++) {
            if (pathOfP.get(i) == pathOfQ.get(i)) {
                lca = pathOfP.get(i);
            } else break;
        }
        return lca;
    }

    private void findPathOfX(
            TreeNode node,
            TreeNode target,
            ArrayList<TreeNode> finalPath,
            ArrayList<TreeNode> currentPath) {
        if (finalPath.size() > 0) return;
        if (node == null) return;

        currentPath.add(node);

        if (node == target) {
            finalPath.addAll(currentPath);
            return;
        }

        findPathOfX(node.left, target, finalPath, currentPath);
        findPathOfX(node.right, target, finalPath, currentPath);

        currentPath.remove(currentPath.size() - 1);
    }
}
