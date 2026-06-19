package study.neetcode.interview.binarysearch;

/*
Search in Rotated Sorted Array
You are given an integer array nums sorted in ascending order, but then rotated at an unknown pivot.
Example:
Original: [0,1,2,4,5,6,7]
Rotated:  [4,5,6,7,0,1,2]
Given nums and an integer target, return the index of target if it exists, otherwise return -1.
You must write an algorithm with O(log n) runtime complexity.
Examples:
Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4
Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1
 */
public class SearchInRotatedSortedArray {

    public int search(int[] nums, int target) {
        return binarySearch(nums, target, 0, nums.length - 1);
    }

    private int binarySearch(int[] nums, int target, int low, int high) {
        if (low > high) return -1;
        int mid = low + (high - low) / 2;

        if (nums[mid] == target) return mid;

        if (isSorted(nums, low, mid)) {
            if (target >= nums[low] && target <= nums[mid]) {
                return binarySearch(nums, target, low, mid - 1);
            } else {
                return binarySearch(nums, target, mid + 1, high);
            }
        } else {
            if (target >= nums[mid] && target <= nums[high]) {
                return binarySearch(nums, target, mid + 1, high);
            } else {
                return binarySearch(nums, target, low, mid - 1);
            }
        }
    }

    private boolean isSorted(int[] nums, int low, int high) {
        return low <= high && nums[high] >= nums[low];
    }
}
