package com.arithmetic.Learning;

/***
 * 7大查找算法：(需要已排序列表)
 * 1.二分查找：需要有序
 * 2.插值插值？这是啥
 * 3.查找二叉树：中序遍历得到有序数列
 * 4.Hash查找
 * 5.分块查找
 * 6.斐波那契查找
 * 加分题：
 * 1.红黑树：平衡查找树
 * 2.B+树
 */
public class SearchFunc {
    public static void main(String[] args) {
        // 未找到返回-1

        System.out.println(1/2);
        System.out.println("二分查找算法结果：index=" + binarySearch(new int[]{1,2,3,4},3));
    }

    // [0,1] [0]
    public static int binarySearch(int[] data,int value){
        int front = 0;
        int tail = data.length - 1;
        while (front <= tail){
            int tmp_i = (front + tail) / 2;
            if(value < data[tmp_i]){
                tail = tmp_i - 1;
            } else if(value > data[tmp_i]){
                front = tmp_i + 1;
            } else{
                return tmp_i;
            }
        }
        return -1;
    }
}
