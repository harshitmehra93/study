package study.contest.leetcode.weeklycontest421;

import java.util.List;

/**
 * LeetCode 3337 — Total Characters in String After Transformations II
 *
 * <p>For each lowercase letter {@code c}, one transformation replaces it with the next {@code
 * nums[c-'a']} cyclic letters. Return the resulting string length after {@code t} transformations,
 * modulo {@code 1_000_000_007}.
 *
 * <p>Examples:
 *
 * <pre>
 * Input: s = "abcyy", t = 2, nums = [1,1,...,1,2]
 * Output: 7
 *
 * Input: s = "azbk", t = 1, nums = [2,2,...,2]
 * Output: 8
 * </pre>
 *
 * <p>Constraints: {@code 1 <= s.length() <= 100000}; {@code 1 <= t <= 1000000000}; {@code
 * nums.length == 26}; {@code 1 <= nums[i] <= 25}.
 *
 * @see <a
 *     href="https://leetcode.com/problems/total-characters-in-string-after-transformations-ii/">Problem</a>
 */
public class TotalCharactersInStringAfterTransformationsII {
    int MOD = 1_000_000_007;

    public int lengthAfterTransformations(String s, int t, List<Integer> nums) {
        long[] freq = new long[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a'] += 1;
        }

        for (int time = 0; time < t; time++) {

            long[] newFreq = new long[26];
            for (int i = 0; i < 26; i++) {
                incrementNumInRange(newFreq, freq[i], i + 1, i + nums.get(i) + 1);
            }
            freq = newFreq;
        }

        long count = 0;
        for (long num : freq) {
            count = (count + num) % MOD;
        }
        return (int) count;
    }

    void incrementNumInRange(long[] newFreq, long num, int start, int endExclusive) {
        for (int i = start; i < endExclusive; i++) {
            newFreq[i % 26] = (newFreq[i % 26] + num) % MOD;
        }
    }
}
