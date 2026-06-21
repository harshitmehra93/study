package study.interview.trees;

import java.util.ArrayList;
import study.interview.commons.TreeNode;

/*
Kth Smallest Element in a BST.

This is the right follow-up after Validate BST because it uses the key BST property:

Inorder traversal of a BST gives values in sorted order.
Problem: Kth Smallest Element in a BST

Given the root of a binary search tree and an integer k, return the kth smallest value in the tree.

Example
        3
       / \
      1   4
       \
        2

Input:

k = 1

Output:

1

Because the sorted order is:

[1, 2, 3, 4]

The 1st smallest is 1.

Another example
        5
       / \
      3   6
     / \
    2   4
   /
  1

Input:

k = 3

Output:

3

Sorted order:

[1, 2, 3, 4, 5, 6]

The 3rd smallest is 3.

Method signature
public int kthSmallest(TreeNode root, int k)
 */
public class KthSmallestElementInBST {

    public int kthSmallest(TreeNode root, int k) {
        ArrayList<TreeNode> result = new ArrayList<>();
        inorder(root, result);
        return result.get(k - 1).val;
    }

    private void inorder(TreeNode node, ArrayList<TreeNode> result) {
        if (node == null) return;

        inorder(node.left, result);
        result.add(node);
        inorder(node.right, result);
    }
}
