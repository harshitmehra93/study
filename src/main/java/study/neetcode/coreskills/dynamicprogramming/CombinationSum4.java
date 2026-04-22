package study.neetcode.coreskills.dynamicprogramming;

public class CombinationSum4 {
    //    Given nums[] and a target, return the number of ordered combinations that add up to
    // {1,2,3} target = 4 , number of ways = 7
    //  1,1,1,1 ; 2,1,1 ; 1,2,1 ; 2,1,1 ; 2,2 ; 1,3 ; 3,1
    //
    //
    // target.
    public int combinationSum(int[] nums, int target) {
        return helper(nums, target);
    }

    int helper(int[] nums, int target) {
        if (target < 0) return 0;
        if (target == 0) return 1;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            int interim = helper(nums, target - nums[i]);
            sum += interim;
        }
        return sum;
    }
}
