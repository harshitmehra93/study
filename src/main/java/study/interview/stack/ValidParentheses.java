package study.interview.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class ValidParentheses {
    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
                continue;
            }
            if (c == ')' || c == '}' || c == ']') {
                if (stack.isEmpty() || stack.peek() != isMatchingOpeningBrace(c)) return false;
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    private Character isMatchingOpeningBrace(char c) {
        return switch (c) {
            case ')' -> '(';
            case '}' -> '{';
            case ']' -> '[';
            default -> '0';
        };
    }
}
