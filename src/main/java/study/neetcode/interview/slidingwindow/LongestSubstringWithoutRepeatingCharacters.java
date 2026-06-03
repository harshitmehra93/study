package study.neetcode.interview.slidingwindow;

import java.util.HashSet;
import java.util.Set;

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
        if (s.length() == 0) return 0;
        int max = 1;
        int left = 0, right = 1;
        Set<Character> set = new HashSet<>();
        set.add(s.charAt(0));
        for (; right < s.length(); right++) {
            while (set.contains(s.charAt(right))) {
                set.remove(s.charAt(left));
                left++;
            }
            max = Math.max(max, right - left + 1);
            set.add(s.charAt(right));
        }
        return max;
    }
}
