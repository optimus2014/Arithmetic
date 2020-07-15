package com.Structure.Link.LeetCode;

import com.Structure.Link.Learning.LinkNode;

/**
 * 链表相关的LeetCode题目
 */
public class LinkLeetCode {
    public static void main(String[] args) {
        testCheckPlalindrome();
    }

    /**
     *判断一个单链表是否是回文链表
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
}
