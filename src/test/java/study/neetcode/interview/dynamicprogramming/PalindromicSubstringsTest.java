package study.neetcode.interview.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PalindromicSubstringsTest {

    @ParameterizedTest
    @MethodSource("testCases")
    void test(String input, int expected) {
        PalindromicSubstrings solver = new PalindromicSubstrings();
        assertEquals(expected, solver.countPalindromicSubstrings(input));
    }

    static Stream<Arguments> testCases() {
        return Stream.of(
                // Edge cases
                //                Arguments.of("", 0),
                //                Arguments.of("a", 1),
                //                Arguments.of("ab", 2),
                Arguments.of("aa", 3),

                // Basic examples
                Arguments.of("abc", 3), // a,b,c
                Arguments.of("aaa", 6), // a,a,a,aa,aa,aaa

                // Odd/even palindromes
                Arguments.of("aba", 4), // a,b,a,aba
                Arguments.of("abba", 6), // a,b,b,a,bb,abba
                Arguments.of("racecar", 10),

                // No larger palindromes
                Arguments.of("abcdef", 6),

                // Repeated characters
                Arguments.of("aaaa", 10), // 4 + 3 + 2 + 1
                Arguments.of("aaaaa", 15),

                // Palindromes mixed inside
                Arguments.of("ababa", 9),
                Arguments.of("aabaa", 9),
                Arguments.of("bananas", 11),

                // Tricky: substring, not subsequence
                Arguments.of("bbbab", 9),

                // Larger structured cases
                Arguments.of("abcddcbae", 13),
                Arguments.of("abccba", 9),
                Arguments.of("zzracecarzz", 18));
    }
}
