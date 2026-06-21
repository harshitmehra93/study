package study.interview.backtracking;

import java.util.LinkedList;
import java.util.List;

/*
Problem: Generate Parentheses

Given n pairs of parentheses, generate all combinations of well-formed parentheses.

You must return all valid strings that use exactly:

n opening parentheses '('
n closing parentheses ')'
Example 1
n = 3

Output:

[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]
Example 2
n = 1

Output:

[
  "()"
]
 */
public class GenerateParentheses {
    public List<String> generateParenthesis(int n) {
        List<String> result = new LinkedList<>();
        tryParentheses(0, 0, n, n * 2, "", result);
        return result;
    }

    void tryParentheses(
            int index,
            int currentlyOpened,
            int remainingToOpen,
            int len,
            String combination,
            List<String> result) {
        if (index == len) {
            result.add(combination);
            return;
        }

        if (remainingToOpen > 0) {
            tryParentheses(
                    index + 1,
                    currentlyOpened + 1,
                    remainingToOpen - 1,
                    len,
                    combination + "(",
                    result);
        }

        if (currentlyOpened > 0) {
            tryParentheses(
                    index + 1,
                    currentlyOpened - 1,
                    remainingToOpen,
                    len,
                    combination + ")",
                    result);
        }
    }
}
