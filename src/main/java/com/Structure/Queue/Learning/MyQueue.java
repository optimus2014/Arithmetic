package com.Structure.Queue.Learning;

import com.Structure.Link.Learning.LinkNode;

/**
 * 自己实现一个队列
 * 实现方法：
 * 1.使用链表：链式队列
 * 2.使用数组：（实现动态调整队列，“均摊时间复杂度”的概念）顺序队列
 *
 * 需要完成：
 * 1.链式队列
 * 2.循环队列
 * 3.动态可调整的顺序队列
 */
public class MyQueue {
    public static void main(String[] args) {
        LinkQueue.testLinkQueue();
//        MyQueue.testMyQueue();
    }
    public static void testMyQueue(){
        MyQueue qu = new MyQueue(5);
        System.out.println(qu.isFull());
        System.out.println(qu.isEmpty());
        for(int i = 0; i < 10; i ++){
            if(!qu.isFull()){
                qu.enqueue(i);
                qu.showQueue();
            } else{
                System.out.println("队列已满.");
                break;
            }
        }

        for(int i = 0; i< 10;i ++){
            if(!qu.isEmpty()){
                qu.showQueue("删除前");
                System.out.println("删除元素：" + qu.dequeue());
                qu.showQueue("删除后");
            } else{
                System.out.println("队列已空.");
                break;
            }
        }


    }
    public void showQueue(){
        this.showQueue("");
    }
    public void showQueue(String str){
        StringBuilder sb = new StringBuilder();
        sb.append(this.queue[0]);
        for(int i = 1; i < this.print; i ++){
            sb.append("->");
            sb.append(this.queue[i]);
        }
        System.out.println(str+"当前队列：" + sb.toString());
    }
    public MyQueue(){
         this.queue = new int[10];
    }
    public MyQueue(int len){
        this.queue = new int[len];
    }
    // 申请10位长度的队列
    int[] queue;
    int print = 0; // 指针标识，可插入元素位置
    public boolean enqueue(int item){
        if(this.isFull()){
            return false;
        } else if(this.isEmpty()){
            // 空队列
            queue[0] = item;
            print += 1;
            return true;
        }else {
            for(int i = this.print - 1;i >= 0; i --){
//                this.showQueue("临时");
                this.queue[i + 1] = this.queue[i];
//                this.showQueue("临时");
            }
            this.queue[0] = item;
            print += 1;
            return true;
        }
    }

    public int dequeue(){
        if(this.isEmpty()){
            return -1;
        } else {
            int tmp = queue[this.print - 1];
            queue[this.print - 1] = 0;
            this.print -= 1;
            return tmp;
        }
    }

    // 是否空
    public boolean isEmpty(){
        // 只能用数组实现队列
        if(this.print == 0){
            return true;
        } else {
            return false;
        }
    }

    // 是否满
    public boolean isFull(){
        if(this.print >= queue.length){
            return true;
        } else {
            return false;
        }
    }


}
// 链式队列
class LinkQueue{

    public static void testLinkQueue(){
        LinkQueue lq = new LinkQueue();
        System.out.println(lq.isEmpty());
        for(int i = 0; i < 10; i ++){
            lq.enqueue(i);
            lq.showLinkQueue();
        }

        for(int i = 0; i< 20;i ++){
            if(!lq.isEmpty()){
                System.out.println("删除元素：" + lq.dequeue());
                lq.showLinkQueue();
            } else{
                System.out.println("队列已空.");
                break;
            }
        }

    }

    public void showLinkQueue(){
        StringBuilder sb = new StringBuilder();
        LinkNode tmp = head;
        while(tmp != null){
            sb.append("->");
            sb.append(tmp.value);
            tmp = tmp.next;
        }
        System.out.println("当前链表队列：" + sb.toString());
    }

    LinkNode head = null;
    LinkNode tail = head;

    public boolean enqueue(int item){
        // 链表队列使用尾插入法
        if(this.isEmpty()){
            head = new LinkNode(item);
            tail = head;
        } else{
            tail.next = new LinkNode(item);
            tail = tail.next;
        }
        return true;
    }

    public int dequeue(){
        // 输出时候输出头指针
        if(!this.isEmpty()){
            int tmp = head.value;
            head = head.next;
            return tmp;
        } else {
            return -1;
        }
    }

    // 是否空
    public boolean isEmpty(){
        // 只能用数组实现队列
        if(head == null){
            return true;
        } else {
            return false;
        }
    }

}

// 循环队列
class CirculeQueue{
    public boolean enqueue(String item){
        return true;
    }

    public String dequeue(){
        return null;
    }

    // 是否空
    public boolean isEmpty(){
        // 只能用数组实现队列
        return true;
    }

    // 是否满
    public boolean isFull(){
        return true;
    }

}