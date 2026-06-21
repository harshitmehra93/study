package study.interview.linkedlist;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;
import study.interview.commons.ListNode;

class RemoveNthNodeFromEndOfListTest {

    private final RemoveNthNodeFromEndOfList solution = new RemoveNthNodeFromEndOfList();

    @Test
    void removesMiddleNodeFromGivenExample() {
        ListNode head = list(1, 2, 3, 4, 5);

        ListNode result = solution.removeNthFromEnd(head, 2);

        assertSame(head, result);
        assertValues(result, 1, 2, 3, 5);
    }

    @Test
    void removesOnlyNode() {
        ListNode head = new ListNode(1);

        ListNode result = solution.removeNthFromEnd(head, 1);

        assertNull(result);
    }

    @Test
    void removesHeadWhenNEqualsLength() {
        ListNode head = list(1, 2);
        ListNode second = head.next;

        ListNode result = solution.removeNthFromEnd(head, 2);

        assertSame(second, result);
        assertValues(result, 2);
    }

    @Test
    void removesTailWhenNIsOne() {
        ListNode head = list(1, 2);

        ListNode result = solution.removeNthFromEnd(head, 1);

        assertSame(head, result);
        assertValues(result, 1);
    }

    @Test
    void removesHeadFromLongerList() {
        ListNode head = list(1, 2, 3);
        ListNode second = head.next;

        ListNode result = solution.removeNthFromEnd(head, 3);

        assertSame(second, result);
        assertValues(result, 2, 3);
    }

    @Test
    void removesTail() {
        ListNode head = list(1, 2, 3);
        ListNode second = head;

        ListNode result = solution.removeNthFromEnd(head, 1);

        assertSame(second, result);
        assertValues(result, 1, 2);
    }

    @Test
    void keepsRemainingOriginalNodesInOrder() {
        ListNode first = new ListNode(10);
        ListNode second = new ListNode(20);
        ListNode third = new ListNode(30);
        ListNode fourth = new ListNode(40);
        first.next = second;
        second.next = third;
        third.next = fourth;

        ListNode result = solution.removeNthFromEnd(first, 2);

        assertSame(first, result);
        assertSame(second, result.next);
        assertSame(fourth, result.next.next);
        assertNull(result.next.next.next);
    }

    private ListNode list(int... values) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        for (int value : values) {
            tail.next = new ListNode(value);
            tail = tail.next;
        }

        return dummy.next;
    }

    private void assertValues(ListNode head, int... expected) {
        int[] actual = new int[expected.length];
        ListNode current = head;

        for (int i = 0; i < expected.length; i++) {
            assertFalse(current == null, "List contains fewer nodes than expected");
            actual[i] = current.val;
            current = current.next;
        }

        assertNull(current, "List contains extra nodes or a cycle");
        assertArrayEquals(expected, actual);
    }
}
