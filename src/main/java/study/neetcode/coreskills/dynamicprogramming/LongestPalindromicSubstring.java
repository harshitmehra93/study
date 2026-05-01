package study.neetcode.coreskills.dynamicprogramming;

public class LongestPalindromicSubstring {
    //    Problem: Longest Palindromic Substring
    //
    //    Given a string s, return the longest substring of s that is a palindrome.
    //
    //    A substring is contiguous.
    //
    //    Examples
    //    Input: "babad"
    //    Output: "bab"
    Integer[][] memo;

    public int lcs(String s) {
        if (s.length() == 0) return 0;
        memo = new Integer[s.length() + 1][s.length() + 1];
        return helper(s, 0, s.length() - 1);
    }

    private int helper(String s, int i, int j) {
        if (i == j) return 1;
        if (i > j) return 0;
        if (memo[i][j] != null) return memo[i][j];

        int skipI = helper(s, i + 1, j);
        int skipJ = helper(s, i, j - 1);
        int skipBoth = helper(s, i + 1, j - 1);

        if (s.charAt(i) == s.charAt(j)) {
            if (skipBoth == (j - i) - 1) skipBoth += 2;
        }

        return memo[i][j] = Math.max(skipBoth, Math.max(skipI, skipJ));
    }
}
