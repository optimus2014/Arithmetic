package com.Algorithm.Sort;

/**
 * 8大排序算法：
 * 交换排序：
 * 1.冒泡:Done
 * 2.快排:Done(nlogN)，核心思想，分治和分区
 *
 * 插入排序
 * 1.希尔排序
 * 2.简单插入排序:Done
 *
 * 选择排序：
 * 1.简单选择排序:Done
 * 2.堆排序：Done
 *
 * 归并排序:(nlogN),空间复杂度较高，不是原地排序算法
 *
 * 基数排序
 *
 * 需要掌握排序算法：
 * 稳定性
 * 最好时间复杂度
 * 最坏时间复杂度
 * 平均时间复杂度
 *
 * 归并排序&快速排序都用到了分治思想
 */
public class SortFunc {
    public static void main(String[] args) {

        System.out.println("快排的结果：" + showList(quickSort(new int[]{1, 2, 32, 2, 1, 4, 5, 6, 7, 3, 2})));
        System.out.println("冒泡的结果：" + showList(bubbleSort(new int[]{1, 2, 32, 2, 1, 4, 5, 6, 7, 3, 2})));
        System.out.println("选择排序的结果：" + showList(selectSort(new int[]{1, 2, 32, 2, 1, 4, 5, 6, 7, 3, 2})));
        System.out.println("插入排序的结果：" + showList(insertSort(new int[]{1, 2, 32, 2, 1, 4, 5, 6, 7, 3, 2})));
        System.out.println("堆排序的结果：" + showList(heapSort(new int[]{1, 2, 32, 2, 1, 4, 5, 6, 7, 3, 2})));

        System.out.println("第K大的元素结果：" + findKthLargest(new int[]{1, 2, 32, 2, 1, 4, 5, 6, 7, 3, 2},5));
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
     * 基本思想是：将待排序序列构造成一个大顶堆，此时，整个序列的最大值就是堆顶的根节点。将其与末尾元素进行交换，此时末尾就为最大值。然后将剩余n-1个元素重新构造成一个堆，这样会得到n个元素的次小值。如此反复执行，便能得到一个有序序列了
     * 1. 初始化堆
     * 2. 置换最大结点
     * 3. 从根结点开始，再重新堆；确保data[i] >= data[2i+1] && data[i] >= data[2i+2]
     */
    public static int[]  heapSort(int[] data){
        for(int i = data.length/2 - 1;i >= 0;i --){
            // 初始化大根堆，从第一个非叶子结点开始，扫描至根结点
            adjustHeap(data,i , data.length);
        }
        for(int i = data.length - 1; i > 0 ; i --){
            int tmp = data[0];
            data[0] = data[i];
            data[i] = tmp;
            adjustHeap(data,0,i - 1);
        }
        return data;
    }

    public static void adjustHeap(int[] data,int i ,int len){
        // 调整每个非叶子结点，使data[i] >data[2i + 1] && data[i] > data[2i + 2]
        if(i >= len || 2*i +1 >=len){
            return;
        }
        int left = i * 2 + 1;
        int right = i * 2 + 2;
        int max = i;  // 默认当前值是最大值
        if(left <= len && data[max] < data[left]){
            max = left;
        }
        if(right <= len && data[max] < data[right]){
            max = right;
        }

        if(max != i){
            // 当前结点不是最大值，交换位置
            int tmp = data[i];
            data[i] = data[max];
            data[max] = tmp;
            adjustHeap(data,max,len);
        }

    }

    /**
     * 归并排序
     *
     */
    public static int[] mergeSort(int[] data){
        return data;
    }


    /**
     * 使用快排思想，查找第k大的元素
     */
    public static int findKthLargest(int[] nums, int k) {
        int need = nums.length - k;
        int front = 0;
        int tail = nums.length - 1;
        int index = getQuickIndex(nums,front,tail);
        while(need != index){
            System.out.println("Index=" + index+";need="+need);
            if(index > need){
                tail = index - 1;
                index = getQuickIndex(nums,front,tail);
            } else {
                front = index + 1;
                System.out.println("front=" + front+";tail="+tail);
                index = getQuickIndex(nums,front,tail);
                System.out.println("Index=" + index+";need="+need);
            }
        }
        return nums[index];

    }
    public static int getQuickIndex(int[] data,int front,int tail){
        int tmp = data[front];
        int addr = front;
        while(front < tail){
            while(data[tail] > tmp && front < tail){
                tail -= 1;
            }
            while(data[front] <= tmp && front < tail){
                front += 1;
            }
            if(tail != front){
                int swp = data[tail];
                data[tail] = data[front];
                data[front] = swp;
            }
        }
        if(tmp > data[front]){
            data[addr] = data[front];
            data[front] = tmp;
        }
        return front;
    }



    /**
     * 线性排序
     * */
}
