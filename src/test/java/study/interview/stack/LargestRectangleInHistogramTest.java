package study.interview.stack;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class LargestRectangleInHistogramTest {

    private final LargestRectangleInHistogram solution = new LargestRectangleInHistogram();

    @Test
    void givenExample() {
        assertEquals(10, solution.largestRectangleArea(new int[] {2, 1, 5, 6, 2, 3}));
    }

    @Test
    void singleBar() {
        assertEquals(7, solution.largestRectangleArea(new int[] {7}));
    }

    @Test
    void allZeroes() {
        assertEquals(0, solution.largestRectangleArea(new int[] {0, 0, 0}));
    }

    @Test
    void zeroSplitsHistogramIntoIndependentSections() {
        assertEquals(6, solution.largestRectangleArea(new int[] {2, 3, 0, 2, 2, 2}));
    }

    @Test
    void allBarsHaveEqualHeight() {
        assertEquals(20, solution.largestRectangleArea(new int[] {5, 5, 5, 5}));
    }

    @Test
    void strictlyIncreasingHeightsRequireFinalStackDrain() {
        assertEquals(9, solution.largestRectangleArea(new int[] {1, 2, 3, 4, 5}));
    }

    @Test
    void strictlyDecreasingHeightsResolveAtEveryIndex() {
        assertEquals(9, solution.largestRectangleArea(new int[] {5, 4, 3, 2, 1}));
    }

    @Test
    void shortestBarSpansEntireHistogram() {
        assertEquals(6, solution.largestRectangleArea(new int[] {2, 1, 2, 1, 2, 1}));
    }

    @Test
    void poppedStartIndexMustBeInheritedByShorterBar() {
        assertEquals(8, solution.largestRectangleArea(new int[] {3, 4, 2, 3}));
    }

    @Test
    void optimumRectangleIsStrictlyInsideHistogram() {
        assertEquals(12, solution.largestRectangleArea(new int[] {1, 4, 4, 4, 1}));
    }

    @Test
    void adjacentTwoBars() {
        assertEquals(2, solution.largestRectangleArea(new int[] {2, 1}));
        assertEquals(2, solution.largestRectangleArea(new int[] {1, 2}));
    }

    @Test
    void largeValuesStayWithinProblemConstraints() {
        int[] heights = new int[100_000];
        java.util.Arrays.fill(heights, 10_000);

        assertEquals(1_000_000_000, solution.largestRectangleArea(heights));
    }

    @Test
    void exhaustiveSmallHistogramsMatchBruteForceOracle() {
        int length = 6;
        int heightChoices = 5;
        int combinations = (int) Math.pow(heightChoices, length);

        for (int encoded = 0; encoded < combinations; encoded++) {
            int[] heights = decodeHistogram(encoded, length, heightChoices);
            assertEquals(
                    bruteForceLargestArea(heights),
                    solution.largestRectangleArea(heights),
                    "Failed for " + java.util.Arrays.toString(heights));
        }
    }

    private int[] decodeHistogram(int encoded, int length, int heightChoices) {
        int[] heights = new int[length];
        for (int i = 0; i < length; i++) {
            heights[i] = encoded % heightChoices;
            encoded /= heightChoices;
        }
        return heights;
    }

    private int bruteForceLargestArea(int[] heights) {
        int maximumArea = 0;

        for (int left = 0; left < heights.length; left++) {
            int minimumHeight = Integer.MAX_VALUE;
            for (int right = left; right < heights.length; right++) {
                minimumHeight = Math.min(minimumHeight, heights[right]);
                maximumArea = Math.max(maximumArea, minimumHeight * (right - left + 1));
            }
        }

        return maximumArea;
    }
}
