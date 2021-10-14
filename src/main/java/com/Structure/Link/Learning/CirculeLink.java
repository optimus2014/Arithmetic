package com.Structure.Link.Learning;

/***
 * 循环链表
 * 实现功能：
 * 1. 查找
 */
public class CirculeLink {
    private LinkNode head;
    private LinkNode tail;
    private int length;

    // 头插法需要有一个空指针

    public CirculeLink(){
        // 不设置的话默认长度20.
        this.length = 20;
        this.head = new LinkNode();
        this.tail = head.next;
    }
    public CirculeLink(int length){
        this.length = length;
        this.head = new LinkNode();
        this.tail = head.next;
    }

    // 查找节点是否在链表中
    public LinkNode search(LinkNode data){
        LinkNode index = this.head.next;
        while (index != null && index != this.tail.next){
            // 按值来判定
            if (index.value == data.value){
                return index;
            }
            index = index.next;
        }
        // 元素不存在，默认返回为空
        return null;
    }
    // 查找当前节点的前一个指针，用于删除节点
    public LinkNode searchPrev(LinkNode data){
        LinkNode index = this.head;
        while (index.next != null && index.next != this.tail.next){
            // 按值来判定
            if (index.next.value == data.value){
                return index;
            }
            index = index.next;
        }
        // 元素不存在，默认返回为空
        return null;
    }



    // 删除节点
    public void delete(LinkNode data){
        LinkNode indexPrev = this.searchPrev(data);
        if(indexPrev != null){
            // 节点存在，说明data节点也存在
            indexPrev.next = indexPrev.next.next;
//            this.c
        } else {
            System.out.println("节点不存在");
        }
    }

    // 插入节点,将data节点插入到index位置
    public void insert(LinkNode data,LinkNode index){
        if(this.isFull()){
            System.out.println("链表已满，无法添加节点");
            return;
        }

        data.next = index.next;
        index.next = data;
    }

    // 获取头节点
    public LinkNode getHead(){
        return this.head;
    }

    // 获取环形链表的尾节点
    public LinkNode getTail(){
        return this.tail;
    }

    // 判断环状链表是否已满
    public boolean isFull(){
        if (head == tail){
           return true;
        } else {
            return false;
        }
    }

}
