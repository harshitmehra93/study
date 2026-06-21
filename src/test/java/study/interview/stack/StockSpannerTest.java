package study.interview.stack;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class StockSpannerTest {

    @Test
    void givenExample() {
        StockSpanner stockSpanner = new StockSpanner();

        assertSpans(
                stockSpanner,
                new int[] {100, 80, 60, 70, 60, 75, 85},
                new int[] {1, 1, 1, 2, 1, 4, 6});
    }

    @Test
    void strictlyIncreasingPrices() {
        StockSpanner stockSpanner = new StockSpanner();

        assertSpans(stockSpanner, new int[] {10, 20, 30, 40, 50}, new int[] {1, 2, 3, 4, 5});
    }

    @Test
    void strictlyDecreasingPrices() {
        StockSpanner stockSpanner = new StockSpanner();

        assertSpans(stockSpanner, new int[] {50, 40, 30, 20, 10}, new int[] {1, 1, 1, 1, 1});
    }

    @Test
    void equalPricesAreIncludedInSpan() {
        StockSpanner stockSpanner = new StockSpanner();

        assertSpans(stockSpanner, new int[] {25, 25, 25, 25}, new int[] {1, 2, 3, 4});
    }

    @Test
    void greaterPriceAbsorbsPreviouslyCompressedSpans() {
        StockSpanner stockSpanner = new StockSpanner();

        assertSpans(
                stockSpanner, new int[] {90, 60, 70, 65, 80, 100}, new int[] {1, 1, 2, 1, 4, 6});
    }

    @Test
    void earlierGreaterPriceStopsSpan() {
        StockSpanner stockSpanner = new StockSpanner();

        assertSpans(stockSpanner, new int[] {100, 70, 80, 80, 90}, new int[] {1, 1, 2, 3, 4});
    }

    @Test
    void singlePrice() {
        assertEquals(1, new StockSpanner().next(42));
    }

    @Test
    void multipleInstancesKeepIndependentState() {
        StockSpanner first = new StockSpanner();
        StockSpanner second = new StockSpanner();

        assertEquals(1, first.next(100));
        assertEquals(1, first.next(80));

        assertEquals(1, second.next(10));
        assertEquals(2, second.next(20));

        assertEquals(2, first.next(90));
    }

    @Test
    void longIncreasingSequence() {
        StockSpanner stockSpanner = new StockSpanner();

        for (int price = 1; price <= 10_000; price++) {
            assertEquals(price, stockSpanner.next(price));
        }
    }

    @Test
    void exhaustiveSmallPriceStreamsMatchBruteForceOracle() {
        int streamLength = 7;
        int priceChoices = 4;
        int combinations = (int) Math.pow(priceChoices, streamLength);

        for (int encoded = 0; encoded < combinations; encoded++) {
            int[] prices = decodePrices(encoded, streamLength, priceChoices);
            StockSpanner stockSpanner = new StockSpanner();
            List<Integer> seenPrices = new ArrayList<>();

            for (int price : prices) {
                seenPrices.add(price);
                assertEquals(
                        bruteForceSpan(seenPrices),
                        stockSpanner.next(price),
                        "Failed for stream " + seenPrices);
            }
        }
    }

    private void assertSpans(StockSpanner stockSpanner, int[] prices, int[] expectedSpans) {
        int[] actualSpans = new int[prices.length];
        for (int i = 0; i < prices.length; i++) {
            actualSpans[i] = stockSpanner.next(prices[i]);
        }

        assertArrayEquals(expectedSpans, actualSpans);
    }

    private int[] decodePrices(int encoded, int length, int priceChoices) {
        int[] prices = new int[length];
        for (int i = 0; i < length; i++) {
            prices[i] = encoded % priceChoices + 1;
            encoded /= priceChoices;
        }
        return prices;
    }

    private int bruteForceSpan(List<Integer> prices) {
        int currentPrice = prices.get(prices.size() - 1);
        int span = 0;

        for (int i = prices.size() - 1; i >= 0 && prices.get(i) <= currentPrice; i--) {
            span++;
        }

        return span;
    }
}
