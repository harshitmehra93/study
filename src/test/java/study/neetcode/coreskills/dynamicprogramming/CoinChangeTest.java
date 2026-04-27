package study.neetcode.coreskills.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CoinChangeTest {

    static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of(new int[] {1, 2, 5}, 11, 3),
                Arguments.of(new int[] {2}, 3, -1),
                Arguments.of(new int[] {1}, 0, 0),
                Arguments.of(new int[] {1}, 2, 2),
                Arguments.of(new int[] {2, 5, 10, 1}, 27, 4), // 10+10+5+2
                Arguments.of(new int[] {186, 419, 83, 408}, 6249, 20) // classic tricky case
                );
    }

    @ParameterizedTest
    @MethodSource("testCases")
    void testCoinChange(int[] coins, int target, int result) {
        CoinChange coinChange = new CoinChange();
        assertEquals(result, coinChange.coinChange(coins, target));
    }
}
