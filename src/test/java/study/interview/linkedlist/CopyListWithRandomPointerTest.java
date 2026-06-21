package study.interview.linkedlist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import study.interview.commons.ListNodeWithRandomPointer;

class CopyListWithRandomPointerTest {

    private final CopyListWithRandomPointer solution = new CopyListWithRandomPointer();

    @Test
    void copiesNullList() {
        assertNull(solution.copyRandomList(null));
    }

    @Test
    void copiesSingleNodeWithNullRandom() {
        ListNodeWithRandomPointer head = new ListNodeWithRandomPointer(7);

        ListNodeWithRandomPointer copy = solution.copyRandomList(head);

        assertNotSame(head, copy);
        assertEquals(7, copy.val);
        assertNull(copy.next);
        assertNull(copy.randomPointer);
    }

    @Test
    void copiesSingleNodeWithSelfRandom() {
        ListNodeWithRandomPointer head = new ListNodeWithRandomPointer(7);
        head.randomPointer = head;

        ListNodeWithRandomPointer copy = solution.copyRandomList(head);

        assertNotSame(head, copy);
        assertEquals(7, copy.val);
        assertNull(copy.next);
        assertSame(copy, copy.randomPointer);
    }

    @Test
    void copiesListWithForwardBackwardAndNullRandomPointers() {
        ListNodeWithRandomPointer head = list(7, 13, 11, 10, 1);
        List<ListNodeWithRandomPointer> nodes = nodes(head);
        nodes.get(0).randomPointer = null;
        nodes.get(1).randomPointer = nodes.get(0);
        nodes.get(2).randomPointer = nodes.get(4);
        nodes.get(3).randomPointer = nodes.get(2);
        nodes.get(4).randomPointer = nodes.get(0);

        ListNodeWithRandomPointer copy = solution.copyRandomList(head);

        assertDeepCopy(head, copy, new int[] {7, 13, 11, 10, 1}, new Integer[] {null, 0, 4, 2, 0});
    }

    @Test
    void duplicateValuesAreStillCopiedByNodeIdentity() {
        ListNodeWithRandomPointer head = list(5, 5, 5);
        List<ListNodeWithRandomPointer> nodes = nodes(head);
        nodes.get(0).randomPointer = nodes.get(2);
        nodes.get(1).randomPointer = nodes.get(0);
        nodes.get(2).randomPointer = nodes.get(1);

        ListNodeWithRandomPointer copy = solution.copyRandomList(head);

        assertDeepCopy(head, copy, new int[] {5, 5, 5}, new Integer[] {2, 0, 1});
    }

    private ListNodeWithRandomPointer list(int... values) {
        ListNodeWithRandomPointer head = null;
        ListNodeWithRandomPointer tail = null;

        for (int value : values) {
            ListNodeWithRandomPointer node = new ListNodeWithRandomPointer(value);
            if (head == null) {
                head = node;
            } else {
                tail.next = node;
            }
            tail = node;
        }

        return head;
    }

    private List<ListNodeWithRandomPointer> nodes(ListNodeWithRandomPointer head) {
        List<ListNodeWithRandomPointer> result = new ArrayList<>();
        ListNodeWithRandomPointer current = head;

        while (current != null) {
            result.add(current);
            current = current.next;
        }

        return result;
    }

    private void assertDeepCopy(
            ListNodeWithRandomPointer originalHead,
            ListNodeWithRandomPointer copyHead,
            int[] expectedValues,
            Integer[] expectedRandomIndexes) {
        List<ListNodeWithRandomPointer> originals = nodes(originalHead);
        List<ListNodeWithRandomPointer> copies = nodes(copyHead);

        assertEquals(expectedValues.length, originals.size());
        assertEquals(expectedValues.length, copies.size());

        for (int i = 0; i < expectedValues.length; i++) {
            assertNotSame(originals.get(i), copies.get(i));
            assertEquals(expectedValues[i], copies.get(i).val);
        }

        for (int i = 0; i < expectedRandomIndexes.length; i++) {
            Integer randomIndex = expectedRandomIndexes[i];
            if (randomIndex == null) {
                assertNull(copies.get(i).randomPointer);
            } else {
                assertSame(copies.get(randomIndex), copies.get(i).randomPointer);
            }
        }
    }
}
