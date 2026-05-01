package study.neetcode.interview.dynamicprogramming;

import java.util.Arrays;

public class MaximumProductCutting {
    // Given a rod of length n, cut it into pieces to maximize the product of lengths.
    int[] memo;

    public int maximumProductCutting(int num) {
        memo = new int[num + 1];
        Arrays.fill(memo, -1);
        return helper(num);
    }

    int helper(int num) {
        if (memo[num] != -1) return memo[num];
        if (num <= 2) return 1;

        int max = Integer.MIN_VALUE;
        for (int i = 1; i < num; i++) {
            int noCut = i * (num - i);
            int cut = i * helper(num - i);

            int interim = Math.max(noCut, cut);
            max = Math.max(interim, max);
        }

        return memo[num] = max;
    }
}
