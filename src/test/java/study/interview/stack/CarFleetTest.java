package study.interview.stack;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CarFleetTest {

    @Test
    void example1() {
        CarFleet solution = new CarFleet();

        int target = 12;
        int[] position = {10, 8, 0, 5, 3};
        int[] speed = {2, 4, 1, 1, 3};

        assertEquals(3, solution.carFleet(target, position, speed));
    }

    @Test
    void singleCarIsOneFleet() {
        CarFleet solution = new CarFleet();

        int target = 10;
        int[] position = {3};
        int[] speed = {3};

        assertEquals(1, solution.carFleet(target, position, speed));
    }

    @Test
    void allCarsMergeIntoOneFleet() {
        CarFleet solution = new CarFleet();

        int target = 100;
        int[] position = {0, 2, 4};
        int[] speed = {4, 2, 1};

        assertEquals(1, solution.carFleet(target, position, speed));
    }

    @Test
    void noCarsMerge() {
        CarFleet solution = new CarFleet();

        int target = 10;
        int[] position = {6, 8};
        int[] speed = {3, 2};

        assertEquals(2, solution.carFleet(target, position, speed));
    }

    @Test
    void fasterCarBehindCatchesSlowerCarAhead() {
        CarFleet solution = new CarFleet();

        int target = 10;
        int[] position = {6, 4};
        int[] speed = {1, 3};

        assertEquals(1, solution.carFleet(target, position, speed));
    }

    @Test
    void sameArrivalTimeFormsOneFleet() {
        CarFleet solution = new CarFleet();

        int target = 10;
        int[] position = {6, 8};
        int[] speed = {2, 1};

        assertEquals(1, solution.carFleet(target, position, speed));
    }

    @Test
    void carBehindSlowerDoesNotCatchAheadCar() {
        CarFleet solution = new CarFleet();

        int target = 10;
        int[] position = {8, 6};
        int[] speed = {1, 1};

        assertEquals(2, solution.carFleet(target, position, speed));
    }

    @Test
    void unsortedPositionsStillWork() {
        CarFleet solution = new CarFleet();

        int target = 20;
        int[] position = {6, 2, 17};
        int[] speed = {3, 9, 2};

        assertEquals(2, solution.carFleet(target, position, speed));
    }

    @Test
    void multipleFleetsMixedCase() {
        CarFleet solution = new CarFleet();

        int target = 15;
        int[] position = {0, 4, 8, 10};
        int[] speed = {2, 2, 1, 1};

        assertEquals(3, solution.carFleet(target, position, speed));
    }

    @Test
    void carNearTargetCanStillBeCaughtBeforeTarget() {
        CarFleet solution = new CarFleet();

        int target = 10;
        int[] position = {9, 8};
        int[] speed = {1, 2};

        assertEquals(1, solution.carFleet(target, position, speed));
    }

    @Test
    void fasterCarCatchesBetweenIntegerTicks() {
        CarFleet solution = new CarFleet();

        int target = 10;
        int[] position = {8, 5};
        int[] speed = {1, 3};

        assertEquals(1, solution.carFleet(target, position, speed));
    }
}
