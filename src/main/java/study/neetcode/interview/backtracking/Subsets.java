package study.neetcode.interview.backtracking;

import java.util.LinkedList;
import java.util.List;

public class Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        traverseSubsets(0, nums, new LinkedList<>(), result);
        return result;
    }

    private void traverseSubsets(
            int index, int[] nums, LinkedList<Integer> set, List<List<Integer>> result) {
        if (index == nums.length) {
            result.add(new LinkedList<>(set));
            return;
        }

        // take index
        set.add(nums[index]);
        traverseSubsets(index + 1, nums, set, result);
        set.removeLast();

        // skip index
        traverseSubsets(index + 1, nums, set, result);
    }
}
