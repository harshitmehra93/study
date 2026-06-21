package study.interview.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class FindMinimumInRotatedSortedArrayTest {

    private final FindMinimumInRotatedSortedArray solution = new FindMinimumInRotatedSortedArray();

    @Test
    void findsMinimumAfterRotationPoint() {
        int[] nums = {3, 4, 5, 1, 2};

        int result = solution.findMin(nums);

        assertEquals(1, result);
    }

    @Test
    void findsMinimumInLaterRotation() {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};

        int result = solution.findMin(nums);

        assertEquals(0, result);
    }

    @Test
    void returnsFirstElementWhenArrayIsNotRotated() {
        int[] nums = {11, 13, 15, 17};

        int result = solution.findMin(nums);

        assertEquals(11, result);
    }

    @Test
    void handlesSingleElementArray() {
        int[] nums = {7};

        int result = solution.findMin(nums);

        assertEquals(7, result);
    }

    @Test
    void handlesTwoElementArrays() {
        assertEquals(1, solution.findMin(new int[] {2, 1}));
        assertEquals(1, solution.findMin(new int[] {1, 2}));
    }

    @Test
    void findsMinimumWhenRotationPointIsAtLastIndex() {
        int[] nums = {2, 3, 4, 5, 1};

        int result = solution.findMin(nums);

        assertEquals(1, result);
    }
}
