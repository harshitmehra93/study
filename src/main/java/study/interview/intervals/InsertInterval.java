package study.interview.intervals;

import java.util.ArrayList;
import java.util.List;

/*
Insert Interval — Intervals

This is the natural follow-up to Merge Intervals.

Given a list of non-overlapping intervals sorted by start time,
and a new interval,
insert the new interval into the list and merge if necessary.

Example:

intervals = [[1,3],[6,9]]
newInterval = [2,5]

Output = [[1,5],[6,9]]

Another example:

intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]]
newInterval = [4,8]

Output = [[1,2],[3,10],[12,16]]

 */
public class InsertInterval {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int i = 0;

        while (i < intervals.length && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }

        result.add(newInterval);

        while (i < intervals.length && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }

        while (i < intervals.length) {
            result.add(intervals[i]);
            i++;
        }

        return result.toArray(new int[result.size()][]);
    }
}
