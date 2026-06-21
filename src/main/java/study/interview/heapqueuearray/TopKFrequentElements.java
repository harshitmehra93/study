package study.interview.heapqueuearray;

import java.util.*;

/*
Top K Frequent Elements

Given an integer array nums and an integer k, return the k most frequent elements.

You may return the answer in any order.

Example 1
nums = [1, 1, 1, 2, 2, 3]
k = 2

Output:

[1, 2]

Because:

1 appears 3 times
2 appears 2 times
3 appears 1 time

The top 2 frequent elements are 1 and 2.

Example 2
nums = [1]
k = 1

Output:

[1]
Method signature
public int[] topKFrequent(int[] nums, int k)
 */
public class TopKFrequentElements {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> frequency = new HashMap<>();
        for (int num : nums) {
            if (!frequency.containsKey(num)) frequency.put(num, 0);
            frequency.put(num, frequency.get(num) + 1);
        }
        Comparator<Integer> comp = Comparator.comparingInt(frequency::get);
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(comp);
        for (int num : frequency.keySet()) {
            minHeap.offer(num);
            if (minHeap.size() > k) minHeap.poll();
        }
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = minHeap.poll();
        }
        return result;
    }
}
