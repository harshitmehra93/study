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
        tryCombinations(0, 0, target, new LinkedList<Integer>(), candidates, result);
        return result;
    }

    void tryCombinations(
            int index,
            int currentSum,
            int target,
            LinkedList<Integer> combination,
            int[] candidates,
            List<List<Integer>> result) {
        if (currentSum == target) {
            result.add(new LinkedList<>(combination));
            return;
        }
        if (currentSum > target) return;

        for (int i = index; i < candidates.length; i++) {
            combination.add(candidates[i]);

            tryCombinations(i, currentSum + candidates[i], target, combination, candidates, result);

            combination.removeLast();
        }
    }
}
