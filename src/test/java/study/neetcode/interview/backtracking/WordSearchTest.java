package study.neetcode.interview.backtracking;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class WordSearchTest {

    @Test
    void test_givenExample_ABCCED_returnsTrue() {
        WordSearch test = new WordSearch();

        char[][] board = {
            {'A', 'B', 'C', 'E'},
            {'S', 'F', 'C', 'S'},
            {'A', 'D', 'E', 'E'}
        };

        assertTrue(test.exist(board, "ABCCED"));
    }

    @Test
    void test_givenExample_SEE_returnsTrue() {
        WordSearch test = new WordSearch();

        char[][] board = {
            {'A', 'B', 'C', 'E'},
            {'S', 'F', 'C', 'S'},
            {'A', 'D', 'E', 'E'}
        };

        assertTrue(test.exist(board, "SEE"));
    }

    @Test
    void test_givenExample_ABCB_returnsFalseBecauseCellReuseNotAllowed() {
        WordSearch test = new WordSearch();

        char[][] board = {
            {'A', 'B', 'C', 'E'},
            {'S', 'F', 'C', 'S'},
            {'A', 'D', 'E', 'E'}
        };

        assertFalse(test.exist(board, "ABCB"));
    }

    @Test
    void test_singleCell_matchingWord_returnsTrue() {
        WordSearch test = new WordSearch();

        char[][] board = {{'A'}};

        assertTrue(test.exist(board, "A"));
    }

    @Test
    void test_singleCell_nonMatchingWord_returnsFalse() {
        WordSearch test = new WordSearch();

        char[][] board = {{'A'}};

        assertFalse(test.exist(board, "B"));
    }

    @Test
    void test_wordLongerThanAvailableCells_returnsFalse() {
        WordSearch test = new WordSearch();

        char[][] board = {
            {'A', 'B'},
            {'C', 'D'}
        };

        assertFalse(test.exist(board, "ABCDE"));
    }

    @Test
    void test_mustBacktrackToFindCorrectPath_returnsTrue() {
        WordSearch test = new WordSearch();

        char[][] board = {
            {'A', 'B', 'A'},
            {'A', 'B', 'A'},
            {'A', 'A', 'A'}
        };

        /*
         * There are multiple tempting A/B paths.
         * A correct solution must mark visited, explore, then unmark while backtracking.
         */
        assertTrue(test.exist(board, "ABBA"));
    }

    @Test
    void test_diagonalMovementNotAllowed_returnsFalse() {
        WordSearch test = new WordSearch();

        char[][] board = {
            {'A', 'X'},
            {'X', 'B'}
        };

        /*
         * A and B are diagonal only.
         */
        assertFalse(test.exist(board, "AB"));
    }

    @Test
    void test_canStartFromAnyCell_returnsTrue() {
        WordSearch test = new WordSearch();

        char[][] board = {
            {'X', 'X', 'X'},
            {'X', 'C', 'A'},
            {'X', 'X', 'T'}
        };

        assertTrue(test.exist(board, "CAT"));
    }

    @Test
    void test_cannotReuseSameCell_returnsFalse() {
        WordSearch test = new WordSearch();

        char[][] board = {{'A', 'A'}};

        /*
         * Need three A's, but only two cells exist.
         */
        assertFalse(test.exist(board, "AAA"));
    }
}
