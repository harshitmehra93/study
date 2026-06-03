package study.neetcode.interview.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/*
Longest Repeating Character Replacement — Sliding Window

You are given a string s and an integer k.

You can choose any character in the string and change it to any other uppercase English character. You can perform this operation at most k times.

Return the length of the longest substring containing the same letter after performing at most k replacements.

Example 1
Input: s = "ABAB", k = 2
Output: 4

Explanation: Replace the two As with Bs, or the two Bs with As.

Example 2
Input: s = "AABABBA", k = 1
Output: 4

Explanation: Replace one character in "AABA" to make "AAAA", or one character in "ABBA" to make "BBBB".
 */
public class LongestRepeatingCharacterReplacement {
    public int characterReplacement(String s, int k) {
        Map<Character, Integer> frequency = new HashMap<>();
        int left = 0;
        frequency.put(s.charAt(0), 1);

        int max = 1;
        for (int right = 1; right < s.length(); right++) {
            char R = s.charAt(right);
            frequency.put(R, frequency.getOrDefault(R, 0) + 1);
            while ((right - left + 1) - getMostFrequent(frequency) > k) {
                char L = s.charAt(left);
                frequency.put(L, frequency.get(L) - 1);
                left++;
            }
            max = Math.max(max, right - left + 1);
        }
        return max;
    }

    private int getMostFrequent(Map<Character, Integer> frequency) {
        int max = 0;
        for (var count : frequency.values()) {
            max = Math.max(max, count);
        }
        return max;
    }
}
