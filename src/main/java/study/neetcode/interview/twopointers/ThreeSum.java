package study.neetcode.interview.twopointers;

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
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[k]);
                        if (!alreadyExists(result, list)) result.add(list);
                    }
                }
            }
        }
        return result;
    }

    private boolean alreadyExists(List<List<Integer>> results, List<Integer> list) {
        for (var result : results) {
            boolean exists = true;
            for (int i = 0; i < list.size(); i++) {
                if (result.get(i) != list.get(i)) {
                    exists = false;
                    break;
                }
            }
            if (exists) return true;
        }
        return false;
    }
}
