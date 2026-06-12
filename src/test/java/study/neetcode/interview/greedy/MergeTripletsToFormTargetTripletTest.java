package study.neetcode.interview.greedy;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class MergeTripletsToFormTargetTripletTest {

    @Test
    void exampleCanFormTarget() {
        MergeTripletsToFormTargetTriplet solution = new MergeTripletsToFormTargetTriplet();

        int[][] triplets = {
            {2, 5, 3},
            {1, 8, 4},
            {1, 7, 5}
        };

        int[] target = {2, 7, 5};

        assertTrue(solution.mergeTriplets(triplets, target));
    }

    @Test
    void cannotFormBecauseRequiredCoordinateMissing() {
        MergeTripletsToFormTargetTriplet solution = new MergeTripletsToFormTargetTriplet();

        int[][] triplets = {
            {3, 4, 5},
            {4, 5, 6}
        };

        int[] target = {3, 2, 5};

        assertFalse(solution.mergeTriplets(triplets, target));
    }

    @Test
    void exactTargetExistsDirectly() {
        MergeTripletsToFormTargetTriplet solution = new MergeTripletsToFormTargetTriplet();

        int[][] triplets = {
            {1, 2, 3},
            {2, 7, 5},
            {2, 1, 1}
        };

        int[] target = {2, 7, 5};

        assertTrue(solution.mergeTriplets(triplets, target));
    }

    @Test
    void tripletWithValueGreaterThanTargetMustBeIgnored() {
        MergeTripletsToFormTargetTriplet solution = new MergeTripletsToFormTargetTriplet();

        int[][] triplets = {
            {2, 8, 5},
            {2, 7, 4},
            {1, 7, 5}
        };

        int[] target = {2, 7, 5};

        assertTrue(solution.mergeTriplets(triplets, target));
    }

    @Test
    void onlyOvershootingTripletsCannotBeUsed() {
        MergeTripletsToFormTargetTriplet solution = new MergeTripletsToFormTargetTriplet();

        int[][] triplets = {
            {2, 8, 5},
            {3, 7, 5},
            {2, 7, 6}
        };

        int[] target = {2, 7, 5};

        assertFalse(solution.mergeTriplets(triplets, target));
    }

    @Test
    void coordinatesCanComeFromDifferentTriplets() {
        MergeTripletsToFormTargetTriplet solution = new MergeTripletsToFormTargetTriplet();

        int[][] triplets = {
            {2, 1, 1},
            {1, 7, 1},
            {1, 1, 5}
        };

        int[] target = {2, 7, 5};

        assertTrue(solution.mergeTriplets(triplets, target));
    }

    @Test
    void allTripletsUsableButOneCoordinateNeverReached() {
        MergeTripletsToFormTargetTriplet solution = new MergeTripletsToFormTargetTriplet();

        int[][] triplets = {
            {2, 1, 1},
            {1, 6, 1},
            {1, 1, 5}
        };

        int[] target = {2, 7, 5};

        assertFalse(solution.mergeTriplets(triplets, target));
    }

    @Test
    void smallerTripletsAloneCannotReachTarget() {
        MergeTripletsToFormTargetTriplet solution = new MergeTripletsToFormTargetTriplet();

        int[][] triplets = {
            {1, 1, 1},
            {1, 2, 3},
            {2, 3, 4}
        };

        int[] target = {3, 4, 5};

        assertFalse(solution.mergeTriplets(triplets, target));
    }

    @Test
    void singleTripletCanFormTarget() {
        MergeTripletsToFormTargetTriplet solution = new MergeTripletsToFormTargetTriplet();

        int[][] triplets = {{3, 4, 5}};

        int[] target = {3, 4, 5};

        assertTrue(solution.mergeTriplets(triplets, target));
    }

    @Test
    void singleTripletCannotFormTarget() {
        MergeTripletsToFormTargetTriplet solution = new MergeTripletsToFormTargetTriplet();

        int[][] triplets = {{3, 4, 4}};

        int[] target = {3, 4, 5};

        assertFalse(solution.mergeTriplets(triplets, target));
    }
}
