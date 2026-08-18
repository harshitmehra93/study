package study.contest.leetcode.weeklycontest421;

/**
 * LeetCode 3335 — Total Characters in String After Transformations I
 *
 * <p>Perform {@code t} simultaneous transformations: replace {@code z} with {@code ab}, and replace
 * every other lowercase letter with its successor. Return the resulting length modulo {@code
 * 1_000_000_007}.
 *
 * <p>Examples:
 *
 * <pre>
 * Input: s = "abcyy", t = 2
 * Output: 7
 *
 * Input: s = "azbk", t = 1
 * Output: 5
 * </pre>
 *
 * <p>Constraints: {@code 1 <= s.length() <= 100000}; {@code 1 <= t <= 100000}; {@code s} contains
 * only lowercase English letters.
 *
 * @see <a
 *     href="https://leetcode.com/problems/total-characters-in-string-after-transformations-i/">Problem</a>
 */
public class TotalCharactersInStringAfterTransformationsI {
    int MOD = 1_000_000_007;

    public int lengthAfterTransformations(String s, int t) {
        long[] freq = new long[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a'] += 1;
        }

        for (int time = 0; time < t; time++) {
            long zCount = freq[25];
            long[] newFreq = new long[26];
            for (int i = 1; i < 26; i++) {
                newFreq[i] = freq[i - 1];
            }
            newFreq[0] = zCount;
            newFreq[1] = (int) ((newFreq[1] + zCount) % MOD);
            freq = newFreq;
        }
        long count = 0;
        for (var num : freq) {
            count = (int) ((count + num) % MOD);
        }
        return (int) (count % MOD);
    }
}
