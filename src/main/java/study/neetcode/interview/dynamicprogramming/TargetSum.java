package study.neetcode.interview.dynamicprogramming;

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

    Map<State, Integer> memo;

    public int targetSum(int[] nums, int target) {
        memo = new HashMap<>();
        return helper(nums, 0, 0, target);
    }

    private int helper(int[] nums, int index, int current, int target) {
        if (index == nums.length && current == target) return 1;
        if (index >= nums.length) return 0;
        State state = new State(index, current);
        if (memo.containsKey(state)) return memo.get(state);

        int minus = helper(nums, index + 1, current - nums[index], target);
        int plus = helper(nums, index + 1, current + nums[index], target);

        memo.put(state, minus + plus);
        return minus + plus;
    }

    record State(int index, int current) {}
}
