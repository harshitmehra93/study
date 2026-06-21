package study.interview.slidingwindow;

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
        if (s.isEmpty()) return 0;
        int left = 0;
        int max = 1;
        HashSet<Character> set = new HashSet<>();
        set.add(s.charAt(0));
        for (int right = 1; right < s.length(); right++) {
            char R = s.charAt(right);

            while (set.contains(R)) {
                char L = s.charAt(left);
                set.remove(L);
                left++;
            }

            set.add(R);
            max = Math.max(max, right - left + 1);
        }
        return max;
    }
}
