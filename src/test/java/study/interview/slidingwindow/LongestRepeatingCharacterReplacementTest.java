package study.interview.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class LongestRepeatingCharacterReplacementTest {

    static Stream<TestCase> testCases() {
        return Stream.of(
                new TestCase(
                        "ABAB", 2, 4, "classic case: replace two chars to make whole string same"),
                new TestCase("AABABBA", 1, 4, "classic trap: longest valid window length is 4"),
                new TestCase("AAAA", 0, 4, "already all same, no replacements needed"),
                new TestCase(
                        "ABCDE",
                        0,
                        1,
                        "no replacements allowed, longest repeated-char substring is 1"),
                new TestCase(
                        "ABCDE", 1, 2, "with one replacement, any two-char window can become same"),
                new TestCase("ABCDE", 4, 5, "can replace all except one char"),
                new TestCase("BAAA", 0, 3, "longest existing repeated block is AAA"),
                new TestCase("BAAA", 1, 4, "replace B with A, whole string becomes AAAA"),
                new TestCase(
                        "ABBBAC",
                        2,
                        5,
                        "ABBBA is valid with two A replacements, full window is invalid"),
                new TestCase(
                        "ABBBAC", 3, 6, "full string can become all B with three replacements"),
                new TestCase("AAAB", 0, 3, "no replacement, longest is AAA"),
                new TestCase("AAAB", 1, 4, "replace B with A"),
                new TestCase("AABA", 1, 4, "replace B with A"),
                new TestCase("ABAA", 0, 2, "longest existing repeated block is AA"),
                new TestCase("ABAA", 1, 4, "replace B with A"),
                new TestCase("BAAAB", 2, 5, "replace both Bs with A"),
                new TestCase("ABABBA", 2, 5, "longest valid window has length 5"),
                new TestCase("A", 0, 1, "single character"),
                new TestCase("A", 1, 1, "single character with replacement budget still length 1"));
    }

    @ParameterizedTest
    @MethodSource("testCases")
    void characterReplacement(TestCase testCase) {
        LongestRepeatingCharacterReplacement solver = new LongestRepeatingCharacterReplacement();

        int actual = solver.characterReplacement(testCase.s, testCase.k);

        assertEquals(testCase.expected, actual);
    }

    private record TestCase(String s, int k, int expected, String description) {}
}
