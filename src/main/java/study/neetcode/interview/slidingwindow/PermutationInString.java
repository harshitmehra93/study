package study.neetcode.interview.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/*
Permutation in String — Sliding Window / Fixed-size Frequency Window

Given two strings s1 and s2, return true if s2 contains a permutation of s1.

In other words, return true if one of s1’s permutations is a substring of s2.

Example 1
Input: s1 = "ab", s2 = "eidbaooo"
Output: true

Explanation: s2 contains "ba", which is a permutation of "ab".

Example 2
Input: s1 = "ab", s2 = "eidboaoo"
Output: false
Constraints
1 <= s1.length, s2.length <= 10000
s1 and s2 consist of lowercase English letters.
 */
public class PermutationInString {
    public boolean checkInclusion(String s1, String s2) {
        if (s2.length() < s1.length()) return false;

        Map<Character, Integer> target = new HashMap<>();
        for (char c : s1.toCharArray()) {
            target.put(c, target.getOrDefault(c, 0) + 1);
        }

        int left = 0;
        int right = s1.length() - 1;
        Map<Character, Integer> current = new HashMap<>();
        for (char c : s2.substring(left, right).toCharArray()) {
            current.put(c, current.getOrDefault(c, 0) + 1);
        }
        for (; right < s2.length(); right++) {
            char R = s2.charAt(right);
            current.put(R, current.getOrDefault(R, 0) + 1);

            if (bothMapsMatch(current, target)) return true;

            // move left forward
            char L = s2.charAt(left);
            current.put(L, current.get(L) - 1);
            if (current.get(L) == 0) current.remove(L);
            left++;
        }
        return false;
    }

    private boolean bothMapsMatch(Map<Character, Integer> current, Map<Character, Integer> target) {
        if (current.size() != target.size()) return false;
        for (char c : target.keySet()) {
            if (!current.containsKey(c)) return false;
            if (!current.get(c).equals(target.get(c))) return false;
        }
        return true;
    }
}
