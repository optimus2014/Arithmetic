package com.String.Learning;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class LengthOfLongestSubString {
    public static void main(String[] args) {
        LengthOfLongestSubString lo = new LengthOfLongestSubString();
        System.out.println(lo.lengthOfLongestSubstring("asadastertda"));
        System.out.println(lo.lengthOfLongestSubstring(""));
        System.out.println(lo.lengthOfLongestSubstring("aaaa"));
    }

    /**
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     *
     * 示例 1:
     *
     * 输入: "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     *
     * 解题思路：
     * 双指针，滑动窗口
     * */
    public int lengthOfLongestSubstring(String s){

        if(null == s || s.length() == 0){
            return 0;
        }
        int result = 1;
        HashMap<Character,Integer> bucket = new HashMap<Character, Integer>();
        for(int left=0,right=0;right <s.length();right ++){
            if(bucket.containsKey(s.charAt(right))){
                left = Math.max(left,bucket.get(s.charAt(right)) + 1);
            }
            bucket.put(s.charAt(right),right);
            result = Math.max(result,right - left + 1);
        }
        return result;
    }
}
