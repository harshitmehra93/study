package study.interview.twopointers;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Arrays;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class TwoSumIITest {

    static Stream<TestCase> testCases() {
        return Stream.of(
                new TestCase(new int[] {2, 7, 11, 15}, 9, new int[] {1, 2}, "classic case"),
                new TestCase(
                        new int[] {2, 3, 4}, 6, new int[] {1, 3}, "uses first and last element"),
                new TestCase(
                        new int[] {-1, 0},
                        -1,
                        new int[] {1, 2},
                        "two elements with negative target"),
                new TestCase(
                        new int[] {1, 2, 3, 4, 4, 9},
                        8,
                        new int[] {4, 5},
                        "duplicate values form target"),
                new TestCase(
                        new int[] {-5, -3, 0, 2, 4, 8},
                        5,
                        new int[] {2, 6},
                        "negative plus positive"),
                new TestCase(
                        new int[] {-10, -7, -3, -1}, -8, new int[] {2, 4}, "all negative numbers"),
                new TestCase(
                        new int[] {1, 2, 3, 4, 5}, 9, new int[] {4, 5}, "solution near right side"),
                new TestCase(
                        new int[] {1, 2, 3, 4, 5}, 3, new int[] {1, 2}, "solution near left side"),
                new TestCase(new int[] {0, 0, 3, 4}, 0, new int[] {1, 2}, "two zeroes"),
                new TestCase(
                        new int[] {1, 10, 20, 30, 40, 50},
                        70,
                        new int[] {3, 6},
                        "middle plus last"));
    }

    @ParameterizedTest
    @MethodSource("testCases")
    void twoSum(TestCase testCase) {
        TwoSumII solver = new TwoSumII();

        int[] actual = solver.twoSum(testCase.numbers, testCase.target);

        assertArrayEquals(testCase.expected, actual);
    }

    private record TestCase(int[] numbers, int target, int[] expected, String description) {
        @Override
        public String toString() {
            return Arrays.toString(numbers);
        }
    }
}
