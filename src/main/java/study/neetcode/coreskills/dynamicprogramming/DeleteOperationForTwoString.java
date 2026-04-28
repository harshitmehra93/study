package study.neetcode.coreskills.dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

public class DeleteOperationForTwoString {
    //    Delete Operation for Two Strings
    //
    // 🧩 Problem
    //
    //    Given two strings word1 and word2, return the minimum number of deletions required to make
    // the two strings equal.
    //
    //    Rules
    //    You can only delete characters
    //    No insert or replace
    //    You want both strings to become exactly the same
    //            Examples
    //    word1 = "sea"
    //    word2 = "eat"
    //    Output = 2
    //
    //    Explanation:
    //
    //    sea → ea  (delete 's')
    //    eat → ea  (delete 't')
    Map<State, Integer> memo;

    public int deleteOperations(String s1, String s2) {
        memo = new HashMap<>();
        return helper(s1, s2, 0, 0);
    }

    private int helper(String s1, String s2, int i, int j) {
        if (i >= s1.length() && j >= s2.length()) return 0;
        if (i == s1.length()) return s2.length() - j;
        if (j == s2.length()) return s1.length() - i;
        State state = new State(i, j);
        if (memo.containsKey(state)) return memo.get(state);

        if (s1.charAt(i) == s2.charAt(j)) {
            int interim = helper(s1, s2, i + 1, j + 1);
            memo.put(state, interim);
            return interim;
        }

        int deleteI = helper(s1, s2, i + 1, j);
        int deleteJ = helper(s1, s2, i, j + 1);

        memo.put(state, 1 + Math.min(deleteJ, deleteI));
        return 1 + Math.min(deleteJ, deleteI);
    }

    record State(int i, int j) {}
}
