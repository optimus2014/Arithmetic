package com.Algorithm.Classical;

import sun.security.util.Length;

import java.util.HashSet;
import java.util.Set;

/**
 * 布隆过滤器
 * 用途：快速查找某个元素是否在集合中，是Hash算法的一个变种
 * 步骤：
 * 1.创建一个m长度的2进制容器，初始全部为0，准备k个Hash函数
 * 2.对待检验的集合，进行k个函数的Hash校验，对应m长度容器的位置置为1
 * 3.对待检验元素，进行k个函数的Hash计算，结果对应的容器位置全部为1，则为存在；如果有0元素，则为不存在。
 *
 * 优缺点：
 * 优点：查找快速，Hash算法，复杂度接近于O(1)，
 * 缺点：
 * 1. 有误差率，判断不存在的元素一定不存在，判断为“存在”的元素，可能是Hash有碰撞；
 * 2. 元素只可增不可减少
 *
 * 改进方法：
 * 1.误差率问题：误差不可避免，要选择对误差有一定容忍度的场景进行使用；另外辅助”核心数据白名单“的方式，进行二次校验；
 * 2.使用整数型容器，每出现一次Hash碰撞，数值+1，但是可能会有溢出情况，这种方法不理想。
 */
public class BloomFilter {
    public static void main(String[] args) {
        System.out.println("这是布隆过滤器");
        Set source = new HashSet();
        source.add("a");
        source.add("b");
        BloomFilter bf = new BloomFilter(10,source,3);
        System.out.println(bf.b.length);

    }


    // 二进制容器
    private boolean[] b;

    /**
     * 参数释义：
     * n：BloomFliter容器长度
     * sourceSet：原始待检验的Set集合
     * k：Hash函数个数。
     * */
    public BloomFilter(int n, Set sourceSet, int k){
        this.b = new boolean[n];
        this.b[n/2] = true;
        for (boolean i : this.b) {
//            i = false;
            System.out.println(i);

        }   // 这一句可能就不需要，默认全部是False


    }



    // 检验函数，判断Item是否在原始集合中
    public boolean checkItem(String item){
        return true;
    }
    // 添加元素
    public void addItem(String item){
        // this.b
    }

    // Hash 函数内部类
//    https://www.cnblogs.com/xiaobaituyun/p/11011393.html
}


