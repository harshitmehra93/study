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
        int farthestBoundary = 0;
        int currentFarthest = 0;
        int steps = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            int farthest = nums[i] + i;
            currentFarthest = Math.max(currentFarthest, farthest);

            if (i == farthestBoundary) {
                steps++;
                farthestBoundary = currentFarthest;
            }
        }
        return steps;
    }
}
