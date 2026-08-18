package study.coreskills.numbertheory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GcdTest {

    private Gcd runner;

    @Test
    void test() {
        runner = new Gcd();
        assertEquals(0, runner.gcd(new int[] {}));

        assertEquals(2, runner.gcd(new int[] {2}));
        assertEquals(3, runner.gcd(new int[] {3}));
        assertEquals(4, runner.gcd(new int[] {4}));
        assertEquals(5, runner.gcd(new int[] {5}));

        assertEquals(1, runner.gcd(new int[] {1, 2}));
        assertEquals(2, runner.gcd(new int[] {2, 2}));
        assertEquals(1, runner.gcd(new int[] {2, 3}));

        assertEquals(1, runner.gcd(new int[] {1, 2, 3, 4, 5}));
        assertEquals(2, runner.gcd(new int[] {2, 4, 8, 16}));
        assertEquals(4, runner.gcd(new int[] {4, 8, 16}));

        assertEquals(1, runner.gcd(new int[] {1, 0, 16}));
        assertEquals(0, runner.gcd(new int[] {0, 0}));

        assertEquals(1, runner.gcd(new int[] {-1, -2, -16}));
        assertEquals(2, runner.gcd(new int[] {-2, -4, -16}));
    }
}
