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

    String transformed;
    int[] radiiArr;

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

        initTransformed(result);
        radiiArr = new int[transformed.length()];
        buildManacher();

        boolean[] answer = new boolean[parent.length];
        for (int i = 0; i < parent.length; i++) {
            int start = enter[i];
            int end = exit[i];
            answer[i] = isPalindrome(start, end - 1);
        }
        return answer;
    }

    boolean isPalindrome(int start, int end) {
        int left = 2 * start + 1;
        int right = 2 * end + 1;
        int center = (left + right) / 2;
        int expectedSize = right - left + 1;
        return (radiiArr[center] - 1) * 2 >= expectedSize;
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

    void initTransformed(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            sb.append("#" + c);
        }
        sb.append("#");
        transformed = sb.toString();
    }

    void buildManacher() {
        int L = 0;
        int R = -1;

        for (int i = 0; i < transformed.length(); i++) {
            int radius;
            if (i >= R) {
                radius = 0;
            } else {
                int mirror = L + R - i;
                radius = Math.min(radiiArr[mirror], R - i);
            }

            while (i - radius >= 0
                    && i + radius < transformed.length()
                    && transformed.charAt(i - radius) == transformed.charAt(i + radius)) {
                radius++;
            }

            radiiArr[i] = radius;

            if (i + radius > R) {
                L = i - radius;
                R = i + radius;
            }
        }
    }
}
