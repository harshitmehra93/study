package study.neetcode.interview.stack;

import java.util.*;

/*
Next Greater Element I

You are given two integer arrays:

nums1
nums2

nums1 is a subset of nums2.

For each element in nums1, find the next greater element in nums2.

The next greater element of x is the first greater number to the right of x in nums2.

If no greater number exists, return -1.

Example 1
nums1 = [4,1,2]
nums2 = [1,3,4,2]

Output:

[-1,3,-1]

Explanation:

4 in nums2 -> no greater element to its right -> -1
1 in nums2 -> next greater to right is 3
2 in nums2 -> no greater element to its right -> -1
Example 2
nums1 = [2,4]
nums2 = [1,2,3,4]

Output:

[3,-1]
 */
public class NextGreaterElementI {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> nextGreater = new HashMap<>();
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < nums2.length; i++) {
            while (!stack.isEmpty() && nums2[stack.peek()] < nums2[i]) {
                int index = stack.pop();
                nextGreater.put(nums2[index], nums2[i]);
            }

            stack.push(i);
        }

        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            result[i] = nextGreater.getOrDefault(nums1[i], -1);
        }
        return result;
    }
}
