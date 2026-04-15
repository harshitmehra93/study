package study.neetcode.coreskills.dynamicprogramming;

public class HouseRobber {
    /*
      * You are given an integer array nums where each element represents the amount of money in a house.

        Constraint:

        You cannot rob two adjacent houses (alarm will trigger)

        Return the maximum amount of money you can rob.

        Example
        Input: nums = [2,7,9,3,1]
        Output: 12

        Explanation:
        Rob house 1 (2) + house 3 (9) + house 5 (1) = 12
    * */
    long memo[];

    public long rob(int[] nums) {
        memo = new long[nums.length + 1];
        for (int i = 0; i <= nums.length; i++) memo[i] = -1;
        return helper(nums, 0);
    }

    private long helper(int[] nums, int index) {
        if (index >= nums.length) {
            return 0;
        }
        if (memo[index] != -1) {
            return memo[index];
        }
        return memo[index] =
                Math.max(helper(nums, index + 2) + nums[index], helper(nums, index + 1));
    }
}
