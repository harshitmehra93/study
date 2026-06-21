package study.interview.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class ValidPalindromeTest {

    static Stream<TestCase> testCases() {
        return Stream.of(
                new TestCase(
                        "A man, a plan, a canal: Panama",
                        true,
                        "classic true case with spaces and punctuation"),
                new TestCase("race a car", false, "classic false case"),
                new TestCase(" ", true, "only space becomes empty string"),
                new TestCase("", true, "empty string"),
                new TestCase("a", true, "single character"),
                new TestCase(".,", true, "only punctuation"),
                new TestCase("0P", false, "digit and letter mismatch"),
                new TestCase("ab_a", true, "underscore is non-alphanumeric, becomes aba"),
                new TestCase("No lemon, no melon", true, "mixed case phrase"),
                new TestCase("Was it a car or a cat I saw?", true, "palindrome sentence"),
                new TestCase("Madam, I'm Adam", true, "apostrophe and comma ignored"),
                new TestCase("hello", false, "simple non-palindrome"),
                new TestCase("12321", true, "numeric palindrome"),
                new TestCase("1231", false, "numeric non-palindrome"),
                new TestCase("a1b2b1a", true, "near miss with digits"),
                new TestCase("a1b1a", true, "letters and digits palindrome"));
    }

    @ParameterizedTest
    @MethodSource("testCases")
    void isPalindrome(TestCase testCase) {
        ValidPalindrome solver = new ValidPalindrome();

        boolean actual = solver.isPalindrome(testCase.s);

        assertEquals(testCase.expected, actual);
    }

    private record TestCase(String s, boolean expected, String description) {}
}
