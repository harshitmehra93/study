package study.neetcode.interview.backtracking;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/*
Problem: Permutations

You are given an array nums of distinct integers.

Return all possible permutations.

Example:

nums = [1, 2, 3]

Output can be in any order:

[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]

Method signature:

public List<List<Integer>> permute(int[] nums)
 */
public class Permutation {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        permutation(0, nums, result, new LinkedList<>(), new HashSet<Integer>());
        return result;
    }

    private void permutation(
            int index,
            int[] nums,
            List<List<Integer>> result,
            LinkedList<Integer> permutation,
            HashSet<Integer> set) {
        if (permutation.size() == nums.length) {
            result.add(new LinkedList<>(permutation));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!set.contains(nums[i])) {
                permutation.add(index, nums[i]);
                set.add(nums[i]);

                permutation(index + 1, nums, result, permutation, set);

                set.remove(nums[i]);
                permutation.remove(index);
            }
        }
    }
}
