package study.neetcode.interview.graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MaxAreaOfIslandTest {
    @Test
    void test1() {
        MaxAreaOfIsland test = new MaxAreaOfIsland();
        assertEquals(
                4,
                test.maxAreaOfIsland(new char[][] {{'1', '1', '0'}, {'1', '1', '0'}, {'0', '0', '0'}}));
    }

    @Test
    void test2() {
        MaxAreaOfIsland test = new MaxAreaOfIsland();
        assertEquals(
                9,
                test.maxAreaOfIsland(
                        new char[][] {
                                {'1', '1', '0', '0', '1'},
                                {'1', '1', '0', '0', '0'},
                                {'0', '0', '0', '0', '0'},
                                {'1', '1', '1', '1', '1'},
                                {'1', '1', '0', '1', '1'}
                        }));
    }
}