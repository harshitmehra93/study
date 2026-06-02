package study.neetcode.interview.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class LongestSubstringWithoutRepeatingCharactersTest {

    static Stream<TestCase> testCases() {
        return Stream.of(
                new TestCase("abcabcbb", 3, "classic case: longest is abc"),
                new TestCase("bbbbb", 1, "all same characters"),
                new TestCase("pwwkew", 3, "longest is wke; pwke is not contiguous"),
                new TestCase("", 0, "empty string"),
                new TestCase("a", 1, "single character"),
                new TestCase("ab", 2, "two unique characters"),
                new TestCase("aa", 1, "two duplicate characters"),
                new TestCase("dvdf", 3, "important trap: longest is vdf"),
                new TestCase("abba", 2, "important trap for left pointer jumping"),
                new TestCase("tmmzuxt", 5, "longest is mzuxt"),
                new TestCase("abcdef", 6, "all unique"),
                new TestCase("abcdea", 5, "repeat of first char at end"),
                new TestCase("aab", 2, "left must move past first a"),
                new TestCase("ohvhjdml", 6, "longest is vhjdml"),
                new TestCase("anviaj", 5, "longest is nviaj"));
    }

    @ParameterizedTest
    @MethodSource("testCases")
    void lengthOfLongestSubstring(TestCase testCase) {
        LongestSubstringWithoutRepeatingCharacters solver =
                new LongestSubstringWithoutRepeatingCharacters();

        int actual = solver.lengthOfLongestSubstring(testCase.s);

        assertEquals(testCase.expected, actual);
    }

    private record TestCase(String s, int expected, String description) {}
}
