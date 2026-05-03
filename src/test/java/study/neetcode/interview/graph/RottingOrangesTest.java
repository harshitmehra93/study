package study.neetcode.interview.graph;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RottingOrangesTest {

    @Test
    void test() {
        RottingOranges test = new RottingOranges();
        assertEquals(1, test.orangesRotting(new int[][] {{2, 1, 0}, {0, 0, 0}}));
    }

    @Test
    void test2() {
        RottingOranges test = new RottingOranges();
        assertEquals(
                -1,
                test.orangesRotting(
                        new int[][] {
                            {2, 1, 1, 0, 0},
                            {1, 1, 0, 1, 0},
                            {1, 0, 0, 0, 0},
                            {0, 2, 1, 1, 0}
                        }));
    }

    @Test
    void test3() {
        RottingOranges test = new RottingOranges();
        assertEquals(
                2,
                test.orangesRotting(
                        new int[][] {
                            {2, 1, 1, 0, 0},
                            {1, 1, 0, 0, 0},
                            {1, 0, 0, 0, 0},
                            {0, 2, 1, 1, 0}
                        }));
    }

    @Test
    void test5() {
        RottingOranges test = new RottingOranges();
        assertEquals(
                2,
                test.orangesRotting(
                        new int[][] {
                            {2, 1, 1, 0, 0},
                            {1, 1, 0, 0, 0},
                            {1, 0, 0, 0, 0},
                            {1, 2, 1, 1, 0}
                        }));
    }
}
