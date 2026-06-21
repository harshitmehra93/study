package study.interview.backtracking;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

class CombinationSumTest {

    @Test
    void test_givenExample() {
        CombinationSum test = new CombinationSum();

        int[] candidates = {2, 3, 6, 7};
        int target = 7;

        List<List<Integer>> expected = List.of(List.of(2, 2, 3), List.of(7));

        assertCombinationsEqual(expected, test.combinationSum(candidates, target));
    }

    @Test
    void test_multipleCombinations() {
        CombinationSum test = new CombinationSum();

        int[] candidates = {2, 3, 5};
        int target = 8;

        List<List<Integer>> expected =
                List.of(List.of(2, 2, 2, 2), List.of(2, 3, 3), List.of(3, 5));

        assertCombinationsEqual(expected, test.combinationSum(candidates, target));
    }

    @Test
    void test_noCombination() {
        CombinationSum test = new CombinationSum();

        int[] candidates = {2};
        int target = 1;

        List<List<Integer>> expected = List.of();

        assertCombinationsEqual(expected, test.combinationSum(candidates, target));
    }

    @Test
    void test_candidateCanBeReused() {
        CombinationSum test = new CombinationSum();

        int[] candidates = {3};
        int target = 9;

        List<List<Integer>> expected = List.of(List.of(3, 3, 3));

        assertCombinationsEqual(expected, test.combinationSum(candidates, target));
    }

    @Test
    void test_singleCandidateEqualsTarget() {
        CombinationSum test = new CombinationSum();

        int[] candidates = {7};
        int target = 7;

        List<List<Integer>> expected = List.of(List.of(7));

        assertCombinationsEqual(expected, test.combinationSum(candidates, target));
    }

    @Test
    void test_avoidDuplicateOrderings() {
        CombinationSum test = new CombinationSum();

        int[] candidates = {2, 3};
        int target = 5;

        List<List<Integer>> expected = List.of(List.of(2, 3));

        /*
         * Should not return both [2, 3] and [3, 2].
         * Combination order does not matter.
         */
        assertCombinationsEqual(expected, test.combinationSum(candidates, target));
    }

    @Test
    void test_largerTarget() {
        CombinationSum test = new CombinationSum();

        int[] candidates = {2, 4, 6, 8};
        int target = 8;

        List<List<Integer>> expected =
                List.of(
                        List.of(2, 2, 2, 2),
                        List.of(2, 2, 4),
                        List.of(2, 6),
                        List.of(4, 4),
                        List.of(8));

        assertCombinationsEqual(expected, test.combinationSum(candidates, target));
    }

    private void assertCombinationsEqual(List<List<Integer>> expected, List<List<Integer>> actual) {
        assertEquals(expected.size(), actual.size());
        Set<List<Integer>> expectedSet = normalize(expected);
        Set<List<Integer>> actualSet = normalize(actual);
        assertEquals(expectedSet, actualSet);
    }

    private Set<List<Integer>> normalize(List<List<Integer>> combinations) {
        return combinations.stream()
                .map(combination -> combination.stream().sorted().collect(Collectors.toList()))
                .collect(Collectors.toSet());
    }
}
