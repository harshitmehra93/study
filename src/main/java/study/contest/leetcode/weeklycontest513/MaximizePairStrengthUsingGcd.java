package study.contest.leetcode.weeklycontest513;

import java.util.ArrayList;
import java.util.HashMap;
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
    HashMap<Pair, Integer> memo = new HashMap<>();

    public long maxPairStrength(int[] nums) {
        long maxStrength = Long.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                long strength =
                        (nums[i] * nums[j])
                                / (long) Math.pow(gcd(nums[i], nums[j]), 2);
                maxStrength = Math.max(maxStrength, strength);
            }
        }
        return maxStrength;
    }

    int gcd(int a, int b) {
        Pair pair = new Pair(a, b);
        if (memo.containsKey(pair)) return memo.get(pair);
        int answer = 1;
        int limit = Math.min(a, b);
        for (int factor = 2; factor <= limit; factor++) {
            while (a % factor == 0 && b % factor == 0) {
                answer *= factor;
                a /= factor;
                b /= factor;
            }
        }
        memo.put(pair, answer);
        return answer;
    }

    record Pair(int i, int j) {}
}
