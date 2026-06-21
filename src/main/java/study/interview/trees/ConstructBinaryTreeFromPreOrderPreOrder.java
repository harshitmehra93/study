package study.interview.trees;

import study.interview.commons.TreeNode;

/*
Construct Binary Tree from Preorder and Inorder Traversal.

This is a harder tree problem than the last few, so treat it slowly. It teaches tree construction from traversal arrays.

Problem

Given two integer arrays preorder and inorder, construct and return the binary tree.

Traversal meaning
Preorder  = root, left, right
Inorder   = left, root, right
Example
preorder = [3, 9, 20, 15, 7]
inorder  = [9, 3, 15, 20, 7]

Tree:

        3
       / \
      9   20
         /  \
        15   7

Output: root of this tree.

Method signature
public TreeNode buildTree(int[] preorder, int[] inorder)
 */
public class ConstructBinaryTreeFromPreOrderPreOrder {
    int rootIndex;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        rootIndex = -1;
        return buildTreeFromArray(preorder, inorder, 0, inorder.length - 1);
    }

    private TreeNode buildTreeFromArray(int[] preorder, int[] inorder, int i, int j) {
        if (i > j || i < 0 || j < 0 || i >= preorder.length || j >= preorder.length) return null;

        rootIndex++;
        int nextRoot = preorder[rootIndex];
        var root = new TreeNode(nextRoot);

        int indexOfRootInInorder = indexOfRootInInorder(inorder, nextRoot);

        root.left = buildTreeFromArray(preorder, inorder, i, indexOfRootInInorder - 1);
        root.right = buildTreeFromArray(preorder, inorder, indexOfRootInInorder + 1, j);

        return root;
    }

    private int indexOfRootInInorder(int[] inorder, int nextRoot) {
        int i = 0;
        for (; i < inorder.length; i++) {
            if (inorder[i] == nextRoot) break;
        }
        return i;
    }
}
