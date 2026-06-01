package study.neetcode.interview.heapqueuearray;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class KthLargestElementInAnArrayTest {

    @Test
    void test_givenExample1() {
        KthLargestElementInAnArray test = new KthLargestElementInAnArray();

        int[] nums = {3, 2, 1, 5, 6, 4};

        assertEquals(5, test.findKthLargest(nums, 2));
    }

    @Test
    void test_givenExample2_withDuplicates() {
        KthLargestElementInAnArray test = new KthLargestElementInAnArray();

        int[] nums = {3, 2, 3, 1, 2, 4, 5, 5, 6};

        assertEquals(4, test.findKthLargest(nums, 4));
    }

    @Test
    void test_k1_returnsLargest() {
        KthLargestElementInAnArray test = new KthLargestElementInAnArray();

        int[] nums = {7, 10, 4, 3, 20, 15};

        assertEquals(20, test.findKthLargest(nums, 1));
    }

    @Test
    void test_kEqualsLength_returnsSmallest() {
        KthLargestElementInAnArray test = new KthLargestElementInAnArray();

        int[] nums = {7, 10, 4, 3, 20, 15};

        assertEquals(3, test.findKthLargest(nums, 6));
    }

    @Test
    void test_singleElement() {
        KthLargestElementInAnArray test = new KthLargestElementInAnArray();

        int[] nums = {42};

        assertEquals(42, test.findKthLargest(nums, 1));
    }

    @Test
    void test_negativeNumbers() {
        KthLargestElementInAnArray test = new KthLargestElementInAnArray();

        int[] nums = {-1, -3, -2, -4};

        assertEquals(-2, test.findKthLargest(nums, 2));
    }

    @Test
    void test_duplicatesAreCounted_notDistinct() {
        KthLargestElementInAnArray test = new KthLargestElementInAnArray();

        int[] nums = {5, 5, 5, 4, 4, 3};

        /*
         * Sorted descending:
         * [5, 5, 5, 4, 4, 3]
         *
         * 3rd largest is 5, not 4.
         */
        assertEquals(5, test.findKthLargest(nums, 3));
    }

    @Test
    void test_unsortedArray_middleK() {
        KthLargestElementInAnArray test = new KthLargestElementInAnArray();

        int[] nums = {9, 1, 8, 2, 7, 3, 6, 4, 5};

        /*
         * Sorted descending:
         * [9, 8, 7, 6, 5, 4, 3, 2, 1]
         *
         * 5th largest = 5
         */
        assertEquals(5, test.findKthLargest(nums, 5));
    }
}
