package study.interview.greedy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class GasStationTest {

    private final GasStation solution = new GasStation();

    @Test
    void exampleWithValidStart() {
        int[] gas = {1, 2, 3, 4, 5};
        int[] cost = {3, 4, 5, 1, 2};

        assertEquals(3, solution.canCompleteCircuit(gas, cost));
    }

    @Test
    void exampleWithNoValidStart() {
        int[] gas = {2, 3, 4};
        int[] cost = {3, 4, 3};

        assertEquals(-1, solution.canCompleteCircuit(gas, cost));
    }

    @Test
    void singleStationCanFinishWithExactlyZeroFuel() {
        int[] gas = {1};
        int[] cost = {1};

        assertEquals(0, solution.canCompleteCircuit(gas, cost));
    }

    @Test
    void singleStationDoesNotHaveEnoughFuel() {
        int[] gas = {1};
        int[] cost = {2};

        assertEquals(-1, solution.canCompleteCircuit(gas, cost));
    }

    @Test
    void failedCandidatesDeficitDoesNotCarryIntoNextCandidate() {
        int[] gas = {0, 6, 5};
        int[] cost = {10, 0, 0};

        assertEquals(1, solution.canCompleteCircuit(gas, cost));
    }

    @Test
    void maximumImmediateSurplusNeedNotBeTheValidStart() {
        int[] gas = {3, 0, 2};
        int[] cost = {1, 3, 1};

        assertEquals(2, solution.canCompleteCircuit(gas, cost));
    }
}
