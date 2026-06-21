package study.interview.linkedlist;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;
import study.interview.commons.ListNode;

class ReverseLinkedListTest {

    private final ReverseLinkedList solution = new ReverseLinkedList();

    @Test
    void reversesTypicalList() {
        ListNode head = list(1, 2, 3, 4, 5);

        ListNode reversed = solution.reverseList(head);

        assertValues(reversed, 5, 4, 3, 2, 1);
    }

    @Test
    void nullHeadRemainsNull() {
        assertNull(solution.reverseList(null));
    }

    @Test
    void singleNodeIsReturnedUnchanged() {
        ListNode head = new ListNode(42);

        ListNode reversed = solution.reverseList(head);

        assertSame(head, reversed);
        assertNull(reversed.next);
    }

    @Test
    void reversesTwoNodes() {
        ListNode head = list(1, 2);

        ListNode reversed = solution.reverseList(head);

        assertValues(reversed, 2, 1);
    }

    @Test
    void handlesDuplicateAndNegativeValues() {
        ListNode head = list(-3, 7, 7, 0, -3);

        ListNode reversed = solution.reverseList(head);

        assertValues(reversed, -3, 0, 7, 7, -3);
    }

    @Test
    void reusesOriginalNodesAndMakesOldHeadTheTail() {
        ListNode first = new ListNode(10);
        ListNode second = new ListNode(20);
        ListNode third = new ListNode(30);
        first.next = second;
        second.next = third;

        ListNode reversed = solution.reverseList(first);

        assertSame(third, reversed);
        assertSame(second, reversed.next);
        assertSame(first, reversed.next.next);
        assertNull(first.next);
    }

    @Test
    void reversingTwiceRestoresOriginalNodeOrder() {
        ListNode first = new ListNode(1);
        ListNode second = new ListNode(2);
        ListNode third = new ListNode(3);
        first.next = second;
        second.next = third;

        ListNode restored = solution.reverseList(solution.reverseList(first));

        assertSame(first, restored);
        assertSame(second, restored.next);
        assertSame(third, restored.next.next);
        assertNull(third.next);
    }

    @Test
    void reversesLongListWithoutLosingNodes() {
        int length = 10_000;
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        for (int value = 0; value < length; value++) {
            tail.next = new ListNode(value);
            tail = tail.next;
        }

        ListNode current = solution.reverseList(dummy.next);
        for (int expected = length - 1; expected >= 0; expected--) {
            assertNotNull(current);
            assertEquals(expected, current.val);
            current = current.next;
        }
        assertNull(current);
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
            assertNotNull(current, "List contains fewer nodes than expected");
            actual[i] = current.val;
            current = current.next;
        }

        assertNull(current, "List contains more nodes than expected or has a cycle");
        assertArrayEquals(expected, actual);
    }
}
