package study.interview.slidingwindow;

/*
Best Time to Buy and Sell Stock — Sliding Window / One-pass

You are given an integer array prices, where prices[i] is the price of a stock on day i.

You may choose one day to buy one stock and choose a later day to sell that stock.

Return the maximum profit you can achieve from this transaction.

If you cannot achieve any profit, return 0.

Example 1
Input: prices = [7,1,5,3,6,4]
Output: 5

Explanation: Buy on day 1 at price 1, sell on day 4 at price 6, profit = 6 - 1 = 5.

Example 2
Input: prices = [7,6,4,3,1]
Output: 0

Explanation: Prices only go down, so no profitable transaction is possible.

Constraints
1 <= prices.length <= 100000
0 <= prices[i] <= 10000
 */
public class BestTimeToBuyAndSellStock {

    public int maxProfit(int[] prices) {
        int minBuy = prices[0];
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            int profit = prices[i] - minBuy;
            maxProfit = Math.max(maxProfit, profit);
            minBuy = Math.min(minBuy, prices[i]);
        }
        return maxProfit;
    }
}
