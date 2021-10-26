package com.Leetcode;

import java.util.Arrays;
import java.util.HashSet;

public class ArrayPairSum {
    public static void main(String[] args) {
        int[] a={1,4,-1,5,0,-1};
        System.out.println((new ArrayPairSum()).arrayPairSum(a));
        System.out.println((new ArrayPairSum()).smallestSubsequence("cbacdcbc"));
    }

    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int res = 0;
        for (int i = 0;i < nums.length;i = i + 2){
            res += nums[i];
        }
        return res;
    }
    public String smallestSubsequence(String s) {
        char[] c = s.toCharArray();
        int[] set = new int[26];  // 只有小写字符
        StringBuilder sb = new StringBuilder();
        for(char i : c){
            if (set[i - 'a'] == 0){
                set[i - 'a'] = 1;
                sb.append(i);
            } else {
                continue;
            }
        }

        for(int i = 0 ; i < set.length; i ++){
            if(set[i] == 1){
                sb.append((char)(i + 'a'));
            }
        }
        return sb.reverse().toString();
    }
}
