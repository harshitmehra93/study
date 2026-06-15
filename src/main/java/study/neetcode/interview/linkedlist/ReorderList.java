package study.neetcode.interview.linkedlist;

import java.util.ArrayDeque;
import java.util.Deque;
import study.neetcode.interview.commons.ListNode;

/*
Reorder List
Given:
L0 → L1 → … → Ln-1 → Ln

Reorder it in place to:
L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → …

You may not modify node values, only links.
Example:
Input:  1 → 2 → 3 → 4 → 5
Output: 1 → 5 → 2 → 4 → 3
 */
public class ReorderList {

    public void reorderList(ListNode head) {
        if (head == null) return;

        Deque<ListNode> stack = new ArrayDeque<>();
        var tmp = head;
        int count = 0;
        while (tmp != null) {
            stack.push(tmp);
            tmp = tmp.next;
            count++;
        }
        if (count < 3) return;

        ListNode nodeA = head;
        ListNode nodeB = head.next;
        while (nodeA != stack.peek() && nodeB != stack.peek()) {
            nodeA.next = stack.pop();
            nodeA.next.next = nodeB;
            nodeA = nodeB;
            nodeB = nodeA.next;
        }
        if (count % 2 == 0) nodeB.next = null;
        else nodeA.next = null;
    }
}
