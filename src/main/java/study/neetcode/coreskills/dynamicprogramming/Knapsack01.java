package study.neetcode.coreskills.dynamicprogramming;

public class Knapsack01 {

    //    🧩 Problem: 0/1 Knapsack
    //
    //    You are given:
    //    An integer array weights[]
    //    An integer array values[]
    //    An integer capacity
    //    Return the maximum total value you can put into the knapsack without the total weight
    // exceeding capacity.
    //    📌 Rules
    //    Each item can be selected at most once
    //    You may choose to skip any item
    //    You cannot split an item
    //    Total selected weight must be <= capacity
    //
    //    Each item i has:
    //    weight = weights[i]
    //    value  = values[i]

    //    Example
    //    weights = [1, 3, 4, 5]
    //    values  = [1, 4, 5, 7]
    //    capacity = 7
    //    answer = 9
    //    Take item with weight 3, value 4
    //    Take item with weight 4, value 5
    //
    //    Total weight = 7
    //    Total value = 9

    public int knapsack(int[] weights, int[] values, int capacity) {
        return helper(weights, values, capacity, 0);
    }

    private int helper(int[] weights, int[] values, int currentCapacity, int index) {
        if (currentCapacity < 0) return 0;
        if (index >= weights.length) return 0;

        int take = Integer.MIN_VALUE;
        if (weights[index] <= currentCapacity) {
            take =
                    values[index]
                            + helper(weights, values, currentCapacity - weights[index], index + 1);
        }

        int skip = helper(weights, values, currentCapacity, index + 1);

        return Math.max(take, skip);
    }
}
