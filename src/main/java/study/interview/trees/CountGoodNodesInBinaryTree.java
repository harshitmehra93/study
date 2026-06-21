package study.interview.trees;

import study.interview.commons.TreeNode;

/*
 Count Good Nodes in Binary Tree

Given the root of a binary tree, return the number of good nodes.

A node X in the tree is called good if, on the path from the root to X, there is no node with a value greater than X.val.

In other words:

node is good if node.val >= maximum value seen so far on the path from root
Example
        3
       / \
      1   4
     /   / \
    3   1   5

Good nodes:

3  → root, always good
4  → max so far was 3, and 4 >= 3
5  → max so far was 4, and 5 >= 4
3  → path 3 -> 1 -> 3, max was 3, and 3 >= 3

Output:

4
Method signature
public int goodNodes(TreeNode root)
 */
public class CountGoodNodesInBinaryTree {
    int counter;

    public int goodNodes(TreeNode root) {
        counter = 0;
        countGoodNodes(root, Integer.MIN_VALUE);
        return counter;
    }

    private void countGoodNodes(TreeNode node, int maxValue) {
        if (node == null) return;

        if (node.val >= maxValue) {
            counter++;
            maxValue = node.val;
        }

        countGoodNodes(node.left, maxValue);
        countGoodNodes(node.right, maxValue);
    }
}
