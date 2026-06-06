package study.neetcode.interview.intervals;

import java.util.*;

/*
Non-overlapping Intervals — Intervals

This is the next core interval pattern after Merge + Insert.

Problem

Given an array of intervals, return the minimum number of intervals you need to remove so that the
remaining intervals are non-overlapping.

Example:

intervals = [[1,2],[2,3],[3,4],[1,3]]

Output:

1

Because remove [1,3], and the rest do not overlap:

[1,2], [2,3], [3,4]

Another example:

intervals = [[1,2],[1,2],[1,2]]

Output:

2
 */
public class NonOverlappingIntervals {

    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) return 0;

        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        int[] previous = intervals[0];
        int removedIntervals = 0;

        for (int i = 1; i < intervals.length; i++) {
            int[] current = intervals[i];

            if (current[0] < previous[1]) {
                removedIntervals++;

                if (current[1] < previous[1]) {
                    previous = current;
                }
            } else {
                previous = current;
            }
        }

        return removedIntervals;
    }
}
