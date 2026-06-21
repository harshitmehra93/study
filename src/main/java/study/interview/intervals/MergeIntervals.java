package study.interview.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Merge Intervals

Given an array of intervals where:

intervals[i] = [start, end]

merge all overlapping intervals and return an array of the non-overlapping intervals that cover all the intervals in the input.

Example 1
Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
 */
public class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> result = new ArrayList<>();
        result.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            int[] current = intervals[i];
            int[] last = result.get(result.size() - 1);

            if (last[1] >= current[0]) {
                last[1] = Math.max(current[1], last[1]);
            } else {
                result.add(current);
            }
        }
        return result.toArray(new int[result.size()][]);
    }
}
