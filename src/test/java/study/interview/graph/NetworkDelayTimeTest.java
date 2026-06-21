package study.interview.graph;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NetworkDelayTimeTest {

    @Test
    void test_givenExample() {
        NetworkDelayTime test = new NetworkDelayTime();

        int[][] times = {
            {2, 1, 1},
            {2, 3, 1},
            {3, 4, 1}
        };

        assertEquals(2, test.networkDelayTime(times, 4, 2));
    }

    @Test
    void test_singleEdge_allReachable() {
        NetworkDelayTime test = new NetworkDelayTime();

        int[][] times = {{1, 2, 1}};

        assertEquals(1, test.networkDelayTime(times, 2, 1));
    }

    @Test
    void test_singleEdge_notAllReachable() {
        NetworkDelayTime test = new NetworkDelayTime();

        int[][] times = {{1, 2, 1}};

        assertEquals(-1, test.networkDelayTime(times, 2, 2));
    }

    @Test
    void test_chooseShorterIndirectPath() {
        NetworkDelayTime test = new NetworkDelayTime();

        int[][] times = {
            {1, 2, 10},
            {1, 3, 1},
            {3, 2, 1}
        };

        assertEquals(2, test.networkDelayTime(times, 3, 1));
    }

    @Test
    void test_cycle_shouldStillFindShortestTimes() {
        NetworkDelayTime test = new NetworkDelayTime();

        int[][] times = {
            {1, 2, 1},
            {2, 3, 1},
            {3, 1, 1},
            {2, 4, 2}
        };

        assertEquals(3, test.networkDelayTime(times, 4, 1));
    }

    @Test
    void test_disconnectedNode_returnsMinusOne() {
        NetworkDelayTime test = new NetworkDelayTime();

        int[][] times = {
            {1, 2, 1},
            {2, 3, 2}
        };

        assertEquals(-1, test.networkDelayTime(times, 4, 1));
    }

    @Test
    void test_multipleOutgoingEdges_answerIsMaxShortestDistance() {
        NetworkDelayTime test = new NetworkDelayTime();

        int[][] times = {
            {1, 2, 5},
            {1, 3, 2},
            {1, 4, 1}
        };

        assertEquals(5, test.networkDelayTime(times, 4, 1));
    }

    @Test
    void test_startNodeOnly_whenNIsOne() {
        NetworkDelayTime test = new NetworkDelayTime();

        int[][] times = {};

        assertEquals(0, test.networkDelayTime(times, 1, 1));
    }

    @Test
    void test_parallelEdges_chooseSmallerWeight() {
        NetworkDelayTime test = new NetworkDelayTime();

        int[][] times = {
            {1, 2, 5},
            {1, 2, 2},
            {2, 3, 1}
        };

        assertEquals(3, test.networkDelayTime(times, 3, 1));
    }

    @Test
    void test_parallelEdges_chooseSmallerWeight_shouldFailCurrentImplementation() {
        NetworkDelayTime test = new NetworkDelayTime();

        int[][] times = {
            {1, 2, 10},
            {1, 2, 1},
            {2, 3, 1}
        };

        assertEquals(2, test.networkDelayTime(times, 3, 1));
    }
}
