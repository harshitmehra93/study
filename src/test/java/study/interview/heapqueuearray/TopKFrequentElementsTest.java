package study.interview.heapqueuearray;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

class TopKFrequentElementsTest {

    @Test
    void test_givenExample() {
        TopKFrequentElements test = new TopKFrequentElements();

        int[] nums = {1, 1, 1, 2, 2, 3};

        assertArrayEqualsIgnoreOrder(new int[] {1, 2}, test.topKFrequent(nums, 2));
    }

    @Test
    void test_singleElement() {
        TopKFrequentElements test = new TopKFrequentElements();

        int[] nums = {1};

        assertArrayEqualsIgnoreOrder(new int[] {1}, test.topKFrequent(nums, 1));
    }

    @Test
    void test_kEqualsNumberOfUniqueElements() {
        TopKFrequentElements test = new TopKFrequentElements();

        int[] nums = {1, 1, 2, 2, 3};

        assertArrayEqualsIgnoreOrder(new int[] {1, 2, 3}, test.topKFrequent(nums, 3));
    }

    @Test
    void test_negativeNumbers() {
        TopKFrequentElements test = new TopKFrequentElements();

        int[] nums = {-1, -1, -1, -2, -2, -3};

        assertArrayEqualsIgnoreOrder(new int[] {-1, -2}, test.topKFrequent(nums, 2));
    }

    @Test
    void test_zeroAndNegativeNumbers() {
        TopKFrequentElements test = new TopKFrequentElements();

        int[] nums = {0, 0, 0, -1, -1, 2};

        assertArrayEqualsIgnoreOrder(new int[] {0, -1}, test.topKFrequent(nums, 2));
    }

    @Test
    void test_topOneFrequentElement() {
        TopKFrequentElements test = new TopKFrequentElements();

        int[] nums = {4, 4, 4, 5, 5, 6};

        assertArrayEqualsIgnoreOrder(new int[] {4}, test.topKFrequent(nums, 1));
    }

    @Test
    void test_frequenciesNotBasedOnValueOrder() {
        TopKFrequentElements test = new TopKFrequentElements();

        int[] nums = {10, 10, 1, 1, 1, 2};

        /*
         * Frequencies:
         * 1  -> 3
         * 10 -> 2
         * 2  -> 1
         *
         * Top 2 frequent = 1 and 10.
         */
        assertArrayEqualsIgnoreOrder(new int[] {1, 10}, test.topKFrequent(nums, 2));
    }

    @Test
    void test_tiedFrequencies_acceptAnyValidTopK() {
        TopKFrequentElements test = new TopKFrequentElements();

        int[] nums = {1, 1, 2, 2, 3, 3};

        /*
         * All have frequency 2.
         * Any 2 elements among {1, 2, 3} are valid.
         */
        int[] result = test.topKFrequent(nums, 2);

        assertEquals(2, result.length);

        for (int value : result) {
            assertTrue(value == 1 || value == 2 || value == 3);
        }

        assertEquals(2, Arrays.stream(result).distinct().count());
    }

    private void assertArrayEqualsIgnoreOrder(int[] expected, int[] actual) {
        int[] expectedCopy = Arrays.copyOf(expected, expected.length);
        int[] actualCopy = Arrays.copyOf(actual, actual.length);

        Arrays.sort(expectedCopy);
        Arrays.sort(actualCopy);

        assertArrayEquals(expectedCopy, actualCopy);
    }
}
