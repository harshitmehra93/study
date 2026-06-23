package study.interview.dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

public class CoinChange {
    /*
    * 📝 Problem

        You are given:

        An array of coin denominations coins[]
        An integer amount
        🎯 Task

        Return the minimum number of coins needed to make up that amount.

        If it’s not possible, return -1.

        📥 Example
        coins = [1, 2, 5]
        amount = 11

        Output = 3
        Explanation: 5 + 5 + 1
    * */
    Map<Integer, Integer> memo;

    public int coinChange(int[] coins, int target) {
        memo = new HashMap<>();
        return getMinCoins(coins, target);
    }

    private int getMinCoins(int[] coins, int target) {
        if (target < 0) return -1;
        if (target == 0) return 0;
        if (memo.containsKey(target)) return memo.get(target);

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            int interim = getMinCoins(coins, target - coins[i]);
            if (interim == -1) continue;
            min = Math.min(min, interim);
        }

        if (min == Integer.MAX_VALUE) {
            memo.put(target, -1);
            return -1;
        }
        memo.put(target, 1 + min);
        return 1 + min;
    }
}
