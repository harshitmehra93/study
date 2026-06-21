package study.interview.intervals;

import java.util.Arrays;

/*
Minimum Number of Arrows to Burst Balloons

You are given balloons as intervals:

points[i] = [start, end]

One arrow shot at position x bursts every balloon where:

start <= x <= end

Return the minimum number of arrows needed to burst all balloons.

Example:

points = [[10,16],[2,8],[1,6],[7,12]]

Output:

2

Because:

[1,6] and [2,8] overlap → shoot at 6
[7,12] and [10,16] overlap → shoot at 12
 */
public class MinimumNumberOfArrowsToBurstBalloons {

    public int findMinArrowShots(int[][] intervals) {
        if (intervals.length == 0) return 0;
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));
        int arrows = 1;
        int shootingPosition = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            int start = intervals[i][0];
            int end = intervals[i][1];

            if (start <= shootingPosition) {
                continue;
            }

            arrows++;
            shootingPosition = end;
        }
        return arrows;
    }
}
