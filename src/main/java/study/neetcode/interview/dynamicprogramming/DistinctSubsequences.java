package study.neetcode.interview.dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

public class DistinctSubsequences {
    //    Distinct Subsequences
    //
    // 🧩 Problem
    //
    //    Given two strings s and t, return the number of distinct subsequences of s which equal t.
    //
    //    Example
    //            s = "rabbbit"
    //    t = "rabbit"
    //    Output = 3
    //
    //    Explanation:
    //
    //    There are 3 ways to delete one 'b' from "rabbbit"
    Map<State, Integer> memo;

    public int distinctSubsequences(String source, String target) {
        memo = new HashMap<>();
        //        return helper(source, target, 0, "");
        return helper2(source, target, 0, 0);
    }

    private int helper2(String source, String target, int i, int j) {
        if (j == target.length()) return 1;
        if (i == source.length()) return 0;

        if (source.charAt(i) == target.charAt(j)) {
            int takeI = helper2(source, target, i + 1, j + 1);
            int skipI = helper2(source, target, i + 1, j);
            return takeI + skipI;
        }

        return helper2(source, target, i + 1, j);
    }

    private int helper(String source, String target, int i, String current) {
        State state = new State(i, current);
        if (memo.containsKey(state)) return memo.get(state);
        if (!target.startsWith(current)) {
            memo.put(state, 0);
            return 0;
        }
        if (current.equals(target)) {
            memo.put(state, 1);
            return 1;
        }
        if (i >= source.length()) return 0;

        int takeI = helper(source, target, i + 1, current + source.charAt(i));
        int skipI = helper(source, target, i + 1, current);

        memo.put(state, takeI + skipI);
        return takeI + skipI;
    }

    record State(int index, String current) {}
}
