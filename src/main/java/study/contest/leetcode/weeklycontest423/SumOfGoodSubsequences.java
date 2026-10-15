package study.contest.leetcode.weeklycontest423;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * LeetCode 3351 — Sum of Good Subsequences
 *
 * <p>A subsequence is good when the absolute difference between each pair of consecutive elements
 * is exactly one. Return the sum of every good subsequence, modulo {@code 10^9 + 7}. A one-element
 * subsequence is good.
 *
 * <p>Examples:
 *
 * <pre>
 * Input: nums = [1,2,1]
 * Output: 14
 *
 * Input: nums = [3,4,5]
 * Output: 40
 * </pre>
 *
 * <p>Constraints: {@code 1 <= nums.length <= 100000}; {@code 0 <= nums[i] <= 100000}.
 *
 * @see <a href="https://leetcode.com/problems/sum-of-good-subsequences/">Problem</a>
 */
public class SumOfGoodSubsequences {
    public int sumOfGoodSubsequences(int[] nums) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        int sum = 0;
        int MOD = 1_000_000_007;
        for (int i = 0; i < nums.length; i++) {
            int current = nums[i];
            sum = (sum + current) % MOD;
            if (map.containsKey(current + 1)) {
                Set<Integer> list = map.get(current + 1);
                int noOfIndexesSmallerThanCurrentIndex =
                        noOfIndicesSmallerThanCurrentIndex(list, i);
                sum = (sum + (noOfIndexesSmallerThanCurrentIndex * (current)) % MOD) % MOD;
            }
            if (map.containsKey(current - 1)) {
                Set<Integer> list = map.get(current - 1);
                int noOfIndexesSmallerThanCurrentIndex =
                        noOfIndicesSmallerThanCurrentIndex(list, i);
                sum = (sum + (noOfIndexesSmallerThanCurrentIndex * (current)) % MOD) % MOD;
            }
            if (!map.containsKey(current)) {
                map.put(current, new TreeSet<>());
            }
            map.get(current).add(i);
        }
        return sum;
    }

    int noOfIndicesSmallerThanCurrentIndex(Set<Integer> list, int target) {
        ArrayList<Integer> sortedList = new ArrayList<>(list);
        int left = 0;
        int right = sortedList.size() - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (sortedList.get(mid) > target) {
                right = mid - 1;
            } else if (sortedList.get(mid) < target) {
                left = mid;
            }
        }
        return left + 1;
    }
}
