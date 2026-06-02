package study.neetcode.interview.slidingwindow;

import java.util.HashSet;

/*
Longest Substring Without Repeating Characters — Sliding Window

Given a string s, find the length of the longest substring without repeating characters.

A substring is a contiguous sequence of characters.

Example 1
Input: s = "abcabcbb"
Output: 3

Explanation: "abc" has length 3.

Example 2
Input: s = "bbbbb"
Output: 1

Explanation: "b" is the longest substring without repeats.

Example 3
Input: s = "pwwkew"
Output: 3

Explanation: "wke" has length 3.

Important: "pwke" is not valid because it is not contiguous.
 */
public class LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        if (s.length() <= 1) return s.length();
        int i = 0;
        int j = 1;
        HashSet<Character> set = new HashSet<>();
        set.add(s.charAt(0));
        int max = 1;
        int currentLength = 1;
        while (j != s.length()) {
            if (i == j) {
                j++;
                set.add(s.charAt(i));
                currentLength++;
                continue;
            }

            char I = s.charAt(i);
            char J = s.charAt(j);

            if (!set.contains(J)) {
                set.add(J);
                currentLength++;
                max = Math.max(max, currentLength);
                j++;
            } else {
                i++;
                currentLength--;
                set.remove(I);
            }
        }
        return max;
    }
}
