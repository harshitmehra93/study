package study.interview.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/*
Min Stack

This is the next classic Stack pattern after Valid Parentheses.

Problem

Design a stack that supports:

push(int val)
pop()
top()
getMin()

All operations must be O(1).

Example
MinStack stack = new MinStack();

stack.push(5);
stack.push(3);
stack.push(7);

stack.getMin(); // 3
stack.pop();    // removes 7
stack.getMin(); // 3
stack.pop();    // removes 3
stack.getMin(); // 5
 */
public class MinStack {
    Deque<Integer> stack = new ArrayDeque<>();
    Deque<Integer> minStack = new ArrayDeque<>();

    public MinStack() {}

    public void push(int value) {
        int min;
        if (minStack.isEmpty()) min = value;
        else min = Math.min(minStack.peek(), value);
        stack.push(value);
        minStack.push(min);
    }

    public void pop() {
        stack.pop();
        minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
