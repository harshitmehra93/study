package study.neetcode.interview.stack;

import java.util.Arrays;

/*
Daily Temperatures

You are given an integer array temperatures, where:

temperatures[i]

is the temperature on day i.

Return an array answer such that:

answer[i]

is the number of days you have to wait after day i to get a warmer temperature.

If there is no future day with a warmer temperature, put 0.

Example 1
temperatures = [73,74,75,71,69,72,76,73]

Output:

[1,1,4,2,1,1,0,0]

Explanation:

Day 0: 73 -> warmer at day 1, wait 1 day
Day 1: 74 -> warmer at day 2, wait 1 day
Day 2: 75 -> warmer at day 6, wait 4 days
Day 3: 71 -> warmer at day 5, wait 2 days
Day 4: 69 -> warmer at day 5, wait 1 day
Day 5: 72 -> warmer at day 6, wait 1 day
Day 6: 76 -> no warmer future day, 0
Day 7: 73 -> no warmer future day, 0
 */
public class DailyTemperatures {
    public int[] dailyTemperatures(int[] temperatures) {
        int[] aux = new int[temperatures.length];
        Arrays.fill(aux, -1);
        for (int i = temperatures.length - 1; i >= 0; i--) {
            int key = temperatures[i];
            for (int j = i - 1; j >= 0; j--) {
                if (temperatures[j] < key) aux[j] = i;
            }
        }
        int[] result = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            if (aux[i] == -1) result[i] = 0;
            else result[i] = aux[i] - i;
        }
        return result;
    }
}
