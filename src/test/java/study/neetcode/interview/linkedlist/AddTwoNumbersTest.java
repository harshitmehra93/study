package study.neetcode.interview.linkedlist;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import study.neetcode.interview.commons.ListNode;

class AddTwoNumbersTest {

    private final AddTwoNumbers solution = new AddTwoNumbers();

    @Test
    void addsGivenExample() {
        ListNode result = solution.addTwoNumbers(list(2, 4, 3), list(5, 6, 4));

        assertValues(result, 7, 0, 8);
    }

    @Test
    void addsTwoZeroes() {
        ListNode result = solution.addTwoNumbers(list(0), list(0));

        assertValues(result, 0);
    }

    @Test
    void addsDifferentLengthListsWithoutFinalCarry() {
        ListNode result = solution.addTwoNumbers(list(1, 8), list(0));

        assertValues(result, 1, 8);
    }

    @Test
    void carriesAcrossMultipleDigits() {
        ListNode result = solution.addTwoNumbers(list(9, 9, 9, 9, 9, 9, 9), list(9, 9, 9, 9));

        assertValues(result, 8, 9, 9, 9, 0, 0, 0, 1);
    }

    @Test
    void createsFinalCarryNode() {
        ListNode result = solution.addTwoNumbers(list(5), list(5));

        assertValues(result, 0, 1);
    }

    @Test
    void continuesAfterOneListEndsWithCarry() {
        ListNode result = solution.addTwoNumbers(list(9, 9), list(1));

        assertValues(result, 0, 0, 1);
    }

    @Test
    void handlesNumbersTooLargeForPrimitiveIntegers() {
        ListNode result = solution.addTwoNumbers(list(9, 9, 9, 9, 9, 9, 9, 9, 9, 9), list(1));

        assertValues(result, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1);
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
