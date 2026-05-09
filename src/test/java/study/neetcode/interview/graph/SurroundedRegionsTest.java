package study.neetcode.interview.graph;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SurroundedRegionsTest {

    @Test
    void test() {
        SurroundedRegions test = new SurroundedRegions();
        char[][] board =
                new char[][] {
                    {'X', 'X', 'X', 'X'},
                    {'X', 'O', 'O', 'X'},
                    {'X', 'X', 'O', 'X'},
                    {'X', 'O', 'X', 'X'}
                };
        char[][] expected =
                new char[][] {
                    {'X', 'X', 'X', 'X'},
                    {'X', 'X', 'X', 'X'},
                    {'X', 'X', 'X', 'X'},
                    {'X', 'O', 'X', 'X'}
                };
        test.surroundingRegions(board);
        assertMatriciesAreEqual(expected, board);
    }

    private void assertMatriciesAreEqual(char[][] expected, char[][] result) {
        assertEquals(expected.length, result.length);
        assertEquals(expected[0].length, result[0].length);
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                assertEquals(expected[i][j], result[i][j], "(" + i + ", " + j + ")");
            }
        }
    }
}
