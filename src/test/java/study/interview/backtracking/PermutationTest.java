package study.interview.backtracking;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

class PermutationTest {

    @Test
    void test_emptyArray_returnsOneEmptyPermutation() {
        Permutation test = new Permutation();

        int[] nums = {};

        List<List<Integer>> expected = List.of(List.of());

        assertPermutationsEqual(expected, test.permute(nums));
    }

    @Test
    void test_singleElement() {
        Permutation test = new Permutation();

        int[] nums = {1};

        List<List<Integer>> expected = List.of(List.of(1));

        assertPermutationsEqual(expected, test.permute(nums));
    }

    @Test
    void test_twoElements() {
        Permutation test = new Permutation();

        int[] nums = {1, 2};

        List<List<Integer>> expected = List.of(List.of(1, 2), List.of(2, 1));

        assertPermutationsEqual(expected, test.permute(nums));
    }

    @Test
    void test_threeElements() {
        Permutation test = new Permutation();

        int[] nums = {1, 2, 3};

        List<List<Integer>> expected =
                List.of(
                        List.of(1, 2, 3),
                        List.of(1, 3, 2),
                        List.of(2, 1, 3),
                        List.of(2, 3, 1),
                        List.of(3, 1, 2),
                        List.of(3, 2, 1));

        assertPermutationsEqual(expected, test.permute(nums));
    }

    @Test
    void test_negativeNumbers() {
        Permutation test = new Permutation();

        int[] nums = {-1, 2};

        List<List<Integer>> expected = List.of(List.of(-1, 2), List.of(2, -1));

        assertPermutationsEqual(expected, test.permute(nums));
    }

    @Test
    void test_resultSizeIsFactorialOfN() {
        Permutation test = new Permutation();

        int[] nums = {1, 2, 3, 4};

        List<List<Integer>> result = test.permute(nums);

        assertEquals(24, result.size());
    }

    private void assertPermutationsEqual(List<List<Integer>> expected, List<List<Integer>> actual) {
        Set<List<Integer>> expectedSet = normalize(expected);
        Set<List<Integer>> actualSet = normalize(actual);

        assertEquals(expectedSet, actualSet);
    }

    private Set<List<Integer>> normalize(List<List<Integer>> permutations) {
        return permutations.stream().map(ArrayList::new).collect(Collectors.toSet());
    }
}
