package study.neetcode.coreskills.dynamicprogramming;

import java.util.Arrays;

public class CombinationSum4 {
    //    Given nums[] and a target, return the number of ordered combinations that add up to
    // {1,2,3} target = 4 , number of ways = 7
    //  1,1,1,1 ; 2,1,1 ; 1,2,1 ; 2,1,1 ; 2,2 ; 1,3 ; 3,1
    // target.

    int[] memo;

    public int combinationSum(int[] nums, int target) {
        memo = new int[target + 1];
        Arrays.fill(memo, -1);
        return helper(nums, target);
    }

    private int helper(int[] nums, int target) {
        if (target == 0) return 1;
        if (target < 0) return 0;
        if (memo[target] != -1) return memo[target];

        int count = 0;
        for (int num : nums) {
            if (num == 0) continue;
            int interim = helper(nums, target - num);
            count += interim;
        }

        memo[target] = count;
        return count;
    }
}
