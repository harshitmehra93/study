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
        Map<Character, Integer> freq = getFrequencyMap(s1, new HashMap<>());
        int left = 0, right = s1.length() - 1;
        Map<Character, Integer> temp = new HashMap<>();
        while (right < s2.length()) {
            Map<Character, Integer> subsMap = getFrequencyMap(s2.substring(left, right + 1), temp);
            if (areFrequencyMapsEqual(freq, subsMap)) return true;
            left++;
            right++;
        }
        return false;
    }

    private boolean areFrequencyMapsEqual(
            Map<Character, Integer> map1, Map<Character, Integer> map2) {
        if (map1.size() != map2.size()) return false;
        for (var key : map1.keySet()) {
            if (map1.get(key) != map2.get(key)) return false;
        }
        return true;
    }

    private Map<Character, Integer> getFrequencyMap(String s, Map<Character, Integer> freq) {
        freq.clear();
        for (char c : s.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }
        return freq;
    }
}
