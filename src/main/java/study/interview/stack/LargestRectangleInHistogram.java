package study.interview.stack;

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
        Deque<Integer> stack = new ArrayDeque<>();
         int maxArea = 0;

         for(int i=0;i<=heights.length;i++){
             int current;
             if(i!=heights.length){
                 current = heights[i];
             }else {
                 current = 0;
             }

             while (!stack.isEmpty() && heights[stack.peek()]>current){
                 Integer topIndex = stack.pop();
                 int top = heights[topIndex];
                 int leftBoundary = stack.isEmpty() ? -1 : stack.peek();
                 int width = i - leftBoundary -1;
                 maxArea = Math.max(maxArea, top * width);
             }
             stack.push(i);
         }
         return maxArea;
    }
}
