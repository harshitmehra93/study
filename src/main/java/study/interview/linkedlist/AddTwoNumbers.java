package study.interview.linkedlist;

import study.interview.commons.ListNode;

/*
Add Two Numbers
You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order, and each node contains a single digit.
Add the two numbers and return the sum as a linked list, also in reverse order.
You may assume the two numbers do not contain leading zeroes, except the number 0 itself.
Example:
Input:  l1 = [2,4,3], l2 = [5,6,4]
Number: 342 + 465 = 807
Output: [7,0,8]
 */
public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode node1, ListNode node2) {
        int number = 0;
        int carry = 0;
        ListNode tail = new ListNode(0);
        var dummy = tail;

        while (node1 != null || node2 != null) {
            number = 0;
            if (node1 != null) {
                number = node1.val;
                node1 = node1.next;
            }
            if (node2 != null) {
                number += node2.val;
                node2 = node2.next;
            }
            number += carry;

            carry = number / 10;

            ListNode node = new ListNode();
            tail.next = node;
            node.val = number % 10;
            tail = node;
        }
        if (carry != 0) tail.next = new ListNode(carry);
        return dummy.next;
    }
}
