package study.interview.backtracking;

import java.util.LinkedList;
import java.util.List;

public class Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        trySubsets(0, new LinkedList<Integer>(), nums, result);
        return result;
    }

    private void trySubsets(
            int index, LinkedList<Integer> set, int[] nums, List<List<Integer>> result) {
        result.add(new LinkedList<>(set));

        for (int i = index; i < nums.length; i++) {
            set.add(nums[i]);

            trySubsets(i + 1, set, nums, result);

            set.removeLast();
        }
    }
}
