package study.neetcode.coreskills.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RodCuttingTest {
    @Test
    void test() {
        RodCutting test = new RodCutting();
        assertEquals(10, test.giveBestSplit(4, new int[] {1, 5, 8, 9}));
    }
}
