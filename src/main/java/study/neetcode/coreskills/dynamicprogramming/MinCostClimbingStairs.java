package study.neetcode.coreskills.dynamicprogramming;

public class MinCostClimbingStairs {

    /*Problem Statement

      You are given an array cost where:

      cost[i] = cost of stepping on stair i

      You can:

      Start from step 0 or step 1
      Move either 1 step or 2 steps

      👉 Return the minimum cost to reach the top

      🔍 Example 1
      Input: cost = [10,15,20]
      Output: 15
    */

    long memo[];

    public long minCostClimbingStairs(int[] cost) {
        if (cost.length == 1) return 0;
        memo = new long[cost.length + 1];
        for (int i = 0; i < cost.length; i++) memo[i] = -1;
        return Math.min(helper(cost, 0), helper(cost, 1));
    }

    private long helper(int[] cost, int index) {
        if (index >= cost.length) {
            return 0;
        }
        if (memo[index] != -1) return memo[index];
        return memo[index] =
                cost[index] + Math.min(helper(cost, index + 1), helper(cost, index + 2));
    }
}
