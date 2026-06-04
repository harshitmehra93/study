package study.neetcode.interview.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class FindAllAnagramsInAStringTest {

    static Stream<TestCase> testCases() {
        return Stream.of(
                new TestCase("cbaebabacd", "abc", List.of(0, 6), "classic case: cba and bac"),
                new TestCase("abab", "ab", List.of(0, 1, 2), "overlapping anagrams: ab, ba, ab"),
                new TestCase("baa", "aa", List.of(1), "duplicate chars in pattern"),
                new TestCase("aaabaaa", "aaa", List.of(0, 4), "duplicate-heavy windows"),
                new TestCase(
                        "aaaaaaaaaa",
                        "aa",
                        List.of(0, 1, 2, 3, 4, 5, 6, 7, 8),
                        "all overlapping matches"),
                new TestCase("abcdefg", "hij", List.of(), "no matching characters"),
                new TestCase("abc", "abcd", List.of(), "pattern longer than string"),
                new TestCase("a", "a", List.of(0), "single character match"),
                new TestCase("a", "b", List.of(), "single character mismatch"),
                new TestCase("af", "be", List.of(), "same length but no match"),
                new TestCase("ab", "ba", List.of(0), "whole string is an anagram"),
                new TestCase(
                        "eidbaooo",
                        "ab",
                        List.of(3),
                        "same case as Permutation in String, index of ba"),
                new TestCase(
                        "eidboaoo", "ab", List.of(), "same false case as Permutation in String"),
                new TestCase("abcabc", "abc", List.of(0, 1, 2, 3), "many overlapping anagrams"),
                new TestCase(
                        "abacbabc",
                        "abc",
                        List.of(1, 2, 3, 5),
                        "multiple scattered valid windows"));
    }

    @ParameterizedTest
    @MethodSource("testCases")
    void findAnagrams(TestCase testCase) {
        FindAllAnagramsInAString solver = new FindAllAnagramsInAString();

        List<Integer> actual = solver.findAnagrams(testCase.s, testCase.p);

        assertEquals(testCase.expected, actual);
    }

    private record TestCase(String s, String p, List<Integer> expected, String description) {}
}
