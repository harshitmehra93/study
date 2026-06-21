package study.interview.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class KokoEatingBananasTest {

    private final KokoEatingBananas solution = new KokoEatingBananas();

    @Test
    void findsMinimumSpeedForExampleCase() {
        int[] piles = {3, 6, 7, 11};

        int result = solution.minEatingSpeed(piles, 8);

        assertEquals(4, result);
    }

    @Test
    void findsMinimumSpeedWhenHoursAreTight() {
        int[] piles = {30, 11, 23, 4, 20};

        int result = solution.minEatingSpeed(piles, 5);

        assertEquals(30, result);
    }

    @Test
    void findsMinimumSpeedWhenExtraHoursAreAvailable() {
        int[] piles = {30, 11, 23, 4, 20};

        int result = solution.minEatingSpeed(piles, 6);

        assertEquals(23, result);
    }

    @Test
    void handlesSinglePile() {
        int[] piles = {9};

        assertEquals(3, solution.minEatingSpeed(piles, 3));
        assertEquals(2, solution.minEatingSpeed(piles, 5));
        assertEquals(1, solution.minEatingSpeed(piles, 9));
    }

    @Test
    void returnsOneWhenOneBananaPerHourIsEnough() {
        int[] piles = {1, 1, 1, 1};

        int result = solution.minEatingSpeed(piles, 4);

        assertEquals(1, result);
    }

    @Test
    void usesCeilingDivisionPerPile() {
        int[] piles = {8, 15};

        int result = solution.minEatingSpeed(piles, 5);

        assertEquals(5, result);
    }
}
