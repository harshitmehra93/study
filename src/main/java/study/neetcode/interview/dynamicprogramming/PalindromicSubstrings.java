package study.neetcode.interview.dynamicprogramming;

public class PalindromicSubstrings {
    // count the number of palindromic substrings in a given string
    Boolean[][] memo;

    public int countPalindromicSubstrings(String s) {
        if (s.length() == 0) return 0;
        memo = new Boolean[s.length() + 1][s.length() + 1];

        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                if (isPalindrome(s, i, j)) count++;
            }
        }
        return count;
    }

    public boolean isPalindrome(String s, int i, int j) {
        if (i == j) return true;
        if (i > j) return false;
        if (i >= s.length() && j < 0) return false;
        if (s.charAt(i) == s.charAt(j)) {
            if (j == i + 1) return true;
            return isPalindrome(s, i + 1, j - 1);
        }
        return false;
    }
}
