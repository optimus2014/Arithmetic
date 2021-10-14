package com.Structure.Link.Learning;

/***
 * 循环链表
 * 实现功能：
 * 1. 查找
 */
public class CirculeLink {
    public static void main(String[] args) {
        CirculeLink cl = new CirculeLink(10);
        cl.show();

        cl.search(new LinkNode(10));

        for(int i = 1; i <= 5 ; i ++){
            cl.insert(new LinkNode(i),cl.getHead());
            cl.show();
        }
        cl.delete(new LinkNode(1));
        cl.show();
        for(int i = 6; i <= 10 ; i ++){
            cl.insert(new LinkNode(i),cl.getHead());
            cl.show();
        }

        cl.delete(new LinkNode(5));
        cl.show();

        cl.delete(new LinkNode(8));
        cl.show();

        cl.insert(new LinkNode(11),new LinkNode(1));
        cl.show();
        cl.insert(new LinkNode(12),new LinkNode(6));
        cl.show();
        cl.insert(new LinkNode(12),new LinkNode(6));
        cl.show();

        cl.delete(cl.tail);
        cl.show();
    }

    // 打印链表
    public void show(){
        LinkNode index = this.head.next;
        System.out.print("当前队列为：");
        if(index == null){
            System.out.print("NULL");
        } else {
            while(index != null && index != this.tail.next){
                System.out.printf(" --> %s",index.value);
                index = index.next;
            }
        }
//        System.out.println();
        System.out.println(";尾结点：" + this.getTail().value);
    }


    private LinkNode head = new LinkNode();
    private LinkNode tail = this.head;
    // 链表总长度
    private int length;
    // 当前链表长度
    private int now_length = 0;

    // 头插法需要有一个空指针

    public CirculeLink(){
        // 不设置的话默认长度20.
        this.length = 20;
    }
    public CirculeLink(int length){
        this.length = length;
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
        // 节点存在，说明data节点也存在
        if(indexPrev != null){

            // 删除尾结点
            if (indexPrev.next == this.tail){
                indexPrev.next = this.head;
                this.tail = indexPrev;
            } else {
                indexPrev.next = indexPrev.next.next;
            }
            this.now_length --;
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

        // 分为三种情况，插入位置为头，插入位置为尾，插入位置为中间
        if(index == this.head){
            insertHead(data);
        } else if (index.value == this.tail.value){
            insertTail(data);
        } else {
            // 找到index在链表中对应的位置
            LinkNode tmp = this.search(index);
            // index不存在
            if (tmp == null) {
                insertHead(data);
            } else {
                data.next = tmp.next;
                tmp.next = data;
            }
        }
        this.now_length ++;
    }

    // 头插入法
    private void insertHead(LinkNode data){
        // 处理空队列
        if(this.head.next == null){
            this.head.next = data;
            this.tail = data;
            this.tail.next =this.head;
        } else {
            data.next = this.head.next;
            this.head.next = data;
        }
    }
    // 尾插入法
    private void insertTail(LinkNode data){
        this.tail.next = data;
        this.tail = data;
        this.tail.next = this.head;
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
        if (this.length == this.now_length){
           return true;
        } else {
            return false;
        }
    }

}
