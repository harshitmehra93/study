package study.interview.dynamicprogramming;

import java.util.HashMap;
import java.util.Map;

public class PerfectSquares {
    //    2. Perfect Squares
    //    Given an integer n, return the minimum number of perfect square numbers (like 1, 4, 9,
    // 16…) that sum to n.
    Map<Integer, Integer> memo;

    public int perfectSquares(int num) {
        memo = new HashMap<>();
        return getMinimumPerfectSquares(num);
    }

    private int getMinimumPerfectSquares(int num) {
        if (num < 0) return -1;
        if (num == 0) return 0;
        if (memo.containsKey(num)) return memo.get(num);

        int min = Integer.MAX_VALUE;
        for (int i = 1; i * i <= num; i++) {
            int interim = getMinimumPerfectSquares(num - i * i);
            if (interim == -1) continue;
            min = Math.min(min, interim);
        }

        if (min == Integer.MAX_VALUE) {
            memo.put(num, -1);
            return -1;
        }
        memo.put(num, 1 + min);
        return 1 + min;
    }
}
