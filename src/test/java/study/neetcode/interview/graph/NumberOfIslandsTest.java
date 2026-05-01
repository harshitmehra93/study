package study.neetcode.interview.graph;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NumberOfIslandsTest {

    @Test
    void test1() {
        NumberOfIslands test = new NumberOfIslands();
        assertEquals(
                1,
                test.numIslands(new char[][] {{'1', '1', '0'}, {'1', '1', '0'}, {'0', '0', '0'}}));
    }

    @Test
    void test2() {
        NumberOfIslands test = new NumberOfIslands();
        assertEquals(
                3,
                test.numIslands(
                        new char[][] {
                            {'1', '1', '0', '0', '0'},
                            {'1', '1', '0', '0', '0'},
                            {'0', '0', '0', '0', '0'},
                            {'1', '1', '0', '1', '1'},
                            {'1', '1', '0', '1', '1'}
                        }));
    }
}
