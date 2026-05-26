package study.neetcode.interview.backtracking;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

class Subsets2Test {

    @Test
    void test_givenExample() {
        Subsets2 test = new Subsets2();

        int[] nums = {1, 2, 2};

        List<List<Integer>> expected =
                List.of(
                        List.of(),
                        List.of(1),
                        List.of(2),
                        List.of(1, 2),
                        List.of(2, 2),
                        List.of(1, 2, 2));

        assertSubsetsEqual(expected, test.subsetsWithDup(nums));
    }

    @Test
    void test_emptyArray_returnsOnlyEmptySubset() {
        Subsets2 test = new Subsets2();

        int[] nums = {};

        List<List<Integer>> expected = List.of(List.of());

        assertSubsetsEqual(expected, test.subsetsWithDup(nums));
    }

    @Test
    void test_singleElement() {
        Subsets2 test = new Subsets2();

        int[] nums = {1};

        List<List<Integer>> expected = List.of(List.of(), List.of(1));

        assertSubsetsEqual(expected, test.subsetsWithDup(nums));
    }

    @Test
    void test_allDuplicates() {
        Subsets2 test = new Subsets2();

        int[] nums = {2, 2, 2};

        List<List<Integer>> expected =
                List.of(List.of(), List.of(2), List.of(2, 2), List.of(2, 2, 2));

        assertSubsetsEqual(expected, test.subsetsWithDup(nums));
    }

    @Test
    void test_multipleDuplicateGroups() {
        Subsets2 test = new Subsets2();

        int[] nums = {1, 2, 2, 3, 3};

        List<List<Integer>> expected =
                List.of(
                        List.of(),
                        List.of(1),
                        List.of(2),
                        List.of(2, 2),
                        List.of(3),
                        List.of(3, 3),
                        List.of(1, 2),
                        List.of(1, 2, 2),
                        List.of(1, 3),
                        List.of(1, 3, 3),
                        List.of(2, 3),
                        List.of(2, 2, 3),
                        List.of(2, 3, 3),
                        List.of(2, 2, 3, 3),
                        List.of(1, 2, 3),
                        List.of(1, 2, 2, 3),
                        List.of(1, 2, 3, 3),
                        List.of(1, 2, 2, 3, 3));

        assertSubsetsEqual(expected, test.subsetsWithDup(nums));
    }

    @Test
    void test_unsortedInputStillAvoidsDuplicates() {
        Subsets2 test = new Subsets2();

        int[] nums = {2, 1, 2};

        List<List<Integer>> expected =
                List.of(
                        List.of(),
                        List.of(1),
                        List.of(2),
                        List.of(1, 2),
                        List.of(2, 2),
                        List.of(1, 2, 2));

        assertSubsetsEqual(expected, test.subsetsWithDup(nums));
    }

    @Test
    void test_noDuplicateSubsetsReturned() {
        Subsets2 test = new Subsets2();

        int[] nums = {1, 2, 2};

        List<List<Integer>> result = test.subsetsWithDup(nums);

        Set<List<Integer>> normalized = normalize(result);

        assertEquals(normalized.size(), result.size());
    }

    private void assertSubsetsEqual(List<List<Integer>> expected, List<List<Integer>> actual) {
        assertEquals(expected.size(), actual.size());
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
