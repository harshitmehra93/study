package study.neetcode.interview.trees;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import study.neetcode.interview.trees.commons.TreeNode;

/*
Binary Tree Right Side View

Given the root of a binary tree, imagine you are standing on the right side of the tree.

Return the values of the nodes you can see, ordered from top to bottom.

Example
        1
       / \
      2   3
       \   \
        5   4

Output:

[1, 3, 4]

Why?

From the right side:

Level 0: see 1
Level 1: see 3
Level 2: see 4
Another example
        1
       /
      2
     /
    3

Output:

[1, 2, 3]

Even though there is no right child, from the right side you still see the only node at each level.

Method signature
public List<Integer> rightSideView(TreeNode root)
 */
public class BinaryTreeRightSideView {
    public List<Integer> rightSideView(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int currentSize = q.size();
            for (int i = 0; i < currentSize; i++) {
                var node = q.poll();
                if (i == currentSize - 1) result.add(node.val);
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }
        }

        return result;
    }
}
