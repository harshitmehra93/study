package study.neetcode.coreskills.dynamicprogramming;

public class LongestPalindromicSubsequence {
//    Longest Palindromic Subsequence (LPS)
//
//    Problem
//    Given a string s, return the length of the longest subsequence that is a palindrome.
//
//    Rules
//    A subsequence can skip characters (not necessarily contiguous)
//    A palindrome reads the same forward and backward
//    Input: s = "bbbab"
//    Output: 4
//    Explanation: "bbbb"
    public int lps(String s) {
        if(s.length()==0||s.isEmpty()||s.isBlank()) return 0;
        return helper(0, s.length()-1, s);
    }

    private int helper(int i, int j, String s) {
        if(i>j) return 0;
        if(i==j) return 1;

        if(s.charAt(i)==s.charAt(j)){
            return 2 + helper(i+1,j-1,s);
        }
        int increaseI = helper(i+1, j, s);
        int decreaseJ = helper(i, j-1, s);
        return Math.max(increaseI, decreaseJ);
    }

    private int helper2(String current, int index, String s) { // correct answer but high O(n)
        if(index>=s.length()) return 0;

        String interim = current + s.charAt(index);
        String reverse = new StringBuilder(interim).reverse().toString();
        int take = helper2(interim, index+1, s);
        if(interim.equals(reverse)){
            System.out.println(interim);
            take = Math.max(take, interim.length());
        }
        int skip = helper2(current, index+1, s);

        return Math.max(take, skip);
    }
}
