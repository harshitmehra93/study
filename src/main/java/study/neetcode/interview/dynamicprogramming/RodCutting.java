package study.neetcode.interview.dynamicprogramming;

import java.util.Arrays;

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
    int[] memo;

    public int giveBestSplit(int rodLength, int[] priceList) {
        memo = new int[rodLength + 1];
        Arrays.fill(memo, -2);
        return helper(rodLength, priceList);
    }

    public int helper(int rodLength, int[] priceList) {
        if (rodLength < 0) return -1;
        if (memo[rodLength] != -2) return memo[rodLength];
        if (rodLength == 0) return 0;
        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= priceList.length; i++) {
            int interim = helper(rodLength - i, priceList);
            if (interim == -1) continue;
            max = Math.max(max, priceList[i - 1] + interim);
        }
        if (max == Integer.MIN_VALUE) {
            return memo[rodLength] = -1;
        }
        return memo[rodLength] = max;
    }
}
