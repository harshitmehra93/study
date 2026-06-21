package study.interview.mathgeometrybit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class SingleNumberTest {

    private final SingleNumber solution = new SingleNumber();

    @Test
    void findsSingleNumberInSmallArray() {
        int[] nums = {2, 2, 1};

        int result = solution.singleNumber(nums);

        assertEquals(1, result);
    }

    @Test
    void findsSingleNumberInMixedOrderArray() {
        int[] nums = {4, 1, 2, 1, 2};

        int result = solution.singleNumber(nums);

        assertEquals(4, result);
    }

    @Test
    void handlesSingleElementArray() {
        int[] nums = {1};

        int result = solution.singleNumber(nums);

        assertEquals(1, result);
    }

    @Test
    void handlesNegativeSingleNumber() {
        int[] nums = {-1, 7, 7, 3, 3};

        int result = solution.singleNumber(nums);

        assertEquals(-1, result);
    }

    @Test
    void handlesDuplicateNegativeNumbers() {
        int[] nums = {-4, 6, -4, 8, 6};

        int result = solution.singleNumber(nums);

        assertEquals(8, result);
    }
}
