package study.interview.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class PermutationInStringTest {

    static Stream<TestCase> testCases() {
        return Stream.of(
                new TestCase("ab", "eidbaooo", true, "classic true case: s2 contains ba"),
                new TestCase("ab", "eidboaoo", false, "classic false case"),
                new TestCase("ab", "ba", true, "entire s2 is a permutation"),
                new TestCase("ab", "ab", true, "exact same string"),
                new TestCase(
                        "abc",
                        "ccccbbbbaaaa",
                        false,
                        "same letters exist but no window has correct counts"),
                new TestCase("adc", "dcda", true, "permutation cda exists at the end"),
                new TestCase("hello", "ooolleoooleh", false, "common tricky false case"),
                new TestCase("a", "a", true, "single character match"),
                new TestCase("a", "b", false, "single character mismatch"),
                new TestCase("a", "bbbba", true, "single character appears at end"),
                new TestCase("abcd", "abc", false, "s1 longer than s2"),
                new TestCase("aa", "eidbaooo", false, "requires two a characters, not just one"),
                new TestCase("aa", "eidbaaaooo", true, "contains aa"),
                new TestCase("aab", "eidbaaooo", true, "contains baa, same frequency as aab"),
                new TestCase("aab", "eidabaooo", true, "contains aba, same frequency as aab"),
                new TestCase(
                        "aabc",
                        "caaebaooo",
                        false,
                        "near miss: has letters but not all in one fixed window"),
                new TestCase("aabc", "caaabcooo", true, "contains aabc permutation"),
                new TestCase("xyz", "afdgzyxksldfm", true, "permutation zyx in middle"),
                new TestCase("xyz", "afdgzyksldfm", false, "missing x"));
    }

    @ParameterizedTest
    @MethodSource("testCases")
    void checkInclusion(TestCase testCase) {
        PermutationInString solver = new PermutationInString();

        boolean actual = solver.checkInclusion(testCase.s1, testCase.s2);

        assertEquals(testCase.expected, actual);
    }

    private record TestCase(String s1, String s2, boolean expected, String description) {}
}
