package study.neetcode.algorithms;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MaximumSubarrayTest {

    @Test
    void noSolution2() {
        int[] A = {};

        MaximumSubarray maximumSubarray = new MaximumSubarray();
        var result = maximumSubarray.divideAndConquer(A);

        assertEquals(null, result.left());
        assertEquals(null, result.right());
        assertEquals(null, result.max());
    }

    @Test
    void oneSolution() {
        int[] A = {1};

        MaximumSubarray maximumSubarray = new MaximumSubarray();
        var result = maximumSubarray.divideAndConquer(A);

        assertEquals(0, result.left());
        assertEquals(0, result.right());
        assertEquals(1, result.max());
    }

    @Test
    void happyCase() {
        int[] A = {1, 2, 3};

        MaximumSubarray maximumSubarray = new MaximumSubarray();
        var result = maximumSubarray.divideAndConquer(A);

        assertEquals(0, result.left());
        assertEquals(2, result.right());
        assertEquals(6, result.max());
    }

    @Test
    void happyCase2() {
        int[] A = {-2, 3, 4, -1, 2, -5};

        MaximumSubarray maximumSubarray = new MaximumSubarray();
        var result = maximumSubarray.divideAndConquer(A);

        assertEquals(1, result.left());
        assertEquals(4, result.right());
        assertEquals(8, result.max());
    }
}
