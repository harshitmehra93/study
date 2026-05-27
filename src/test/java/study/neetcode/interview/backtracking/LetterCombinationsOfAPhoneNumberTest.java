package study.neetcode.interview.backtracking;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

class LetterCombinationsOfAPhoneNumberTest {

    @Test
    void test_emptyDigits_returnsEmptyList() {
        LetterCombinationsOfAPhoneNumber test = new LetterCombinationsOfAPhoneNumber();

        assertTrue(test.letterCombinations("").isEmpty());
    }

    @Test
    void test_singleDigitTwo() {
        LetterCombinationsOfAPhoneNumber test = new LetterCombinationsOfAPhoneNumber();

        List<String> expected = List.of("a", "b", "c");

        assertCombinationsEqual(expected, test.letterCombinations("2"));
    }

    @Test
    void test_twoDigits_givenExample() {
        LetterCombinationsOfAPhoneNumber test = new LetterCombinationsOfAPhoneNumber();

        List<String> expected = List.of("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf");

        assertCombinationsEqual(expected, test.letterCombinations("23"));
    }

    @Test
    void test_digitWithFourLetters() {
        LetterCombinationsOfAPhoneNumber test = new LetterCombinationsOfAPhoneNumber();

        List<String> expected = List.of("p", "q", "r", "s");

        assertCombinationsEqual(expected, test.letterCombinations("7"));
    }

    @Test
    void test_twoDigitsIncludingSeven() {
        LetterCombinationsOfAPhoneNumber test = new LetterCombinationsOfAPhoneNumber();

        List<String> result = test.letterCombinations("27");

        assertEquals(12, result.size()); // 3 letters for 2 * 4 letters for 7
        assertTrue(result.contains("ap"));
        assertTrue(result.contains("cs"));
    }

    @Test
    void test_threeDigits_resultSize() {
        LetterCombinationsOfAPhoneNumber test = new LetterCombinationsOfAPhoneNumber();

        List<String> result = test.letterCombinations("234");

        assertEquals(27, result.size()); // 3 * 3 * 3
    }

    @Test
    void test_allGeneratedStringsHaveSameLengthAsDigits() {
        LetterCombinationsOfAPhoneNumber test = new LetterCombinationsOfAPhoneNumber();

        List<String> result = test.letterCombinations("79");

        for (String combination : result) {
            assertEquals(2, combination.length());
        }
    }

    @Test
    void test_noDuplicates() {
        LetterCombinationsOfAPhoneNumber test = new LetterCombinationsOfAPhoneNumber();

        List<String> result = test.letterCombinations("279");

        Set<String> unique = Set.copyOf(result);

        assertEquals(unique.size(), result.size());
    }

    private void assertCombinationsEqual(List<String> expected, List<String> actual) {
        Set<String> expectedSet = expected.stream().collect(Collectors.toSet());
        Set<String> actualSet = actual.stream().collect(Collectors.toSet());

        assertEquals(expectedSet, actualSet);
    }
}
