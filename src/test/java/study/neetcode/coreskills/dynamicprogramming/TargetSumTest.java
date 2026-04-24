package study.neetcode.coreskills.dynamicprogramming;

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

                // Given example
                Arguments.of(new int[] {1, 1, 1, 1, 1}, 3, 5),

                // Single element
                Arguments.of(new int[] {1}, 1, 1),
                Arguments.of(new int[] {1}, -1, 1),
                Arguments.of(new int[] {1}, 0, 0),

                // Small arrays
                Arguments.of(new int[] {1, 2, 1}, 2, 2),
                Arguments.of(new int[] {1, 2, 3}, 0, 2),

                // All zeros (important edge case)
                Arguments.of(new int[] {0, 0, 0, 0, 0}, 0, 32), // 2^5
                Arguments.of(new int[] {0, 0, 0}, 1, 0),

                // Mix with zeros
                Arguments.of(new int[] {0, 0, 1}, 1, 4),

                // Larger combinations
                Arguments.of(new int[] {2, 3, 5, 6, 8, 10}, 10, 1),

                // No possible solution
                Arguments.of(new int[] {2, 4, 6}, 5, 0),

                // Larger values
                Arguments.of(new int[] {100, 200, 300, 400, 500}, 300, 3),

                // Symmetric possibilities
                Arguments.of(new int[] {1, 2, 3, 4, 5}, 3, 3),

                // Stress case
                Arguments.of(new int[] {1, 2, 1, 2, 1, 2, 1, 2, 1, 2}, 3, 135));
    }
}
