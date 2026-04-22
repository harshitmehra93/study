package study.neetcode.coreskills.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class PerfectSquaresTest {
    @ParameterizedTest
    @MethodSource("testCases")
    public void test(int n, int result) {
        PerfectSquares test = new PerfectSquares();
        assertEquals(result, test.perfectSquares(n));
    }

    static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of(0, 0),
                Arguments.of(1, 1),
                Arguments.of(2, 2),
                Arguments.of(3, 3),
                Arguments.of(4, 1),
                Arguments.of(12, 3), // 4 + 4 + 4
                Arguments.of(13, 2), // 4 + 9
                Arguments.of(99, 3), // 81 + 9 + 9
                Arguments.of(100, 1), // 10^2
                Arguments.of(101, 2), // 100 + 1
                Arguments.of(144, 1), // 12^2
                Arguments.of(145, 2), // 144 + 1
                Arguments.of(169, 1), // 13^2
                Arguments.of(7, 4),
                Arguments.of(15, 4),
                Arguments.of(23, 4),
                Arguments.of(28, 4),
                Arguments.of(60, 4));
    }
}
