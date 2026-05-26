package study.neetcode.interview.backtracking;

import java.util.LinkedList;
import java.util.List;

public class Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        findAllSubsets(0, nums, new LinkedList<Integer>(), result);
        return result;
    }

    private void findAllSubsets(
            int index, int[] nums, LinkedList<Integer> current, List<List<Integer>> result) {
        result.add(new LinkedList<>(current));

        for (int i = index; i < nums.length; i++) {
            current.add(nums[i]);

            findAllSubsets(i + 1, nums, current, result);

            current.removeLast();
        }
    }
}
