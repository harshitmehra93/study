package study.interview.linkedlist;

import study.interview.commons.ListNode;

/*
Linked List Cycle
Given the head of a linked list, return true if following next pointers eventually revisits a node.
3 → 2 → 0 → -4
    ↑         |
    └─────────┘
Output:
true
Requirements:
O(n) time
O(1) extra space
Do not modify the list
 */
public class LinkedListCycle {
    public boolean hasCycle(ListNode node) {
        if (node == null) return false;
        ListNode fast = node;
        ListNode slow = node;

        if (node.next == null) return false;
        fast = fast.next.next;
        slow = slow.next;

        while (fast != slow) {
            if (fast == null || fast.next == null || slow == null) return false;
            fast = fast.next.next;
            slow = slow.next;
        }
        return true;
    }
}
