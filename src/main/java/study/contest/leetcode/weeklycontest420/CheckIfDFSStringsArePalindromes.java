package study.contest.leetcode.weeklycontest420;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LeetCode 3327 — Check if DFS Strings Are Palindromes
 *
 * <p>A rooted tree is represented by {@code parent}, and node {@code i} owns character {@code
 * s[i]}. For each node, traverse its subtree by visiting children in increasing numerical order and
 * appending the current node's character after its children. Return whether every resulting subtree
 * DFS string is a palindrome.
 *
 * <p>Examples:
 *
 * <pre>
 * Input: parent = [-1,0,0,1,1,2], s = "aababa"
 * Output: [true,true,false,true,true,true]
 *
 * Input: parent = [-1,0,0,0,0], s = "aabcb"
 * Output: [true,true,true,true,true]
 * </pre>
 *
 * <p>Constraints: {@code n == parent.length == s.length()}, {@code 1 <= n <= 100000}; {@code
 * parent} represents a valid tree, and {@code s} contains only lowercase English letters.
 *
 * @see <a href="https://leetcode.com/problems/check-if-dfs-strings-are-palindromes/">Problem</a>
 */
public class CheckIfDFSStringsArePalindromes {
    int[] enter;
    int[] exit;
    Map<Integer, List<Integer>> childrenMap;

    public boolean[] findAnswer(int[] parent, String s) {
        childrenMap = new HashMap<>();
        for (int i = 1; i < parent.length; i++) {
            if (!childrenMap.containsKey(parent[i])) {
                childrenMap.put(parent[i], new ArrayList<>());
            }
            childrenMap.get(parent[i]).add(i);
        }

        enter = new int[parent.length];
        exit = new int[parent.length];
        StringBuilder sb = new StringBuilder();
        dfs(0, s, sb);
        String result = sb.toString();

        boolean[] answer = new boolean[parent.length];
        for (int i = 0; i < parent.length; i++) {
            int start = enter[i];
            int end = exit[i];
            answer[i] = isPalindrome(result, start, end - 1);
        }
        return answer;
    }

    void dfs(int node, String s, StringBuilder sb) {
        enter[node] = sb.length();

        var children = childrenMap.getOrDefault(node, new ArrayList<>());
        for (var child : children) {
            dfs(child, s, sb);
        }
        sb.append(s.charAt(node));

        exit[node] = sb.length();
    }

    boolean isPalindrome(String str, int left, int right) {
        if (right - left + 1 < 2) return true;
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }
}
