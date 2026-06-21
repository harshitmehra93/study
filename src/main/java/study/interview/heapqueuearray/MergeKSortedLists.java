package study.interview.heapqueuearray;

import java.util.Comparator;
import java.util.PriorityQueue;
import study.interview.commons.ListNode;

/*
Merge K Sorted Lists

You are given an array of k linked lists.

Each linked list is sorted in ascending order.

Merge all the linked lists into one sorted linked list and return its head.

Example
lists = [
  1 -> 4 -> 5,
  1 -> 3 -> 4,
  2 -> 6
]

Output:

1 -> 1 -> 2 -> 3 -> 4 -> 4 -> 5 -> 6
Method signature
public ListNode mergeKLists(ListNode[] lists)
 */
public class MergeKSortedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        Comparator<ListNode> comp = Comparator.comparingInt(n -> n.val);
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(comp);
        for (ListNode rootOfList : lists) {
            if (rootOfList != null) minHeap.offer(rootOfList);
        }

        ListNode root = minHeap.poll();
        if (root != null && root.next != null) minHeap.offer(root.next);
        ListNode parent = root;
        while (!minHeap.isEmpty()) {
            var node = minHeap.poll();
            parent.next = node;
            parent = node;
            if (node.next != null) minHeap.offer(node.next);
        }
        return root;
    }
}
