package study.neetcode.interview.stack;

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
        int[][] cars = new int[position.length][2];
        for (int i = 0; i < position.length; i++) {
            cars[i][0] = position[i];
            cars[i][1] = speed[i];
        }

        Arrays.sort(cars, (a, b) -> Integer.compare(b[0], a[0]));
        Deque<Double> stack = new ArrayDeque<>();
        double maxTimeOfCarInFront = 0;
        for (int[] car : cars) {
            int pos = car[0];
            int s = car[1];

            double timeToTarget = (double) (target - pos) / s;

            if (timeToTarget > maxTimeOfCarInFront) {
                maxTimeOfCarInFront = timeToTarget;
                stack.push(timeToTarget);
            }
        }
        return stack.size();
    }
}
