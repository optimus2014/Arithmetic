package com.Algorithm.Sort;

import java.util.Random;

/***
 * 堆排序
 * 使用二叉树模式
 *
 * 包括大根堆，小根堆
 *
 * 堆是一种完全二叉树，大根堆，是每轮迭代之后，根节点是最大数，和最末尾的叶子节点进行置换。
 * 堆排序的平均时间复杂度为 Ο(nlogn)。
 *
 * 算法流程：
 *
 *
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] data = new int[8];
        for (int i = 0; i < 8; i++){
            data[i] = new Random().nextInt(20) + 1;
        }

        HeapSort.heapSord(data);


    }

    public static int[] heapSord(int[] data){
        show(data);
        int len = data.length - 1;

        /**
         * 步骤一：构建大根堆
         * */
        // 最后一个非叶子结点
        int end = len / 2 - 1;

        for (int i = end ; i >= 0 ; i--){
            heapAdjust(data,len,i);
        }
        show(data);
        /**
         * 步骤二：置换最大值，调整大根堆
         * */
        for(int i = data.length - 1; i > 0 ; i --){
            // 第一个元素(最大值)置换为最末尾值
            int tmp_value = data[0];
            data[0] = data[i];
            data[i] = tmp_value;
            // 待比对数组长度减小1(最末尾的元素已经确定)
            len --;
            heapAdjust(data,len,0);

        }
        show(data);
        return data;
    }

    /**
     * 调整堆中元素的具体位置
     * int[] data：原始数据
     * int len：待调整的数组总长度
     * int index：待调整元素下标
     * */
    private static void heapAdjust(int[] data,int len,int index){
        /**
         * 递归函数结束条件:
         * 叶子结点，
         * 当前结点最大
         * */
        int tmp_index = max(data,len,index);
        if (tmp_index == index){
            return;
        }

        // 交换最大值
        int tmp_value = data[index];
        data[index] = data[tmp_index];
        data[tmp_index] = tmp_value;
        heapAdjust(data,len,tmp_index);
    }
    // 获取当前index结点，和左右结点中，最大元素下标
    private static int max(int[] data,int len,int index){
        int max = index;
        int left = index * 2 + 1;
        int right = index * 2 + 2;
        if (left < len && data[left] > data[max]){
            max = left;
        }

        if (right < len && data[right] > data[max]){
            max = right;
        }
        return max;
    }
    private static void show(int[] data){
        System.out.print("当前数组：");
        for(int i = 0; i < data.length; i ++){
            System.out.printf("  %s;", data[i]);
        }
        System.out.println();
    }
}
