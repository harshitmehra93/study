package study.neetcode.coreskills.dynamicprogramming;

import java.util.HashMap;

public class LongestCommonSubsequence {

    HashMap<Pair, Integer> memo;

    public int lcs(String s1, String s2) {
        String str1 = s1.length() > s2.length() ? s1 : s2;
        String str2 = s1.length() > s2.length() ? s2 : s1;
        if (s1.length() == s2.length()) {
            str1 = s1;
            str2 = s2;
        }
        int lcs = Integer.MIN_VALUE;
        for (int i = 0; i < str2.length(); i++) {
            lcs = Math.max(getLcs(str1, str2.substring(i, str2.length())), lcs);
        }
        if (lcs == Integer.MIN_VALUE) return 0;
        return lcs;
    }

    private static int getLcs(String s1, String s2) {
        int lcs = 0;

        for (int j = 0, i = 0; j < s1.length() && i < s2.length(); j++) { // bigger string
            if (s1.charAt(j) == s2.charAt(i)) {
                i++;
                lcs++;
            }
        }

        return lcs;
    }

    public int lcs2(String s1, String s2) {
        memo = new HashMap<>();
        return helper(s1, s2, 0, 0);
    }

    private int helper(String s1, String s2, int i, int j) {
        if (i >= s1.length() || j >= s2.length()) return 0;
        Pair pair = new Pair(i, j);
        if (memo.containsKey(pair)) return memo.get(pair);

        int max = 0;
        if (s1.charAt(i) == s2.charAt(j)) {
            max = Math.max(helper(s1, s2, i + 1, j + 1) + 1, max);
        } else {
            int skippingI = helper(s1, s2, i + 1, j);
            int skippingJ = helper(s1, s2, i, j + 1);
            max = Math.max(max, Math.max(skippingI, skippingJ));
        }

        memo.put(pair, max);
        return max;
    }

    record Pair(int i, int j) {}
}
