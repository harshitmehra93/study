package study.neetcode.interview.stack;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class MinStackTest {

    @Test
    void shouldReturnMinimumAfterPushes() {
        MinStack stack = new MinStack();

        stack.push(5);
        assertEquals(5, stack.top());
        assertEquals(5, stack.getMin());

        stack.push(3);
        assertEquals(3, stack.top());
        assertEquals(3, stack.getMin());

        stack.push(7);
        assertEquals(7, stack.top());
        assertEquals(3, stack.getMin());
    }

    @Test
    void shouldUpdateMinimumAfterPop() {
        MinStack stack = new MinStack();

        stack.push(5);
        stack.push(3);
        stack.push(7);

        stack.pop(); // remove 7
        assertEquals(3, stack.top());
        assertEquals(3, stack.getMin());

        stack.pop(); // remove 3
        assertEquals(5, stack.top());
        assertEquals(5, stack.getMin());
    }

    @Test
    void shouldHandleDuplicateMinimums() {
        MinStack stack = new MinStack();

        stack.push(5);
        stack.push(3);
        stack.push(3);
        stack.push(7);

        assertEquals(3, stack.getMin());

        stack.pop(); // remove 7
        assertEquals(3, stack.getMin());

        stack.pop(); // remove one 3
        assertEquals(3, stack.getMin());

        stack.pop(); // remove second 3
        assertEquals(5, stack.getMin());
    }

    @Test
    void shouldHandleNegativeNumbers() {
        MinStack stack = new MinStack();

        stack.push(-2);
        stack.push(0);
        stack.push(-3);

        assertEquals(-3, stack.getMin());

        stack.pop(); // remove -3

        assertEquals(0, stack.top());
        assertEquals(-2, stack.getMin());
    }

    @Test
    void shouldHandleIncreasingNumbers() {
        MinStack stack = new MinStack();

        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertEquals(3, stack.top());
        assertEquals(1, stack.getMin());

        stack.pop();
        assertEquals(2, stack.top());
        assertEquals(1, stack.getMin());
    }

    @Test
    void shouldHandleDecreasingNumbers() {
        MinStack stack = new MinStack();

        stack.push(3);
        stack.push(2);
        stack.push(1);

        assertEquals(1, stack.getMin());

        stack.pop();
        assertEquals(2, stack.getMin());

        stack.pop();
        assertEquals(3, stack.getMin());
    }
}
