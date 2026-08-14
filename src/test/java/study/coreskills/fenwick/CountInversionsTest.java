package study.coreskills.fenwick;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CountInversionsTest {

    private final CountInversions countInversions = new CountInversions();

    @Test
    void emptyArrayHasNoInversions() {
        assertEquals(0L, countInversions.countInversions(new int[0]));
    }

    @Test
    void singleElementHasNoInversions() {
        assertEquals(0L, countInversions.countInversions(new int[] {7}));
    }

    @Test
    void sortedArrayHasNoInversions() {
        assertEquals(0L, countInversions.countInversions(new int[] {1, 2, 3, 4}));
    }

    @Test
    void reverseSortedArrayCountsEveryPair() {
        assertEquals(6L, countInversions.countInversions(new int[] {4, 3, 2, 1}));
    }

    @Test
    void countsInversionsInExample() {
        assertEquals(4L, countInversions.countInversions(new int[] {5, 2, 6, 1}));
    }

    @Test
    void equalValuesDoNotCountAsInversions() {
        assertEquals(4L, countInversions.countInversions(new int[] {2, 1, 3, 1, 2}));
        assertEquals(0L, countInversions.countInversions(new int[] {1, 1, 1}));
    }

    @Test
    void supportsNegativeValues() {
        assertEquals(2L, countInversions.countInversions(new int[] {-1, -3, -2}));
    }

    @Test
    void doesNotModifyInput() {
        int[] nums = {5, 2, 6, 1};
        int[] original = nums.clone();

        countInversions.countInversions(nums);

        assertArrayEquals(original, nums);
    }
}
