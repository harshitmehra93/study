package study.neetcode.coreskills.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LongestPalindromicSubsequenceTest {

    @ParameterizedTest
    @MethodSource("testCases")
    void test(String input, int expected) {
        LongestPalindromicSubsequence solver = new LongestPalindromicSubsequence();
        assertEquals(expected, solver.lps(input));
    }

    static Stream<Arguments> testCases() {
        return Stream.of(

                // Basic
                Arguments.of("abcbd", 3), // bcb
                Arguments.of("bbbab", 4), // bbbb
                Arguments.of("cbbd", 2), // bb

                // Single / empty
                Arguments.of("a", 1),
                Arguments.of("", 0),

                // All same
                Arguments.of("aaaaaa", 6),

                // No repeating characters
                Arguments.of("abcdef", 1),

                // Palindrome already
                Arguments.of("racecar", 7),
                Arguments.of("abba", 4),

                // Even vs odd length
                Arguments.of("abccba", 6),
                Arguments.of("abcba", 5),

                // 🔴 Greedy trap
                Arguments.of("agbdba", 5), // abdba

                // 🔴 Skip both sides needed
                Arguments.of("abcda", 3), // aca or ada

                // 🔴 Inner subsequence important
                Arguments.of("character", 5), // carac

                // 🔴 Multiple choices
                Arguments.of("aebcbda", 5), // abcba

                // 🔴 Cross dependencies
                Arguments.of("bbabcbcab", 7), // babcbab

                // Medium random
                Arguments.of("pmjghexybyrgzczy", 5),

                // Larger structured case
                Arguments.of("abcdeffedcba", 12),

                // Large non-palindromic mix
                Arguments.of("abcdefgfedxyz", 7), // defgfed

                // Stress-like repeated pattern
                Arguments.of("abababababababab", 15));
    }
}
