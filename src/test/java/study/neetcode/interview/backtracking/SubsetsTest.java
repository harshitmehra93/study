package study.neetcode.interview.backtracking;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

class SubsetsTest {

    @Test
    void test_emptyArray_returnsOnlyEmptySubset() {
        Subsets test = new Subsets();

        int[] nums = {};

        List<List<Integer>> expected = List.of(List.of());

        assertSubsetsEqual(expected, test.subsets(nums));
    }

    @Test
    void test_singleElement() {
        Subsets test = new Subsets();

        int[] nums = {1};

        List<List<Integer>> expected = List.of(List.of(), List.of(1));

        assertSubsetsEqual(expected, test.subsets(nums));
    }

    @Test
    void test_twoElements() {
        Subsets test = new Subsets();

        int[] nums = {1, 2};

        List<List<Integer>> expected = List.of(List.of(), List.of(1), List.of(2), List.of(1, 2));

        assertSubsetsEqual(expected, test.subsets(nums));
    }

    @Test
    void test_threeElements() {
        Subsets test = new Subsets();

        int[] nums = {1, 2, 3};

        List<List<Integer>> expected =
                List.of(
                        List.of(),
                        List.of(1),
                        List.of(2),
                        List.of(3),
                        List.of(1, 2),
                        List.of(1, 3),
                        List.of(2, 3),
                        List.of(1, 2, 3));

        assertSubsetsEqual(expected, test.subsets(nums));
    }

    @Test
    void test_negativeNumbers() {
        Subsets test = new Subsets();

        int[] nums = {-1, 2};

        List<List<Integer>> expected = List.of(List.of(), List.of(-1), List.of(2), List.of(-1, 2));

        assertSubsetsEqual(expected, test.subsets(nums));
    }

    @Test
    void test_resultSizeIsTwoPowerN() {
        Subsets test = new Subsets();

        int[] nums = {1, 2, 3, 4};

        List<List<Integer>> result = test.subsets(nums);

        assertEquals(16, result.size());
    }

    private void assertSubsetsEqual(List<List<Integer>> expected, List<List<Integer>> actual) {
        Set<List<Integer>> expectedSet = normalize(expected);
        Set<List<Integer>> actualSet = normalize(actual);

        assertEquals(expectedSet, actualSet);
    }

    private Set<List<Integer>> normalize(List<List<Integer>> subsets) {
        return subsets.stream()
                .map(subset -> subset.stream().sorted().collect(Collectors.toList()))
                .collect(Collectors.toSet());
    }
}
