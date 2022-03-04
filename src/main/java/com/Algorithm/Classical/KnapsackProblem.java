package com.Algorithm.Classical;

import java.lang.reflect.Array;
import java.util.Arrays;

/*********
 * 背包问题：
 * 是一种组合优化的NP完全问题
 *
 * 给定n个重量为w1，w2，w3，…,wn，价值为v1，v2，v3，…,vn的物品和容量为C的背包
 * 求这个物品中一个最有价值的子集，使得在满足背包的容量的前提下，包内的总价值最大
 * 注意：0-1背包问题指的是每个物品只能使用一次
 *
 * 解题思路：
 * 1.回溯
 * 2.动态规划
 */
public class KnapsackProblem {
    public static void main(String[] args) {
        System.out.println("0-1背包问题");
        int[] values = {9, 10, 7, 4};
        int[] weights = {3, 5, 2, 1,4,6};
        int capacity = 10;
        KnapsackProblem kp = new KnapsackProblem();
//        int v = kp.knapsack(values,weights,capacity);
//        System.out.println("背包总容量：" + capacity + ";最大可装价值：" + v);


        int v2 = kp.knapsack2(weights, 2, capacity);
        System.out.println("背包总容量：" + capacity + ";最大可装价值：" + v2);
    }

    /**
     * 0-1背包计算主函数，每个物品只能采用一次
     * @param values：物品清单的价值
     * @param weights：物品的重量
     * @param capacity：
     * @return
     */

    int MAX = 0;
    public int knapsack(int[] values,int[] weights,int capacity){
        boolean[] flag = new boolean[values.length];
        backTrack(values,weights,capacity,
                0,0,flag);
        return MAX;
    }

    // 回溯函数
    private void backTrack(int[] values,int[] weights,int capacity,
                           int w,int v,boolean[] flag){
        // w是当前背包重量
        if (w > capacity){
            return;
        }
        if(v > MAX){
            System.out.println("当前价值：" + v);
            for(int i = 0; i < flag.length; i++) {
                System.out.print("| " + values[i] + " ");
                System.out.print("--> " + flag[i] + " ");
            }
            System.out.println();
            MAX = v;
        }

        for(int i = 0;i < values.length; i ++){
            if (flag[i]){
                continue;
            }
            flag[i] = true;
            backTrack(values,weights,capacity,
                    w + weights[i],v + values[i],flag);
            flag[i] = false;
        }
    }


    /***
     * 使用一维数组存储动态规划的中间结果
     * 计算0-1背包问题
     */
    public int knapsack2(int[] items, int n, int w) {
//        Arrays.sort(items);
        boolean[] states = new boolean[w+1]; // 默认值false
        states[0] = true;  // 第一行的数据要特殊处理，可以利用哨兵优化
        if (items[0] <= w) {
            states[items[0]] = true;
        }
        for (int i = 1; i < n; ++i) { // 动态规划
            for (int j = w-items[i]; j >= 0; --j) {//把第i个物品放入背包
                if (states[j]==true) states[j+items[i]] = true;
            }
        }
        for (int i = w; i >= 0; --i) { // 输出结果
            if (states[i] == true) return i;
        }
        return 0;
    }


}
