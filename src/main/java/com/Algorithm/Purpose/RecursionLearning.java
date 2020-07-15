package com.Algorithm.Purpose;

/**
 * 递归练习
 * 使用递归需要满足的三个条件：
 * 1.一个问题的解可以分解为几个子问题的解
 * 2.这个问题与分解之后的子问题，除了数据规模不同，求解思路完全一样
 * 3.存在递归终止条件
 *
 * > 写递归代码的关键就是找到如何将大问题分解为小问题的规律，并且基于此写出递推公式，然后再推敲终止条件，最后将递推公式和终止条件翻译成代码。
 *
 * 递归要注意：
 * 1.重复计算
 * 2.深度太深，栈溢出
 *
 * 递归代码都可以转化为迭代代码
 */
public class RecursionLearning {

    public static void main(String[] args) {
        for(int i = 0;i < 15 ; i ++){
            System.out.println("斐波那契数列："+ fibFunc(i));
        }
    }
    // 斐波那契数列:获取第n个斐波那契数列数列值
    public static int fibFunc(int n){
        if(n <= 0){
            return -1;
        } else if (n == 1){
            return 1;
        } else if (n == 2){
            return 1;
        } else{
            return fibFunc(n - 1) + fibFunc( n - 2);
        }
    }

}
