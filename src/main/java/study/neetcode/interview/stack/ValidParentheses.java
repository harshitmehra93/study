package study.neetcode.interview.stack;

import java.util.Stack;

public class ValidParentheses {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
                continue;
            }
            if (c == ')' || c == '}' || c == ']') {
                if (stack.empty() || stack.peek() != isMatchingOpeningBrace(c)) return false;
                stack.pop();
            }
        }
        if (!stack.empty()) return false;
        return true;
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
