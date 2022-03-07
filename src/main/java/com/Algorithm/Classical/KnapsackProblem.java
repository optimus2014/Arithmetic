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
        int[] values = {9, 10, 7, 4,8, 12};
        int[] weights = {3, 5, 2, 1,4,6};
        int capacity = 10;
        KnapsackProblem kp = new KnapsackProblem();
        int v = kp.knapsack(values,weights,capacity);
        System.out.println("背包总容量：" + capacity + ";回溯法最大可装价值：" + v);

        int v2 = kp.knapsack2(weights, values, capacity);
        System.out.println("背包总容量：" + capacity + ";动规法最大可装价值：" + v2);
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


    /**
     * 使用动态规划方法实现0-1背包问题
     * 背包约束条件：物品价格，物品重量，最大物品个数，背包容量
     * @param items,物品重量清单
     * @param values，物品价值清单
     * @param w，背包容积
     * @return
     */
    public int knapsack2(int[] items, int[] values, int w) {

        // 思路：遍历每一个元素，记录选择这个元素和不选择这个元素的价值差多少
        // 使用一个n * (w + 1)的二维数组作为备忘录，
        int n = items.length;
        if (n <= 0){
            return 0;
        }
        // 设置初始值
        int[][] mem = new int[n][w + 1];
        for(int i = 0; i < n ; i ++){
            for(int j = 0; j <= w; j ++){
                mem[i][j] = -1;
            }
        }
        // 设置哨兵，没有放置元素的情况下，重量为0
        mem[0][0] = 0;
        // 需要先设置初始值，用于后续记录的前置判断，先看第一个元素是否要放置进去
        if(items[0] <= w){
            mem[0][items[0]] = values[0];
        }

        //从第1行开始记录
        for(int i = 1; i < n; i ++){
            // 第i个元素不放置的情况
            for(int j = 0;j <= w; j ++){
                if(mem[i - 1][j] >= 0){
                    mem[i][j] = mem[i - 1][j];
                }
            }

            // 第i个元素放置的情况
            for (int j = 0; j <= w - items[i]; j++){
                if(mem[i - 1][j] >= 0){
                    int v = mem[i - 1][j] + values[i];
                    // 选择i元素后的价值，大于对应重量下标的价值
                    if(v > mem[i][j + items[i]]){
                        mem[i][j + items[i]] = v;
                    }
                }
            }
        }

        int max_v = -1;
        for(int i = w ; i >= 0;i --){
            if(mem[n -1][i] > max_v){
                max_v = mem[n -1][i];
            }
        }
        return max_v;
    }
}
