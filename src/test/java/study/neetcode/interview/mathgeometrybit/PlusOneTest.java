package study.neetcode.interview.mathgeometrybit;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

class PlusOneTest {

    private final PlusOne solution = new PlusOne();

    @Test
    void incrementsNumberWithoutCarry() {
        int[] digits = {1, 2, 3};

        int[] result = solution.plusOne(digits);

        assertArrayEquals(new int[] {1, 2, 4}, result);
    }

    @Test
    void incrementsLastDigitWithoutChangingPrefix() {
        int[] digits = {4, 3, 2, 1};

        int[] result = solution.plusOne(digits);

        assertArrayEquals(new int[] {4, 3, 2, 2}, result);
    }

    @Test
    void carriesAcrossTrailingNines() {
        int[] digits = {1, 2, 9, 9};

        int[] result = solution.plusOne(digits);

        assertArrayEquals(new int[] {1, 3, 0, 0}, result);
    }

    @Test
    void expandsArrayWhenAllDigitsAreNine() {
        int[] digits = {9, 9, 9};

        int[] result = solution.plusOne(digits);

        assertArrayEquals(new int[] {1, 0, 0, 0}, result);
    }

    @Test
    void handlesSingleDigitWithoutCarry() {
        int[] digits = {8};

        int[] result = solution.plusOne(digits);

        assertArrayEquals(new int[] {9}, result);
    }

    @Test
    void handlesSingleNine() {
        int[] digits = {9};

        int[] result = solution.plusOne(digits);

        assertArrayEquals(new int[] {1, 0}, result);
    }
}
