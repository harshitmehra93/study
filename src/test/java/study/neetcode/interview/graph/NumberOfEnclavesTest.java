package study.neetcode.interview.graph;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NumberOfEnclavesTest {

    @Test
    void test() {
        NumberOfEnclaves test = new NumberOfEnclaves();
        int[][] grid = {{0, 0, 0, 0}, {1, 0, 1, 0}, {0, 1, 1, 0}, {0, 0, 0, 0}};
        assertEquals(3, test.numberOfEnclaves(grid));
    }
}
