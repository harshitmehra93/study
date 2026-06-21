package study.interview.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class ThreeSumTest {

    static Stream<TestCase> testCases() {
        return Stream.of(
                new TestCase(
                        new int[] {-1, 0, 1, 2, -1, -4},
                        List.of(List.of(-1, -1, 2), List.of(-1, 0, 1)),
                        "classic case"),
                new TestCase(new int[] {0, 1, 1}, List.of(), "no triplet sums to zero"),
                new TestCase(
                        new int[] {0, 0, 0}, List.of(List.of(0, 0, 0)), "exactly one zero triplet"),
                new TestCase(
                        new int[] {0, 0, 0, 0},
                        List.of(List.of(0, 0, 0)),
                        "duplicate zeroes should produce only one triplet"),
                new TestCase(
                        new int[] {-2, 0, 1, 1, 2},
                        List.of(List.of(-2, 0, 2), List.of(-2, 1, 1)),
                        "duplicate middle values"),
                new TestCase(
                        new int[] {-4, -2, -2, -2, 0, 1, 2, 2, 2, 3},
                        List.of(List.of(-4, 1, 3), List.of(-4, 2, 2), List.of(-2, 0, 2)),
                        "many duplicates"),
                new TestCase(new int[] {1, 2, 3, 4}, List.of(), "all positive numbers"),
                new TestCase(new int[] {-5, -4, -3, -2}, List.of(), "all negative numbers"),
                new TestCase(
                        new int[] {-1, -1, -1, 2, 2},
                        List.of(List.of(-1, -1, 2)),
                        "duplicate fixed pointer should be skipped"),
                new TestCase(
                        new int[] {-2, -1, 0, 1, 2},
                        List.of(List.of(-2, 0, 2), List.of(-1, 0, 1)),
                        "multiple simple triplets"),
                new TestCase(new int[] {}, List.of(), "empty array"),
                new TestCase(new int[] {1, -1}, List.of(), "less than three elements"));
    }

    @ParameterizedTest
    @MethodSource("testCases")
    void threeSum(TestCase testCase) {
        ThreeSum solver = new ThreeSum();

        List<List<Integer>> actual = solver.threeSum(testCase.nums);

        assertEquals(normalize(testCase.expected), normalize(actual));
    }

    private static Set<List<Integer>> normalize(List<List<Integer>> triplets) {
        Set<List<Integer>> normalized = new HashSet<>();

        for (List<Integer> triplet : triplets) {
            List<Integer> copy = new ArrayList<>(triplet);
            Collections.sort(copy);
            normalized.add(copy);
        }

        return normalized;
    }

    private record TestCase(int[] nums, List<List<Integer>> expected, String description) {}
}
