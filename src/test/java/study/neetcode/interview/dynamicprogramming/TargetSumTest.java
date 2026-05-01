package study.neetcode.interview.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class TargetSumTest {

    @ParameterizedTest
    @MethodSource("testCases")
    void test(int[] nums, int target, int expected) {
        TargetSum solver = new TargetSum();
        assertEquals(expected, solver.targetSum(nums, target));
    }

    static Stream<Arguments> testCases() {
        return Stream.of(

                // ===== BASIC =====
                Arguments.of(new int[] {1, 1, 1, 1, 1}, 3, 5),

                // ===== EDGE =====
                Arguments.of(new int[] {0, 0, 0, 0, 0}, 0, 32), // 2^5
                Arguments.of(new int[] {0, 0, 1}, 1, 4),

                // ===== MEDIUM =====
                Arguments.of(new int[] {1, 2, 3, 4, 5}, 3, 3),
                Arguments.of(new int[] {2, 3, 5, 6, 8, 10}, 10, 1),

                // ===== LARGE (forces DP) =====

                // Fibonacci-style explosion
                Arguments.of(
                        new int[] {
                            1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                            1, 1, 1, 1, 1, 1, 1, 1, 1, 1
                        },
                        0,
                        184756 // C(20,10)
                        ),

                // Mixed small numbers → heavy overlap
                Arguments.of(
                        new int[] {
                            1, 2, 1, 2, 1, 2, 1, 2, 1, 2,
                            1, 2, 1, 2, 1, 2, 1, 2, 1, 2
                        },
                        2,
                        112035),

                // Increasing sequence (large state space)
                Arguments.of(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15}, 5, 0),

                // Larger values (tests sum range)
                Arguments.of(new int[] {10, 20, 30, 40, 50, 60, 70, 80, 90, 100}, 0, 0),

                // ===== ZERO HEAVY (important DP edge) =====
                Arguments.of(
                        new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, 1, 1024 // 2^10
                        ),

                // ===== HARD NO SOLUTION =====
                Arguments.of(new int[] {3, 7, 11, 15, 19, 23, 27, 31}, 2, 3),

                // ===== BRUTAL (only DP survives) =====
                Arguments.of(
                        new int[] {
                            1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                            1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                            1, 1
                        },
                        2,
                        646646 // C(22,12)
                        ));
    }
}
