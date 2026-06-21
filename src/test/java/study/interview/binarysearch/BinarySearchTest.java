package study.interview.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class BinarySearchTest {

    private final BinarySearch solution = new BinarySearch();

    @Test
    void returnsIndexWhenTargetExistsInMiddle() {
        int[] nums = {-1, 0, 3, 5, 9, 12};

        int result = solution.search(nums, 5);

        assertEquals(3, result);
    }

    @Test
    void returnsIndexWhenTargetIsFirstElement() {
        int[] nums = {-1, 0, 3, 5, 9, 12};

        int result = solution.search(nums, -1);

        assertEquals(0, result);
    }

    @Test
    void returnsIndexWhenTargetIsLastElement() {
        int[] nums = {-1, 0, 3, 5, 9, 12};

        int result = solution.search(nums, 12);

        assertEquals(5, result);
    }

    @Test
    void returnsNegativeOneWhenTargetIsMissingInsideRange() {
        int[] nums = {-1, 0, 3, 5, 9, 12};

        int result = solution.search(nums, 2);

        assertEquals(-1, result);
    }

    @Test
    void returnsNegativeOneWhenTargetIsOutsideRange() {
        int[] nums = {-1, 0, 3, 5, 9, 12};

        assertEquals(-1, solution.search(nums, -2));
        assertEquals(-1, solution.search(nums, 13));
    }

    @Test
    void searchesSingleElementArray() {
        int[] nums = {7};

        assertEquals(0, solution.search(nums, 7));
        assertEquals(-1, solution.search(nums, 3));
    }

    @Test
    void returnsNegativeOneForEmptyArray() {
        int[] nums = {};

        int result = solution.search(nums, 1);

        assertEquals(-1, result);
    }

    @Test
    void handlesTwoElementArray() {
        int[] nums = {2, 5};

        assertEquals(0, solution.search(nums, 2));
        assertEquals(1, solution.search(nums, 5));
        assertEquals(-1, solution.search(nums, 4));
    }
}
