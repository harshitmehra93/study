package study.neetcode.interview.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class MinCostClimbingStairsTest {

    @ParameterizedTest
    @MethodSource("testCases")
    void testMinCost(int[] cost, int expected) {
        MinCostClimbingStairs test = new MinCostClimbingStairs();
        assertEquals(expected, test.minCostClimbingStairs(cost));
    }

    static Stream<Arguments> testCases() {
        return Stream.of(
                // 🟢 Basic
                Arguments.of(new int[] {10, 15, 20}, 15),
                Arguments.of(new int[] {1, 2, 3}, 2),
                Arguments.of(new int[] {1, 1, 1}, 1),

                // 🟡 Standard
                Arguments.of(new int[] {1, 100, 1, 1, 1, 100, 1, 1, 100, 1}, 6),
                Arguments.of(new int[] {0, 0, 0, 0}, 0),
                Arguments.of(new int[] {5, 10, 15}, 10),

                // 🔵 Edge
                Arguments.of(new int[] {1}, 0),
                Arguments.of(new int[] {5, 6}, 5),
                Arguments.of(new int[] {0, 1}, 0),

                // 🟣 Tricky
                Arguments.of(new int[] {1, 100, 1, 100, 1}, 3),
                Arguments.of(new int[] {100, 1, 100, 1, 100, 1}, 3),

                // Increasing
                Arguments.of(new int[] {1, 2, 3, 4, 5, 6}, 9),

                // Decreasing
                Arguments.of(new int[] {6, 5, 4, 3, 2, 1}, 9),

                // 🔥 Larger
                Arguments.of(new int[] {1, 2, 1, 2, 1, 2, 1, 2, 1, 2}, 5),
                Arguments.of(new int[] {10, 15, 20, 10, 5, 15, 5, 10, 5, 5}, 40));
    }

    // 💣 Stress test
    @Test
    void stressTest() {
        int[] cost = new int[1000];
        Arrays.fill(cost, 1);

        MinCostClimbingStairs test = new MinCostClimbingStairs();
        assertEquals(500, test.minCostClimbingStairs(cost));
    }
}
