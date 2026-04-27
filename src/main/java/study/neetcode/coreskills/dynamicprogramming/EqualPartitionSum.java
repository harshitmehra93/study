package study.neetcode.coreskills.dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

public class EqualPartitionSum {

    //    🧩 Problem: Partition Equal Subset Sum
    //
    //        You are given an integer array nums.
    //
    //    🎯 Task
    //
    //        Return true if you can partition the array into two subsets such that the sum of
    // elements in both subsets is equal, otherwise return false.
    //
    //    📌 Rules
    //        Each element must belong to exactly one of the two subsets.
    //        You may not reuse elements.
    //        The two subsets together must include all elements of the array.
    //    📥 Examples
    //        Input: [1, 5, 11, 5]
    //        Output: true
    Map<State, Boolean> memo;

    public boolean canBePartitionedEqually(int[] nums) {
        if (nums.length < 2) return false;

        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) return false;

        memo = new HashMap<>();
        return helper(nums, 0, sum / 2);
    }

    private boolean helper(int[] nums, int index, int target) {
        if (target == 0) return true;
        if (target < 0) return false;
        if (index >= nums.length) return false;
        State state = new State(index, target);
        if (memo.containsKey(state)) return memo.get(state);

        boolean take = helper(nums, index + 1, target - nums[index]);
        boolean skip = helper(nums, index + 1, target);

        memo.put(state, take || skip);
        return take || skip;
    }

    record State(int index, int target) {}
}
