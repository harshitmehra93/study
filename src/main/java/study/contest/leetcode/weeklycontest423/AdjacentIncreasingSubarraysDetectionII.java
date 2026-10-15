package study.contest.leetcode.weeklycontest423;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LeetCode 3350 — Adjacent Increasing Subarrays Detection II
 *
 * <p>Return the maximum {@code k} for which two adjacent length-{@code k} subarrays of {@code nums}
 * are both strictly increasing.
 *
 * <p>Examples:
 *
 * <pre>
 * Input: nums = [2,5,7,8,9,2,3,4,3,1]
 * Output: 3
 *
 * Input: nums = [1,2,3,4,4,4,4,5,6,7]
 * Output: 2
 * </pre>
 *
 * <p>Constraints: {@code 2 <= nums.length <= 200000}; {@code -10^9 <= nums[i] <= 10^9}.
 *
 * @see <a
 *     href="https://leetcode.com/problems/adjacent-increasing-subarrays-detection-ii/">Problem</a>
 */
public class AdjacentIncreasingSubarraysDetectionII {
    public int maxIncreasingSubarrays(List<Integer> arr) {
        Map<Integer, Integer> startAndSizeMap = new HashMap<>();
        ArrayList<Integer> nums = new ArrayList<>(arr);

        int index = 0;
        int prev = nums.get(index);
        index++;
        int startIndex = 0;
        startAndSizeMap.put(startIndex, 1);
        while (index < nums.size()) {
            int current = nums.get(index);
            if (prev < current) {
                startAndSizeMap.put(startIndex, startAndSizeMap.get(startIndex) + 1);
            } else {
                startIndex = index;
                startAndSizeMap.put(startIndex, 1);
            }
            prev = current;
            index++;
        }
        int maxAdjacent = 0;
        for (var entry : startAndSizeMap.entrySet()) {
            int start = entry.getKey();
            int size = entry.getValue();
            if (size > 1) {
                maxAdjacent = Math.max(maxAdjacent, size / 2);
            }
        }
        for (var entry : startAndSizeMap.entrySet()) {
            int start = entry.getKey();
            int size = entry.getValue();

            int nextAdjacent = start + size;
            if (startAndSizeMap.containsKey(nextAdjacent)) {
                int adjSize = startAndSizeMap.get(nextAdjacent);
                int adjacentPairLength = Math.min(size, adjSize);
                maxAdjacent = Math.max(maxAdjacent, adjacentPairLength);
            }
        }
        return maxAdjacent;
    }
}
