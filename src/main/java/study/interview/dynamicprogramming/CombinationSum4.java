package study.interview.dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

public class CombinationSum4 {
    //    Given nums[] and a target, return the number of ordered combinations that add up to
    // {1,2,3} target = 4 , number of ways = 7
    //  1,1,1,1 ; 2,1,1 ; 1,2,1 ; 2,1,1 ; 2,2 ; 1,3 ; 3,1
    // target.

    Map<Integer, Integer> memo;

    public int combinationSum(int[] nums, int target) {
        memo = new HashMap<>();
        return getTotalCombinations(nums, target);
    }

    private int getTotalCombinations(int[] nums, int target) {
        if (target < 0) return 0;
        if (target == 0) return 1;
        if (memo.containsKey(target)) return memo.get(target);

        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int interim = getTotalCombinations(nums, target - nums[i]);
            count += interim;
        }

        memo.put(target, count);
        return count;
    }
}
