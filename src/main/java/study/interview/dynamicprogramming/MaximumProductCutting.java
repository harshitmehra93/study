package study.interview.dynamicprogramming;

public class MaximumProductCutting {

    Integer[] memo;

    public int maximumProductCutting(int num) {
        if (num <= 2) return 1;
        memo = new Integer[num + 1];
        return helper(num);
    }

    private int helper(int num) {
        if (num <= 2) return 1;
        if (memo[num] != null) return memo[num];
        int max = 1;
        for (int i = 1; i < num; i++) {
            int interim = Math.max(i * (num - i), i * helper(num - i));
            max = Math.max(interim, max);
        }
        return memo[num] = max;
    }
}
