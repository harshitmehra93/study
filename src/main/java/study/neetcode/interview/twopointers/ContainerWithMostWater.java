package study.neetcode.interview.twopointers;

/*
Container With Most Water

You are given an integer array height, where height[i] represents the height of a vertical line at index i.

Choose two lines such that, together with the x-axis, they form a container that holds the most water.

Return the maximum amount of water a container can store.

Example
Input: height = [1,8,6,2,5,4,8,3,7]
Output: 49

Explanation: choose height 8 at index 1 and height 7 at index 8.

Width:

8 - 1 = 7

Height limited by the shorter line:

min(8, 7) = 7

Area:

7 * 7 = 49
 */
public class ContainerWithMostWater {
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int max = 0;
        while (left < right) {
            int w = right - left;
            int h = Math.min(height[left], height[right]);
            int area = w * h;
            max = Math.max(max, area);

            if (height[left] <= height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return max;
    }
}
