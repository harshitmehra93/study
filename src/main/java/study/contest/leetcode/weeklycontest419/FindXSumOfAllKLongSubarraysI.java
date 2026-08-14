package study.contest.leetcode.weeklycontest419;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * LeetCode 3318 — Find X-Sum of All K-Long Subarrays I
 *
 * <p>The x-sum of an array keeps every occurrence of its {@code x} most frequent distinct values,
 * then sums those occurrences. A frequency tie favors the larger value. If fewer than {@code x}
 * distinct values occur, the x-sum is the ordinary array sum.
 *
 * <p>Given {@code nums}, {@code k}, and {@code x}, return the x-sum of every length-{@code k}
 * subarray.
 *
 * <p>Examples:
 *
 * <pre>
 * Input: nums = [1,1,2,2,3,4,2,3], k = 6, x = 2
 * Output: [6,10,12]
 *
 * Input: nums = [3,8,7,8,7,5], k = 2, x = 2
 * Output: [11,15,15,15,12]
 * </pre>
 *
 * <p>Constraints: {@code 1 <= nums.length <= 50}, {@code 1 <= nums[i] <= 50}, {@code 1 <= x <= k <=
 * nums.length}.
 *
 * @see <a href="https://leetcode.com/problems/find-x-sum-of-all-k-long-subarrays-i/">Problem</a>
 */
public class FindXSumOfAllKLongSubarraysI {
    public int[] findXSum(int[] nums, int k, int x) {
        int[] answer = new int[nums.length - k + 1];
        int left = 0;
        int right = 0;
        Map<Integer, Integer> map = new HashMap<>();
        while (right < nums.length) {
            while (right < left + k) {
                if (!map.containsKey(nums[right])) map.put(nums[right], 0);
                map.put(nums[right], map.get(nums[right]) + 1);
                right++;
            }

            PriorityQueue<Map.Entry<Integer, Integer>> minHeap =
                    new PriorityQueue<>(
                            (a, b) -> {
                                if (a.getValue() != b.getValue()) {
                                    return Integer.compare(a.getValue(), b.getValue());
                                } else {
                                    return Integer.compare(a.getKey(), b.getKey());
                                }
                            });
            for (var entry : map.entrySet()) {
                minHeap.offer(entry);
                while (minHeap.size() > x) minHeap.poll();
            }

            int sum = 0;
            while (minHeap.size() > 0) {
                var entry = minHeap.poll();
                sum += entry.getKey() * entry.getValue();
            }

            answer[left] = sum;

            map.put(nums[left], map.get(nums[left]) - 1);
            if (map.get(nums[left]) == 0) map.remove(nums[left]);
            left++;
        }
        return answer;
    }
}
