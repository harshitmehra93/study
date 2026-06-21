package study.interview.mathgeometrybit;

/*
Given a non-empty integer array nums, every element appears twice except for one element that appears once. Return the element that appears once.
You must solve it in:
Time: O(n)
Space: O(1)
Examples:
nums = [2,2,1]
output = 1
nums = [4,1,2,1,2]
output = 4
nums = [1]
output = 1
 */
public class SingleNumber {
    public int singleNumber(int[] nums) {
        int xor = nums[0];
        for (int i = 1; i < nums.length; i++) xor = xor ^ nums[i];
        return xor;
    }
}
