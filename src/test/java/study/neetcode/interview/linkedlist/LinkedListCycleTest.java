package study.neetcode.interview.linkedlist;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.IdentityHashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import study.neetcode.interview.commons.ListNode;

class LinkedListCycleTest {

    private final LinkedListCycle solution = new LinkedListCycle();

    @Test
    void nullListHasNoCycle() {
        assertFalse(solution.hasCycle(null));
    }

    @Test
    void singleNodeWithoutCycle() {
        assertFalse(solution.hasCycle(new ListNode(1)));
    }

    @Test
    void singleNodePointingToItselfHasCycle() {
        ListNode node = new ListNode(1);
        node.next = node;

        assertTrue(solution.hasCycle(node));
    }

    @Test
    void detectsCycleEnteringAtSecondNode() {
        ListNode first = new ListNode(3);
        ListNode second = new ListNode(2);
        ListNode third = new ListNode(0);
        ListNode fourth = new ListNode(-4);
        first.next = second;
        second.next = third;
        third.next = fourth;
        fourth.next = second;

        assertTrue(solution.hasCycle(first));
    }

    @Test
    void detectsCycleEnteringAtHead() {
        ListNode head = list(1, 2, 3, 4, 5);
        tail(head).next = head;

        assertTrue(solution.hasCycle(head));
    }

    @Test
    void detectsTwoNodeCycle() {
        ListNode first = new ListNode(1);
        ListNode second = new ListNode(2);
        first.next = second;
        second.next = first;

        assertTrue(solution.hasCycle(first));
    }

    @Test
    void longAcyclicListHasNoCycle() {
        assertFalse(solution.hasCycle(rangeList(100_000)));
    }

    @Test
    void detectsCycleNearEndOfLongList() {
        ListNode head = rangeList(100_000);
        ListNode cycleEntry = nodeAt(head, 99_990);
        tail(head).next = cycleEntry;

        assertTrue(solution.hasCycle(head));
    }

    @Test
    void doesNotMutateAcyclicList() {
        ListNode head = list(1, 2, 3, 4, 5);
        Map<ListNode, ListNode> originalLinks = snapshotLinks(head, 5);

        assertFalse(solution.hasCycle(head));

        assertLinksUnchanged(originalLinks);
    }

    @Test
    void doesNotMutateCyclicList() {
        ListNode head = list(1, 2, 3, 4, 5);
        tail(head).next = nodeAt(head, 2);
        Map<ListNode, ListNode> originalLinks = snapshotLinks(head, 5);

        assertTrue(solution.hasCycle(head));

        assertLinksUnchanged(originalLinks);
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

    private ListNode rangeList(int length) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        for (int value = 0; value < length; value++) {
            tail.next = new ListNode(value);
            tail = tail.next;
        }

        return dummy.next;
    }

    private ListNode nodeAt(ListNode head, int index) {
        ListNode current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    private ListNode tail(ListNode head) {
        ListNode current = head;
        while (current.next != null) {
            current = current.next;
        }
        return current;
    }

    private Map<ListNode, ListNode> snapshotLinks(ListNode head, int nodeCount) {
        Map<ListNode, ListNode> links = new IdentityHashMap<>();
        ListNode current = head;

        for (int i = 0; i < nodeCount; i++) {
            links.put(current, current.next);
            current = current.next;
        }

        return links;
    }

    private void assertLinksUnchanged(Map<ListNode, ListNode> originalLinks) {
        for (Map.Entry<ListNode, ListNode> entry : originalLinks.entrySet()) {
            assertSame(entry.getValue(), entry.getKey().next);
        }
    }
}
