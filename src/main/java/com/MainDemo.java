package com;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Queue;

public class MainDemo {
    public static void main(String[] args) {
        System.out.println("主函数");
        System.out.println((2+1)/2);

        System.out.println(MainDemo.check(12,34));
        System.out.println(MainDemo.check(0,0));
        System.out.println(MainDemo.check(10,2));
        MainDemo md = new MainDemo();
        System.out.println(md.movingCount(100,100,20));

        System.out.println(md.movingCount2(100,100,20));
    }


    // 机器人运动范围，方法一：使用回溯法
    public int movingCount(int m, int n, int k) {
        int[][] flag = new int[m][n];
        for(int i = 0; i < m ; i++){
            for (int j = 0; j < n;j ++){
                flag[i][j] = -1;
            }
        }
        move(m,n,k,0,0,flag);

        int res = 0;
        for(int i = 0; i < m ; i++){
            for (int j = 0; j< n;j ++){
                if (flag[i][j] >= 0){
                    res ++;
                }
            }
        }
        return res;
    }
    // 注：这个方法用的是回溯算法
    private void move(int m, int n,int k,int r,int c,int[][] flag){
        if (r < 0 || r >= m || c < 0 || c >= n){
            return;
        }
        int v = check(r,c);
        if (v > k || flag[r][c] >= 0){
            return;
        }
        flag[r][c] = v;
        move(m,n,k,r - 1,c,flag);
        move(m,n,k,r + 1,c,flag);
        move(m,n,k,r,c + 1,flag);
        move(m,n,k,r,c - 1,flag);
    }

    // 判断当前位置是否符合条件
    private static int check(int row,int col){
        int res = 0;
        while (row >= 10){
            res += row % 10;
            row = row / 10;
        }
        res += row;

        while (col >= 10){
            res += col % 10;
            col = col / 10;
        }
        res += col;
        return res;
    }


    // 机器人运动范围的方法二：使用广度优先算法遍历图
    public int movingCount2(int m, int n, int k) {
        int[][] flag = new int[m][n];
        // flag数组记录三种状态，-1表示不可到达，0表示可到达，1表示已经遍历
        for(int i = 0; i < m ; i++){
            for (int j = 0; j < n;j ++){
                int v = check(i,j);
                if (v > k ){
                    flag[i][j] = -1;
                } else {
                    flag[i][j] = 0;
                }
            }
        }

        int res = 0;
        Queue<HashMap<String,Integer>> queues = new ArrayDeque<HashMap<String, Integer>>();
        if(flag[0][0] == 0){

            HashMap item = new HashMap<String, Integer>();
            item.put("row",0);
            item.put("col",0);
            queues.add(item);
            flag[0][0] = 1;
        }
        while (queues.size() > 0){
            if(queues.isEmpty()){
                break;
            }
            HashMap<String,Integer> tmp = queues.poll();
            res += 1;
            int row = tmp.get("row");
            int col = tmp.get("col");
            if(row >= 0 && row + 1 < m && col >= 0 && col < n && flag[row + 1][col] == 0){
                HashMap item = new HashMap<String, Integer>();
                item.put("row",row + 1);
                item.put("col",col);
                queues.add(item);
                flag[row + 1][col] = 1;
            }
            if(row - 1 >= 0 && row < m && col >= 0 && col < n && flag[row - 1][col] == 0){
                HashMap item = new HashMap<String, Integer>();
                item.put("row",row - 1);
                item.put("col",col);
                queues.add(item);
                flag[row - 1][col] = 1;
            }
            if(row >= 0 && row < m && col >= 0 && col + 1 < n && flag[row][col + 1] == 0){
                HashMap item = new HashMap<String, Integer>();
                item.put("row",row);
                item.put("col",col + 1);
                queues.add(item);
                flag[row][col + 1] = 1;
            }
            if(row >= 0 && row - 1 < m && col - 1 >= 0 && col < n && flag[row][col - 1] == 0){
                HashMap item = new HashMap<String, Integer>();
                item.put("row",row);
                item.put("col",col - 1);
                queues.add(item);
                flag[row][col - 1] = 1;
            }
        }

        return res;
    }

}
