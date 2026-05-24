package study.neetcode.interview.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
Problem: Subsets

You are given an integer array nums of unique elements.

Return all possible subsets.

Example:

nums = [1, 2, 3]

Output can be in any order:

[
  [],
  [1],
  [2],
  [3],
  [1,2],
  [1,3],
  [2,3],
  [1,2,3]
]

Method signature:

public List<List<Integer>> subsets(int[] nums)
 */
public class Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        traverseAllSubsets(0, nums, result, new LinkedList<Integer>());
        return result;
    }

    private void traverseAllSubsets(
            int i, int[] nums, List<List<Integer>> result, LinkedList<Integer> currentSet) {
        if (i >= nums.length) {
            result.add(new LinkedList<>(currentSet));
            return;
        }

        // skip i
        traverseAllSubsets(i + 1, nums, result, currentSet);

        // take i
        currentSet.add(nums[i]);
        traverseAllSubsets(i + 1, nums, result, currentSet);
        currentSet.removeLast();
    }
}
