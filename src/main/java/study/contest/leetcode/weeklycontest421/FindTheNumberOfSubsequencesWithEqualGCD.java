package study.contest.leetcode.weeklycontest421;

import java.util.Arrays;

/**
 * LeetCode 3336 — Find the Number of Subsequences With Equal GCD
 *
 * <p>Count ordered pairs of non-empty, disjoint subsequences whose GCDs are equal. Return the
 * answer modulo {@code 1_000_000_007}.
 *
 * <p>Examples:
 *
 * <pre>
 * Input: nums = [1,2,3,4]
 * Output: 10
 *
 * Input: nums = [10,20,30]
 * Output: 2
 *
 * Input: nums = [1,1,1,1]
 * Output: 50
 * </pre>
 *
 * <p>Constraints: {@code 1 <= nums.length <= 200}; {@code 1 <= nums[i] <= 200}.
 *
 * @see <a
 *     href="https://leetcode.com/problems/find-the-number-of-subsequences-with-equal-gcd/">Problem</a>
 */
public class FindTheNumberOfSubsequencesWithEqualGCD {
    int count = 0;
    int[][][] memo;
    int MOD = 1000000007;

    public int subsequencePairCount(int[] nums) {
        memo = new int[nums.length][201][201];
        for (int[][] twoD : memo) {
            for (int[] arr : twoD) {
                Arrays.fill(arr, -1);
            }
        }
        return subsequence(0, nums, 0, 0);
    }

    int subsequence(int index, int[] nums, int gcdA, int gcdB) {
        if (index == nums.length) {
            if (gcdA != 0 && gcdB != 0 && gcdA == gcdB) return 1;
            return 0;
        }
        if (memo[index][gcdA][gcdB] != -1) return memo[index][gcdA][gcdB];

        int num = nums[index];

        int newGcdA = gcd(gcdA, num);
        long takeA = subsequence(index + 1, nums, newGcdA, gcdB);

        int newGcdB = gcd(gcdB, num);
        long takeB = subsequence(index + 1, nums, gcdA, newGcdB);

        long skip = subsequence(index + 1, nums, gcdA, gcdB);

        return memo[index][gcdA][gcdB] = (int) ((takeA + takeB + skip) % MOD);
    }

    int gcd(int a, int b) {
        if (a == 0 && b == 0) return 0;
        if (a < b) {
            int tmp = a;
            a = b;
            b = tmp;
        }
        if (b == 0) return a;
        if (a % b == 0) return b;
        return gcd(b, a % b);
    }

    int mod(int num) {
        if (num < 0) return -num;
        return num;
    }
}
