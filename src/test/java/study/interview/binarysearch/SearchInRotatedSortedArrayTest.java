package study.interview.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class SearchInRotatedSortedArrayTest {

    private final SearchInRotatedSortedArray solution = new SearchInRotatedSortedArray();

    @Test
    void findsTargetInRightRotatedHalf() {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};

        int result = solution.search(nums, 0);

        assertEquals(4, result);
    }

    @Test
    void findsTargetInLeftRotatedHalf() {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};

        int result = solution.search(nums, 6);

        assertEquals(2, result);
    }

    @Test
    void returnsNegativeOneWhenTargetIsMissing() {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};

        int result = solution.search(nums, 3);

        assertEquals(-1, result);
    }

    @Test
    void searchesUnrotatedArray() {
        int[] nums = {1, 2, 3, 4, 5, 6};

        assertEquals(0, solution.search(nums, 1));
        assertEquals(5, solution.search(nums, 6));
        assertEquals(-1, solution.search(nums, 7));
    }

    @Test
    void searchesSingleElementArray() {
        int[] nums = {1};

        assertEquals(0, solution.search(nums, 1));
        assertEquals(-1, solution.search(nums, 0));
    }

    @Test
    void searchesTwoElementRotatedArray() {
        int[] nums = {3, 1};

        assertEquals(0, solution.search(nums, 3));
        assertEquals(1, solution.search(nums, 1));
        assertEquals(-1, solution.search(nums, 2));
    }

    @Test
    void findsTargetsAtRotationBoundaries() {
        int[] nums = {30, 40, 50, 5, 10, 20};

        assertEquals(2, solution.search(nums, 50));
        assertEquals(3, solution.search(nums, 5));
    }
}
