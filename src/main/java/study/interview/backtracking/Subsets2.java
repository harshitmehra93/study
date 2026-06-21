package study.interview.backtracking;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*
Problem:

nums may contain duplicates.
Return all unique subsets.

Example:

nums = [1, 2, 2]

Expected:

[]
[1]
[2]
[1,2]
[2,2]
[1,2,2]
 */
public class Subsets2 {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        Arrays.sort(nums);
        trySubsets(0, new LinkedList<Integer>(), nums, result);
        return result;
    }

    private void trySubsets(
            int index, LinkedList<Integer> set, int[] nums, List<List<Integer>> result) {
        result.add(new LinkedList<>(set));

        for (int i = index; i < nums.length; i++) {
            if (i > index && nums[i - 1] == nums[i]) continue;

            set.add(nums[i]);
            trySubsets(i + 1, set, nums, result);
            set.removeLast();
        }
    }
}
