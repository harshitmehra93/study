package study.neetcode.coreskills.dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

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
    Map<State, Integer> memo;

    public int knapsack(int[] weights, int[] values, int capacity) {
        memo = new HashMap<>();
        return helper(weights, values, 0, 0, capacity);
    }

    private int helper(int[] weights, int[] values, int index, int currentCapacity, int capacity) {
        if (index >= weights.length) return 0;
        State state = new State(index, currentCapacity);
        if (memo.containsKey(state)) return memo.get(state);

        int take = Integer.MIN_VALUE;
        if (currentCapacity + weights[index] <= capacity) {
            take =
                    values[index]
                            + helper(
                                    weights,
                                    values,
                                    index + 1,
                                    currentCapacity + weights[index],
                                    capacity);
        }
        int skip = helper(weights, values, index + 1, currentCapacity, capacity);

        memo.put(state, Math.max(take, skip));
        return Math.max(take, skip);
    }

    record State(int index, int currentCapacity) {}
}
