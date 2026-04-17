package study.neetcode.coreskills.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class CoinChangeTest {

    static Stream<TestCase> testCases() {
        return Stream.of(
                new TestCase(new int[] {1, 2, 5}, 11, 3),
                new TestCase(new int[] {2}, 3, -1),
                new TestCase(new int[] {1}, 0, 0),
                new TestCase(new int[] {1}, 2, 2),
                new TestCase(new int[] {2, 5, 10, 1}, 27, 4), // 10+10+5+2
                new TestCase(new int[] {186, 419, 83, 408}, 6249, 20) // classic tricky case
                );
    }

    @ParameterizedTest
    @MethodSource("testCases")
    void testCoinChange(TestCase testCase) {
        CoinChange coinChange = new CoinChange();
        assertEquals(testCase.expected, coinChange.coinChange(testCase.coins, testCase.amount));
    }

    static class TestCase {
        int[] coins;
        int amount;
        int expected;

        TestCase(int[] coins, int amount, int expected) {
            this.coins = coins;
            this.amount = amount;
            this.expected = expected;
        }
    }
}
