package study.neetcode.interview.intervals;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.PriorityQueue;

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
        Arrays.sort(intervals,(a,b)->Integer.compare(a[0],b[0]));
        int maxActiveIntervals = 0;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for(int i=0;i<intervals.length;i++){
            int start = intervals[i][0];
            int end = intervals[i][1];

            while (!minHeap.isEmpty() && minHeap.peek()<=start){
                minHeap.poll();
            }

            minHeap.offer(end);

            maxActiveIntervals = Math.max(maxActiveIntervals,minHeap.size());
        }
        return maxActiveIntervals;
    }
}
