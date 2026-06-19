package study.neetcode.interview.binarysearch;

/*
Given a sorted array of unique integers rotated at an unknown pivot, return the minimum element.
Examples:
nums = [3,4,5,1,2]
output = 1
nums = [4,5,6,7,0,1,2]
output = 0
nums = [11,13,15,17]
output = 11
 */
public class FindMinimumInRotatedSortedArray {

    public int findMin(int[] nums) {
        if (nums.length == 0) return -1;
        if (nums.length == 1) return nums[0];
        return findMin(nums, 0, nums.length - 1);
    }

    int findMin(int[] nums, int low, int high) {
        if (low > high) return -1;
        if (low == high) return nums[low];

        int mid = low + (high - low) / 2;

        if (nums[mid] > nums[high]) {
            return findMin(nums, mid + 1, high);
        } else {
            return findMin(nums, low, mid);
        }
    }
}
