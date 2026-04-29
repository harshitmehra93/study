package study.neetcode.coreskills.dynamicprogramming;

public class MinimumAsciiDeleteSumTwoStrings {

    //    Minimum ASCII Delete Sum for Two Strings
    //
    //    Problem
    //    Given two strings s1 and s2, return the minimum ASCII sum of deleted characters to make
    // both strings equal.
    //
    //    Example
    //    s1 = "sea"
    //    s2 = "eat"
    //    Output: 231
    //
    //    Explanation:
    //    Delete 's' → ASCII 115
    //    Delete 't' → ASCII 116
    //    Total = 231
    Integer[][] memo;
    public int minimumDeleteSum(String s1, String s2) {
        memo = new Integer[s1.length()+1][s2.length()+1];
        return helper(s1, s2, 0, 0);
    }

    private int helper(String s1, String s2, int i, int j) {
        if (i >= s1.length() && j >= s2.length()) return 0;
        if(memo[i][j]!=null) return memo[i][j];
        if (i == s1.length()) {
            memo[i][j] = sumAllFrom(s2,j);
            return memo[i][j];
        }
        if (j == s2.length()) {
            memo[i][j] = sumAllFrom(s1,i);
            return memo[i][j];
        }

        if (s1.charAt(i) == s2.charAt(j)) {
            return memo[i][j]=helper(s1, s2, i + 1, j + 1);
        }

        int deleteI = (int) s1.charAt(i) + helper(s1, s2, i + 1, j);
        int deleteJ = (int) s2.charAt(j) + helper(s1, s2, i, j + 1);
        return memo[i][j]=Math.min(deleteI, deleteJ);
    }

    private int sumAllFrom(String s, int index) {
        int sum=0;
        for(;index<s.length();index++) sum+=s.charAt(index);
        return sum;
    }
}
