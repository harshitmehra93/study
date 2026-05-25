package study.neetcode.interview.backtracking;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*
Combination Sum II

Problem: Given an array candidates and a target integer target, return all unique combinations where the chosen numbers sum to target.

Each number in candidates may be used at most once.

Example
Input: candidates = [10,1,2,7,6,1,5], target = 8

Output:
[
  [1,1,6],
  [1,2,5],
  [1,7],
  [2,6]
]
 */
public class CombinationSumII {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new LinkedList<>();
        Arrays.sort(candidates);
        getAllCombinations(0, 0, target, new LinkedList<>(), candidates, result);
        return result;
    }

    private void getAllCombinations(
            int index,
            int currentSum,
            int target,
            LinkedList<Integer> chosenCandidates,
            int[] candidates,
            List<List<Integer>> result) {
        if (currentSum > target) return;
        if (currentSum == target) {
            result.add(new LinkedList<>(chosenCandidates));
            return;
        }
        if (index >= candidates.length) return;

        for (int i = index; i < candidates.length; i++) {
            if (i > index && candidates[i] == candidates[i - 1]) continue;
            chosenCandidates.add(candidates[i]);

            getAllCombinations(
                    i + 1,
                    currentSum + candidates[i],
                    target,
                    chosenCandidates,
                    candidates,
                    result);

            chosenCandidates.removeLast();
        }
    }
}
