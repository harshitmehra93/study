package study.interview.backtracking;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

class GenerateParenthesesTest {

    @Test
    void test_n1() {
        GenerateParentheses test = new GenerateParentheses();

        List<String> result = test.generateParenthesis(1);

        assertParenthesesEqual(List.of("()"), result);
    }

    @Test
    void test_n2() {
        GenerateParentheses test = new GenerateParentheses();

        List<String> result = test.generateParenthesis(2);

        assertParenthesesEqual(List.of("(())", "()()"), result);
    }

    @Test
    void test_n3_givenExample() {
        GenerateParentheses test = new GenerateParentheses();

        List<String> result = test.generateParenthesis(3);

        assertParenthesesEqual(List.of("((()))", "(()())", "(())()", "()(())", "()()()"), result);
    }

    @Test
    void test_resultSizeForN3_isCatalanNumber5() {
        GenerateParentheses test = new GenerateParentheses();

        List<String> result = test.generateParenthesis(3);

        assertEquals(5, result.size());
    }

    @Test
    void test_allGeneratedStringsAreValidForN3() {
        GenerateParentheses test = new GenerateParentheses();

        List<String> result = test.generateParenthesis(3);

        for (String parentheses : result) {
            assertTrue(isValid(parentheses), "Invalid parentheses string: " + parentheses);
            assertEquals(6, parentheses.length());
        }
    }

    @Test
    void test_noDuplicatesForN3() {
        GenerateParentheses test = new GenerateParentheses();

        List<String> result = test.generateParenthesis(3);

        Set<String> unique = Set.copyOf(result);
        assertEquals(unique.size(), result.size());
    }

    private void assertParenthesesEqual(List<String> expected, List<String> actual) {
        Set<String> expectedSet = expected.stream().collect(Collectors.toSet());
        Set<String> actualSet = actual.stream().collect(Collectors.toSet());

        assertEquals(expectedSet, actualSet);
    }

    private boolean isValid(String s) {
        int balance = 0;

        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                balance++;
            } else if (ch == ')') {
                balance--;
            }

            if (balance < 0) {
                return false;
            }
        }

        return balance == 0;
    }
}
