package study.neetcode.interview.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LongestCommonSubsequenceTest {
    @ParameterizedTest
    @MethodSource("testCases")
    void test(String s1, String s2, int result) {
        LongestCommonSubsequence test = new LongestCommonSubsequence();
        assertEquals(result, test.lcs2(s1, s2));
    }

    static Stream<Arguments> testCases() {
        return Stream.of(

                // Basic
                Arguments.of("abcde", "ace", 3),

                // Same strings
                Arguments.of("abc", "abc", 3),

                // No match
                Arguments.of("abc", "def", 0),

                // 🔴 ORDER BREAK (greedy fails)
                Arguments.of("abc", "bac", 2),

                // 🔴 GREEDY TRAP
                Arguments.of("aggtab", "gxtxayb", 4), // correct: "gtab"

                // 🔴 REPEATED CHARS TRAP
                Arguments.of("aaaa", "aa", 2),

                // 🔴 CROSS MATCHING
                Arguments.of("abcba", "abcbcba", 5),

                // 🔴 WRONG EARLY MATCH
                Arguments.of("abc", "cab", 2),

                // 🔴 COMPLEX INTERLEAVING
                Arguments.of("abcxyz", "xyzabc", 3),

                // 🔴 LONGER MISLEADING MATCH
                Arguments.of("abcdef", "fbdamn", 2), // "bd"

                // 🔴 SUBSEQUENCE NOT CONTIGUOUS
                Arguments.of("axbycz", "abc", 3),

                // 🔴 HEAVY MIX
                Arguments.of("pmjghexybyrgzczy", "hafcdqbgncrcbihkd", 4),
                Arguments.of("abcbdab", "bdcaba", 4),
                Arguments.of("abcdgh", "aedfhr", 3),
                Arguments.of("axbxcxdx", "abcd", 4),
                Arguments.of("abcd", "dcba", 1),
                Arguments.of("abcabcaa", "acbacba", 5),
                Arguments.of("bbbab", "babbb", 4),
                Arguments.of("abacbdab", "bdcaba", 4));
    }
}
