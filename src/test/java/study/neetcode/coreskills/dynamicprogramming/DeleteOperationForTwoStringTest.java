package study.neetcode.coreskills.dynamicprogramming;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class DeleteOperationForTwoStringTest {

    @ParameterizedTest
    @MethodSource("testCases")
    void test(String s1, String s2, int expected) {
        DeleteOperationForTwoString solver = new DeleteOperationForTwoString();
        assertEquals(expected, solver.deleteOperations(s1, s2));
    }

    static Stream<Arguments> testCases() {
        return Stream.of(

                // Basic
                Arguments.of("sea", "eat", 2),

                // Same strings
                Arguments.of("abc", "abc", 0),

                // One empty
                Arguments.of("", "abc", 3),
                Arguments.of("abc", "", 3),

                // Both empty
                Arguments.of("", "", 0),

                // No common characters
                Arguments.of("abc", "def", 6),

                // Partial overlap
                Arguments.of("leetcode", "etco", 4),

                // 🔴 LCS trap
                Arguments.of("abcde", "ace", 2), // remove b,d

                // 🔴 Reverse strings
                Arguments.of("abc", "cba", 4),

                // 🔴 Repeated characters
                Arguments.of("aaaa", "aa", 2),

                // 🔴 Complex overlap
                Arguments.of("abcba", "abcbcba", 2),

                // 🔴 Multiple choices
                Arguments.of("aebcbda", "abcda", 2),

                // 🔴 Interleaving
                Arguments.of("axbycz", "abc", 3),

                // 🔴 Larger mix
                Arguments.of("pmjghexybyrgzczy", "hafcdqbgncrcbihkd", 25),

                // 🔴 Stress-like
                Arguments.of("abababab", "baba", 4),

                // 🔴 Nearly identical
                Arguments.of("abcdefg", "abcxefg", 2)
        );
    }
}