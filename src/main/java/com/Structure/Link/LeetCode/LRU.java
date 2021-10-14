package com.Structure.Link.LeetCode;

import com.Structure.Link.Learning.CirculeLink;
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
 * 可以使用环状链表
 *
 * 需要完成的功能：
 * 1. 环形链表的增删查
 * 2. LRU算法的查找逻辑
 *
 */
public class LRU {

    public static void main(String[] args) {
        LinkNode ln = new LinkNode();

        // 测试LRU算法的性能和FIFO的性能区别

    }

    // 查找(缓存是介于CPU和磁盘IO之间的区域，临时存放高频使用的数据)
    public Integer search(LinkNode data){
        // 查找对象指针的具体值，cache中
        LinkNode cacheIndex = this.MemCache.search(data);
        if (cacheIndex == null){
            return cacheIndex.value;
        } else {
            // 去磁盘或者内存IO中查询
            return null;
        }
    }

    // 更新缓存
    public void updateCache(LinkNode data){
        // 如果被访问数据已经存在链表中，则原记录删除，重新头插入；
        if(this.MemCache.search(data) == null){
            // 删除节点，头节点插入
            this.MemCache.delete(data);
            this.MemCache.insert(data,this.MemCache.getHead());
        } else {
            // 待查找的数据不存在
            if (this.MemCache.isFull()){
                // 链表已满，删除尾节点，插入头节点
                this.MemCache.delete(this.MemCache.getTail());
                this.MemCache.insert(data,this.MemCache.getHead());
            } else {
                // 链表未满，数据插入头节点
                this.MemCache.insert(data,this.MemCache.getHead());
            }
        }
    }

    // 缓存链表，使用环形链表
    private CirculeLink MemCache = new CirculeLink();




}




