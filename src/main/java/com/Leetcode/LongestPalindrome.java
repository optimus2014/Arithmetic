package com.Leetcode;

import java.util.HashMap;

public class LongestPalindrome {
    public static void main(String[] args) {
        int res  = (new LongestPalindrome()).longestPalindrome1("1asdhasjkd");
        System.out.println(res);
    }

    public int longestPalindrome(String s) {
        int res = 0;
        HashMap<Character,Integer> hashMap = new HashMap<Character,Integer>();
        for(int i = 0; i < s.length(); i++){
            if(hashMap.containsKey(s.charAt(i))){
                hashMap.put(s.charAt(i), hashMap.get(s.charAt(i)) + 1);
            } else {
                hashMap.put(s.charAt(i), 1);
            }
        }
        int flag = 0;  // 奇偶标识位

        // 遍历hashmap的值
        for (Integer value : hashMap.values()){
            if(value % 2 == 0){
                res += value;
            } else {
                flag = 1;
                if (value > 1) {
                    res += value - 1;
                }
            }
        }

        return res + flag;
    }

    // 使用数组
    public int longestPalindrome1(String s) {
        int[] count = new int[128];
        for(int i = 0; i < s.length(); i++){
            count[s.charAt(i)] ++;
        }

        int res = 0;
        int flag = 0;
        for(int value :count){
            if(value % 2 == 0){
                res += value;
            } else {
                flag = 1;
                if (value > 1) {
                    res += value - 1;
                }
            }
        }
        return res + flag;
    }
}
