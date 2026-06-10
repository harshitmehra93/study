package study.neetcode.interview.greedy;

/*
Jump Game II

This one asks:

Return the minimum number of jumps needed to reach the last index.

Example:

nums = [2,3,1,1,4]

Output:

2

Because:

0 -> 1 -> 4
 */
public class JumpGameII {

    public int jump(int[] nums) {
        int jumps = 0;
        int currentJumpEnd = 0;
        int farthest = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);

            if (i == currentJumpEnd) {
                jumps++;
                currentJumpEnd = farthest;
            }
        }

        return jumps;
    }
}
