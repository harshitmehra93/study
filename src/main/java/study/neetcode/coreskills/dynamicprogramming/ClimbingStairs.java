package study.neetcode.coreskills.dynamicprogramming;

import java.util.Arrays;

public class ClimbingStairs {
    long[] memo;

    /*
    * You are climbing a staircase with n steps.
      Each time you can either climb:
      1 step, or
      2 steps
      Return the total number of distinct ways to reach the top.
    *
    * */
    public long climbStairs(int n) {
        if (memo == null) {
            memo = new long[n + 1];
            Arrays.fill(memo, -1);
        }
        if (n == 0) return 1;
        if (memo[n] != -1) return memo[n];
        if (n == 1) {
            memo[n] = 1;
            return 1;
        }
        if (n == 2) {
            memo[n] = 2;
            return 2;
        }
        memo[n] = climbStairs(n - 1) + climbStairs(n - 2);
        return memo[n];
    }
}
