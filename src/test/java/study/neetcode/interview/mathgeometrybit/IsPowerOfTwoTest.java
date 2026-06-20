package study.neetcode.interview.mathgeometrybit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class IsPowerOfTwoTest {

    private final IsPowerOfTwo solution = new IsPowerOfTwo();

    @Test
    void returnsTrueForOne() {
        assertTrue(solution.isPowerOfTwo(1));
    }

    @Test
    void returnsTrueForPositivePowersOfTwo() {
        assertTrue(solution.isPowerOfTwo(2));
        assertTrue(solution.isPowerOfTwo(16));
        assertTrue(solution.isPowerOfTwo(1024));
    }

    @Test
    void returnsFalseForPositiveNonPowersOfTwo() {
        assertFalse(solution.isPowerOfTwo(3));
        assertFalse(solution.isPowerOfTwo(12));
        assertFalse(solution.isPowerOfTwo(1023));
    }

    @Test
    void returnsFalseForZero() {
        assertFalse(solution.isPowerOfTwo(0));
    }

    @Test
    void returnsFalseForNegativeNumbers() {
        assertFalse(solution.isPowerOfTwo(-1));
        assertFalse(solution.isPowerOfTwo(-16));
        assertFalse(solution.isPowerOfTwo(Integer.MIN_VALUE));
    }

    @Test
    void returnsTrueForLargestPositivePowerOfTwo() {
        assertTrue(solution.isPowerOfTwo(1 << 30));
    }
}
