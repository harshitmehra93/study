package study.neetcode.coreskills.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CombinationSum4Test {
    @ParameterizedTest
    @MethodSource("testCases")
    void test(int[] nums, int target, int expected) {
        CombinationSum4 cs = new CombinationSum4();
        assertEquals(expected, cs.combinationSum(nums, target));
    }

    static Stream<Arguments> testCases() {
        return Stream.of(
                // Basic cases
                Arguments.of(new int[] {1, 2, 3}, 4, 7),

                // Single element
                Arguments.of(new int[] {1}, 3, 1),
                Arguments.of(new int[] {2}, 3, 0),

                // Order matters (important case)
                Arguments.of(new int[] {1, 2}, 3, 3),
                // (1,1,1), (1,2), (2,1)

                // Larger combinations
                Arguments.of(new int[] {2, 3, 5}, 8, 6),

                // Zero target
                Arguments.of(new int[] {1, 2, 3}, 0, 1),

                // No solution
                Arguments.of(new int[] {4, 5}, 3, 0),

                // Repeated use allowed
                Arguments.of(new int[] {3, 4, 7}, 7, 3),
                // (7), (3+4)

                // Slightly bigger
                Arguments.of(new int[] {1, 2, 3}, 10, 274),
                Arguments.of(new int[] {1, 2, 3}, 20, 121415),
                Arguments.of(new int[] {1, 2, 3}, 25, 2555757),
                Arguments.of(new int[] {1, 2}, 30, 1346269),
                Arguments.of(new int[] {1, 2, 3}, 32, 181997601));
    }
}
