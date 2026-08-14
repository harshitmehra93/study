package study.contest.leetcode.weeklycontest419;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import study.interview.commons.TreeNode;

/**
 * LeetCode 3319 — K-th Largest Perfect Subtree Size in Binary Tree
 *
 * <p>Given a binary tree and an integer {@code k}, return the size of its {@code k}-th largest
 * perfect binary subtree, or {@code -1} when fewer than {@code k} perfect subtrees exist. A perfect
 * binary tree has every leaf at the same level and exactly two children at each parent.
 *
 * <p>Examples:
 *
 * <pre>
 * Input: root = [5,3,6,5,2,5,7,1,8,null,null,6,8], k = 2
 * Output: 3
 *
 * Input: root = [1,2,3,4,5,6,7], k = 1
 * Output: 7
 *
 * Input: root = [1,2,3,null,4], k = 3
 * Output: -1
 * </pre>
 *
 * <p>Constraints: the tree contains {@code 1} to {@code 2000} nodes, {@code 1 <= Node.val <= 2000},
 * and {@code 1 <= k <= 1024}.
 *
 * @see <a
 *     href="https://leetcode.com/problems/k-th-largest-perfect-subtree-size-in-binary-tree/">Problem</a>
 */
public class KthLargestPerfectSubtreeSizeInBinaryTree {
    PriorityQueue<Integer> minHeap;
    Map<TreeNode, Integer> depths;
    Map<TreeNode, Integer> sizes;
    int maxHeapSize;

    public int kthLargestPerfectSubtree(TreeNode root, int k) {
        minHeap = new PriorityQueue<Integer>((a, b) -> Integer.compare(a, b));
        maxHeapSize = k;

        depths = new HashMap<>();
        recordMaxDepth(root);

        sizes = new HashMap<>();
        recordSizes(root);

        recordPerfectBinaryTree(root);

        return minHeap.size() < maxHeapSize ? -1 : minHeap.poll();
    }

    int recordMaxDepth(TreeNode node) {
        if (node == null) return 0;
        int left = recordMaxDepth(node.left);
        int right = recordMaxDepth(node.right);
        int maxDepth = 1 + Math.max(left, right);
        depths.put(node, maxDepth);
        return maxDepth;
    }

    int recordSizes(TreeNode node) {
        if (node == null) return 0;
        int left = recordSizes(node.left);
        int right = recordSizes(node.right);
        int size = 1 + left + right;
        sizes.put(node, size);
        return size;
    }

    boolean recordPerfectBinaryTree(TreeNode node) {
        if (node == null) return true;

        boolean isleftPerfect = recordPerfectBinaryTree(node.left);
        boolean isrightPerfect = recordPerfectBinaryTree(node.right);

        if (isleftPerfect && isrightPerfect) {
            if (depths.get(node.left) == depths.get(node.right)) {
                minHeap.offer(sizes.get(node));
                while (minHeap.size() > maxHeapSize) minHeap.poll();
                return true;
            }
        }
        return false;
    }
}
