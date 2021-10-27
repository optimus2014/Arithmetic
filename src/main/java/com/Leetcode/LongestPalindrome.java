package com.Leetcode;

import java.math.BigInteger;
import java.util.*;

public class LongestPalindrome {
    public static void main(String[] args) {
        int res  = (new LongestPalindrome()).longestPalindrome1("1asdhasjkd");
        System.out.println(res);


        System.out.println((new LongestPalindrome()).removeDuplicateLetters("ecbacba"));
        System.out.println((new LongestPalindrome()).join(999999998,999999997));

        int[] data = {999999998,999999997,999999999};
        System.out.println((new LongestPalindrome()).largestNumber(data));

        System.out.println((new LongestPalindrome()).largestNumber(new int[]{0,0}));
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

    // 最小字符序
    // 字典序是啥意思？
    public String removeDuplicateLetters(String s) {
        char[] chars = s.toCharArray();
        int[] index = new int[26];
        boolean[] flag = new boolean[26];
        int len = s.length();
        // 记录每个字符的最大下标
        for(int i = 0; i < len ; i ++){
            index[chars[i] - 'a'] = i;
        }

        Deque<Character> stack = new ArrayDeque<Character>();
        for(int i = 0; i < len ; i ++){
            // 如果标记已经在stack栈中存在，跳过
            if (flag[chars[i] - 'a']){
                continue;
            }

            // TODO:这句没看懂
            /**
             * 字典序，就是字符按照a~z排列，当看到栈中元素比当前元素“大0”的时候，判断后续还有没有这个元素，有的话就剔除
             * 删除栈中失效元素,使用peekLast()取出最后的元素，但是不删除栈顶元素
             */
            while(!stack.isEmpty() && stack.peekLast() > chars[i] && index[stack.peekLast() - 'a'] > i){
//                System.out.println(stack);
                Character tmp = stack.removeLast();
                flag[tmp - 'a'] = false;
            }

            stack.add(chars[i]);
            flag[chars[i] - 'a'] = true;
        }
        StringBuilder sb = new StringBuilder();
        for(char c:stack){
            sb.append(c);
        }
        return sb.toString();
    }

    public String largestNumber(int[] nums) {
        ArrayList<Integer> al = new ArrayList<Integer>();
        for (int i = 0; i < nums.length;i ++){
            int index = al.size();
            // 插入排序
            for(int j = 0; j < al.size(); j ++){
                // 找到具体位置
                if(join(nums[i],al.get(j)) < join(al.get(j),nums[i])){
                    index = j;
                    break;
                }
            }

            // 添加数值到指定的index位置
            al.add(index,nums[i]);
        }

        StringBuilder sb = new StringBuilder();

        int index = al.size() - 1;
        while(al.get(index) <= 0 && index >= 1){
            index --;
        }
        for (int i = index; i >= 0 ; i --){
            sb.append(al.get(i));
        }
        return sb.toString();
    }

    private Long join(long a, long b){
        // 计算b 有多少位
        int len = 10;
        Long tmp = (long)b;
        while(b >= 10){
            len = len * 10;
            b = b / 10;
        }
        return (Long)(a * len + tmp);
    }

    private boolean compare(int a,int b){
        String a_tmp = String.valueOf(a);
        String b_tmp = String.valueOf(b);
        boolean flag = true;
        int a_index = 0;
        int b_index = 0;

        while (a_index < a_tmp.length() && b_index < b_tmp.length()){
            if (a_tmp.charAt(a_index) < b_tmp.charAt(b_index)){
                return true;
            }
        }
        return flag;
    }
}
