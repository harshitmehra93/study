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
//        Return true if you can partition the array into two subsets such that the sum of elements in both subsets is equal, otherwise return false.
//
//    📌 Rules
//        Each element must belong to exactly one of the two subsets.
//        You may not reuse elements.
//        The two subsets together must include all elements of the array.
//    📥 Examples
//        Input: [1, 5, 11, 5]
//        Output: true

    Map<Pair, Boolean> memo = new HashMap<>();

    public boolean canBePartitionedEqually(int[] nums) {
        memo = new HashMap<>();
        if(nums.length<2) return false;

        int sum=0;
        for(int i=0;i<nums.length;i++) sum+=nums[i];
        if(sum%2!=0) return false;
        int target = sum/2;
        System.out.println("target="+target);

        return helper(nums, target,0);
    }

    private boolean helper(int[] nums, int target, int index) {
        if(target<0) return false;
        if(target==0) return true;
        if(index>=nums.length) return false;
        Pair pair = new Pair(target, index);
        if(memo.containsKey(pair)) return memo.get(pair);

        boolean take = helper(nums, target - nums[index], index + 1);
        boolean skip = helper(nums, target, index + 1);

        memo.put(pair, take || skip);
        return take || skip;
    }

    record Pair(int target, int index){}


}
