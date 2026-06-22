package study.interview.stack;

import java.util.*;

/*
Car Fleet

You are given n cars going to the same destination.

You are given:

int target;
int[] position;
int[] speed;

Where:

position[i] = starting position of car i
speed[i]    = speed of car i
target      = destination position

A car can never pass another car ahead of it.

If a faster car catches up to a slower car before the destination, they become a fleet and move together at the slower car’s speed.

Return the number of car fleets that will arrive at the destination.

Example 1
target = 12
position = [10,8,0,5,3]
speed    = [2,4,1,1,3]

Output:

3

Explanation:

Car at 10 reaches target in 1 hour.
Car at 8 reaches target in 1 hour. It catches car at 10, so they form one fleet.

Car at 5 reaches target in 7 hours.

Car at 3 reaches target in 3 hours.
Car at 0 reaches target in 12 hours.

Final fleets = 3
Example 2
target = 10
position = [3]
speed = [3]

Output:

1
 */
public class CarFleet {
    public int carFleet(int target, int[] position, int[] speed) {
        double[][] timeToPosition = new double[position.length][2];
        for (int i = 0; i < position.length; i++) {
            int distanceLeft = target - position[i];
            timeToPosition[i][0] = (double) distanceLeft / speed[i];
            timeToPosition[i][1] = position[i];
        }
        Arrays.sort(timeToPosition, (a, b) -> Double.compare(a[1], b[1]));

        Deque<Double> stack = new ArrayDeque<>();
        for (int i = timeToPosition.length - 1; i >= 0; i--) {
            double currentTime = timeToPosition[i][0];
            if (stack.isEmpty() || currentTime > stack.peek()) {
                stack.push(currentTime); // new fleet
            }
        }
        return stack.size();
    }
}
