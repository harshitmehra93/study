package study.neetcode.interview.backtracking;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

class PalindromePartitioningTest {

    @Test
    void test_givenExample_aab() {
        PalindromePartitioning test = new PalindromePartitioning();

        List<List<String>> expected = List.of(List.of("a", "a", "b"), List.of("aa", "b"));

        assertPartitionsEqual(expected, test.partition("aab"));
    }

    @Test
    void test_singleCharacter() {
        PalindromePartitioning test = new PalindromePartitioning();

        List<List<String>> expected = List.of(List.of("a"));

        assertPartitionsEqual(expected, test.partition("a"));
    }

    @Test
    void test_allCharactersSame() {
        PalindromePartitioning test = new PalindromePartitioning();

        List<List<String>> expected =
                List.of(
                        List.of("a", "a", "a"),
                        List.of("a", "aa"),
                        List.of("aa", "a"),
                        List.of("aaa"));

        assertPartitionsEqual(expected, test.partition("aaa"));
    }

    @Test
    void test_noLongPalindrome() {
        PalindromePartitioning test = new PalindromePartitioning();

        List<List<String>> expected = List.of(List.of("a", "b", "c"));

        assertPartitionsEqual(expected, test.partition("abc"));
    }

    @Test
    void test_mixedPalindromes() {
        PalindromePartitioning test = new PalindromePartitioning();

        List<List<String>> expected =
                List.of(List.of("a", "b", "b", "a"), List.of("a", "bb", "a"), List.of("abba"));

        assertPartitionsEqual(expected, test.partition("abba"));
    }

    @Test
    void test_allGeneratedPartitionsUseEntireString() {
        PalindromePartitioning test = new PalindromePartitioning();

        List<List<String>> result = test.partition("aab");

        for (List<String> partition : result) {
            assertEquals("aab", String.join("", partition));
        }
    }

    @Test
    void test_allGeneratedPiecesArePalindromes() {
        PalindromePartitioning test = new PalindromePartitioning();

        List<List<String>> result = test.partition("abba");

        for (List<String> partition : result) {
            for (String piece : partition) {
                assertTrue(isPalindrome(piece), "Not a palindrome: " + piece);
            }
        }
    }

    @Test
    void test_noDuplicatePartitions() {
        PalindromePartitioning test = new PalindromePartitioning();

        List<List<String>> result = test.partition("aaa");

        Set<List<String>> unique = Set.copyOf(result);

        assertEquals(unique.size(), result.size());
    }

    private void assertPartitionsEqual(List<List<String>> expected, List<List<String>> actual) {
        assertEquals(expected.size(), actual.size());
        Set<List<String>> expectedSet = normalize(expected);
        Set<List<String>> actualSet = normalize(actual);

        assertEquals(expectedSet, actualSet);
    }

    private Set<List<String>> normalize(List<List<String>> partitions) {
        return partitions.stream().map(List::copyOf).collect(Collectors.toSet());
    }

    private boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) return false;
            left++;
            right--;
        }

        return true;
    }
}
