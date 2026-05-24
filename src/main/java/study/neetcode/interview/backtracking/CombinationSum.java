package study.neetcode.interview.backtracking;

import java.util.LinkedList;
import java.util.List;

/*
Next problem: Combination Sum

You are given an array of distinct integers candidates and a target integer target.

Return all unique combinations of candidates where the chosen numbers sum to target.

You may use the same number unlimited times.

Example:

candidates = [2, 3, 6, 7]
target = 7

Output:

[
  [2, 2, 3],
  [7]
]
 */
public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new LinkedList<>();
        tryAllPaths(candidates, target, 0, 0, new LinkedList<Integer>(), result);
        return result;
    }

    private void tryAllPaths(
            int[] candidates,
            int target,
            int currentSum,
            int index,
            LinkedList<Integer> currentSelectedCandidates,
            List<List<Integer>> result) {
        if (currentSum > target) return;
        if (currentSum == target) result.add(new LinkedList<>(currentSelectedCandidates));

        for (int i = index; i < candidates.length; i++) {
            currentSelectedCandidates.add(candidates[i]);
            tryAllPaths(
                    candidates,
                    target,
                    currentSum + candidates[i],
                    i,
                    currentSelectedCandidates,
                    result);
            currentSelectedCandidates.removeLast();
        }
    }
}
