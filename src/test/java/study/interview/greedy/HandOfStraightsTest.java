package study.interview.greedy;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class HandOfStraightsTest {

    @Test
    void example1CanFormGroups() {
        HandOfStraights solution = new HandOfStraights();

        int[] hand = {1, 2, 3, 6, 2, 3, 4, 7, 8};

        assertTrue(solution.isNStraightHand(hand, 3));
    }

    @Test
    void example2CannotDivideEvenly() {
        HandOfStraights solution = new HandOfStraights();

        int[] hand = {1, 2, 3, 4, 5};

        assertFalse(solution.isNStraightHand(hand, 4));
    }

    @Test
    void singleCardGroupsAlwaysPossible() {
        HandOfStraights solution = new HandOfStraights();

        int[] hand = {5, 1, 9, 3};

        assertTrue(solution.isNStraightHand(hand, 1));
    }

    @Test
    void exactOneGroupPossible() {
        HandOfStraights solution = new HandOfStraights();

        int[] hand = {1, 2, 3, 4};

        assertTrue(solution.isNStraightHand(hand, 4));
    }

    @Test
    void exactOneGroupMissingCard() {
        HandOfStraights solution = new HandOfStraights();

        int[] hand = {1, 2, 4, 5};

        assertFalse(solution.isNStraightHand(hand, 4));
    }

    @Test
    void duplicatesFormMultipleGroups() {
        HandOfStraights solution = new HandOfStraights();

        int[] hand = {1, 1, 2, 2, 3, 3};

        assertTrue(solution.isNStraightHand(hand, 3));
    }

    @Test
    void duplicatesButMissingEnoughMiddleCards() {
        HandOfStraights solution = new HandOfStraights();

        int[] hand = {1, 1, 2, 3, 3, 4};

        assertFalse(solution.isNStraightHand(hand, 3));
    }

    @Test
    void unsortedInputStillWorks() {
        HandOfStraights solution = new HandOfStraights();

        int[] hand = {8, 3, 2, 7, 6, 3, 2, 1, 4};

        assertTrue(solution.isNStraightHand(hand, 3));
    }

    @Test
    void gapInCardsFails() {
        HandOfStraights solution = new HandOfStraights();

        int[] hand = {1, 2, 3, 5, 6, 7, 9};

        assertFalse(solution.isNStraightHand(hand, 3));
    }

    @Test
    void smallestCardMustStartAGroup() {
        HandOfStraights solution = new HandOfStraights();

        int[] hand = {1, 2, 3, 4, 6, 7, 8, 9};

        assertTrue(solution.isNStraightHand(hand, 4));
    }

    @Test
    void cannotUseLargeCardsToFixMissingSmallSequence() {
        HandOfStraights solution = new HandOfStraights();

        int[] hand = {1, 2, 4, 5, 6, 7};

        assertFalse(solution.isNStraightHand(hand, 3));
    }

    @Test
    void multipleSeparatedGroups() {
        HandOfStraights solution = new HandOfStraights();

        int[] hand = {10, 11, 12, 20, 21, 22};

        assertTrue(solution.isNStraightHand(hand, 3));
    }
}
