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
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        Map<int[], Integer> overlaps = new HashMap<>();
        for (int i = 0; i < intervals.length; i++) {
            int j = i + 1;
            while (j < intervals.length && intervals[j][0] < intervals[i][1]) {
                overlaps.put(intervals[i], overlaps.getOrDefault(intervals[i], 0) + 1);
                overlaps.put(intervals[j], overlaps.getOrDefault(intervals[j], 0) + 1);
                j++;
            }
        }

        PriorityQueue<int[]> maxHeap =
                new PriorityQueue<>((a, b) -> Integer.compare(overlaps.get(b), overlaps.get(a)));
        for (int i = 0; i < intervals.length; i++) {
            maxHeap.offer(intervals[i]);
        }

        Set<int[]> removedIntervals = new HashSet<>();
        while (!isNonOverlappingIgnoringRemoved(intervals, removedIntervals)) {
            removedIntervals.add(maxHeap.poll());
        }
        return removedIntervals.size();
    }

    private boolean isNonOverlappingIgnoringRemoved(
            int[][] intervals, Set<int[]> removedIntervals) {

        for (int i = 0; i < intervals.length; i++) {
            if (removedIntervals.contains(intervals[i])) continue;
            int j = i+1;
            while (j < intervals.length) {
                if (removedIntervals.contains(intervals[j])) {
                    j++;
                    continue;
                }
                if (intervals[j][0] < intervals[i][1]) return false;
                j++;
            }
        }
        return true;
    }
}
