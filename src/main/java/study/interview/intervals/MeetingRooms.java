package study.interview.intervals;

import java.util.Arrays;

/*
Meeting Rooms — Intervals

Problem

Given an array of meeting time intervals:

intervals[i] = [start, end]

Determine if a person can attend all meetings.

Example 1:

intervals = [[0,30],[5,10],[15,20]]
Output = false

Because [0,30] overlaps with both.

Example 2:

intervals = [[7,10],[2,4]]
Output = true

 */
public class MeetingRooms {

    public boolean canAttendMeetings(int[][] intervals) {
        if (intervals.length == 0) return true;
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        int[] previous = intervals[0];
        int i = 1;
        while (i < intervals.length) {
            int[] current = intervals[i];
            if (current[0] < previous[1]) return false;
            previous = current;
            i++;
        }
        return true;
    }
}
