package study.interview.mathgeometrybit;

/*
Given an array nums containing n distinct numbers in the range [0, n], return the only number in the range that is missing from the array.
public int missingNumber(int[] nums)
Examples:
nums = [3,0,1]
n = 3
range = [0,1,2,3]
missing = 2
nums = [0,1]
n = 2
range = [0,1,2]
missing = 2
nums = [9,6,4,2,3,5,7,0,1]
n = 9
range = [0,1,2,3,4,5,6,7,8,9]
missing = 8
 */
public class MissingNumber {
    public int missingNumber(int[] nums) {
        int xor = nums.length;
        for (int i = 0; i < nums.length; i++) {
            xor = xor ^ i;
            xor = xor ^ nums[i];
        }
        return xor;
    }
}
