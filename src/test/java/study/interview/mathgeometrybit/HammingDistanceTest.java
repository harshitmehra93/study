package study.interview.mathgeometrybit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class HammingDistanceTest {

    private final HammingDistance solution = new HammingDistance();

    @Test
    void countsDifferingBitsFromExample() {
        int result = solution.hammingDistance(1, 4);

        assertEquals(2, result);
    }

    @Test
    void countsOneDifferingBit() {
        int result = solution.hammingDistance(3, 1);

        assertEquals(1, result);
    }

    @Test
    void returnsZeroForEqualNumbers() {
        int result = solution.hammingDistance(7, 7);

        assertEquals(0, result);
    }

    @Test
    void countsAllBitsDifferent() {
        int result = solution.hammingDistance(0, -1);

        assertEquals(32, result);
    }

    @Test
    void handlesSignBitDifference() {
        int result = solution.hammingDistance(0, Integer.MIN_VALUE);

        assertEquals(1, result);
    }
}
