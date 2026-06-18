package study.neetcode.interview.binarysearch;

/*
Binary Search
Given an array of integers nums sorted in ascending order, and an integer target, return the index of target if it exists in nums. Otherwise, return -1.
You must write an algorithm with O(log n) runtime complexity.
Example:
Input:
nums = [-1,0,3,5,9,12], target = 9
Output: 4
Example 2:
Input:
nums = [-1,0,3,5,9,12], target = 2
Output: -1
 */
public class BinarySearch {
    public int search(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            if (low > high) return -1;
            int middle = low + (high - low) / 2;

            if (target > nums[middle]) {
                low = middle + 1;
            }
            if (target < nums[middle]) {
                high = middle - 1;
            }
            if (target == nums[middle]) return middle;
        }
        return -1;
    }
}
