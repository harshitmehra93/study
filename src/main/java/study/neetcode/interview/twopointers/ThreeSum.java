package study.neetcode.interview.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Next roadmap problem:

80. 3Sum — Two Pointers

Given an integer array nums, return all unique triplets:

[nums[i], nums[j], nums[k]]

such that:

i != j
i != k
j != k
nums[i] + nums[j] + nums[k] == 0

The solution set must not contain duplicate triplets.

Example 1
Input: nums = [-1,0,1,2,-1,-4]
Output: [[-1,-1,2],[-1,0,1]]
Example 2
Input: nums = [0,1,1]
Output: []
 */
public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        Arrays.sort(nums);
        for(int i=0;i<nums.length-2;i++){
            if(i>0&&nums[i-1]==nums[i]) continue;

            int left = i+1;
            int right = nums.length-1;

            while (left<right){
                int sum = nums[left]+ nums[right];
                if(sum<-nums[i]){
                    left++;
                }else if (sum>-nums[i]){
                    right--;
                }else if (sum==-nums[i]){
                    results.add(List.of(nums[i],nums[left],nums[right]));

                    left++;
                    right--;

                    while (left<right && nums[left]== nums[left-1]){
                        left++;
                    }
                    while (left<right && nums[right]== nums[right+1]){
                        right--;
                    }
                }
            }

        }
        return results;
    }

}
