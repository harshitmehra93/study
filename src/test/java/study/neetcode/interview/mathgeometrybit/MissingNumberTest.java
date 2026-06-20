package study.neetcode.interview.mathgeometrybit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class MissingNumberTest {

    private final MissingNumber solution = new MissingNumber();

    @Test
    void findsMissingNumberInMiddle() {
        int[] nums = {3, 0, 1};

        int result = solution.missingNumber(nums);

        assertEquals(2, result);
    }

    @Test
    void findsMissingNumberAtEnd() {
        int[] nums = {0, 1};

        int result = solution.missingNumber(nums);

        assertEquals(2, result);
    }

    @Test
    void findsMissingNumberInLargerArray() {
        int[] nums = {9, 6, 4, 2, 3, 5, 7, 0, 1};

        int result = solution.missingNumber(nums);

        assertEquals(8, result);
    }

    @Test
    void findsMissingZero() {
        int[] nums = {1, 2, 3};

        int result = solution.missingNumber(nums);

        assertEquals(0, result);
    }

    @Test
    void handlesSingleElementWhenZeroIsMissing() {
        int[] nums = {1};

        int result = solution.missingNumber(nums);

        assertEquals(0, result);
    }

    @Test
    void handlesSingleElementWhenOneIsMissing() {
        int[] nums = {0};

        int result = solution.missingNumber(nums);

        assertEquals(1, result);
    }
}
