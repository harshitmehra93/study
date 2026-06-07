package study.neetcode.interview.greedy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class MaximumSubarrayTest {

    @Test
    void example1() {
        MaximumSubarray solution = new MaximumSubarray();

        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};

        assertEquals(6, solution.maxSubArray(nums));
    }

    @Test
    void singlePositiveNumber() {
        MaximumSubarray solution = new MaximumSubarray();

        int[] nums = {5};

        assertEquals(5, solution.maxSubArray(nums));
    }

    @Test
    void singleNegativeNumber() {
        MaximumSubarray solution = new MaximumSubarray();

        int[] nums = {-5};

        assertEquals(-5, solution.maxSubArray(nums));
    }

    @Test
    void allPositiveNumbers() {
        MaximumSubarray solution = new MaximumSubarray();

        int[] nums = {1, 2, 3, 4};

        assertEquals(10, solution.maxSubArray(nums));
    }

    @Test
    void allNegativeNumbers() {
        MaximumSubarray solution = new MaximumSubarray();

        int[] nums = {-8, -3, -6, -2, -5, -4};

        assertEquals(-2, solution.maxSubArray(nums));
    }

    @Test
    void bestSubarrayIsAtBeginning() {
        MaximumSubarray solution = new MaximumSubarray();

        int[] nums = {5, 4, -10, 1, 2};

        assertEquals(9, solution.maxSubArray(nums));
    }

    @Test
    void bestSubarrayIsAtEnd() {
        MaximumSubarray solution = new MaximumSubarray();

        int[] nums = {-5, -2, 3, 4};

        assertEquals(7, solution.maxSubArray(nums));
    }

    @Test
    void bestSubarrayIsInMiddle() {
        MaximumSubarray solution = new MaximumSubarray();

        int[] nums = {-2, -1, 4, -1, 2, 1, -5};

        assertEquals(6, solution.maxSubArray(nums));
    }

    @Test
    void zerosMixedWithNegatives() {
        MaximumSubarray solution = new MaximumSubarray();

        int[] nums = {-2, 0, -1};

        assertEquals(0, solution.maxSubArray(nums));
    }

    @Test
    void negativeDipStillWorthKeepingPreviousSubarray() {
        MaximumSubarray solution = new MaximumSubarray();

        int[] nums = {4, -1, 2, 1};

        assertEquals(6, solution.maxSubArray(nums));
    }

    @Test
    void largeNegativeResetsSubarray() {
        MaximumSubarray solution = new MaximumSubarray();

        int[] nums = {3, -100, 4, 5};

        assertEquals(9, solution.maxSubArray(nums));
    }
}
