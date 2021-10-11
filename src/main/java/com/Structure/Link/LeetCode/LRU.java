package com.Structure.Link.LeetCode;

import com.Structure.Link.Learning.LinkNode;

/**
 * 使用链表实现LRU算法(缓存淘汰算法)
 *
 * LRU：最近最少使用算法
 * 是一种缓存机制，常见的缓存机制有FIFO(先进先出)，LFU(最近最少使用)，LRU(最近最久未使用)
 * 缓存算法是针对CPU缓存已满时，判断应该清除哪部分存储内容，也称为页面置换算法。
 *
 * LRU算法内容
 * 实现思路： 维护一个单链表，越靠近链尾，节点存储越早访问的数据，每次有访问请求时，都需要遍历链表。
 * 1. 如果被访问数据已经存在链表中，则原记录删除，重新头插入；
 * 2. 如果数据不存在：
 *   2.1 缓存不满，新的数据节点插入到链表头部
 *   2.2 缓存已满，删除尾结点，新的数据节点插入到链表头部。
 *
 * 注：由于每次遍历的时间复杂度是O(n)，可以再维护一个Hash表，查找复杂度O(1)
 *
 */
public class LRU {


    public static void main(String[] args) {
        LinkNode ln = new LinkNode();
    }

    // 缓存包含查找功能
    public void search(){

    }

    // 缓存链表
    private Node MemCache;

    // 缓存队列的数据节点类型，使用内部类
    class Node{

    }
}


