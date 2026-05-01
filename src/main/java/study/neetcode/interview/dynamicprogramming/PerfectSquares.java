package study.neetcode.interview.dynamicprogramming;

import java.util.Arrays;

public class PerfectSquares {
    //    2. Perfect Squares
    //    Given an integer n, return the minimum number of perfect square numbers (like 1, 4, 9,
    // 16…) that sum to n.
    int memo[];

    public int perfectSquares(int num) {
        memo = new int[num + 1];
        Arrays.fill(memo, -1);
        return helper(num);
    }

    public int helper(int num) {
        if (num == 0) return 0;
        if (memo[num] != -1) return memo[num];
        int min = Integer.MAX_VALUE;

        for (int i = 1; i * i <= num; i++) {
            int interim = helper(num - (i * i));
            min = Math.min(min, interim);
        }

        return memo[num] = 1 + min;
    }
}
