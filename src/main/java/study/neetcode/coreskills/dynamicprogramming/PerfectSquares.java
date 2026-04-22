package study.neetcode.coreskills.dynamicprogramming;

import java.util.Arrays;

public class PerfectSquares {
//    2. Perfect Squares
//    Given an integer n, return the minimum number of perfect square numbers (like 1, 4, 9, 16…) that sum to n.
    public int perfectSquares(int num){
        if(num == 0) return 0;

        int min = Integer.MAX_VALUE;

        for(int i = 1; i * i <= num; i++){
            int interim = perfectSquares(num - (i * i));
            min = Math.min(min, interim);
        }

        return 1 + min;
    }
}
