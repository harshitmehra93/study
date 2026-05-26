package study.neetcode.interview.backtracking;

import java.util.LinkedList;
import java.util.List;

public class Permutation {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        permutation(0, nums, new LinkedList<>(), result);
        return result;
    }

    void permutation(
            int index, int[] nums, LinkedList<Integer> current, List<List<Integer>> result) {
        if (index == nums.length) {
            result.add(new LinkedList<>(current));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (current.contains(nums[i])) continue;

            current.add(nums[i]);
            permutation(index + 1, nums, current, result);
            current.removeLast();
        }
    }
}
