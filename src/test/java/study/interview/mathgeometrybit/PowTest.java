package study.interview.mathgeometrybit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PowTest {

    private static final double DELTA = 1e-9;

    private final Pow solution = new Pow();

    @Test
    void raisesNumberToPositiveEvenPower() {
        double result = solution.myPow(2.0, 10);

        assertEquals(1024.0, result, DELTA);
    }

    @Test
    void raisesNumberToPositiveOddPower() {
        double result = solution.myPow(2.1, 3);

        assertEquals(9.261, result, DELTA);
    }

    @Test
    void returnsOneForZeroExponent() {
        double result = solution.myPow(5.0, 0);

        assertEquals(1.0, result, DELTA);
    }

    @Test
    void handlesNegativeExponent() {
        double result = solution.myPow(2.0, -2);

        assertEquals(0.25, result, DELTA);
    }

    @Test
    void handlesNegativeBaseWithOddExponent() {
        double result = solution.myPow(-2.0, 3);

        assertEquals(-8.0, result, DELTA);
    }

    @Test
    void handlesNegativeBaseWithEvenExponent() {
        double result = solution.myPow(-2.0, 4);

        assertEquals(16.0, result, DELTA);
    }

    @Test
    void handlesMinimumIntegerExponentWithoutOverflow() {
        double result = solution.myPow(2.0, Integer.MIN_VALUE);

        assertEquals(0.0, result, DELTA);
    }
}
