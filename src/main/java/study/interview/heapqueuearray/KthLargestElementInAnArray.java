package study.interview.heapqueuearray;

import java.util.PriorityQueue;

/*
Kth Largest Element in an Array

Given an integer array nums and an integer k, return the kth largest element in the array.

Important: it is the kth largest in sorted order, not the kth distinct element.

Example 1
nums = [3, 2, 1, 5, 6, 4]
k = 2

Output:

5

Explanation:

Sorted descending:

[6, 5, 4, 3, 2, 1]

The 2nd largest is 5.

Example 2
nums = [3, 2, 3, 1, 2, 4, 5, 5, 6]
k = 4

Output:

4

Sorted descending:

[6, 5, 5, 4, 3, 3, 2, 2, 1]

The 4th largest is 4.

Method signature
public int findKthLargest(int[] nums, int k)
 */
public class KthLargestElementInAnArray {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();

        for (int num : nums) {
            heap.offer(num);

            if (heap.size() > k) heap.poll();
        }

        return heap.poll();
    }
}
