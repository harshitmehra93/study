package study.neetcode.interview.dynamicprogramming;

import java.util.Arrays;

public class LongestIncreasingSubsequence {

    //    🧩 Problem: Longest Increasing Subsequence (LIS)
    //    You are given an integer array nums.
    //
    //    🎯 Task
    //    Return the length of the longest strictly increasing subsequence.
    //
    //    📌 Rules
    // *   A subsequence is formed by deleting some (or none) elements without changing the order of
    // the remaining elements.
    //            * The sequence must be strictly increasing (each next element is greater than the
    // previous one).
    //
    //    📥 Examples
    //
    //    Input: [10,9,2,5,3,7,101,18]
    //    Output: 4
    //
    //    Input: [0,1,0,3,2,3]
    //    Output: 4
    //
    //    Input: [7,7,7,7,7]
    //    Output: 1
    int[] memo;

    public int longestIncreasingSubsequence(int[] nums) {
        memo = new int[nums.length];
        Arrays.fill(memo, -1);

        helper(nums, 0);

        int max = 0;
        for (int i = 0; i < nums.length; i++) max = Math.max(memo[i], max);
        return max;
    }

    private int helper(int[] nums, int index) {
        if (index >= nums.length) return 0;
        if (memo[index] != -1) return memo[index];

        int subsequence = 0;
        for (int i = index + 1; i < nums.length; i++) {
            int interim = helper(nums, i);
            if (nums[i] > nums[index]) {
                subsequence = Math.max(interim, subsequence);
            }
        }

        return memo[index] = 1 + subsequence;
    }
}
