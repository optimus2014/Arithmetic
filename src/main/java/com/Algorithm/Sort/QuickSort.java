package com.Algorithm.Sort;

import java.util.Random;

/**
 * 快速排序
 * 算法思想：分治法（分而治之）
 *
 * 快速排序执行流程：
 * 首先从数列的右边开始往左边找，我们设这个下标为 i，也就是进行减减操作（i--），
 * 找到第 1 个比基准数小的值，让它与基准值交换；
 * 接着从左边开始往右边找，设这个下标为 j，然后执行加加操作（j++），
 * 找到第 1 个比基准数大的值，让它与基准值交换；
 * 然后继续寻找，直到 i 与 j 相遇时结束，
 * 最后基准值所在的位置即 k 的位置，也就是说 k 左边的值均比 k 上的值小，
 * 而 k 右边的值都比 k 上的值大。
 *
 * 注意事项：
 * 1.起始指针不能相同，相同的话会死循环；
 * 2.最终index左侧均小于标准值；右侧均大于等于标准值；
 */
public class QuickSort implements SortTool{
    public static void main(String[] args) {
        System.out.println("快速排序：");
        int data_len = 10;
        int[] data = new int[data_len];
        for (int i = 0; i < data_len; i++){
            data[i] = new Random().nextInt(20) + 1;
        }

        QuickSort.show(data);
        QuickSort qs = new QuickSort();
        qs.sort(data);
        QuickSort.show(data);

    }
    @Override
    public int[] sort(int[] data) {
        if(data.length < 2){
            return data;
        }
        quickSort(data,0,data.length - 1);
        return data;
    }

    private void quickSort(int[] data,int start,int end){
        if ((end - start) < 1 ){
            return;
        }
        int index = partition(data,start,end);
        QuickSort.show(data);
        System.out.println("Index=" + index);
        quickSort(data,start,index - 1);
        quickSort(data,index + 1, end);
    }

    /***
     * 快排分区函数
     * @param data
     * @param start
     * @param end
     * @return 返回最终下标位置
     */
    private int partition(int[] data,int start,int end){
        int init = data[start];   // 待寻址元素
        int tmp_s = start;
        int tmp_e = end;

        while(tmp_s < tmp_e){
            // 先从右开始查找，找到第一个基准值元素
            // 找到小于init的下标
            while (tmp_s < tmp_e && data[tmp_e] >= init){
                tmp_e --;
            }
            if(tmp_s < tmp_e){
                swap(data,tmp_s,tmp_e);
                tmp_s ++;
            }

            // 找到大于等于init的下标
            while (tmp_s < tmp_e && data[tmp_s] < init){
                tmp_s ++;
            }
            if(tmp_s < tmp_e){
                swap(data,tmp_s,tmp_e);
                tmp_e --;
            }
        }
        return tmp_s;
    }

    private void swap(int[] data,int tmp_s,int tmp_e){
        int tmp_v = data[tmp_s];
        data[tmp_s] = data[tmp_e];
        data[tmp_e] = tmp_v;
    }

    private static void show(int[] data){
        System.out.print("当前数组：");
        for(int i = 0; i < data.length; i ++){
            System.out.printf("  %s;", data[i]);
        }
        System.out.println();
    }
}
