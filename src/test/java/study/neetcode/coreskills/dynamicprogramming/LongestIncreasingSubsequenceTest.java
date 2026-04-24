package study.neetcode.coreskills.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LongestIncreasingSubsequenceTest {

    @ParameterizedTest
    @MethodSource("testCases")
    void test(int[] nums, int expected) {
        LongestIncreasingSubsequence solver = new LongestIncreasingSubsequence();
        assertEquals(expected, solver.longestIncreasingSubsequence(nums));
    }

    static Stream<Arguments> testCases() {
        return Stream.of(

                // Basic
                Arguments.of(new int[] {10, 9, 2, 5, 3, 7, 101, 18}, 4),

                // All increasing
                Arguments.of(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 10),

                // All decreasing
                Arguments.of(new int[] {10, 9, 8, 7, 6, 5, 4, 3, 2, 1}, 1),

                // All same
                Arguments.of(new int[] {5, 5, 5, 5, 5}, 1),

                // Zig-zag pattern
                Arguments.of(new int[] {1, 3, 2, 4, 3, 5, 4, 6}, 5),

                // Alternating highs and lows
                Arguments.of(new int[] {10, 1, 9, 2, 8, 3, 7, 4, 6, 5}, 5),

                // Larger increasing subsequence hidden
                Arguments.of(new int[] {50, 3, 10, 7, 40, 80}, 4),

                // Medium random
                Arguments.of(new int[] {4, 10, 4, 3, 8, 9}, 3),

                // Many duplicates + increasing tail
                Arguments.of(new int[] {2, 2, 2, 2, 2, 3, 4, 5}, 4),

                // Long mixed case
                Arguments.of(new int[] {5, 7, 4, 8, 6, 9, 10, 3, 2, 1, 11, 12, 0, 13}, 8),

                // Stress case (forces DP)
                Arguments.of(
                        new int[] {
                            9, 1, 3, 7, 5, 6, 20, 4, 8, 2, 10, 11, 15, 14, 13, 12, 16, 17, 18
                        },
                        11));
    }
}
