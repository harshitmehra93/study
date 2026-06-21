package study.interview.stack;

import java.util.ArrayDeque;
import java.util.Deque;

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
        int[] result = new int[temperatures.length];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                int index = stack.pop();
                result[index] = i - index;
            }

            stack.push(i);
        }

        return result;
    }
}
