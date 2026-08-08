package study.contest.leetcode.weeklycontest513;

/**
 * LeetCode 4011 — Count Subarrays With Even Odd Ratio I
 *
 * <p>You are given an integer array {@code nums} and two integers {@code a} and {@code b}. For a
 * subarray, let {@code x} be its number of even elements and {@code y} its number of odd elements.
 * The subarray is valid when {@code y > 0} and {@code x / y <= a / b}, comparing the ratios by
 * their exact rational values.
 *
 * <p>Return the number of valid subarrays.
 *
 * <p>Examples:
 *
 * <pre>
 * Input: nums = [1, 2, 1, 2], a = 3, b = 2
 * Output: 7
 *
 * Input: nums = [2, 2, 1], a = 2, b = 1
 * Output: 3
 *
 * Input: nums = [2, 2, 2], a = 1, b = 1
 * Output: 0
 * </pre>
 *
 * <p>Constraints: {@code 1 <= nums.length <= 1000}, {@code 1 <= nums[i], a, b <= 1000}.
 *
 * @see <a href="https://leetcode.com/problems/count-subarrays-with-even-odd-ratio-i/">Problem</a>
 */
public class CountSubarraysWithEvenOddRatioI {
    public int countRatioSubarrays(int[] nums, int a, int b) {
        double target = ((double) a) / b;

        int[] prefixCountOfOdds = new int[nums.length];
        int[] prefixCountOfEvens = new int[nums.length];
        int oddCount = 0;
        int evenCount = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 0) {
                evenCount++;
            } else {
                oddCount++;
            }
            prefixCountOfOdds[i] = oddCount;
            prefixCountOfEvens[i] = evenCount;
        }

        int answer = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                if (prefixCountOfOdds[j] == 0) continue;
                int odds;
                int evens;
                if (i > 0) {
                    odds = prefixCountOfOdds[j] - prefixCountOfOdds[i - 1];
                    evens = prefixCountOfEvens[j] - prefixCountOfEvens[i - 1];
                } else {
                    odds = prefixCountOfOdds[j];
                    evens = prefixCountOfEvens[j];
                }
                double candidate = ((double) evens) / odds;
                if (candidate <= target) answer++;
            }
        }
        return answer;
    }
}
