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
        tryCombinationSum(0, 0, new LinkedList<>(), target, candidates, result);
        return result;
    }

    private void tryCombinationSum(
            int currentSum,
            int index,
            LinkedList<Integer> selectedCandidates,
            int target,
            int[] candidates,
            List<List<Integer>> result) {
        if (currentSum > target) return;
        if (currentSum == target) {
            result.add(new LinkedList<>(selectedCandidates));
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            selectedCandidates.add(candidates[i]);
            tryCombinationSum(
                    currentSum + candidates[i],
                    i,
                    selectedCandidates,
                    target,
                    candidates,
                    result);
            selectedCandidates.removeLast();
        }
    }
}
