package study.neetcode.interview.dynamicprogramming;

public class EditDistance {

    //    Problem: Edit Distance
    //
    //    You are given two strings:
    //    word1
    //    word2
    //
    //    You can perform the following operations on word1:
    //    1. Insert a character
    //    2. Delete a character
    //    3. Replace a character
    //
    //    Return the minimum number of operations required to convert word1 into word2.
    //
    //    Examples
    //    Input:
    //    word1 = "horse"
    //    word2 = "ros"
    //
    //    Output: 3
    //
    //    Explanation:
    //    horse → rorse   replace h with r
    //    rorse → rose    delete r
    //    rose  → ros     delete e
    Integer[][] memo;

    public int editDistance(String word1, String word2) {
        memo = new Integer[word1.length() + 1][word2.length() + 1];
        return helper(word1, word2, 0, 0);
    }

    private int helper(String word, String target, int i, int j) {
        if (i == word.length()) return target.length() - j;
        if (j == target.length()) return word.length() - i;
        if (memo[i][j] != null) return memo[i][j];

        if (word.charAt(i) == target.charAt(j)) {
            return memo[i][j] = helper(word, target, i + 1, j + 1);
        }

        int replace = helper(word, target, i + 1, j + 1);
        int insert = helper(word, target, i, j + 1);
        int remove = helper(word, target, i + 1, j);

        int min = Integer.MAX_VALUE;
        min = Math.min(min, replace);
        min = Math.min(min, insert);
        min = Math.min(min, remove);

        return memo[i][j] = 1 + min;
    }
}
