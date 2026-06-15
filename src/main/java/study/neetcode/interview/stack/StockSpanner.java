package study.neetcode.interview.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class StockSpanner {

    Deque<int[]> stack;

    public StockSpanner() {
        stack = new ArrayDeque<>();
    }

    public int next(int price) {
        int span = 1;
        while (!stack.isEmpty() && stack.peek()[0] <= price) {
            int[] smallerEntry = stack.pop();
            span += smallerEntry[1];
        }
        stack.push(new int[] {price, span});
        return span;
    }
}
