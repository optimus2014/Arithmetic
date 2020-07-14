package com.String.Learning;

import com.Array.Learning.LinkNode;

import java.util.HashMap;

/**
 * LeetCode中关于String的练习题
 */
public class StringPractice {
    public static void main(String[] args) {
        testCheckPlalindrome();

    }


    /**
     *判断一个字符串（用单链表存储）是否是回文字符串
     * absadas
     * 要求：
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * 思路：
     * 反转链表
     */
    public static boolean checkPlalindrome(LinkNode head){
        if(head == null || head.next == null){
            return true;
        }
        // 1. 先找到中间位置
        LinkNode frist = head; // 一次走一步
        LinkNode secode = head ; // 一次走两步

        // 区分奇数、偶数情况
        while(secode.next != null){
            frist = frist.next;
            if(secode.next.next != null){
                // 奇数情况
                secode = secode.next.next;
            } else{
                break;
            }
        }
        // 此时frist是中间节点位置

        // 2.后半段链表逆置
        LinkNode tmpHead = LinkNode.reverseLink(frist);
        // 3.从头比对两个子链表，判断是否一致
        while(head != null && tmpHead != null){
            if(head.value != tmpHead.value){
                return false;
            }
            head = head.next;
            tmpHead = tmpHead.next;
        }
        return true;
    }

    public static void testCheckPlalindrome(){
        LinkNode head = LinkNode.createLink(new int[]{1,2,3,4,5,6,7});
        System.out.println("回文：" + checkPlalindrome(head));
        head = LinkNode.createLink(new int[]{1,2,3,4,5,4,3,2,1});
        System.out.println("回文：" + checkPlalindrome(head));
        System.out.println("回文：" + checkPlalindrome(LinkNode.createLink(new int[]{1,2})));
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
    public static int lengthOfLongestSubstring(String s){

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
