package study.neetcode.interview.stack;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ValidParenthesesTest {

    @Test
    void simpleRoundBracketsAreValid() {
        ValidParentheses solution = new ValidParentheses();

        assertTrue(solution.isValid("()"));
    }

    @Test
    void allBracketTypesSideBySideAreValid() {
        ValidParentheses solution = new ValidParentheses();

        assertTrue(solution.isValid("()[]{}"));
    }

    @Test
    void nestedBracketsAreValid() {
        ValidParentheses solution = new ValidParentheses();

        assertTrue(solution.isValid("([{}])"));
    }

    @Test
    void wrongClosingBracketIsInvalid() {
        ValidParentheses solution = new ValidParentheses();

        assertFalse(solution.isValid("(]"));
    }

    @Test
    void wrongClosingOrderIsInvalid() {
        ValidParentheses solution = new ValidParentheses();

        assertFalse(solution.isValid("([)]"));
    }

    @Test
    void closingBracketWithoutOpeningBracketIsInvalid() {
        ValidParentheses solution = new ValidParentheses();

        assertFalse(solution.isValid("]"));
    }

    @Test
    void onlyOpeningBracketsAreInvalid() {
        ValidParentheses solution = new ValidParentheses();

        assertFalse(solution.isValid("((("));
    }

    @Test
    void onlyClosingBracketsAreInvalid() {
        ValidParentheses solution = new ValidParentheses();

        assertFalse(solution.isValid(")))"));
    }

    @Test
    void missingFinalClosingBracketIsInvalid() {
        ValidParentheses solution = new ValidParentheses();

        assertFalse(solution.isValid("(()"));
    }

    @Test
    void extraClosingBracketIsInvalid() {
        ValidParentheses solution = new ValidParentheses();

        assertFalse(solution.isValid("())"));
    }

    @Test
    void complexValidCase() {
        ValidParentheses solution = new ValidParentheses();

        assertTrue(solution.isValid("{[()()]}"));
    }

    @Test
    void complexInvalidCase() {
        ValidParentheses solution = new ValidParentheses();

        assertFalse(solution.isValid("{[(])}"));
    }

    @Test
    void emptyStringIsValid() {
        ValidParentheses solution = new ValidParentheses();

        assertTrue(solution.isValid(""));
    }
}
