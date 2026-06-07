package study.neetcode.interview.greedy;
/*
Maximum Subarray

This is your first Greedy problem.

Problem

Given an integer array nums, find the contiguous subarray with the largest sum and return its sum.

Example:

nums = [-2,1,-3,4,-1,2,1,-5,4]

Output:

6

Because the best subarray is:

[4, -1, 2, 1] = 6
 */
public class MaximumSubarray {
    public int maxSubArray(int[] nums) {
        int max = nums[0];
        int sum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] + sum > nums[i]) {
                sum += nums[i];
            } else {
                sum = nums[i];
            }
            max = Math.max(max, sum);
        }
        return max;
    }
}
