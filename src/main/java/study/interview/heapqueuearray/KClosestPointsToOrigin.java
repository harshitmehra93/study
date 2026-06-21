package study.interview.heapqueuearray;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
K Closest Points to Origin

You are given an array of points where each point is represented as:

[x, y]

You need to return the k points that are closest to the origin:

(0, 0)

The distance from a point [x, y] to the origin is:

sqrt(x² + y²)

But for comparison, you do not need the square root. You can compare using:

x² + y²

because square root preserves order.

Example 1
points = [[1, 3], [-2, 2]]
k = 1

Output:

[[-2, 2]]

Explanation:

Distance of [1,3]  = 1² + 3² = 10
Distance of [-2,2] = (-2)² + 2² = 8

So [-2, 2] is closer.

Example 2
points = [[3,3], [5,-1], [-2,4]]
k = 2

Output can be:

[[3,3], [-2,4]]

Explanation:

[3,3]  -> 18
[5,-1] -> 26
[-2,4] -> 20

The two closest are [3,3] and [-2,4].

Method signature
public int[][] kClosest(int[][] points, int k)
 */
public class KClosestPointsToOrigin {
    public int[][] kClosest(int[][] points, int k) {
        Comparator<int[]> comp = (p1, p2) -> Integer.compare(getDistance(p2), getDistance(p1));
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(comp);

        for (int[] point : points) {
            maxHeap.offer(point);
            if (maxHeap.size() > k) maxHeap.poll();
        }

        int[][] result = new int[k][2];
        for (int i = 0; i < k; i++) {
            int[] point = maxHeap.poll();
            result[i][0] = point[0];
            result[i][1] = point[1];
        }

        return result;
    }

    private int getDistance(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }
}
