package study.neetcode.interview.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class MinimumWindowSubstringTest {

    static Stream<TestCase> testCases() {
        return Stream.of(
                new TestCase("ADOBECODEBANC", "ABC", "BANC", "classic case"),
                new TestCase("a", "a", "a", "single character exact match"),
                new TestCase("a", "aa", "", "target needs duplicate char but source has only one"),
                new TestCase("aa", "aa", "aa", "entire string needed for duplicate target"),
                new TestCase(
                        "aaflslflsldkalskaaa",
                        "aaa",
                        "aaa",
                        "minimum duplicate-heavy target at end"),
                new TestCase("ab", "b", "b", "single target character at end"),
                new TestCase("bba", "ab", "ba", "minimum window at end"),
                new TestCase("abc", "d", "", "target character not present"),
                new TestCase("abc", "abc", "abc", "entire source is the only valid window"),
                new TestCase(
                        "acbbaca",
                        "aba",
                        "baca",
                        "target has duplicate a, must include two a and one b"),
                new TestCase(
                        "aaaaaaaaaaaabbbbbcdd",
                        "abcdd",
                        "abbbbbcdd",
                        "target requires two d characters"),
                new TestCase(
                        "cabwefgewcwaefgcf", "cae", "cwae", "valid window appears after shrinking"),
                new TestCase(
                        "aaaaaaaaab", "ab", "ab", "many extra leading chars should shrink away"),
                new TestCase("bbaa", "aba", "baa", "duplicate a plus b"),
                new TestCase("abcabdebac", "cda", "cabd", "minimum window occurs in middle"));
    }

    @ParameterizedTest
    @MethodSource("testCases")
    void minWindow(TestCase testCase) {
        MinimumWindowSubstring solver = new MinimumWindowSubstring();

        String actual = solver.minWindow(testCase.s, testCase.t);

        assertEquals(testCase.expected, actual);
    }

    private record TestCase(String s, String t, String expected, String description) {}
}
