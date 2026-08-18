package study.contest.leetcode.weeklycontest421;

import java.util.List;

/**
 * LeetCode 3334 — Find the Maximum Factor Score of Array
 *
 * <p>The factor score is {@code gcd(nums) * lcm(nums)}. Return the maximum score obtainable after
 * removing at most one element. The score of an empty array is zero.
 *
 * <p>Examples:
 *
 * <pre>
 * Input: nums = [2,4,8,16]
 * Output: 64
 *
 * Input: nums = [1,2,3,4,5]
 * Output: 60
 *
 * Input: nums = [3]
 * Output: 9
 * </pre>
 *
 * <p>Constraints: {@code 1 <= nums.length <= 100}; {@code 1 <= nums[i] <= 30}.
 *
 * @see <a href="https://leetcode.com/problems/find-the-maximum-factor-score-of-array/">Problem</a>
 */
public class FindTheMaximumFactorScoreOfArray {
    List<Integer> primes = List.of(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);

    public long maxScore(int[] nums) {
        if (nums.length == 0) return 0;

        long[] prefixGcd = new long[nums.length];
        long[] suffixGcd = new long[nums.length];
        long[] prefixLcm = new long[nums.length];
        long[] suffixLcm = new long[nums.length];

        long currentGcd = 0;
        long currentLcm = 1;
        for (int i = 0; i < nums.length; i++) {
            currentGcd = gcd(currentGcd, nums[i]);
            prefixGcd[i] = currentGcd;

            currentLcm = lcm(currentLcm, nums[i]);
            prefixLcm[i] = currentLcm;
        }

        currentGcd = 0;
        currentLcm = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            currentGcd = gcd(currentGcd, nums[i]);
            suffixGcd[i] = currentGcd;

            currentLcm = lcm(currentLcm, nums[i]);
            suffixLcm[i] = currentLcm;
        }

        long factorScore = gcd(nums) * lcm(nums);

        for (int i = 0; i < nums.length; i++) {
            long previousGcd = i == 0 ? 0 : prefixGcd[i - 1];
            long proceedingGcd = i == nums.length - 1 ? 0 : suffixGcd[i + 1];
            long changedGcd = gcd(previousGcd, proceedingGcd);

            long previousLcm = i == 0 ? 1 : prefixLcm[i - 1];
            long proceedingLcm = i == nums.length - 1 ? 1 : suffixLcm[i + 1];
            long changedLcm = lcm(previousLcm, proceedingLcm);

            factorScore = Math.max(factorScore, changedGcd * changedLcm);
        }

        return factorScore;
    }

    long gcd(int[] nums) {
        long a = mod(nums[0]);
        for (int i = 1; i < nums.length; i++) {
            a = gcd(a, mod(nums[i]));
        }
        return a;
    }

    private long mod(long num) {
        if (num < 0) return -num;
        return num;
    }

    private long gcd(long a, long b) {
        if (a < b) {
            long tmp = a;
            a = b;
            b = tmp;
        }
        if (b == 0) return a;
        if (a == 0 && b == 0) return 0;
        if (a % b == 0) return b;
        return gcd(b, a % b);
    }

    long lcm(int nums[]) {
        long lcm = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (lcm == 0 || nums[i] == 0) return 0;
            lcm = lcm(nums[i], lcm);
        }
        return lcm;
    }

    long lcm(long a, long b) {
        if (a == 0 || b == 0) return 0;
        return a * b / gcd(a, b);
    }
}
