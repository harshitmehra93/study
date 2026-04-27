package study.neetcode.coreskills.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class EqualPartitionSumTest {

    @ParameterizedTest
    @MethodSource("testCases")
    void test(int[] nums, boolean expected) {
        EqualPartitionSum solver = new EqualPartitionSum();
        assertEquals(expected, solver.canBePartitionedEqually(nums));
    }

    static Stream<Arguments> testCases() {
        return Stream.of(

                // Basic
                Arguments.of(new int[] {1, 5, 11, 5}, true),
                Arguments.of(new int[] {1, 2, 3, 5}, false),

                // Small edge cases
                Arguments.of(new int[] {2, 2}, true),
                Arguments.of(new int[] {1}, false),

                // All same numbers
                Arguments.of(new int[] {2, 2, 2, 2}, true),
                Arguments.of(new int[] {3, 3, 3}, false),

                // Larger valid partitions
                Arguments.of(new int[] {1, 2, 3, 4, 5, 6, 7}, true),
                Arguments.of(new int[] {2, 3, 7, 8, 10}, true),

                // Larger invalid
                Arguments.of(new int[] {2, 3, 5, 9}, false),

                // Many small numbers
                Arguments.of(new int[] {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, true),

                // Mixed tricky
                Arguments.of(new int[] {1, 2, 5, 6, 7, 10}, false),
                Arguments.of(new int[] {1, 2, 3, 4, 6}, true),

                // Stress cases
                Arguments.of(new int[] {100, 100, 100, 100, 100, 100}, true),
                Arguments.of(new int[] {100, 200, 300, 400, 500}, false),

                // Slightly larger
                Arguments.of(new int[] {3, 34, 4, 12, 5, 2}, false),
                Arguments.of(new int[] {3, 34, 4, 12, 5, 2, 1}, false),
                Arguments.of(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12}, true),
                Arguments.of(new int[] {1, 2, 3, 5, 7, 11, 13, 17, 19}, true),
                Arguments.of(new int[] {2, 3, 5, 7, 11, 13, 17, 19, 23}, true),
                Arguments.of(new int[] {3, 34, 4, 12, 5, 2, 7, 8, 9, 10}, true),
                Arguments.of(
                        new int[] {1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 8, 8, 9, 9, 10, 10},
                        true),
                Arguments.of(
                        new int[] {2, 3, 7, 8, 10, 12, 15, 18, 20, 25, 30, 35, 40, 45, 50}, true),
                Arguments.of(
                        new int[] {
                            1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20
                        },
                        true),
                Arguments.of(
                        new int[] {
                            2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67,
                            71
                        },
                        false));
    }
}
