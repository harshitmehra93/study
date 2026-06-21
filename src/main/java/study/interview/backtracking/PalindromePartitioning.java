package study.interview.backtracking;

import java.util.LinkedList;
import java.util.List;

/*
Palindrome Partitioning

Given a string s, partition s such that every substring in the partition is a palindrome.

Return all possible palindrome partitioning of s.

Example 1
s = "aab"

Output:

[
  ["a", "a", "b"],
  ["aa", "b"]
]

Explanation:

"a", "a", "b" are all palindromes
"aa", "b" are all palindromes
Example 2
s = "a"

Output:

[
  ["a"]
]
Method signature
public List<List<String>> partition(String s)
 */
public class PalindromePartitioning {
    public List<List<String>> partition(String s) {
        List<List<String>> result = new LinkedList<>();
        partition(s, 0, new LinkedList<>(), result);
        return result;
    }

    void partition(String s, int currentIndex, LinkedList<String> path, List<List<String>> result) {
        if (s.length() == currentIndex) {
            result.add(new LinkedList<>(path));
            return;
        }

        for (int i = currentIndex; i < s.length(); i++) {
            var cut = s.substring(currentIndex, i + 1);
            if (isPalindromic(cut)) {
                path.add(cut);
                partition(s, i + 1, path, result);
                path.removeLast();
            }
        }
    }

    private boolean isPalindromic(String s) {
        return new StringBuilder(s).reverse().toString().equals(s);
    }
}
