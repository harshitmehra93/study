package study.neetcode.interview.heapqueuearray;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

class KClosestPointsToOriginTest {

    @Test
    void test_givenExample1() {
        KClosestPointsToOrigin test = new KClosestPointsToOrigin();

        int[][] points = {
            {1, 3},
            {-2, 2}
        };

        int[][] result = test.kClosest(points, 1);

        assertPointsEqualIgnoreOrder(new int[][] {{-2, 2}}, result);
    }

    @Test
    void test_givenExample2() {
        KClosestPointsToOrigin test = new KClosestPointsToOrigin();

        int[][] points = {
            {3, 3},
            {5, -1},
            {-2, 4}
        };

        int[][] result = test.kClosest(points, 2);

        assertPointsEqualIgnoreOrder(
                new int[][] {
                    {3, 3},
                    {-2, 4}
                },
                result);
    }

    @Test
    void test_kEqualsNumberOfPoints_returnsAllPoints() {
        KClosestPointsToOrigin test = new KClosestPointsToOrigin();

        int[][] points = {
            {1, 1},
            {2, 2},
            {3, 3}
        };

        int[][] result = test.kClosest(points, 3);

        assertPointsEqualIgnoreOrder(points, result);
    }

    @Test
    void test_pointsWithNegativeCoordinates() {
        KClosestPointsToOrigin test = new KClosestPointsToOrigin();

        int[][] points = {
            {-1, -1}, // distance 2
            {-2, -2}, // distance 8
            {3, 3}, // distance 18
            {0, -1} // distance 1
        };

        int[][] result = test.kClosest(points, 2);

        assertPointsEqualIgnoreOrder(
                new int[][] {
                    {0, -1},
                    {-1, -1}
                },
                result);
    }

    @Test
    void test_originPointIsClosest() {
        KClosestPointsToOrigin test = new KClosestPointsToOrigin();

        int[][] points = {
            {0, 0},
            {1, 1},
            {-1, -1}
        };

        int[][] result = test.kClosest(points, 1);

        assertPointsEqualIgnoreOrder(new int[][] {{0, 0}}, result);
    }

    @Test
    void test_tiedDistances_acceptAnyValidKPoints() {
        KClosestPointsToOrigin test = new KClosestPointsToOrigin();

        int[][] points = {
            {1, 1}, // distance 2
            {-1, 1}, // distance 2
            {1, -1}, // distance 2
            {5, 5} // distance 50
        };

        int[][] result = test.kClosest(points, 2);

        assertEquals(2, result.length);

        Set<String> validClosestPoints =
                Set.of(
                        pointKey(new int[] {1, 1}),
                        pointKey(new int[] {-1, 1}),
                        pointKey(new int[] {1, -1}));

        for (int[] point : result) {
            assertTrue(
                    validClosestPoints.contains(pointKey(point)),
                    "Unexpected point: " + Arrays.toString(point));
        }
    }

    @Test
    void test_resultContainsKPoints() {
        KClosestPointsToOrigin test = new KClosestPointsToOrigin();

        int[][] points = {
            {10, 10},
            {1, 1},
            {2, 2},
            {3, 3}
        };

        int[][] result = test.kClosest(points, 2);

        assertEquals(2, result.length);
    }

    private void assertPointsEqualIgnoreOrder(int[][] expected, int[][] actual) {
        Set<String> expectedSet =
                Arrays.stream(expected)
                        .map(KClosestPointsToOriginTest::pointKey)
                        .collect(Collectors.toSet());

        Set<String> actualSet =
                Arrays.stream(actual)
                        .map(KClosestPointsToOriginTest::pointKey)
                        .collect(Collectors.toSet());

        assertEquals(expectedSet, actualSet);
    }

    private static String pointKey(int[] point) {
        return point[0] + "," + point[1];
    }
}
