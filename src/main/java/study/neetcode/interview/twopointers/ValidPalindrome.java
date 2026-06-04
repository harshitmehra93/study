package study.neetcode.interview.twopointers;

/*
Valid Palindrome — Two Pointers

Given a string s, return true if it is a palindrome after converting all uppercase letters to lowercase and removing all non-alphanumeric characters.

A palindrome reads the same forward and backward.

Example 1
Input: s = "A man, a plan, a canal: Panama"
Output: true

Explanation:

After cleaning: "amanaplanacanalpanama"
It reads the same forward and backward.
Example 2
Input: s = "race a car"
Output: false

Explanation:

After cleaning: "raceacar"
It is not a palindrome.
Example 3
Input: s = " "
Output: true
 */
public class ValidPalindrome {

    public boolean isPalindrome(String s) {
        if (s.trim().isEmpty()) return true;

        s = s.toLowerCase();

        int left = 0, right = s.length() - 1;
        while (left < right) {
            char L = s.charAt(left);
            char R = s.charAt(right);

            if (!isAlphanumeric(L)) {
                left++;
                continue;
            }
            if (!isAlphanumeric(R)) {
                right--;
                continue;
            }

            if (L != R) return false;

            left++;
            right--;
        }
        return true;
    }

    private boolean isAlphanumeric(char c) {
        return c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z' || c >= '0' && c <= '9';
    }
}
