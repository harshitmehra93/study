package study.neetcode.interview.twopointers;

/*
Trapping Rain Water — Two Pointers

Given n non-negative integers representing an elevation map, where the width of each bar is 1, compute how much water it can trap after raining.

Example 1
Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6

Visual intuition:

Bars trap water between taller boundaries.

Water trapped at each index depends on:

min(max height to the left, max height to the right) - current height
Example 2
Input: height = [4,2,0,3,2,5]
Output: 9
 */
public class TrappingRainWater {
    public int trap(int[] height) {
        int min = 0;
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        int water = 0;
        while (left < right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            int minWaterLevel = Math.min(leftMax, rightMax);
            if (leftMax <= rightMax) {
                water += minWaterLevel - height[left];
                left++;
            }
            if (rightMax < leftMax) {
                water += minWaterLevel - height[right];
                right--;
            }
        }
        return water;
    }
}
