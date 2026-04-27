package study.neetcode.coreskills.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class Knapsack01Test {

    @ParameterizedTest
    @MethodSource("testCases")
    void test(int[] weights, int[] values, int capacity, int expected) {
        Knapsack01 solver = new Knapsack01();
        assertEquals(expected, solver.knapsack(weights, values, capacity));
    }

    static Stream<Arguments> testCases() {
        return Stream.of(
                // Basic example
                Arguments.of(new int[] {1, 3, 4, 5}, new int[] {1, 4, 5, 7}, 7, 9),

                // Take weights 2 and 3
                Arguments.of(new int[] {2, 3, 4}, new int[] {4, 5, 6}, 5, 9),

                // No item fits
                Arguments.of(new int[] {5, 6, 7}, new int[] {10, 20, 30}, 4, 0),

                // Important: catches overweight bug
                Arguments.of(new int[] {10}, new int[] {100}, 5, 0),

                // Capacity zero
                Arguments.of(new int[] {1, 2, 3}, new int[] {10, 20, 30}, 0, 0),

                // Choose best value, not most items
                Arguments.of(new int[] {1, 2, 3}, new int[] {6, 10, 12}, 5, 22),

                // Single item fits
                Arguments.of(new int[] {5}, new int[] {50}, 5, 50),

                // Exact capacity with multiple items
                Arguments.of(new int[] {2, 2, 3, 4}, new int[] {3, 4, 5, 6}, 5, 9),

                // Greedy by value fails: 20 alone vs 8+8
                Arguments.of(new int[] {5, 4, 4}, new int[] {20, 8, 8}, 8, 20),

                // Greedy by value/weight can also mislead
                Arguments.of(new int[] {10, 20, 30}, new int[] {60, 100, 120}, 50, 220),

                // Larger mixed case
                Arguments.of(new int[] {3, 4, 7, 8, 9}, new int[] {4, 5, 10, 11, 13}, 17, 24),

                // All items fit
                Arguments.of(new int[] {1, 2, 3}, new int[] {10, 20, 30}, 6, 60),
                // Heavy memo-forcing case: 30 items, capacity 150
                Arguments.of(
                        new int[] {
                            3, 7, 8, 9, 10, 11, 12, 13, 14, 15,
                            16, 17, 18, 19, 20, 21, 22, 23, 24, 25,
                            26, 27, 28, 29, 30, 31, 32, 33, 34, 35
                        },
                        new int[] {
                            4, 10, 11, 13, 14, 16, 17, 19, 20, 22,
                            23, 25, 26, 28, 29, 31, 32, 34, 35, 37,
                            38, 40, 41, 43, 44, 46, 47, 49, 50, 52
                        },
                        150,
                        222));
    }
}
