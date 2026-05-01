package study.neetcode.interview.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class RodCuttingTest {
    @ParameterizedTest
    @MethodSource("testCases")
    void test(int[] priceList, int rodLength, int answer) {
        RodCutting test = new RodCutting();
        assertEquals(answer, test.giveBestSplit(rodLength, priceList));
    }

    static Stream<Arguments> testCases() {
        return Stream.of(

                // Basic cases
                Arguments.of(new int[] {1}, 1, 1),
                Arguments.of(new int[] {1, 5}, 2, 5),
                Arguments.of(new int[] {2, 5}, 2, 5),

                // Classic examples
                Arguments.of(new int[] {1, 5, 8, 9}, 4, 10),
                Arguments.of(new int[] {1, 5, 8, 9, 10, 17, 17, 20}, 8, 22),

                // Must split multiple times
                Arguments.of(new int[] {2, 5, 7, 8}, 5, 12),
                Arguments.of(new int[] {3, 5, 8, 9}, 4, 12),

                // Reuse of same piece many times
                Arguments.of(new int[] {2, 3, 7}, 5, 11),

                // Greedy trap
                Arguments.of(new int[] {1, 5, 6, 7}, 4, 10),

                // Zero case
                Arguments.of(new int[] {1, 2, 3}, 0, 0),

                // Decreasing prices (cut into 1-length pieces)
                Arguments.of(new int[] {10, 9, 8, 7}, 4, 40),

                // Linear prices
                Arguments.of(new int[] {1, 2, 3, 4, 5}, 5, 5),

                // Large reuse scenario
                Arguments.of(new int[] {1, 3, 4, 5}, 7, 10),

                // Weird distribution
                Arguments.of(new int[] {2, 5, 9, 6}, 4, 11),

                // Non-trivial optimal (fixed comment mentally: 2+2+2)
                Arguments.of(new int[] {3, 7, 8, 9, 10, 17}, 6, 21),

                // Large uniform prices
                Arguments.of(generateLinearPrices(100), 100, 100),

                // Increasing prices
                Arguments.of(generateIncreasingPrices(100), 100, 200),

                // Decreasing prices
                Arguments.of(generateDecreasingPrices(100), 100, 10000),

                // Constant prices
                Arguments.of(generateConstantPrices(100, 2), 100, 200),

                // Spike case (FIXED: 53)
                Arguments.of(new int[] {1, 2, 3, 4, 5, 6, 50, 8, 9, 10}, 10, 53),

                // Large repeated chunk (FIXED: 149)
                Arguments.of(new int[] {2, 5, 9, 10, 12}, 50, 149),

                // CLRS extended
                Arguments.of(new int[] {1, 5, 8, 9, 10, 17, 17, 20, 24, 30}, 30, 90),

                // Greedy trap large
                Arguments.of(new int[] {1, 20, 21, 22, 23}, 20, 200),

                // Sparse good values
                Arguments.of(new int[] {1, 1, 1, 50, 1, 1, 100}, 14, 200),

                // Large predictable
                Arguments.of(new int[] {3, 5, 8, 9, 10, 17, 17, 20}, 40, 120));
    }

    static int[] generateLinearPrices(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = i + 1;
        return arr;
    }

    static int[] generateIncreasingPrices(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = (i + 1) * 2;
        return arr;
    }

    static int[] generateDecreasingPrices(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = n - i;
        return arr;
    }

    static int[] generateConstantPrices(int n, int valuePerUnit) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = valuePerUnit;
        return arr;
    }
}
