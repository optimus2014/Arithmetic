package com.Leetcode;

// 位运算相关题目的Demo
public class BitOperateDemo {
    public static void main(String[] args) {
        BitOperateDemo bod = new BitOperateDemo();
        bod.getMaxBin(-19,-3);
        bod.getMaxBin(-1,-3);
//        System.out.println(bod.divide(-19,-3));
//        System.out.println(bod.divide(-19,-3));
//        System.out.println(bod.divide(19,-3));
//        System.out.println(bod.divide(19,3));
        System.out.println(bod.divide(2147483647,2));
    }

    public int divide(int a, int b) {
        // 思路：位运算，困难点是怎么处理小于0的数，java Int型数据最小值比最大值多一

        // 由于（-2^31） 转换为正数会溢出，但是任意正数转换为负数都不会溢出
        // 故，记录负数的个数，并将正数转换为负数方便统一计算
        // 思路：位运算，困难点是怎么处理小于0的数，java Int型数据最小值比最大值多一

        // 由于（-2^31） 转换为正数会溢出，但是任意正数转换为负数都不会溢出
        // 故，记录负数的个数，并将正数转换为负数方便统一计算
        if(b == 1 || a == 0){
            return a;
        } else if (b == -1){
            return a * -1 ;
        }
        int flag = 1;
        if (a > 0) {
            a = a * -1;
            flag = flag * -1;
        }
        if (b > 0) {
            b = b * -1;
            flag = flag * -1;
        }
        // 最终结果
        int res = 0;

        int tmp = a;
        while(tmp >= Integer.MIN_VALUE  && tmp <= b){
            int st = getMaxBin(tmp,b);
            tmp = tmp - (b << st);
            res = res + (1 << st);
        }
        if (flag == 1){
            return res;
        } else {
            return res * -1;
        }
    }

    // 获取b可左移的最大位数
    private int getMaxBin(int a,int b){
        int step = 0;
        int val = b;
        while(val >= Integer.MIN_VALUE >> 1 && a <= val << 1){
            step += 1;
            val = val << 1;
        }
        return step;
    }
}
