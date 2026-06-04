package study.neetcode.interview.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Arrays;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class SlidingWindowMaximumTest {

    static Stream<TestCase> testCases() {
        return Stream.of(
                new TestCase(
                        new int[] {1, 3, -1, -3, 5, 3, 6, 7},
                        3,
                        new int[] {3, 3, 5, 5, 6, 7},
                        "classic case"),
                new TestCase(new int[] {1}, 1, new int[] {1}, "single element"),
                new TestCase(
                        new int[] {1, -1},
                        1,
                        new int[] {1, -1},
                        "window size one returns original array"),
                new TestCase(
                        new int[] {1, -1}, 2, new int[] {1}, "window size equals array length"),
                new TestCase(
                        new int[] {9, 8, 7, 6, 5},
                        3,
                        new int[] {9, 8, 7},
                        "strictly decreasing array"),
                new TestCase(
                        new int[] {1, 2, 3, 4, 5},
                        3,
                        new int[] {3, 4, 5},
                        "strictly increasing array"),
                new TestCase(
                        new int[] {4, 4, 4, 4}, 2, new int[] {4, 4, 4}, "all duplicate values"),
                new TestCase(
                        new int[] {2, 1, 2, 1, 2}, 3, new int[] {2, 2, 2}, "repeated max values"),
                new TestCase(
                        new int[] {7, 2, 4},
                        2,
                        new int[] {7, 4},
                        "small case where old max expires"),
                new TestCase(
                        new int[] {1, 3, 1, 2, 0, 5},
                        3,
                        new int[] {3, 3, 2, 5},
                        "new max appears at the end"),
                new TestCase(
                        new int[] {-7, -8, 7, 5, 7, 1, 6, 0},
                        4,
                        new int[] {7, 7, 7, 7, 7},
                        "mixed negative and positive values"),
                new TestCase(
                        new int[] {10, 9, 8, 7, 100},
                        4,
                        new int[] {10, 100},
                        "large max enters after old window"));
    }

    @ParameterizedTest(name = "{3}: nums={0}, k={1}")
    @MethodSource("testCases")
    void maxSlidingWindow(TestCase testCase) {
        SlidingWindowMaximum solver = new SlidingWindowMaximum();

        int[] actual = solver.maxSlidingWindow(testCase.nums, testCase.k);

        assertArrayEquals(testCase.expected, actual);
    }

    private record TestCase(int[] nums, int k, int[] expected, String description) {
        @Override
        public String toString() {
            return Arrays.toString(nums);
        }
    }
}
