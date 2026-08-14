package study.contest.cses;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class SlidingWindowModeTest {

    private final SlidingWindowMode solver = new SlidingWindowMode();

    static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of(
                        "given example",
                        new int[] {1, 2, 3, 2, 5, 2, 4, 4},
                        3,
                        new int[] {1, 2, 2, 2, 2, 4}),
                Arguments.of(
                        "smallest value wins frequency ties",
                        new int[] {4, 4, 2, 2, 4, 2},
                        4,
                        new int[] {2, 2, 2}),
                Arguments.of(
                        "window size one returns every element",
                        new int[] {7, 3, 9, 3},
                        1,
                        new int[] {7, 3, 9, 3}),
                Arguments.of(
                        "window covers the entire array",
                        new int[] {9, 9, 3, 3, 5},
                        5,
                        new int[] {3}),
                Arguments.of("single element", new int[] {42}, 1, new int[] {42}),
                Arguments.of(
                        "all values are equal", new int[] {6, 6, 6, 6, 6}, 3, new int[] {6, 6, 6}),
                Arguments.of(
                        "mode changes when values enter and leave",
                        new int[] {1, 1, 2, 2, 2, 1, 1},
                        3,
                        new int[] {1, 2, 2, 2, 1}),
                Arguments.of(
                        "values near the upper constraint",
                        new int[] {1_000_000_000, 999_999_999, 1_000_000_000, 999_999_999},
                        3,
                        new int[] {1_000_000_000, 999_999_999}));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("testCases")
    void returnsModeForEveryWindow(String description, int[] nums, int k, int[] expected) {
        int[] actual = solver.slidingWindowMode(nums, k);

        assertArrayEquals(expected, actual);
    }
}
