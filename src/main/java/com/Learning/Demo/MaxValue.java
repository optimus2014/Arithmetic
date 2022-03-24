package com.Learning.Demo;

/**
 * 2022年3月25号，字节面试题
 * 输入数值n和数组A
 * 自由组合A中的数值，使得最终结果是小于n的最大值
 *
 * 例如：
 * n：23431，A={2,4,7,9}
 * 最终结果：22999
 */
public class MaxValue {
    public static void main(String[] args) {
        MaxValue mv = new MaxValue();
        int n = 23431;
        int[] A = {2,3,4,5,7,9};
        System.out.println("结果是："+mv.getMax(393782,new int[]{2,3,4,5,7,9}));
    }

    /**
     * 计算思路：
     * 第一反应应该想到是回溯法
     * 其次对一些特殊情况做处理，比如n== 0 或者位数不足的情况。
     * @param n
     * @param A
     * @return
     */
    int MAX = 0;
    public int getMax(int n , int[] A){

        int len = 0;
        while (n / (int)Math.pow(10,len) > 0){
            len += 1;
        }
       // 特殊情况处理：n == 0
        if (n == 0){
            return 0;
        }
        // 特殊情况处理：第一个数字小于数组最小值
        if(n / (int)Math.pow(10,len - 1) < A[0]){
            for (int i = 0 ; i < len - 1; i ++){
                MAX += (int)Math.pow(10,i) * A[A.length - 1];
            }
            return MAX;
        }

        max(0,len - 1,n,A);
        return MAX;
    }

    private void max(int tmp,int idx ,int n,int[] A){
        if (tmp >= n){
            return ;
        }
        if (idx == -1){
            if (tmp > MAX){
                MAX = tmp;
            }
            return ;
        }
        for (int i = A.length - 1 ; i >= 0; i--){
            max(tmp + (int)Math.pow(10, idx) * A[i], idx - 1, n,A);
        }
    }
}
