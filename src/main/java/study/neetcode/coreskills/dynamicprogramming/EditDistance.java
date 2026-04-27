package study.neetcode.coreskills.dynamicprogramming;

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
    public int editDistance(String current, String target) {
        return helper(current, target, 0, 0);
    }

    private int helper(String current, String target, int i, int j) {
        if (current.equals(target)) return 0;
        if (i == current.length()) return target.length() - j;
        if (j == target.length()) return current.length() - i;

        int min = Integer.MAX_VALUE;

        if ((j < target.length() && i < current.length())
                && (current.charAt(i) == target.charAt(j))) {
            return helper(current, target, i + 1, j + 1);
        }

        // replacement
        int minOfReplacement = Integer.MAX_VALUE;
        if (i < current.length() && j < target.length()) {
            minOfReplacement = helper(current, target, i + 1, j + 1);
            minOfReplacement = minOfReplacement == -1 ? Integer.MAX_VALUE : minOfReplacement;
        }

        // addition
        int minOfAddition = Integer.MAX_VALUE;
        if (j < target.length()) {
            minOfAddition = helper(current, target, i, j + 1);
            minOfAddition = minOfAddition == -1 ? Integer.MAX_VALUE : minOfAddition;
        }

        // removals
        int minOfRemoval = Integer.MAX_VALUE;
        if (i < current.length()) {
            minOfRemoval = helper(current, target, i + 1, j);
            minOfRemoval = minOfRemoval == -1 ? Integer.MAX_VALUE : minOfRemoval;
        }

        min = Math.min(minOfReplacement, min);
        min = Math.min(minOfAddition, min);
        min = Math.min(minOfRemoval, min);

        if (min == Integer.MAX_VALUE) return -1;
        return 1 + min;
    }
}
