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
        int low = 0;
        int high = nums.length - 1;

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] > nums[high]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return nums[low];
    }
}
