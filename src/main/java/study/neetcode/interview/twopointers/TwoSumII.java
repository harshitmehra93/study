package study.neetcode.interview.twopointers;
/*
Two Sum II — Input Array Is Sorted

You are given a 1-indexed array of integers numbers that is already sorted in non-decreasing order.

Find two numbers such that they add up to a specific target.

Return the indices of the two numbers as an integer array [index1, index2], where:

1 <= index1 < index2 <= numbers.length

There is exactly one solution, and you may not use the same element twice.

Example 1
Input: numbers = [2,7,11,15], target = 9
Output: [1,2]

Explanation:

2 + 7 = 9
 */
public class TwoSumII {
    public int[] twoSum(int[] numbers, int target) {
        int left = 0, right = numbers.length - 1;

        while (left < right) {
            System.out.printf("left=%d, right=%d", left, right);
            int sum = numbers[left] + numbers[right];
            if (sum < target) {
                left++;
            } else if (sum > target) {
                right--;
            } else if (sum == target) {
                return new int[] {left + 1, right + 1};
            }
        }
        return new int[] {};
    }
}
