package study.interview.tries;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;

class WordSearch2Test {

    @Test
    void givenExample_returnsWordsPresentOnBoard() {
        WordSearch2 solution = new WordSearch2();
        char[][] board = {
            {'o', 'a', 'a', 'n'},
            {'e', 't', 'a', 'e'},
            {'i', 'h', 'k', 'r'},
            {'i', 'f', 'l', 'v'}
        };
        String[] words = {"oath", "pea", "eat", "rain"};

        List<String> result = solution.findWords(board, words);

        assertEquals(Set.of("oath", "eat"), Set.copyOf(result));
    }

    @Test
    void returnsEmptyListWhenNoWordsExistOnBoard() {
        WordSearch2 solution = new WordSearch2();
        char[][] board = {
            {'a', 'b'},
            {'c', 'd'}
        };
        String[] words = {"efg", "xyz", "aaa"};

        List<String> result = solution.findWords(board, words);

        assertTrue(result.isEmpty());
    }

    @Test
    void returnsPrefixWordAndLongerWordWhenBothExist() {
        WordSearch2 solution = new WordSearch2();
        char[][] board = {{'a', 'p', 'p', 'l', 'e'}};
        String[] words = {"app", "apple", "appl", "apply"};

        List<String> result = solution.findWords(board, words);

        assertEquals(Set.of("app", "appl", "apple"), Set.copyOf(result));
    }

    @Test
    void doesNotReuseSameCellWithinOneWord() {
        WordSearch2 solution = new WordSearch2();
        char[][] board = {{'a', 'b'}};
        String[] words = {"aba", "ab"};

        List<String> result = solution.findWords(board, words);

        assertEquals(Set.of("ab"), Set.copyOf(result));
    }

    @Test
    void diagonalMovementIsNotAllowed() {
        WordSearch2 solution = new WordSearch2();
        char[][] board = {
            {'a', 'x'},
            {'x', 'b'}
        };
        String[] words = {"ab", "ax", "xb"};

        List<String> result = solution.findWords(board, words);

        assertEquals(Set.of("ax", "xb"), Set.copyOf(result));
    }

    @Test
    void duplicateBoardPathsReturnWordOnce() {
        WordSearch2 solution = new WordSearch2();
        char[][] board = {
            {'a', 'a'},
            {'a', 'a'}
        };
        String[] words = {"a", "aa", "aaa"};

        List<String> result = solution.findWords(board, words);

        assertEquals(Set.of("a", "aa", "aaa"), Set.copyOf(result));
        assertEquals(Set.copyOf(result).size(), result.size());
    }

    @Test
    void duplicateWordsInInputReturnWordOnce() {
        WordSearch2 solution = new WordSearch2();
        char[][] board = {
            {'c', 'a', 't'},
            {'x', 'x', 'x'}
        };
        String[] words = {"cat", "cat", "ca", "dog"};

        List<String> result = solution.findWords(board, words);

        assertEquals(Set.of("cat", "ca"), Set.copyOf(result));
        assertEquals(Set.copyOf(result).size(), result.size());
    }

    @Test
    void canStartSearchFromAnyCell() {
        WordSearch2 solution = new WordSearch2();
        char[][] board = {
            {'x', 'x', 'x'},
            {'x', 'c', 'a'},
            {'x', 'x', 't'}
        };
        String[] words = {"cat", "cta", "atc"};

        List<String> result = solution.findWords(board, words);

        assertEquals(Set.of("cat"), Set.copyOf(result));
    }
}
