package study.neetcode.coreskills.dynamicprogramming;

public class CombinationSum4 {
    //    Given nums[] and a target, return the number of ordered combinations that add up to
    // target.
    public int combinationSum(int[] nums, int target) {
        return helper(nums, target);
    }

    private int helper(int[] nums, int target) {
        if (target == 0) return 1;
        if (target < 0) return 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int interim = helper(nums, target - nums[i]);
            count += interim;
        }
        return count;
    }
}
