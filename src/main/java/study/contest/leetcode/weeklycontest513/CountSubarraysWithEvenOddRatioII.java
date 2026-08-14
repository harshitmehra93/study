package study.contest.leetcode.weeklycontest513;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 4013 — Count Subarrays With Even Odd Ratio II
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
 * <p>Constraints: {@code 1 <= nums.length <= 100000} and {@code 1 <= nums[i], a, b <= 1000000000}.
 *
 * @see <a href="https://leetcode.com/problems/count-subarrays-with-even-odd-ratio-ii/">Problem</a>
 */
public class CountSubarraysWithEvenOddRatioII {
    public long countRatioSubarrays(int[] nums, int a, int b) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 0) {
                nums[i] = b;
            } else {
                nums[i] = -a;
            }
        }

        // count all subarrays whose sum <= 0
        // So I want to find all prefixSums which are equal to or greater than
        // current prefix sum which have come before the current index.
        long[] prefixArr = new long[nums.length];
        prefixArr[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            prefixArr[i] = nums[i] + prefixArr[i - 1];
        }

        long[] sortedPrefix = prefixArr.clone();
        Arrays.sort(sortedPrefix);

        Map<Long, Integer> ranks = new HashMap<>();
        int rank = 1;
        for (int i = sortedPrefix.length - 1; i >= 0; i--) {
            if (ranks.containsKey(sortedPrefix[i])) continue;
            ranks.put(sortedPrefix[i], rank);
            rank++;
        }

        int[] ranked = new int[nums.length];
        for (int i = 0; i < sortedPrefix.length; i++) {
            ranked[i] = ranks.get(prefixArr[i]);
        }

        Fenwick ft = new Fenwick(ranks.size());
        long count = 0L;
        for (int i = 0; i < ranked.length; i++) {
            if (prefixArr[i] <= 0) count++;

            int current = ranked[i];
            count += ft.sum(current);

            ft.update(current, 1);
        }
        return count;
    }

    static class Fenwick {
        long[] fenwick;
        int size;

        Fenwick(int size) {
            this.size = size;
            fenwick = new long[size + 1];
        }

        void update(int index, int value) {
            int next = index;
            while (next <= size) {
                fenwick[next] += value;
                next = next + (next & -next);
            }
        }

        long sum(int index) {
            long sum = 0L;
            int parent = index;
            while (parent > 0) {
                sum += fenwick[parent];
                parent = parent - (parent & -parent);
            }
            return sum;
        }
    }
}
