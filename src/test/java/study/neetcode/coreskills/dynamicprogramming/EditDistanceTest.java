package study.neetcode.coreskills.dynamicprogramming;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class EditDistanceTest {

    @ParameterizedTest
    @MethodSource("testCases")
    void test(String word1, String word2, int expected) {
        EditDistance solver = new EditDistance();
        assertEquals(expected, solver.editDistance(word1, word2));
    }

    static Stream<Arguments> testCases() {
        return Stream.of(
                // Empty string cases
                Arguments.of("abc", "", 3),
                Arguments.of("", "abc", 3),
                Arguments.of("", "", 0),

                // Same strings
                Arguments.of("abc", "abc", 0),
                Arguments.of("a", "a", 0),

                // Single operation
                Arguments.of("abc", "ab", 1), // delete
                Arguments.of("ab", "abc", 1), // insert
                Arguments.of("abc", "adc", 1), // replace

                // Classic examples
                Arguments.of("horse", "ros", 3),
                Arguments.of("intention", "execution", 5),

                // More mixed cases
                Arguments.of("kitten", "sitting", 3),
                Arguments.of("flaw", "lawn", 2),
                Arguments.of("sunday", "saturday", 3),

                // Completely different
                Arguments.of("abc", "def", 3),

                // Prefix/suffix differences
                Arguments.of("abcdef", "abc", 3),
                Arguments.of("abc", "abcdef", 3),

                // Repeated characters
                Arguments.of("aaaa", "aa", 2),
                Arguments.of("aa", "aaaa", 2),
                Arguments.of("aab", "ab", 1),

                // Small tricky cases
                Arguments.of("ab", "ba", 2),
                Arguments.of("sea", "eat", 2),
                Arguments.of("park", "spake", 3));
    }
}
