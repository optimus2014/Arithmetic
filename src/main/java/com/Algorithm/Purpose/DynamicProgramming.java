package com.Algorithm.Purpose;

import java.util.HashSet;

/**
 * 动态规划：
 * 适合求最优问题
 *
 * 什么样的问题适合使用动态规划
 *  需要符合3个特征：最优子结构、无后效性和重复子问题。
 * 动态规划的结题方法：
 *
 *
 * 贪心、分治算法、回溯、动归有什么区别
 * 练习：
 * 0-1背包问题（有价格和没有价格）
 * 最短路径问题
 */
public class DynamicProgramming {
    public static void main(String[] args) {
        System.out.println("动态规划算法Demo");

        int[][] items = {{1,3,5,9},
                         {2,1,3,4},
                         {5,2,6,7},
                         {6,8,4,3}};

        DynamicProgramming dp = new DynamicProgramming();
        int res ;
//        res = dp.shortestPath(items);
        res = dp.shortestPathByRecursion(items);
        System.out.println("最短路径计算结果：" + res);
    }

    /**
     * 最短路径问题：Shortest Path
     * 查找二维数组中起始节点到最终节点的最短路径
     * 状态转移方程是： min_dist(i,j) = items[i,j] + min(min_dist(i - 1,j),min_dist(i, j - 1))
     */

    public int shortestPathByIteration(int[][] items){
        if (items.length <= 0){
            return 0;
        }
        int[][] mem = new int[items.length][items[0].length];
        // 对mem数组进行初始化
        mem[0][0] = items[0][0];
        for(int i = 1; i < items.length; i ++){
            mem[i][0] = items[i][0] + mem[i - 1][0];
        }
        for(int j = 1; j < items[0].length; j ++){
            mem[0][j] = items[0][j] + mem[0][j - 1];
        }

        // 使用状态转移表法，迭代方式进行计算
        for(int i = 1; i < mem.length; i ++){
            for(int j = 1; j < mem[0].length; j ++){
                mem[i][j] = items[i][j] + Math.min(mem[i-1][j],mem[i][j-1]);
            }
        }
        return mem[items.length - 1][items[0].length - 1];
    }

    /***
     * 状态转移方程是： min_dist(i,j) = items[i,j] + min(min_dist(i - 1,j),min_dist(i, j - 1))
     * 使用递归+“备忘录”方式(记录状态的数组)
     */
    private int shortestPathByRecursion(int[][] items){
        if (items.length <= 0){
            return 0;
        }
        int[][] mem = new int[items.length][items[0].length];
        // 设置哨兵
        mem[0][0] = items[0][0];
        return minDist(items.length - 1,items[0].length - 1,items,mem);
    }
    private int minDist(int row,int col,int[][] items,int[][] mem){
        // 从最终节点遍历只起始节点
        if(row == 0 && col == 0){
            return items[0][0];
        }

        // 当前位置已经计算过，直接取结果（重复子问题）
        if (mem[row][col] > 0){
            return mem[row][col];
        }

        int left = Integer.MAX_VALUE;
        int up = Integer.MAX_VALUE;
        // 递归找到左侧元素的最短距离
        if(row - 1 >= 0){
            left = minDist(row - 1,col,items,mem);
        }
        if(col - 1 >= 0){
            up = minDist(row,col - 1,items,mem);
        }

        // 使用最大整型，规避了边缘元素的判断问题
        int currDist = items[row][col] + Math.min(left,up);
        mem[row][col] = currDist;
        return currDist;
    }
}
