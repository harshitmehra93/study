package study.interview.greedy;
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
        int max = Integer.MIN_VALUE;
        int currentSum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > currentSum + nums[i]) { // start new
                currentSum = nums[i];
            } else { // extend
                currentSum += nums[i];
            }
            max = Math.max(currentSum, max);
        }
        return max;
    }
}
