package study.neetcode.interview.dynamicprogramming;

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

    Integer[][] memo;

    public int deleteOperations(String s1, String s2) {
        memo = new Integer[s1.length() + 1][s2.length() + 1];
        return helper(s1, s2, 0, 0);
    }

    private int helper(String s1, String s2, int i, int j) {
        if (i >= s1.length() && j >= s2.length()) return 0;
        if (memo[i][j] != null) return memo[i][j];
        if (i == s1.length()) return s2.length() - j;
        if (j == s2.length()) return s1.length() - i;

        if (s1.charAt(i) == s2.charAt(j)) {
            return memo[i][j] = helper(s1, s2, i + 1, j + 1);
        }

        int deleteI = helper(s1, s2, i + 1, j);
        int deleteJ = helper(s1, s2, i, j + 1);

        return memo[i][j] = 1 + Math.min(deleteI, deleteJ);
    }
}
