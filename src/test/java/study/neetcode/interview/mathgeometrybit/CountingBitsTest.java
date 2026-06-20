package study.neetcode.interview.mathgeometrybit;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

class CountingBitsTest {

    private final CountingBits solution = new CountingBits();

    @Test
    void returnsBaseCaseForZero() {
        int[] result = solution.countBits(0);

        assertArrayEquals(new int[] {0}, result);
    }

    @Test
    void countsBitsThroughTwo() {
        int[] result = solution.countBits(2);

        assertArrayEquals(new int[] {0, 1, 1}, result);
    }

    @Test
    void countsBitsThroughFive() {
        int[] result = solution.countBits(5);

        assertArrayEquals(new int[] {0, 1, 1, 2, 1, 2}, result);
    }

    @Test
    void handlesPowerOfTwoBoundary() {
        int[] result = solution.countBits(8);

        assertArrayEquals(new int[] {0, 1, 1, 2, 1, 2, 2, 3, 1}, result);
    }

    @Test
    void countsBitsAcrossMultipleRanges() {
        int[] result = solution.countBits(10);

        assertArrayEquals(new int[] {0, 1, 1, 2, 1, 2, 2, 3, 1, 2, 2}, result);
    }
}
