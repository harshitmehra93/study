package study.interview.stack;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

class NextGreaterElementITest {

    @Test
    void example1() {
        NextGreaterElementI solution = new NextGreaterElementI();

        int[] nums1 = {4, 1, 2};
        int[] nums2 = {1, 3, 4, 2};

        assertArrayEquals(new int[] {-1, 3, -1}, solution.nextGreaterElement(nums1, nums2));
    }

    @Test
    void example2() {
        NextGreaterElementI solution = new NextGreaterElementI();

        int[] nums1 = {2, 4};
        int[] nums2 = {1, 2, 3, 4};

        assertArrayEquals(new int[] {3, -1}, solution.nextGreaterElement(nums1, nums2));
    }

    @Test
    void allElementsHaveNextGreater() {
        NextGreaterElementI solution = new NextGreaterElementI();

        int[] nums1 = {1, 2, 3};
        int[] nums2 = {1, 2, 3, 4};

        assertArrayEquals(new int[] {2, 3, 4}, solution.nextGreaterElement(nums1, nums2));
    }

    @Test
    void noElementsHaveNextGreater() {
        NextGreaterElementI solution = new NextGreaterElementI();

        int[] nums1 = {4, 3, 2};
        int[] nums2 = {4, 3, 2, 1};

        assertArrayEquals(new int[] {-1, -1, -1}, solution.nextGreaterElement(nums1, nums2));
    }

    @Test
    void nextGreaterIsNotNecessarilyImmediateNeighbor() {
        NextGreaterElementI solution = new NextGreaterElementI();

        int[] nums1 = {2};
        int[] nums2 = {2, 1, 3};

        assertArrayEquals(new int[] {3}, solution.nextGreaterElement(nums1, nums2));
    }

    @Test
    void greaterElementBeforeItDoesNotCount() {
        NextGreaterElementI solution = new NextGreaterElementI();

        int[] nums1 = {2};
        int[] nums2 = {3, 2, 1};

        assertArrayEquals(new int[] {-1}, solution.nextGreaterElement(nums1, nums2));
    }

    @Test
    void mixedCase() {
        NextGreaterElementI solution = new NextGreaterElementI();

        int[] nums1 = {1, 5, 3};
        int[] nums2 = {1, 4, 2, 5, 3, 6};

        assertArrayEquals(new int[] {4, 6, 6}, solution.nextGreaterElement(nums1, nums2));
    }

    @Test
    void nums1HasSingleElementWithNoGreater() {
        NextGreaterElementI solution = new NextGreaterElementI();

        int[] nums1 = {7};
        int[] nums2 = {7};

        assertArrayEquals(new int[] {-1}, solution.nextGreaterElement(nums1, nums2));
    }

    @Test
    void nums1OrderShouldBePreserved() {
        NextGreaterElementI solution = new NextGreaterElementI();

        int[] nums1 = {3, 1, 4};
        int[] nums2 = {1, 3, 4, 2};

        assertArrayEquals(new int[] {4, 3, -1}, solution.nextGreaterElement(nums1, nums2));
    }

    @Test
    void largerNumbersMixedOrder() {
        NextGreaterElementI solution = new NextGreaterElementI();

        int[] nums1 = {10, 5, 20};
        int[] nums2 = {10, 7, 5, 30, 20};

        assertArrayEquals(new int[] {30, 30, -1}, solution.nextGreaterElement(nums1, nums2));
    }
}
