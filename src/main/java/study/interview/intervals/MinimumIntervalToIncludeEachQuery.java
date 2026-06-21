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
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        int[][] queryToOldIndex = new int[queries.length][2];
        for (int i = 0; i < queries.length; i++) {
            queryToOldIndex[i][0] = queries[i];
            queryToOldIndex[i][1] = i;
        }
        Arrays.sort(queryToOldIndex, (a, b) -> Integer.compare(a[0], b[0]));

        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        int index = 0;
        int[] result = new int[queries.length];
        for (int[] queryToIndex : queryToOldIndex) {
            int query = queryToIndex[0];
            int oldIndex = queryToIndex[1];

            while (index < intervals.length && intervals[index][0] <= query) {
                minHeap.offer(
                        new int[] {
                            intervals[index][1] - intervals[index][0] + 1, intervals[index][1]
                        });
                index++;
            }

            while (!minHeap.isEmpty() && minHeap.peek()[1] < query) {
                minHeap.poll();
            }

            int minInterval = minHeap.isEmpty() ? -1 : minHeap.peek()[0];
            result[oldIndex] = minInterval;
        }
        return result;
    }
}
