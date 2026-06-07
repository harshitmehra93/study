package study.neetcode.interview.greedy;

/*
Jump Game

Problem

You are given an integer array nums.

Each nums[i] means:

from index i, you can jump at most nums[i] steps forward.

Return true if you can reach the last index, otherwise return false.

Example 1:

nums = [2,3,1,1,4]

Output:

true
index 0 -> index 1 -> index 4

Example 2:

nums = [3,2,1,0,4]

Output:

false

Reason:

You eventually get stuck at index 3 because nums[3] = 0.
 */
public class JumpGame {
    public boolean canJump(int[] nums) {
        int farthestReachable = 0;

        for (int i = 0; i < nums.length; i++) {
            if (i > farthestReachable) {
                return false;
            }

            farthestReachable = Math.max(farthestReachable, i + nums[i]);

            if (farthestReachable >= nums.length - 1) {
                return true;
            }
        }

        return true;
    }
}
