package study.neetcode.interview.backtracking;

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
        traverseSubsets(0, new LinkedList<>(), nums, result);
        return result;
    }

    private void traverseSubsets(
            int index, LinkedList<Integer> selected, int[] nums, List<List<Integer>> result) {

        result.add(new LinkedList<>(selected));

        for (int i = index; i < nums.length; i++) {
            if (i > index && nums[i] == nums[i - 1]) continue;
            selected.add(nums[i]);

            traverseSubsets(i + 1, selected, nums, result);

            selected.removeLast();
        }
    }
}
