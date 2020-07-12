package com.arithmetic.Learning;

/**
 * 8大排序算法：
 * 交换排序：
 * 1.冒泡:Done
 * 2.快排:Done
 *
 * 插入排序
 * 1.希尔排序
 * 2.简单插入排序:Done
 *
 * 选择排序：
 * 1.简单选择排序:Done
 * 2.堆排序
 *
 * 归并排序
 *
 * 基数排序
 */
public class SortFunc {
    public static void main(String[] args) {

        System.out.println("快排的结果：" + showList(quickSort(new int[]{1, 2, 32, 2, 1, 4, 5, 6, 7, 3, 2})));
        System.out.println("冒泡的结果：" + showList(bubbleSort(new int[]{1, 2, 32, 2, 1, 4, 5, 6, 7, 3, 2})));
        System.out.println("选择排序的结果：" + showList(selectSort(new int[]{1, 2, 32, 2, 1, 4, 5, 6, 7, 3, 2})));
        System.out.println("插入排序的结果：" + showList(insertSort(new int[]{1, 2, 32, 2, 1, 4, 5, 6, 7, 3, 2})));
    }

    private static String showList(int[] list){
        StringBuilder sb = new StringBuilder();
        sb.append(String.valueOf(list[0]));
        for(int i = 1;i< list.length ; i++){
            sb.append("|");
            sb.append(String.valueOf(list[i]));
        }
        return sb.toString();
    }

    /***************************************************************
     * 快速排序:
     * 比较高效的是指针交换法,遍历左指针和右指针，确定位置后，两个指针交换
     * 使用递归算法
     */
    public static int[] quickSort(int[] data){
        sort(data,0,data.length - 1);
        return data;
    }
    //快排查找索引函数
    private static void sort(int[] data,int front,int tail){
        if(front >= tail){
            return;
        }
        int index = getQuickSortIndex(data,front,tail);
        sort(data,front,index - 1);
        sort(data,index + 1,tail);
    }
    private static int getQuickSortIndex(int[] data,int front,int tail){
        if(front >= tail){
            return front;
        }
        int tmp = data[front];
        int addr = front;
        while (front < tail){
            while(data[tail] > tmp && front < tail){
                tail -= 1;
            }
            while(data[front] <= tmp && front < tail){
                front += 1;
            }
            int swp = data[tail];
            data[tail] = data[front];
            data[front] = swp;
        }
        if(tmp > data[front]){
            data[addr] = data[front];
            data[front] = tmp;
        }
        return front;
    }
    //  ********************* Quick Sort End ***************************

    /****************************************************
     * 冒泡排序：交换排序的一种
     * 向上冒泡，每次选择最大值
     */
    public static int[] bubbleSort(int[] data){
        for(int i = 0; i < data.length - 1;i ++){
            for(int j = 0; j <data.length - 1 - i; j++){
                if(data[j] > data[j + 1]){
                    int swp = data[j+1];
                    data[j+1] = data[j];
                    data[j] = swp;
                }
            }
        }
        return data;
    }

    /*************************************************
     * 简单选择排序
     * 思路：每次选择出一个最大(小)值，放置在初始（最终结果）位置
     */
    public static int[] selectSort(int[] data){
        for(int i = 0;i < data.length ;i ++) {
            int index = i;
            for(int j = i;j < data.length ;j ++){
                if(data[j] < data[index]){
                    index = j;
                }
            }
            if(index != i){
                int swp = data[i];
                data[i] = data[index];
                data[index] = swp;
            }
        }
        return data;
    }

    /*************************************************
     * 插入排序
     * 思路：维护一个新列表，遍历原有列表，每次找到元素的应有位置，并插入。使用链表较好，查找时候可以使用二分查找法
     * 补充：可以在一个列表上转移数据，空间复杂度降低。
     */
    public static int[] insertSort(int[] data){
        for(int i = 1; i < data.length ; i ++){
            int tmp = data[i]; // 记录待确认位置元素实际值
            int addr = 0; // 记录元素最终位置
            for(int j = 0; j < i; j ++){
                if(data[j] > data[i]){
                    break;
                }
                addr = addr + 1;
            }
            // 逆序移动元素
            for(int t = i ; t > addr ; t --){
//                System.out.println("CS 中间值:"+ showList(data) +";Addr"+addr +"；待校验元素："+tmp+"待校验元素下标："+i);
                data[t] = data[t - 1];
            }
            data[addr] = tmp;
        }
        return data;
    }

    /***
     * 堆排序：
     * 堆排序也是“选择排序的一种”
     * 思路：构建大(小)根堆，？？？
     *
     */
    public static int[]  heapSort(int[] data){

        return data;
    }
}
