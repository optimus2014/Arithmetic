package com.Structure.Link.Learning;

/***
 * 循环链表
 * 实现功能：
 * 1. 查找
 */
public class CirculeLink {
    private int value;
    private CirculeLink next = null;
    private LinkNode head;
    private LinkNode tail;

    // 查找节点是否在链表中
    public LinkNode search(LinkNode data){
        return null;
    }

    // 删除节点
    public void delete(LinkNode data){

    }

    // 插入节点,将data节点插入到index位置
    public void insert(LinkNode data,LinkNode index){

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
        return true;
    }

}
