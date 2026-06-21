package study.interview.linkedlist;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;
import org.junit.jupiter.api.Test;
import study.interview.commons.ListNode;

class MergeTwoSortedListsTest {

    private final MergeTwoSortedLists solution = new MergeTwoSortedLists();

    @Test
    void mergesGivenExample() {
        ListNode merged = solution.mergeTwoLists(list(1, 2, 4), list(1, 3, 4));

        assertValues(merged, 1, 1, 2, 3, 4, 4);
    }

    @Test
    void bothListsNull() {
        assertNull(solution.mergeTwoLists(null, null));
    }

    @Test
    void firstListNullReturnsSecondList() {
        ListNode second = list(1, 2, 3);

        assertSame(second, solution.mergeTwoLists(null, second));
    }

    @Test
    void secondListNullReturnsFirstList() {
        ListNode first = list(1, 2, 3);

        assertSame(first, solution.mergeTwoLists(first, null));
    }

    @Test
    void listsDoNotOverlapInValueRange() {
        ListNode merged = solution.mergeTwoLists(list(1, 2, 3), list(10, 20, 30));

        assertValues(merged, 1, 2, 3, 10, 20, 30);
    }

    @Test
    void listsInterleaveCompletely() {
        ListNode merged = solution.mergeTwoLists(list(1, 3, 5, 7), list(2, 4, 6, 8));

        assertValues(merged, 1, 2, 3, 4, 5, 6, 7, 8);
    }

    @Test
    void handlesDuplicatesAndNegativeValues() {
        ListNode merged = solution.mergeTwoLists(list(-5, -1, 2, 2), list(-5, 0, 2, 9));

        assertValues(merged, -5, -5, -1, 0, 2, 2, 2, 9);
    }

    @Test
    void reusesEveryOriginalNodeExactlyOnce() {
        ListNode first = list(1, 4, 7);
        ListNode second = list(2, 3, 8);
        Set<ListNode> originalNodes = identitySet(first, second);

        ListNode merged = solution.mergeTwoLists(first, second);
        Set<ListNode> mergedNodes = identitySet(merged);

        assertEquals(originalNodes.size(), mergedNodes.size());
        assertTrue(mergedNodes.containsAll(originalNodes));
        assertTrue(originalNodes.containsAll(mergedNodes));
    }

    @Test
    void longListsRemainSortedAndContainEveryNode() {
        int nodesPerList = 5_000;
        ListNode even = arithmeticList(0, 2, nodesPerList);
        ListNode odd = arithmeticList(1, 2, nodesPerList);

        ListNode current = solution.mergeTwoLists(even, odd);
        for (int expected = 0; expected < nodesPerList * 2; expected++) {
            assertFalse(current == null, "List ended before value " + expected);
            assertEquals(expected, current.val);
            current = current.next;
        }
        assertNull(current, "Merged list contains extra nodes or a cycle");
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

    private ListNode arithmeticList(int start, int step, int length) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        for (int i = 0; i < length; i++) {
            tail.next = new ListNode(start + i * step);
            tail = tail.next;
        }

        return dummy.next;
    }

    private Set<ListNode> identitySet(ListNode... heads) {
        Set<ListNode> nodes = Collections.newSetFromMap(new IdentityHashMap<>());

        for (ListNode head : heads) {
            ListNode current = head;
            while (current != null) {
                assertTrue(nodes.add(current), "List contains a cycle or repeated node");
                current = current.next;
            }
        }

        return nodes;
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
