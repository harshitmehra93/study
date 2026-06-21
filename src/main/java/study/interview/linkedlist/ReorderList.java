package study.interview.linkedlist;

import study.interview.commons.ListNode;

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

        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        ListNode secondHead = slow.next;
        slow.next = null;

        // reverse Second List
        secondHead = reverseList(secondHead);

        // interleave
        ListNode tail = head;
        ListNode firstHead = head.next;
        boolean chooseFirst = false;
        while (firstHead != null && secondHead != null) {
            if (chooseFirst) {
                tail.next = firstHead;
                tail = firstHead;
                firstHead = firstHead.next;
            } else {
                tail.next = secondHead;
                tail = secondHead;
                secondHead = secondHead.next;
            }
            chooseFirst = !chooseFirst;
        }
        tail.next = firstHead;
    }

    private static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode nodeA = head;
        ListNode nodeB = head.next;
        ListNode nodeC = head.next.next;
        while (nodeC != null) {
            nodeB.next = nodeA;

            nodeA = nodeB;
            nodeB = nodeC;
            nodeC = nodeC.next;
        }
        nodeB.next = nodeA;
        head.next = null;
        return nodeB;
    }
}
