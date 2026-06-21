package study.interview.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Next roadmap problem:

80. 3Sum — Two Pointers

Given an integer array nums, return all unique triplets:

[nums[i], nums[j], nums[k]]

such that:

i != j
i != k
j != k
nums[i] + nums[j] + nums[k] == 0

The solution set must not contain duplicate triplets.

Example 1
Input: nums = [-1,0,1,2,-1,-4]
Output: [[-1,-1,2],[-1,0,1]]
Example 2
Input: nums = [0,1,1]
Output: []
 */
public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i - 1] == nums[i]) continue;

            int left = i + 1;
            int right = nums.length - 1;
            int target = -nums[i];
            while (left < right) {
                if (left > i + 1 && nums[left - 1] == nums[left]) {
                    left++;
                    continue;
                }
                if (right < nums.length - 1 && nums[right + 1] == nums[right]) {
                    right--;
                    continue;
                }

                int sum = nums[left] + nums[right];
                if (sum > target) {
                    right--;
                } else if (sum < target) {
                    left++;
                } else if (sum == target) {
                    result.add(List.of(nums[i], nums[left], nums[right]));
                    left++;
                    right--;
                }
            }
        }
        return result;
    }
}
