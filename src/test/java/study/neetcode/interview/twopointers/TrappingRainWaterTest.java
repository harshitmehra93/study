package study.neetcode.interview.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class TrappingRainWaterTest {

    static Stream<TestCase> testCases() {
        return Stream.of(
                new TestCase(new int[] {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}, 6, "classic case"),
                new TestCase(new int[] {4, 2, 0, 3, 2, 5}, 9, "classic case with deep valley"),
                new TestCase(new int[] {}, 0, "empty array"),
                new TestCase(new int[] {1}, 0, "single bar"),
                new TestCase(new int[] {1, 2}, 0, "two bars cannot trap water"),
                new TestCase(new int[] {1, 2, 3, 4, 5}, 0, "strictly increasing heights"),
                new TestCase(new int[] {5, 4, 3, 2, 1}, 0, "strictly decreasing heights"),
                new TestCase(new int[] {3, 3, 3, 3}, 0, "flat plateau"),
                new TestCase(new int[] {3, 0, 3}, 3, "simple bowl"),
                new TestCase(new int[] {5, 0, 0, 0, 5}, 15, "wide bowl"),
                new TestCase(new int[] {2, 0, 2}, 2, "small bowl"),
                new TestCase(new int[] {0, 2, 0}, 0, "no right boundary after valley"),
                new TestCase(new int[] {2, 0, 1}, 1, "right boundary lower than left"),
                new TestCase(new int[] {5, 4, 1, 2}, 1, "water trapped near end"),
                new TestCase(
                        new int[] {4, 2, 3}, 1, "right boundary lower but enough to trap one"));
    }

    @ParameterizedTest(name = "{2}: height={0}")
    @MethodSource("testCases")
    void trap(TestCase testCase) {
        TrappingRainWater solver = new TrappingRainWater();

        int actual = solver.trap(testCase.height);

        assertEquals(testCase.expected, actual);
    }

    private record TestCase(int[] height, int expected, String description) {
        @Override
        public String toString() {
            return Arrays.toString(height);
        }
    }
}
