package study.neetcode.interview.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/*
Minimum Window Substring — Sliding Window / Variable-size Minimum Window

Given two strings s and t, return the minimum window substring of s such that every character in t, including duplicates, is included in the window.

If there is no such substring, return the empty string "".

Example 1
Input: s = "ADOBECODEBANC", t = "ABC"
Output: "BANC"

Explanation: "BANC" is the smallest substring that contains A, B, and C.

Example 2
Input: s = "a", t = "a"
Output: "a"
Example 3
Input: s = "a", t = "aa"
Output: ""
 */
public class MinimumWindowSubstring {

    public String minWindow(String s, String t) {
        if(s.length()<t.length())return "";

        Map<Character, Integer> target = new HashMap<>();
        for(char c:t.toCharArray()){
            target.put(c,target.getOrDefault(c,0)+1);
        }

        int left=0;
        String min = "";
        Map<Character, Integer> current = new HashMap<>();
        for(int right=0;right<s.length();right++){
            char R = s.charAt(right);
            current.put(R,current.getOrDefault(R,0)+1);


            while (currentContainsTarget(current,target)){
                if(min==""||min.length()>right-left+1){
                    min=s.substring(left,right+1);
                }

                char L = s.charAt(left);
                decrementFrequencyOfChar(current, L);
                left++;
            }
        }
        return min;
    }

    private static void decrementFrequencyOfChar(Map<Character, Integer> current, char L) {
        if(current.containsKey(L)){
            current.put(L, current.get(L)-1);
            if(current.get(L)==0) current.remove(L);
        }
    }

    private boolean currentContainsTarget(Map<Character, Integer> current, Map<Character, Integer> target) {
        for(char c:target.keySet()){
            if(!current.containsKey(c)) return false;
            if(current.get(c) < target.get(c)) return false;
        }
        return true;
    }
}
