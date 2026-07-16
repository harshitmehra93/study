package study.interview.intervals;

import java.util.Arrays;
import java.util.PriorityQueue;

/*
Minimum Interval to Include Each Query

Problem

You are given:

intervals[i] = [left, right]
queries[j] = q

For each query q, return the size of the smallest interval that contains q.

Interval size is:

right - left + 1

If no interval contains the query, return -1.

Example 1
intervals = [[1,4],[2,4],[3,6],[4,4]]
queries = [2,3,4,5]

Output:

[3,3,1,4]

Explanation:

query 2 is contained in [1,4] size 4 and [2,4] size 3 -> answer 3
query 3 is contained in [1,4] size 4, [2,4] size 3, [3,6] size 4 -> answer 3
query 4 is contained in [1,4], [2,4], [3,6], [4,4] -> smallest is [4,4] size 1
query 5 is contained in [3,6] size 4 -> answer 4
Example 2
intervals = [[2,3],[2,5],[1,8],[20,25]]
queries = [2,19,5,22]

Output:

[2,-1,4,6]
 */
public class MinimumIntervalToIncludeEachQuery {
    public int[] minInterval(int[][] intervals, int[] queries) {
        Arrays.sort(intervals, (i1, i2) -> i1[0] - i2[0]);
        int queriesSorted[][] = new int[queries.length][2];
        for (int i = 0; i < queries.length; i++) {
            queriesSorted[i][0] = queries[i];
            queriesSorted[i][1] = i;
        }
        Arrays.sort(queriesSorted, (i1, i2) -> i1[0] - i2[0]);

        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> getSize(a) - getSize(b));
        int index = 0;
        int[] result = new int[queries.length];
        for (int i = 0; i < queriesSorted.length; i++) {
            int current = queriesSorted[i][0];
            while (index < intervals.length && current >= intervals[index][0]) {
                minHeap.offer(intervals[index]);
                index++;
            }

            while (minHeap.size() > 0 && minHeap.peek()[1] < current) {
                minHeap.poll();
            }

            var min = minHeap.peek();
            int resIndex = queriesSorted[i][1];
            if (min != null && min[0] <= current && min[1] >= current) {
                result[resIndex] = getSize(min);
            } else {
                result[resIndex] = -1;
            }
        }
        return result;
    }

    private int getSize(int[] interval) {
        return interval[1] - interval[0] + 1;
    }
}
