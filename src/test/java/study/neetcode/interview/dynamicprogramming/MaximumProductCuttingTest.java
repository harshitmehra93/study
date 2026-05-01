package study.neetcode.interview.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class MaximumProductCuttingTest {
    @ParameterizedTest
    @MethodSource("testCases")
    void test(int n, int expected) {
        MaximumProductCutting solver = new MaximumProductCutting();
        assertEquals(expected, solver.maximumProductCutting(n));
    }

    static Stream<Arguments> testCases() {
        return Stream.of(
                // Base cases
                Arguments.of(1, 1),
                Arguments.of(2, 1),
                Arguments.of(3, 2),

                // Basic cases
                Arguments.of(4, 4),
                Arguments.of(5, 6),
                Arguments.of(6, 9),

                // Medium cases
                Arguments.of(7, 12),
                Arguments.of(8, 18),
                Arguments.of(9, 27),

                // Pattern cases
                Arguments.of(10, 36),
                Arguments.of(11, 54),
                Arguments.of(12, 81),

                // Larger validation
                Arguments.of(15, 243),
                Arguments.of(20, 1458),
                Arguments.of(25, 8748),
                Arguments.of(30, 59049),
                Arguments.of(35, 354294),
                Arguments.of(40, 2125764));
    }
}
