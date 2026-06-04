package study.neetcode.interview.slidingwindow;

import java.util.PriorityQueue;

/*
Sliding Window Maximum — Sliding Window / Monotonic Deque

Given an integer array nums and an integer k, return an array of the maximum value in each sliding window of size k.

Example
Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
Output: [3,3,5,5,6,7]

Windows:

[1,3,-1]   -> 3
[3,-1,-3]  -> 3
[-1,-3,5]  -> 5
[-3,5,3]   -> 5
[5,3,6]    -> 6
[3,6,7]    -> 7
 */
public class SlidingWindowMaximum {
    public int[] maxSlidingWindow(int[] nums, int k) {
        PriorityQueue<Integer> q = new PriorityQueue<>((n1, n2) -> Integer.compare(n2, n1));
        int left = 0;
        int[] result = new int[nums.length - k + 1];
        int i = 0;
        for (int right = 0; right < nums.length; right++) {
            q.offer(nums[right]);
            if (q.size() == k) {
                int max = q.peek();
                result[i] = max;
                i++;

                q.remove(nums[left]);
                left++;
            }
        }
        return result;
    }
}
