package study.interview.mathgeometrybit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class SumOfTwoIntegersTest {

    private final SumOfTwoIntegers solution = new SumOfTwoIntegers();

    @Test
    void addsTwoPositiveNumbers() {
        int result = solution.getSum(1, 2);

        assertEquals(3, result);
    }

    @Test
    void addsNumbersThatRequireCarry() {
        int result = solution.getSum(2, 3);

        assertEquals(5, result);
    }

    @Test
    void addsNegativeAndPositiveNumber() {
        int result = solution.getSum(-2, 3);

        assertEquals(1, result);
    }

    @Test
    void addsTwoNegativeNumbers() {
        int result = solution.getSum(-4, -7);

        assertEquals(-11, result);
    }

    @Test
    void returnsOtherNumberWhenOneInputIsZero() {
        assertEquals(9, solution.getSum(0, 9));
        assertEquals(-9, solution.getSum(-9, 0));
    }

    @Test
    void followsJavaIntegerOverflowBehavior() {
        int result = solution.getSum(Integer.MAX_VALUE, 1);

        assertEquals(Integer.MIN_VALUE, result);
    }
}
