package com.Leetcode;

import java.util.HashMap;

public class LeetcodeDemo {
    public static void main(String[] args) {
        LeetcodeDemo.show(LeetcodeDemo.twoSum(new int[]{3,2,4},6));
    }
    public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        map.put(nums[0],0);
        for(int i = 1; i < nums.length ; i ++){
            int diff = target - nums[i];
            if (map.containsKey(diff)){
                return new int[]{map.get(diff),i};
            } else {
                map.put(nums[i],i);
            }
        }
        return new int[]{-1,-1};
    }

    private static void show(int[] data){
        for(int d : data){
            System.out.print(d + "-> ");
        }
    }
}
