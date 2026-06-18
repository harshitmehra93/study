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
        return binarySearch(nums, target, 0, nums.length - 1);
    }

    private int binarySearch(int[] nums, int target, int low, int high) {
        if (low > high) return -1;
        int middle = low + (high - low) / 2;

        if (target > nums[middle]) {
            return binarySearch(nums, target, middle + 1, high);
        }
        if (target < nums[middle]) {
            return binarySearch(nums, target, low, middle - 1);
        }
        return middle;
    }
}
