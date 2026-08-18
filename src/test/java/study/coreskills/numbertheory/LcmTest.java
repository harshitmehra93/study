package study.coreskills.numbertheory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LcmTest {

    private Lcm runner;

    @Test
    void test() {
        runner = new Lcm();

        assertEquals(1, runner.lcm(new int[] {1}));
        assertEquals(2, runner.lcm(new int[] {2}));
        assertEquals(3, runner.lcm(new int[] {3}));

        assertEquals(3, runner.lcm(new int[] {3, 3}));
        assertEquals(3, runner.lcm(new int[] {1, 3}));
        assertEquals(30, runner.lcm(new int[] {2, 3, 5}));

        assertEquals(16, runner.lcm(new int[] {2, 4, 8, 16}));
        assertEquals(60, runner.lcm(new int[] {1, 2, 3, 4, 5}));

        assertEquals(0, runner.lcm(new int[] {1, 0, 3, 4, 5}));
        assertEquals(0, runner.lcm(new int[] {1, 2, 3, 4, 0}));

        assertEquals(0, runner.lcm(new int[] {0}));
        assertEquals(0, runner.lcm(new int[] {0, 0, 0, 0}));
        //
        assertEquals(10L, runner.lcm(new int[] {-5, -10}));
        assertEquals(0L, runner.lcm(new int[] {0, 10}));
        assertEquals(2_499_950_000L, runner.lcm(new int[] {50_000, 49_999}));
    }
}
