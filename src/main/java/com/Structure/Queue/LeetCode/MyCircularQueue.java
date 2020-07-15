package com.Structure.Queue.LeetCode;

/**
 * 自定义一个循环队列
 */
public class MyCircularQueue {
    public static void main(String[] args) {
        MyCircularQueue mcq = new MyCircularQueue(3);
        System.out.println("List:" + mcq.showContent(mcq.queue) + "|front:" +mcq.front+"|tail:" + mcq.tail);
        System.out.println(mcq.enQueue(1));
        System.out.println("List:" + mcq.showContent(mcq.queue) + "|front:" +mcq.front+"|tail:" + mcq.tail);
        System.out.println(mcq.enQueue(2));
        System.out.println(mcq.enQueue(3));
        System.out.println(mcq.enQueue(4));
        System.out.println(mcq.Rear());
        System.out.println(mcq.isFull());
        System.out.println(mcq.deQueue());
        System.out.println(mcq.enQueue(4));
        System.out.println(mcq.Rear());
    }
    private String showContent(int[] content){
        StringBuilder sb = new StringBuilder();
        sb.append(content[0]);
        for(int i = 1;i < content.length;i++){
            sb.append(",");
            sb.append(content[i]);
        }
        return sb.toString();
    }


    // 设置头指针
    public int front = 0;
    // 设置尾指针
    public int tail = 0;
    // 设队列长度
    private int length;

    public int[] queue;
    /** Initialize your data structure here. Set the size of the queue to be k. */
    public MyCircularQueue(int k) {
        this.length = k;
        queue = new int[k];
        // format
        for(int i = 0;i < k ; i ++){
            this.queue[i] = -1;
        }
    }

    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if(this.isFull()){
            return false;
        }
        this.queue[this.tail] = value;
        this.tail = (this.tail + 1) % this.length;
        return true;
    }

    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if(this.isEmpty()){
            return false;
        }
        this.queue[this.front] = -1;
        if(this.front == this.length - 1){
            this.front = 0;
        } else {
            this.front = this.front + 1;
        }
        return true;
    }

    /** Get the front item from the queue. */
    public int Front() {
        return this.queue[this.front];
    }

    /** Get the last item from the queue. */
    public int Rear() {
        int real ;
        if(this.tail == 0){
            real = this.length - 1;
        } else {
            real = this.tail - 1;
        }
        return this.queue[real];

    }

    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        if(this.front == this.tail && this.queue[this.tail] == -1){
            // 头尾指针重叠，且是初始化值
            return true;
        } else {
            return false;
        }
    }

    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        if(this.tail % this.length == this.front && this.queue[this.front] != -1){
            return true;
        } else {
            return false;
        }
    }
}
