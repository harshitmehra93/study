package study.contest.leetcode.weeklycontest422;

/**
 * LeetCode 3340 — Check Balanced String
 *
 * <p>Given a digit string, return whether the sum of the digits at even indices equals the sum at
 * odd indices.
 *
 * <p>Examples:
 *
 * <pre>
 * Input: num = "1234"
 * Output: false
 *
 * Input: num = "24123"
 * Output: true
 * </pre>
 *
 * <p>Constraints: {@code 2 <= num.length() <= 100}; {@code num} contains only digits.
 *
 * @see <a href="https://leetcode.com/problems/check-balanced-string/">Problem</a>
 */
public class CheckBalancedString {
    public boolean isBalanced(String num) {
        int even = 0;
        int odd = 0;
        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);
            int convert = Integer.parseInt("" + c);
            if (i % 2 == 0) {
                even += convert;
            } else {
                odd += convert;
            }
        }
        return even == odd;
    }
}
