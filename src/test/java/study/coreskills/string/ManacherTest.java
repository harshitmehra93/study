package study.coreskills.string;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ManacherTest {

    static Stream<Arguments> palindromeChecks() {
        return Stream.of(
                Arguments.of("single character", "a", 0, 1, true),
                Arguments.of("complete odd palindrome", "abacaba", 0, 7, true),
                Arguments.of("odd palindrome inside a string", "zabacabay", 1, 8, true),
                Arguments.of("complete even palindrome", "abba", 0, 4, true),
                Arguments.of("even palindrome inside a string", "zabbaq", 1, 5, true),
                Arguments.of("repeated characters", "aaaa", 0, 4, true),
                Arguments.of("odd non-palindrome", "abc", 0, 3, false),
                Arguments.of("odd-length prefix that is not a palindrome", "abacaba", 0, 5, false),
                Arguments.of("even non-palindrome", "abca", 0, 4, false),
                Arguments.of("prefix that is not a palindrome", "abba", 0, 3, false));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("palindromeChecks")
    void checksHalfOpenIntervals(
            String description,
            String text,
            int leftInclusive,
            int rightExclusive,
            boolean expected) {
        Manacher manacher = new Manacher(text);

        assertEquals(expected, manacher.isPalindrome(leftInclusive, rightExclusive));
    }
}
