package study.neetcode.coreskills.dynamicprogramming;

public class RodCutting {
    /*
    * 🧩 Problem

        You are given:

        An integer n representing the length of a rod
        An array price[] of size n, where
        price[i] represents the price of a rod of length i + 1
        🎯 Task

        Write a function to compute the maximum revenue obtainable by cutting up the rod and selling the pieces.

        📌 Rules
        You may cut the rod into any number of pieces
        You may also choose not to cut the rod at all
        Each piece must have integer length
        The total length of all pieces must equal n
        ✍️ Function Signature (Java)
        int cutRod(int[] price, int n);
        📥 Example
        Input:
        n = 4
        price = {1, 5, 8, 9}

        Output:
        10
    *
    * */
    public int giveBestSplit(int lengthOfRod, int[] prices) {
        return helper(lengthOfRod, prices);
    }

    private int helper(int lengthOfRod, int[] prices) {
        if (lengthOfRod < 0) return -1;
        if (lengthOfRod == 0) return 0;
        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= lengthOfRod; i++) {
            int intermediateMaximum = helper(lengthOfRod - i, prices);
            if (intermediateMaximum == -1) continue;
            max = Math.max(intermediateMaximum + prices[i - 1], max);
        }
        if (max == Integer.MIN_VALUE) return -1;
        return max;
    }
}
