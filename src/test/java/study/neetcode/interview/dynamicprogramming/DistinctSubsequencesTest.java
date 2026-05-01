package study.neetcode.interview.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DistinctSubsequencesTest {

    @ParameterizedTest
    @MethodSource("testCases")
    void test(String source, String target, int result) {
        DistinctSubsequences test = new DistinctSubsequences();
        assertEquals(result, test.distinctSubsequences(source, target));
    }

    static Stream<Arguments> testCases() {
        return Stream.of(

                // Basic
                Arguments.of("rabbbit", "rabbit", 3),

                // Same strings
                Arguments.of("abc", "abc", 1),

                // Target longer than source
                Arguments.of("abc", "abcd", 0),

                // Empty target
                Arguments.of("abc", "", 1),

                // Empty source
                Arguments.of("", "abc", 0),

                // Both empty
                Arguments.of("", "", 1),

                // No match
                Arguments.of("abc", "def", 0),

                // Single character repeats
                Arguments.of("aaaaa", "a", 5),

                // Combinations
                Arguments.of("aaaaa", "aa", 10), // C(5,2)
                Arguments.of("aaaaa", "aaa", 10), // C(5,3)

                // Interleaving classic
                Arguments.of("babgbag", "bag", 5),

                // Skipping required
                Arguments.of("abcdef", "ace", 1),

                // Multiple placements
                Arguments.of("abcabc", "abc", 4),

                // Order matters
                Arguments.of("abc", "cba", 0),

                // Interleaving noise
                Arguments.of("axbxcxdx", "abcd", 1),

                // Complex overlapping (fixed)
                Arguments.of("abcbcba", "aba", 3),

                // Heavy repetition
                Arguments.of("aaaaaaaaaa", "aaaaa", 252), // C(10,5)

                // Repeated single char
                Arguments.of("bbbbbbbbbb", "bbb", 120), // C(10,3)

                // Mixed segments (fixed)
                Arguments.of("abcdeabcde", "ace", 4),

                // Edge alignment
                Arguments.of("xyzabc", "abc", 1),

                // Tail match
                Arguments.of("zzzzabc", "abc", 1),

                // 🔥 Additional strong cases

                // Choose positions non-contiguously
                Arguments.of("aaaa", "aa", 6), // C(4,2)

                // Many skips before match
                Arguments.of("zzzzzzzzzzabc", "abc", 1),

                // Prefix noise
                Arguments.of("xxxxabc", "abc", 1),

                // Alternating pattern
                Arguments.of("abababab", "aaa", 4),

                // Sparse matches
                Arguments.of("a1b2c3d4", "abcd", 1),

                // Target equals repeated subsequence
                Arguments.of("abcabcabc", "abc", 10),

                // Single match at end
                Arguments.of("xxxxxxxa", "a", 1),

                // Impossible due to order
                Arguments.of("bac", "abc", 0));
    }
}
