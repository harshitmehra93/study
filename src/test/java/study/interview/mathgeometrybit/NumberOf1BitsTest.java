package study.interview.mathgeometrybit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class NumberOf1BitsTest {

    private final NumberOf1Bits solution = new NumberOf1Bits();

    @Test
    void countsSetBitsInSmallPositiveNumber() {
        int result = solution.hammingWeight(11);

        assertEquals(3, result);
    }

    @Test
    void countsSingleSetBit() {
        int result = solution.hammingWeight(128);

        assertEquals(1, result);
    }

    @Test
    void countsManySetBitsInLargePositiveNumber() {
        int result = solution.hammingWeight(2147483645);

        assertEquals(30, result);
    }

    @Test
    void returnsZeroWhenNoBitsAreSet() {
        int result = solution.hammingWeight(0);

        assertEquals(0, result);
    }

    @Test
    void treatsNegativeNumberAsThirtyTwoBitRepresentation() {
        int result = solution.hammingWeight(-1);

        assertEquals(32, result);
    }

    @Test
    void handlesOnlySignBitSet() {
        int result = solution.hammingWeight(Integer.MIN_VALUE);

        assertEquals(1, result);
    }
}
