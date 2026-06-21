package study.interview.graph;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CheapestFlightWithinKStopsTest {

    @Test
    void test_givenExample() {
        CheapestFlightWithinKStops test = new CheapestFlightWithinKStops();

        int[][] flights =
                new int[][] {
                    {0, 1, 100},
                    {1, 2, 100},
                    {2, 0, 100},
                    {1, 3, 600},
                    {2, 3, 200}
                };

        assertEquals(700, test.findCheapestPrice(4, flights, 0, 3, 1));
    }

    @Test
    void test_cheaperPathUsesTooManyStops_soChooseDirect() {
        CheapestFlightWithinKStops test = new CheapestFlightWithinKStops();

        int[][] flights =
                new int[][] {
                    {0, 1, 100},
                    {1, 2, 100},
                    {2, 3, 100},
                    {0, 3, 500}
                };

        // 0 -> 1 -> 2 -> 3 costs 300 but has 2 stops: 1 and 2.
        // k = 1 allows at most one stop, so must choose direct 0 -> 3.
        assertEquals(500, test.findCheapestPrice(4, flights, 0, 3, 1));
    }

    @Test
    void test_cheaperPathAllowedWhenKIsLargeEnough() {
        CheapestFlightWithinKStops test = new CheapestFlightWithinKStops();

        int[][] flights =
                new int[][] {
                    {0, 1, 100},
                    {1, 2, 100},
                    {2, 3, 100},
                    {0, 3, 500}
                };

        // k = 2 allows stops 1 and 2, so cheaper path is valid.
        assertEquals(300, test.findCheapestPrice(4, flights, 0, 3, 2));
    }

    @Test
    void test_noRouteWithinKStops_returnsMinusOne() {
        CheapestFlightWithinKStops test = new CheapestFlightWithinKStops();

        int[][] flights =
                new int[][] {
                    {0, 1, 100},
                    {1, 2, 100},
                    {2, 3, 100}
                };

        // Route exists with 2 stops, but k = 1 disallows it.
        assertEquals(-1, test.findCheapestPrice(4, flights, 0, 3, 1));
    }

    @Test
    void test_directFlightOnlyWhenKZero() {
        CheapestFlightWithinKStops test = new CheapestFlightWithinKStops();

        int[][] flights =
                new int[][] {
                    {0, 1, 100},
                    {1, 2, 100},
                    {0, 2, 500}
                };

        // k = 0 means no intermediate stops.
        // Only direct 0 -> 2 is allowed.
        assertEquals(500, test.findCheapestPrice(3, flights, 0, 2, 0));
    }

    @Test
    void test_kZeroNoDirectFlight_returnsMinusOne() {
        CheapestFlightWithinKStops test = new CheapestFlightWithinKStops();

        int[][] flights =
                new int[][] {
                    {0, 1, 100},
                    {1, 2, 100}
                };

        // k = 0 means direct flight only.
        assertEquals(-1, test.findCheapestPrice(3, flights, 0, 2, 0));
    }

    @Test
    void test_normalDijkstraTrap_moreExpensiveToIntermediateButValidForStops() {
        CheapestFlightWithinKStops test = new CheapestFlightWithinKStops();

        int[][] flights =
                new int[][] {
                    {0, 1, 5},
                    {0, 2, 1},
                    {2, 1, 1},
                    {1, 3, 1},
                    {2, 3, 100}
                };

        /*
         * Cheapest way to reach 1 is 0 -> 2 -> 1 with cost 2,
         * but that already uses one stop: 2.
         *
         * With k = 1, path 0 -> 2 -> 1 -> 3 is invalid because it has 2 stops.
         *
         * Valid cheapest path is:
         * 0 -> 1 -> 3 = 6
         *
         * This is the trap: plain dist[node] can discard a more expensive route
         * to the same node that uses fewer stops.
         */
        assertEquals(6, test.findCheapestPrice(4, flights, 0, 3, 1));
    }

    @Test
    void test_sourceEqualsDestination_returnsZero() {
        CheapestFlightWithinKStops test = new CheapestFlightWithinKStops();

        int[][] flights =
                new int[][] {
                    {0, 1, 100},
                    {1, 2, 100}
                };

        assertEquals(0, test.findCheapestPrice(3, flights, 0, 0, 1));
    }
}
