package study.interview.heapqueuearray;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import study.interview.commons.ListNode;

class MergeKSortedListsTest {

    @Test
    void test_givenExample() {
        MergeKSortedLists test = new MergeKSortedLists();

        ListNode[] lists = {list(1, 4, 5), list(1, 3, 4), list(2, 6)};

        ListNode result = test.mergeKLists(lists);

        assertListEquals(new int[] {1, 1, 2, 3, 4, 4, 5, 6}, result);
    }

    @Test
    void test_emptyArray_returnsNull() {
        MergeKSortedLists test = new MergeKSortedLists();

        ListNode result = test.mergeKLists(new ListNode[] {});

        assertNull(result);
    }

    @Test
    void test_arrayWithOnlyNullLists_returnsNull() {
        MergeKSortedLists test = new MergeKSortedLists();

        ListNode[] lists = {null, null, null};

        ListNode result = test.mergeKLists(lists);

        assertNull(result);
    }

    @Test
    void test_singleList_returnsSameSortedValues() {
        MergeKSortedLists test = new MergeKSortedLists();

        ListNode[] lists = {list(1, 2, 3)};

        ListNode result = test.mergeKLists(lists);

        assertListEquals(new int[] {1, 2, 3}, result);
    }

    @Test
    void test_someListsAreNull() {
        MergeKSortedLists test = new MergeKSortedLists();

        ListNode[] lists = {null, list(1, 3, 5), null, list(2, 4, 6)};

        ListNode result = test.mergeKLists(lists);

        assertListEquals(new int[] {1, 2, 3, 4, 5, 6}, result);
    }

    @Test
    void test_listsWithDuplicates() {
        MergeKSortedLists test = new MergeKSortedLists();

        ListNode[] lists = {list(1, 1, 2), list(1, 3), list(2, 2, 4)};

        ListNode result = test.mergeKLists(lists);

        assertListEquals(new int[] {1, 1, 1, 2, 2, 2, 3, 4}, result);
    }

    @Test
    void test_negativeNumbers() {
        MergeKSortedLists test = new MergeKSortedLists();

        ListNode[] lists = {list(-10, -5, 0), list(-6, -3, 2), list(-8, 1)};

        ListNode result = test.mergeKLists(lists);

        assertListEquals(new int[] {-10, -8, -6, -5, -3, 0, 1, 2}, result);
    }

    @Test
    void test_listsOfDifferentLengths() {
        MergeKSortedLists test = new MergeKSortedLists();

        ListNode[] lists = {list(1), list(0, 2, 4, 6, 8), list(3, 5)};

        ListNode result = test.mergeKLists(lists);

        assertListEquals(new int[] {0, 1, 2, 3, 4, 5, 6, 8}, result);
    }

    private ListNode list(int... values) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        for (int value : values) {
            current.next = new ListNode(value);
            current = current.next;
        }

        return dummy.next;
    }

    private void assertListEquals(int[] expected, ListNode actual) {
        ListNode current = actual;

        for (int value : expected) {
            assertNotNull(current, "List ended early. Expected value: " + value);
            assertEquals(value, current.val);
            current = current.next;
        }

        assertNull(current, "List has extra nodes after expected values.");
    }
}
