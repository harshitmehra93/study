package study.neetcode.interview.backtracking;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;

class CombinationSumIITest {

    private final CombinationSumII solution = new CombinationSumII();

    @Test
    void exampleCase() {
        int[] candidates = {10, 1, 2, 7, 6, 1, 5};
        int target = 8;

        List<List<Integer>> expected =
                List.of(List.of(1, 1, 6), List.of(1, 2, 5), List.of(1, 7), List.of(2, 6));

        assertCombinationsEqual(expected, solution.combinationSum2(candidates, target));
    }

    @Test
    void handlesDuplicatesCorrectly() {
        int[] candidates = {2, 5, 2, 1, 2};
        int target = 5;

        List<List<Integer>> expected = List.of(List.of(1, 2, 2), List.of(5));

        assertCombinationsEqual(expected, solution.combinationSum2(candidates, target));
    }

    @Test
    void returnsEmptyWhenNoCombinationExists() {
        int[] candidates = {3, 4, 5};
        int target = 2;

        List<List<Integer>> expected = List.of();

        assertCombinationsEqual(expected, solution.combinationSum2(candidates, target));
    }

    @Test
    void singleElementMatchesTarget() {
        int[] candidates = {1};
        int target = 1;

        List<List<Integer>> expected = List.of(List.of(1));

        assertCombinationsEqual(expected, solution.combinationSum2(candidates, target));
    }

    @Test
    void singleElementDoesNotMatchTarget() {
        int[] candidates = {1};
        int target = 2;

        List<List<Integer>> expected = List.of();

        assertCombinationsEqual(expected, solution.combinationSum2(candidates, target));
    }

    @Test
    void allSameNumbersUseEachElementAtMostOnce() {
        int[] candidates = {1, 1, 1, 1};
        int target = 2;

        List<List<Integer>> expected = List.of(List.of(1, 1));

        assertCombinationsEqual(expected, solution.combinationSum2(candidates, target));
    }

    @Test
    void doesNotReuseSameElement() {
        int[] candidates = {1, 2};
        int target = 4;

        List<List<Integer>> expected = List.of();

        assertCombinationsEqual(expected, solution.combinationSum2(candidates, target));
    }

    @Test
    void handlesLargeIntegerValuesCorrectly() {
        int[] candidates = {1000, 1000, 1};
        int target = 1001;

        List<List<Integer>> expected = List.of(List.of(1, 1000));

        assertCombinationsEqual(expected, solution.combinationSum2(candidates, target));
    }

    private void assertCombinationsEqual(List<List<Integer>> expected, List<List<Integer>> actual) {
        assertEquals(normalize(expected), normalize(actual));
    }

    private List<List<Integer>> normalize(List<List<Integer>> combinations) {
        return combinations.stream()
                .map(list -> list.stream().sorted().toList())
                .sorted(
                        (a, b) -> {
                            int minSize = Math.min(a.size(), b.size());

                            for (int i = 0; i < minSize; i++) {
                                int comparison = Integer.compare(a.get(i), b.get(i));
                                if (comparison != 0) {
                                    return comparison;
                                }
                            }

                            return Integer.compare(a.size(), b.size());
                        })
                .toList();
    }
}
