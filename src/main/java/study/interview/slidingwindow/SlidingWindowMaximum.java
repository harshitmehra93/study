package study.interview.slidingwindow;

import java.util.*;

/*
Sliding Window Maximum - Decreasing Monotonic Deque

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
        Deque<Integer> dq = new ArrayDeque<>();
        int left = 0;
        int[] result = new int[nums.length - k + 1];
        int resIndex = 0;
        for (int right = 0; right < nums.length; right++) {
            while (dq.size() > 0 && nums[right] > nums[dq.peekLast()]) {
                dq.pollLast();
            }

            dq.offerLast(right);

            while (dq.peekFirst() < right - k + 1) {
                dq.pollFirst();
            }

            if (right - left + 1 == k) {
                result[resIndex] = (nums[dq.peekFirst()]);
                resIndex++;
                left++;
            }
        }
        return result;
    }
}
