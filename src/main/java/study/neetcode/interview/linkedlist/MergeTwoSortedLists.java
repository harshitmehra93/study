package study.neetcode.interview.linkedlist;

import study.neetcode.interview.commons.ListNode;

public class MergeTwoSortedLists {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        ListNode currentA = list1;
        ListNode currentB = list2;
        ListNode tail;
        ListNode head;
        if (currentA.val <= currentB.val) {
            head = currentA;
            tail = currentA;
            currentA = currentA.next;
        } else {
            head = currentB;
            tail = currentB;
            currentB = currentB.next;
        }

        while (currentA != null && currentB != null) {
            if (currentA.val <= currentB.val) {
                tail.next = currentA;
                tail = currentA;
                currentA = currentA.next;
            } else {
                tail.next = currentB;
                tail = currentB;
                currentB = currentB.next;
            }
        }
        tail.next = currentA == null ? currentB : currentA;
        return head;
    }
}
