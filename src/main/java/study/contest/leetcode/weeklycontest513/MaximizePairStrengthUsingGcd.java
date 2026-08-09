package study.contest.leetcode.weeklycontest513;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 4010 — Maximize Pair Strength Using GCD
 *
 * <p>You are given an integer array {@code nums}. Choose exactly one pair of distinct indices
 * {@code i} and {@code j}. The strength of the pair is:
 *
 * <pre>
 * (nums[i] * nums[j]) / gcd(nums[i], nums[j])^2
 * </pre>
 *
 * <p>Return the maximum strength over all possible pairs.
 *
 * <p>Examples:
 *
 * <pre>
 * Input: nums = [2, 3, 5]
 * Output: 15
 *
 * Input: nums = [4, 6, 8]
 * Output: 12
 *
 * Input: nums = [3, 3]
 * Output: 1
 * </pre>
 *
 * <p>Constraints: {@code 2 <= nums.length <= 2000}, {@code 1 <= nums[i] <= 100000}.
 *
 * @see <a href="https://leetcode.com/problems/maximize-pair-strength-using-gcd/">Problem</a>
 */
public class MaximizePairStrengthUsingGcd {
    List<Integer> primeFactors = new ArrayList<>();

    public long maxPairStrength(int[] nums) {
        long maxStrength = Long.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                long strength =
                        ((long) nums[i] * (long) nums[j])
                                / (long) Math.pow(gcd(nums[i], nums[j]), 2);
                maxStrength = Math.max(maxStrength, strength);
            }
        }
        return maxStrength;
    }

    int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}
