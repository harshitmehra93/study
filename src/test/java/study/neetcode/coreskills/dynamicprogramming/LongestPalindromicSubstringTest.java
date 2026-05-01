package study.neetcode.coreskills.dynamicprogramming;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class LongestPalindromicSubstringTest {

    @ParameterizedTest
    @MethodSource("testCases")
    void test(String input, int expected) {
        LongestPalindromicSubstring solver = new LongestPalindromicSubstring();
        assertEquals(expected, solver.lcs(input));
    }

    static Stream<Arguments> testCases() {
        return Stream.of(
                // Basic examples
                Arguments.of("babad", 3),     // "bab" or "aba"
                Arguments.of("cbbd", 2),      // "bb"

                // Edge cases
                Arguments.of("", 0),
                Arguments.of("a", 1),
                Arguments.of("ab", 1),
                Arguments.of("aa", 2),

                // Entire string is palindrome
                Arguments.of("racecar", 7),
                Arguments.of("abba", 4),
                Arguments.of("abcba", 5),
                Arguments.of("abccba", 6),

                // No longer palindrome than 1
                Arguments.of("abcdef", 1),
                Arguments.of("xyz", 1),

                // Odd length palindromes
                Arguments.of("bananas", 5),   // "anana"
                Arguments.of("forgeeksskeegfor", 10), // "geeksskeeg"
                Arguments.of("xabax", 5),     // "xabax"

                // Even length palindromes
                Arguments.of("abb", 2),       // "bb"
                Arguments.of("aabbcc", 2),
                Arguments.of("aaaabaaa", 7),  // "aaabaaa"

                // Repeated characters
                Arguments.of("aaaa", 4),
                Arguments.of("aaaaaaaaaa", 10),

                // Palindrome in middle
                Arguments.of("abcddcbae", 8), // "abcddcba"
                Arguments.of("zzracecarzz", 11),

                // Tricky: subsequence would be longer, substring is smaller
                Arguments.of("bbbab", 3),     // "bbb", not 4
                Arguments.of("character", 3), // "ara"

                // Multiple possible answers
                Arguments.of("abacdfgdcaba", 3), // "aba"
                Arguments.of("abaxyzzyxf", 6),   // "xyzzyx"

                // Long-ish stress
                Arguments.of("abcdedcba123456789", 9), // "abcdedcba"
                Arguments.of("123456789abccba987654321", 24) // "abccba"
        );
    }
}