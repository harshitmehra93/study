package study.interview.binarysearch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CapacityToShipPackagesWithinDDaysTest {

    private final CapacityToShipPackagesWithinDDays solution =
            new CapacityToShipPackagesWithinDDays();

    @Test
    void findsMinimumCapacityForExampleCase() {
        int[] weights = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        int result = solution.shipWithinDays(weights, 5);

        assertEquals(15, result);
    }

    @Test
    void handlesGroupedShipmentsAcrossDays() {
        int[] weights = {3, 2, 2, 4, 1, 4};

        int result = solution.shipWithinDays(weights, 3);

        assertEquals(6, result);
    }

    @Test
    void returnsMaxWeightWhenEachPackageCanShipOnItsOwnDay() {
        int[] weights = {1, 2, 3, 1, 1};

        int result = solution.shipWithinDays(weights, 4);

        assertEquals(3, result);
    }

    @Test
    void returnsTotalWeightWhenEverythingMustShipInOneDay() {
        int[] weights = {5, 8, 2, 4};

        int result = solution.shipWithinDays(weights, 1);

        assertEquals(19, result);
    }

    @Test
    void handlesSinglePackage() {
        int[] weights = {7};

        int result = solution.shipWithinDays(weights, 1);

        assertEquals(7, result);
    }

    @Test
    void preservesOriginalOrderWhenComputingDays() {
        int[] weights = {10, 50, 50, 10};

        int result = solution.shipWithinDays(weights, 2);

        assertEquals(60, result);
    }
}
