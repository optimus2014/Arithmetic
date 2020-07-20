package com.LeetCode.String;

/**
 * 查找类的leetcode练习
 * 1. 查找第一个，和最后一个值等于给定值的元素
 * 2.
 */
public class SearchPractice {

    // 查找第一个，和最后一个值等于给定值的元素
    public int[] searchRange(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        if (low == high){
            if (nums[low] == target){
                return new int[]{low,high};
            } else {
                return new int[]{-1,-1};
            }
        }

        int result = -1;
        while(low <= high){
            int mid = low + ((high - low) >> 1);
            if(nums[mid] > target){
                high -= 1;
            } else if(nums[mid] < target){
                low += 1;
            } else {
                result = mid;
                break;
            }
        }

        low = result;
        high = result;
        if(result == -1){
            return new int[]{-1,-1};
        }else {
            // find low
            while (low > 0 && nums[low] == nums[low - 1]){
                low -= 1;
            }
            // find high
            while (high < nums.length - 1 && nums[high] == nums[high + 1]){
                high += 1;
            }
            return new int[]{low,high};
        }
    }
}
