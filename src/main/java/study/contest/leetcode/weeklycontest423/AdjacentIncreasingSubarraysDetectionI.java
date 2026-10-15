package study.contest.leetcode.weeklycontest423;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 3349 — Adjacent Increasing Subarrays Detection I
 *
 * <p>Given {@code nums} and {@code k}, return whether two adjacent length-{@code k} subarrays are
 * both strictly increasing.
 *
 * <p>Examples:
 *
 * <pre>
 * Input: nums = [2,5,7,8,9,2,3,4,3,1], k = 3
 * Output: true
 *
 * Input: nums = [1,2,3,4,4,4,4,5,6,7], k = 5
 * Output: false
 * </pre>
 *
 * <p>Constraints: {@code 2 <= nums.length <= 100}; {@code 1 < 2 * k <= nums.length}; {@code -1000
 * <= nums[i] <= 1000}.
 *
 * @see <a
 *     href="https://leetcode.com/problems/adjacent-increasing-subarrays-detection-i/">Problem</a>
 */
public class AdjacentIncreasingSubarraysDetectionI {
    public boolean hasIncreasingSubarrays(List<Integer> nums, int k) {
        // for every ith position try and see if there is a window of strictly increaseing k
        // elements. If yes do the same for i+k+1th index
        // IF both are true then we return true else false;
        // I will do one pass
        ArrayList<Integer> nums2 = new ArrayList<>(nums);
        for (int i = 0; i < nums2.size(); i++) {
            boolean firstWindowFound = false;
            boolean secondWindowFound = false;
            if (firstWindowFound = checkStrictlyIncreasing(nums2, i, k)) {
                secondWindowFound = checkStrictlyIncreasing(nums2, i + k, k);
                if (firstWindowFound && secondWindowFound) return true;
            }
        }
        return false;
    }

    boolean checkStrictlyIncreasing(ArrayList<Integer> nums, int i, int k) {
        int end = i + k - 1;
        if (end >= nums.size()) return false;
        int prev = nums.get(i);
        i++;
        for (; i <= end; i++) {
            int current = nums.get(i);
            if (prev >= current) return false;
            prev = current;
        }
        return true;
    }
}
