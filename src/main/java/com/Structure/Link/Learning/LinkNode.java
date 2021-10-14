package com.Structure.Link.Learning;

/***
 * 链表数据结构：
 * 单链表
 * 双链表
 * 循环链表
 * 双向循环链表（不常用）
 *
 * 需要练习的操作：
 * 1.单链表反转：Done
 * 2.链表中环的检测:Done
 * 3.两个有序的链表合并:Done
 * 4.删除链表倒数第 n 个结点:获取倒数第n个结点使用快指针，先后置n位一个指针，再和头结点同步后移，快指针到尾部，头指针既是最终结果：Done；创建一个空头部(哨兵)，作为head的前置。
 * 5.求链表的中间结点(快慢指针，一个一步，一个两步，两步指针到尾时，第一个指针既是结果)：回文链表已练习
 *
 * 链表需要注意的点：
 * 1.引用的概念
 * 2.注意指针丢失和内存泄漏
 * 3.多使用哨兵，多创建几个引用；（有哨兵的链表也叫带头链表）
 * 4.注意头结点，头结点的插入、删除操作和中间节点不同；头结点是否是空结点也需要考虑
 * 5.边界条件
 */
//单链表
public class LinkNode {
    public int value;
    public LinkNode next = null;
    public LinkNode(){
        // 不赋值默认是一个空指针
    }
    public LinkNode(int value){
        this.value = value;
    }

    // 逆置单链表：原地反转，空间复杂度O(1)
    public static LinkNode reverseLink(LinkNode head){
        LinkNode reverseHead = head;
        head = head.next;
        reverseHead.next = null;

        // 思路：头插入法
        while(head != null){
            LinkNode tmp_head = head;
            head = head.next;

            tmp_head.next = reverseHead;
            reverseHead = tmp_head;
        }
        return reverseHead;
    }

    public static void main(String[] args) {

        LinkNode head = createLink(new int[]{1,2,3,4,5,6,7});
        showLink(head);
        showLink(reverseLink(head));

    }


    // 根据数组创建一个链表
    public static LinkNode createLink(int[] data){
        LinkNode head;
        if(data.length == 0){
            return null;
        } else{
            head = new LinkNode(data[0]);
        }
        // 尾插入法
        LinkNode tmp = head;
        for(int i = 1; i < data.length ; i ++){
            tmp.next = new LinkNode(data[i]);
            tmp = tmp.next;
        }
        return head;


    }
    public static void showLink(LinkNode head){
        StringBuilder sb = new StringBuilder();
        sb.append(head.value);
        head = head.next;
        while(head!= null){
            sb.append("->");
            sb.append(head.value);
            head = head.next;
        }
        System.out.println("链表内容："+sb.toString());
    }


    /***
     * 检测链表中是否有环
     * 思路：快慢指针
     */
    public static boolean hasCycle(LinkNode head){
        LinkNode frist = head;
        LinkNode second = head;
        while(frist != null && second != null){

            frist = frist.next;
            if (second.next == null || second.next.next == null){
                return false;
            } else {
                second = second.next.next;
            }
            if(frist == second){
                return true;
            }
        }
        return false;
    }
}

// 双向链表
class BothwayLinkNode{
    private int value;
    private BothwayLinkNode prev = null;
    private BothwayLinkNode next = null;
}

