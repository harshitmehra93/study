package study.neetcode.interview.slidingwindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Find All Anagrams in a String — Sliding Window / Fixed-size Frequency Window

Given two strings s and p, return a list of all start indices of p’s anagrams in s.

You may return the answer in any order.

A string is an anagram of another string if both have the same character frequencies.

Example 1
Input: s = "cbaebabacd", p = "abc"
Output: [0, 6]

Explanation:

s[0..2] = "cba" -> anagram of "abc"
s[6..8] = "bac" -> anagram of "abc"
Example 2
Input: s = "abab", p = "ab"
Output: [0, 1, 2]

Explanation:

"ab", "ba", "ab"
 */
public class FindAllAnagramsInAString {
    public List<Integer> findAnagrams(String s, String targetS) {
        if (targetS.length() > s.length()) return new ArrayList<>();

        Map<Character, Integer> target = new HashMap<>();
        for (char c : targetS.toCharArray()) {
            target.put(c, target.getOrDefault(c, 0) + 1);
        }

        int left = 0;
        Map<Character, Integer> current = new HashMap<>();
        List<Integer> result = new ArrayList<>();
        for (int right = 0; right < s.length(); right++) {
            char R = s.charAt(right);
            current.put(R, current.getOrDefault(R, 0) + 1);

            if (right - left + 1 > targetS.length()) {
                char L = s.charAt(left);
                current.put(L, current.getOrDefault(L, 0) - 1);
                if (current.get(L) == 0) current.remove(L);
                left++;
            }

            if (mapsMatch(target, current)) result.add(left);
        }
        return result;
    }

    private boolean mapsMatch(Map<Character, Integer> target, Map<Character, Integer> current) {
        if (target.size() != current.size()) return false;
        for (char c : target.keySet()) {
            if (!current.containsKey(c)) return false;
            if (!current.get(c).equals(target.get(c))) return false;
        }
        return true;
    }
}
