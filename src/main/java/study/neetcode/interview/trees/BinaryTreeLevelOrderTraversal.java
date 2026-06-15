package study.neetcode.interview.trees;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import study.neetcode.interview.commons.TreeNode;

/*
Binary Tree Level Order Traversal

Given the root of a binary tree, return the level order traversal of its nodes’ values.

That means you should return values level by level, from left to right.

Example
        3
       / \
      9   20
         /  \
        15   7

Output:

[
  [3],
  [9, 20],
  [15, 7]
]
Method signature
public List<List<Integer>> levelOrder(TreeNode root)
 */
public class BinaryTreeLevelOrderTraversal {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        if (root == null) return result;

        Queue<TreeNode> q1 = new ArrayDeque<>();
        Queue<TreeNode> q2 = new ArrayDeque<>();
        LinkedList<Integer> level = new LinkedList<>();
        q1.offer(root);
        while (!q1.isEmpty()) {
            var node = q1.poll();

            level.add(node.val);

            if (node.left != null) q2.offer(node.left);
            if (node.right != null) q2.offer(node.right);
            if (q1.isEmpty()) {
                var tmp = q1;
                q1 = q2;
                q2 = tmp;
                result.add(level);
                level = new LinkedList<>();
            }
        }

        return result;
    }
}
