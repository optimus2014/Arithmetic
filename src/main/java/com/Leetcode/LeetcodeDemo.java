package com.Leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class LeetcodeDemo {
    public static void main(String[] args) {

        LeetcodeDemo.show(LeetcodeDemo.twoSum(new int[]{3,2,4},6));
        LeetcodeDemo.show(LeetcodeDemo.generateParenthesis(2));
        LeetcodeDemo.show(LeetcodeDemo.generateParenthesis(3));
        LeetcodeDemo.show(LeetcodeDemo.generateParenthesis(4));

        /**
         * "(())","()()"
         * ["((()))","(()())","(())()","()(())","()()()"]
         * ["(((())))","((()()))","((())())","((()))()","(()(()))","(()()())","(()())()","(())(())","(())()()","()((()))","()(()())","()(())()","()()(())","()()()()"]
         */

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
        System.out.println();
    }
    private static void show(List data){
        for(Object d : data){
            System.out.print(d + "-> ");
        }
        System.out.println();
    }


    public static List<String> generateParenthesis(int n) {
        // 归纳法
        LinkedList<String> result = new LinkedList<String>();
        result.add("()");
        for (int i = 2;i <= n ; i++){
            int len = result.size();
            for(int j = 0; j < len; j ++){
                result.add("(" + result.get(j) + ")");
                if(!(result.get(j) + "()").equals("()" + result.get(j))){
                    result.add("()" + result.get(j));
                    result.add(result.get(j) + "()");
                } else {
                    result.add(result.get(j) + "()");
                }

            }
            // 删除无效
            for(int j = 0; j < len; j ++){
                result.remove(0);
            }
        }
        return result;
    }
}
