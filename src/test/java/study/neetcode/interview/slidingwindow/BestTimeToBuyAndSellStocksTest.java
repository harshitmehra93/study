package study.neetcode.interview.slidingwindow;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class BestTimeToBuyAndSellStockTest {

    static Stream<TestCase> testCases() {
        return Stream.of(
                new TestCase(new int[] {7, 1, 5, 3, 6, 4}, 5, "classic case: buy at 1, sell at 6"),
                new TestCase(new int[] {7, 6, 4, 3, 1}, 0, "strictly decreasing prices, no profit"),
                new TestCase(
                        new int[] {1, 2, 3, 4, 5},
                        4,
                        "strictly increasing prices, buy first sell last"),
                new TestCase(new int[] {5}, 0, "single day, cannot sell after buying"),
                new TestCase(new int[] {5, 5, 5, 5}, 0, "all prices same, no profit"),
                new TestCase(new int[] {2, 4, 1}, 2, "best profit occurs before later lower price"),
                new TestCase(
                        new int[] {3, 2, 6, 5, 0, 3},
                        4,
                        "buy at 2, sell at 6; later 0 to 3 is smaller"),
                new TestCase(new int[] {10, 1, 2, 11}, 10, "minimum price occurs after first day"),
                new TestCase(
                        new int[] {1, 10, 2, 20},
                        19,
                        "do not lock onto first local max; buy at 1 sell at 20"),
                new TestCase(
                        new int[] {8, 1, 9, 2, 10}, 9, "best uses early minimum and later maximum"),
                new TestCase(new int[] {0, 10000}, 10000, "constraint edge: zero to max price"),
                new TestCase(new int[] {10000, 0}, 0, "constraint edge: max price to zero"));
    }

    @ParameterizedTest(name = "{2}")
    @MethodSource("testCases")
    void maxProfit(TestCase testCase) {
        BestTimeToBuyAndSellStock solver = new BestTimeToBuyAndSellStock();

        int actual = solver.maxProfit(testCase.prices);

        assertEquals(testCase.expected, actual);
    }

    @Test
    void largeIncreasingArray() {
        BestTimeToBuyAndSellStock solver = new BestTimeToBuyAndSellStock();

        int[] prices = new int[100_000];
        for (int i = 0; i < prices.length; i++) {
            prices[i] = i;
        }

        assertEquals(99_999, solver.maxProfit(prices));
    }

    @Test
    void largeDecreasingArray() {
        BestTimeToBuyAndSellStock solver = new BestTimeToBuyAndSellStock();

        int[] prices = new int[100_000];
        for (int i = 0; i < prices.length; i++) {
            prices[i] = 100_000 - i;
        }

        assertEquals(0, solver.maxProfit(prices));
    }

    private record TestCase(int[] prices, int expected, String description) {}
}
