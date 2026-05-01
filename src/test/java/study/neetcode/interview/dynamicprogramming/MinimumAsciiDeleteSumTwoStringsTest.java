package study.neetcode.interview.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class MinimumAsciiDeleteSumTwoStringsTest {
    @ParameterizedTest
    @MethodSource("testCases")
    void test(String s1, String s2, int result) {
        MinimumAsciiDeleteSumTwoStrings test = new MinimumAsciiDeleteSumTwoStrings();
        assertEquals(result, test.minimumDeleteSum(s1, s2));
    }

    static Stream<Arguments> testCases() {
        return Stream.of(

                // Basic
                Arguments.of("sea", "eat", 231),

                // Same strings
                Arguments.of("abc", "abc", 0),

                // One empty
                Arguments.of("", "abc", 'a' + 'b' + 'c'), // 294
                Arguments.of("abc", "", 'a' + 'b' + 'c'),

                // Both empty
                Arguments.of("", "", 0),

                // No common characters
                Arguments.of(
                        "abc", "def", ('a' + 'b' + 'c') + ('d' + 'e' + 'f')), // 294 + 303 = 597

                // 🔴 Partial overlap
                Arguments.of("delete", "leet", 403),

                // 🔴 Reverse strings
                Arguments.of("abc", "cba", ('a' + 'b') * 2),
                // keep 'b' → delete others

                // 🔴 Repeated characters
                Arguments.of("aaaa", "aa", 2 * 'a'), // delete 2 'a's → 194

                // 🔴 Interleaving
                Arguments.of("axbycz", "abc", 'x' + 'y' + 'z'), // 120 + 121 + 122 = 363

                // 🔴 Complex overlap
                Arguments.of("aebcbda", "abcda", 'e' + 'b'), // delete e and one b → 101 + 98 = 199

                // 🔴 Larger mismatch
                Arguments.of(
                        "abcdef",
                        "ghijkl",
                        ('a' + 'b' + 'c' + 'd' + 'e' + 'f') + ('g' + 'h' + 'i' + 'j' + 'k' + 'l')),

                // 🔴 Heavy asymmetry
                Arguments.of("aaaaaaaaaa", "aaa", 7 * 'a'), // delete 7 'a's → 679

                // 🔴 Long structured case
                Arguments.of("abcdeffedcba", "abcdef", ('f' + 'e' + 'd' + 'c' + 'b' + 'a')),
                // removing mirrored tail

                // 🔴 Stress-like (forces DP)
                Arguments.of("pmjghexybyrgzczy", "hafcdqbgncrcbihkd", 2698));
    }
}
