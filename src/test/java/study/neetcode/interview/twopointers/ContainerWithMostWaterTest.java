package study.neetcode.interview.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class ContainerWithMostWaterTest {

    static Stream<TestCase> testCases() {
        return Stream.of(
                new TestCase(new int[] {1, 8, 6, 2, 5, 4, 8, 3, 7}, 49, "classic case"),
                new TestCase(new int[] {1, 1}, 1, "minimum length"),
                new TestCase(new int[] {4, 3, 2, 1, 4}, 16, "best container uses both ends"),
                new TestCase(new int[] {1, 2, 1}, 2, "middle height is taller but width matters"),
                new TestCase(new int[] {1, 2, 3, 4, 5}, 6, "strictly increasing heights"),
                new TestCase(new int[] {5, 4, 3, 2, 1}, 6, "strictly decreasing heights"),
                new TestCase(new int[] {5, 5, 5, 5}, 15, "all heights same"),
                new TestCase(
                        new int[] {2, 3, 10, 5, 7, 8, 9},
                        36,
                        "best is not obvious from tallest pair"),
                new TestCase(new int[] {1, 100, 1, 1, 1, 100, 1}, 400, "two tall lines far apart"),
                new TestCase(new int[] {2, 1}, 1, "two elements decreasing"));
    }

    @ParameterizedTest(name = "{2}: height={0}")
    @MethodSource("testCases")
    void maxArea(TestCase testCase) {
        ContainerWithMostWater solver = new ContainerWithMostWater();

        int actual = solver.maxArea(testCase.height);

        assertEquals(testCase.expected, actual);
    }

    private record TestCase(int[] height, int expected, String description) {
        @Override
        public String toString() {
            return Arrays.toString(height);
        }
    }
}
