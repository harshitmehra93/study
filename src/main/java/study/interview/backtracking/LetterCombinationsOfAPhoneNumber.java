package study.interview.backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
Problem statement

Given a string digits containing digits from 2 to 9, return all possible letter combinations that the number could represent.

The mapping is like a phone keypad:

2 -> abc
3 -> def
4 -> ghi
5 -> jkl
6 -> mno
7 -> pqrs
8 -> tuv
9 -> wxyz

Example:

digits = "23"

Output:

[
  "ad", "ae", "af",
  "bd", "be", "bf",
  "cd", "ce", "cf"
]
 */
public class LetterCombinationsOfAPhoneNumber {
    HashMap<Integer, List<String>> map;

    public List<String> letterCombinations(String digits) {
        if (digits.isEmpty()) return List.of();
        List<String> result = new ArrayList<>();
        setupMap();
        int[] nums = StringToNumArray(digits);
        tryCombination(0, nums, "", result);
        return result;
    }

    private static int[] StringToNumArray(String digits) {
        int[] nums = new int[digits.length()];
        char[] charArray = digits.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            nums[i] = Integer.valueOf("" + charArray[i]);
        }
        return nums;
    }

    void tryCombination(int index, int[] digits, String combination, List<String> result) {
        if (index == digits.length) {
            result.add(combination);
            return;
        }

        Integer key = Integer.valueOf(digits[index]);
        List<String> candidates = map.get(key);
        for (String candidate : candidates) {
            tryCombination(index + 1, digits, combination + candidate, result);
        }
    }

    private void setupMap() {
        map = new HashMap<>();
        map.put(2, List.of("a", "b", "c"));
        map.put(3, List.of("d", "e", "f"));
        map.put(4, List.of("g", "h", "i"));
        map.put(5, List.of("j", "k", "l"));
        map.put(6, List.of("m", "n", "o"));
        map.put(7, List.of("p", "q", "r", "s"));
        map.put(8, List.of("t", "u", "v"));
        map.put(9, List.of("w", "x", "y", "z"));
    }
}
