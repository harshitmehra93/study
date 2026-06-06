package study.neetcode.interview.intervals;

import java.util.Arrays;

/*
Meeting Rooms II

Problem

Given meeting intervals:

intervals[i] = [start, end]

Return the minimum number of meeting rooms required.

Example:

intervals = [[0,30],[5,10],[15,20]]
Output = 2
 */
public class MeetingRooms2 {
    public int minMeetingRooms(int[][] intervals) {
        if (intervals.length == 0) return 0;
        if (intervals.length == 1) return 1;
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        int endTime = 0;
        for (int i = 0; i < intervals.length; i++) {
            endTime = Math.max(endTime, intervals[i][1]);
        }
        int maxOverlap = 0;
        for (int time = intervals[0][0]; time <= endTime; time++) {
            int overlap = 0;
            for (int i = 0; i < intervals.length; i++) {
                if (intervals[i][0] <= time && intervals[i][1] > time) {
                    overlap++;
                }
            }
            maxOverlap = Math.max(maxOverlap, overlap);
        }
        return maxOverlap;
    }
}
