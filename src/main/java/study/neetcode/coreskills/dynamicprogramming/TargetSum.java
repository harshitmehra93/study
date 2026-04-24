package study.neetcode.coreskills.dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

public class TargetSum {

    //    📝 Problem : Target Sum
    //
    //        You are given an integer array nums and an integer target.
    //
    //        You can assign either a + or − sign to each element in the array.
    //
    //    🎯 Task
    //
    //        Return the number of ways to assign signs such that the resulting expression equals
    // target.
    //
    //    📌 Rules
    //        Each number must be used exactly once.
    //        You can choose either +nums[i] or -nums[i].
    //        Order of elements remains the same.
    Map<Pair, Integer> memo;

    public int targetSum(int[] nums, int target) {
        memo = new HashMap<>();
        return helper(nums, 0, 0, target);
    }

    private int helper(int[] nums, int index, int current, int finalTarget) {
        Pair pair = new Pair(index, current);
        if (memo.containsKey(pair)) return memo.get(pair);

        if (index == nums.length) {
            int res = (current == finalTarget) ? 1 : 0;
            memo.put(pair, res);
            return res;
        }

        int minus = helper(nums, index + 1, current - nums[index], finalTarget);
        int plus = helper(nums, index + 1, current + nums[index], finalTarget);

        memo.put(pair, minus + plus);
        return minus + plus;
    }

    record Pair(int index, int current) {}
}
