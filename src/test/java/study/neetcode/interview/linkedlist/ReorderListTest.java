package study.neetcode.interview.linkedlist;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;
import org.junit.jupiter.api.Test;
import study.neetcode.interview.commons.ListNode;

class ReorderListTest {

    private final ReorderList solution = new ReorderList();

    @Test
    void nullHeadRemainsNull() {
        solution.reorderList(null);
    }

    @Test
    void singleNodeRemainsUnchanged() {
        ListNode head = new ListNode(1);

        solution.reorderList(head);

        assertEquals(1, head.val);
        assertNull(head.next);
    }

    @Test
    void twoNodesRemainInOriginalOrder() {
        ListNode head = list(1, 2);

        solution.reorderList(head);

        assertValues(head, 1, 2);
    }

    @Test
    void reordersEvenLengthList() {
        ListNode head = list(1, 2, 3, 4);

        solution.reorderList(head);

        assertValues(head, 1, 4, 2, 3);
    }

    @Test
    void reordersOddLengthList() {
        ListNode head = list(1, 2, 3, 4, 5);

        solution.reorderList(head);

        assertValues(head, 1, 5, 2, 4, 3);
    }

    @Test
    void reordersLongerEvenLengthList() {
        ListNode head = list(1, 2, 3, 4, 5, 6);

        solution.reorderList(head);

        assertValues(head, 1, 6, 2, 5, 3, 4);
    }

    @Test
    void reusesEveryOriginalNodeExactlyOnce() {
        ListNode head = list(10, 20, 30, 40, 50, 60, 70);
        Set<ListNode> originalNodes = identitySet(head);

        solution.reorderList(head);

        Set<ListNode> reorderedNodes = identitySet(head);
        assertEquals(originalNodes.size(), reorderedNodes.size());
        assertTrue(reorderedNodes.containsAll(originalNodes));
        assertTrue(originalNodes.containsAll(reorderedNodes));
        assertValues(head, 10, 70, 20, 60, 30, 50, 40);
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

    private Set<ListNode> identitySet(ListNode head) {
        Set<ListNode> nodes = Collections.newSetFromMap(new IdentityHashMap<>());
        ListNode current = head;

        while (current != null) {
            assertTrue(nodes.add(current), "List contains a cycle or repeated node");
            current = current.next;
        }

        return nodes;
    }

    private void assertValues(ListNode head, int... expected) {
        int[] actual = new int[expected.length];
        ListNode current = head;
        Set<ListNode> visited = Collections.newSetFromMap(new IdentityHashMap<>());

        for (int i = 0; i < expected.length; i++) {
            assertTrue(current != null, "List contains fewer nodes than expected");
            assertTrue(visited.add(current), "List contains a cycle or repeated node");
            actual[i] = current.val;
            current = current.next;
        }

        assertNull(current, "List contains extra nodes or a cycle");
        assertArrayEquals(expected, actual);
    }
}
