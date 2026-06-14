package study.neetcode.interview.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/*
Largest Rectangle in Histogram
Current phase: Stack / Monotonic Stack.
You are given an integer array heights, where heights[i] is the height of a histogram bar of width 1. Return the area of the largest rectangle that can be formed using consecutive bars.
Input:  [2, 1, 5, 6, 2, 3]
Output: 10
The optimal rectangle uses heights 5 and 6, giving:
width = 2
height = 5
area = 10
 */
public class LargestRectangleInHistogram {
    public int largestRectangleArea(int[] heights) {
        Deque<int[]> stack = new ArrayDeque<>(); // [start index, height]
        int maximumArea = 0;

        for (int i = 0; i < heights.length; i++) {
            int start = i;

            while (!stack.isEmpty() && stack.peek()[1] > heights[i]) {
                int[] previous = stack.pop();
                int previousStart = previous[0];
                int previousHeight = previous[1];

                maximumArea = Math.max(maximumArea, previousHeight * (i - previousStart));
                start = previousStart;
            }

            stack.push(new int[] {start, heights[i]});
        }

        while (!stack.isEmpty()) {
            int[] previous = stack.pop();
            int start = previous[0];
            int height = previous[1];
            maximumArea = Math.max(maximumArea, height * (heights.length - start));
        }

        return maximumArea;
    }
}
