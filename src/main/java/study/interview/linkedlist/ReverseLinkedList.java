package study.interview.linkedlist;

import study.interview.commons.ListNode;

/*
Reverse Linked List. The monotonic-stack block is complete; don’t force more recall today.
Given the head of a singly linked list, reverse it and return the new head.
1 → 2 → 3 → 4 → null

becomes

4 → 3 → 2 → 1 → null
 */
public class ReverseLinkedList {
    public ListNode reverseList(ListNode head) {
        if (head == null) return null;
        ListNode A = head;
        ListNode B = head.next;
        if (B == null) return head;
        ListNode C = B.next;
        while (C != null) {
            B.next = A;

            A = B;
            var tmp = C.next;
            B = C;
            C = tmp;
        }
        B.next = A;
        head.next = null;
        return B;
    }
}
