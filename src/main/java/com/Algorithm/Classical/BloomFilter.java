package com.Algorithm.Classical;


import jdk.nashorn.internal.ir.debug.ObjectSizeCalculator;

import java.util.BitSet;
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
 *
 * 创建Hash函数库
 *
 * TODO：
 * 1. 搞清楚正确率计算方法
 * 2. 搞清楚不同数据量需要适配的Hash函数个数
 * 3. 计算误算率（False Positive）
 */
public class BloomFilter {
    public static void main(String[] args) {
        System.out.println("这是布隆过滤器");
        Set<String> source = loadDataSet(data);
        BloomFilter bf = new BloomFilter(2<<15,3);
        for (String item : source){
            bf.add(item);
        }
        System.out.println("K：" + bf.seedLen);
        System.out.println("存在：" + bf.checkItem("哈"));
        System.out.println("存在：" + bf.checkItem("这是Hash"));

        System.out.println("原始数据大小：" + ObjectSizeCalculator.getObjectSize(data));
        System.out.println("转化之后的Set大小：" + ObjectSizeCalculator.getObjectSize(source));
        System.out.println("BloomFilter大小：" + ObjectSizeCalculator.getObjectSize(bf.bloom));
        System.out.println("BloomFilter：" + bf.bloom);
        bf.add("眼");
        System.out.println("存在：" + bf.checkItem("眼"));

    }

    /**
     * 加载数据，转化为Set
     * @param data1
     * @return
     */
    public static Set<String> loadDataSet(String data1){
        Set<String> source = new HashSet<String>();
        for(int i = 0 ; i < data1.length(); i ++){
            if(data1.charAt(i) == '\n' || data1.charAt(i) == '\0' || data1.charAt(i) == ' '){
                continue;
            }
            source.add(String.valueOf(data1.charAt(i)));
        }
        System.out.println("转化之后的Set长度：" + source.size());
        return source;
    }

    /****************** 以下是BloomFilter代码 *********************/

    private static final int DEFAULT_SIZE = 2 << 10;
    private static final int[] seeds = {2,4,12,54,7,3,27,32,1,45,25,98,43,6};
    private static final HashOperator[] func = new HashOperator[seeds.length];

    // 二进制容器
    private BitSet bloom;
    private int seedLen = seeds.length;

    // 静态方法，JVM加载类时进行初始化
//    static {
//        for(int i = 0; i < seeds.length; i ++){
//            func[i] = new HashOperator(DEFAULT_SIZE,seeds[i]);
//        }
//    }
    private void init(int cap){
        this.bloom = new BitSet(cap);
        for(int i = 0; i < seeds.length; i ++){
            func[i] = new HashOperator(cap,seeds[i]);
        }
    }

    /**
     * 参数释义：
     * n：BloomFliter容器长度
     * sourceSet：原始待检验的Set集合
     * k：Hash函数个数。
     * */
    public BloomFilter(){
        init(DEFAULT_SIZE);
    }
    public BloomFilter(int n, int k){
        // 获取seed种子长度
        if(k > seeds.length || k <= 0){
            // k 超出现有种子库长度时，模式使用最长种子库
            this.seedLen = seeds.length;
        } else {
            this.seedLen = k;
        }
        // 初始化BF容器大小
        this.bloom = new BitSet(n);
        init(n);
    }


    // 检验函数，判断Item是否在原始集合中
    public boolean checkItem(String item){
        if(item == null){
            return false;
        }
        for(int i = 0; i < seedLen; i ++){
            if(!bloom.get(func[i].hash(item))){
                return false;
            }
        }
        return true;
    }
    // 添加元素
    public void add(String item){
        for(int i = 0; i < seedLen; i ++){
            bloom.set(func[i].hash(item));
        }
    }

    /*******************
     * Hash函数相关内容
     * TODO:Hash函数做的还有问题，所有seed得到的最终结果都一样
     */
    // Hash 函数内部类(函数可以替换，MD5就是一个经典的Hash函数)
    public static class HashOperator {
        private int cap;   // ? cap是分桶数？
        private int seed;  // 不同Hash函数的种子不同
        public HashOperator(int cap,int seed){
            this.cap = cap;
            this.seed = seed;
        }

        public int hash(String value){
            // Hash函数方法待定
            int result = 0;
            int len = value.length();
            for(int i = 0;i < len; i ++){
                // 注：之前遇到的一个坑，字符串长度为1时，seed失效，for循环只会遍历一个数值
                result = (result << this.seed)  + value.charAt(i) >> this.seed;
            }
            return  (this.cap - 1) & result;  //
        }
    }

    static String data= "值得注意的是，4 这个 bit 位由于两个值的哈希函数都返回了这个 bit 位，因此它被覆盖了。现在我们如果想查询 “dianping” 这个值是否存在，哈希函数返回了 1、5、8三个值，结果我们发现 5 这个 bit 位上的值为 0，说明没有任何一个值映射到这个 bit 位上，因此我们可以很确定地说 “dianping” 这个值不存在。而当我们需要查询 “baidu” 这个值是否存在的话，那么哈希函数必然会返回 1、4、7，然后我们检查发现这三个 bit 位上的值均为 1，那么我们可以说 “baidu” 存在了么？答案是不可以，只能是 “baidu” 这个值可能存在。这是为什么呢？答案跟简单，因为随着增加的值越来越多，被置为 1 的 bit 位也会越来越多，这样某个值 “taobao” 即使没有被存储过，但是万一哈希函数返回的三个 bit 位都被其他值置位了 1 ，那么程序还是会判断 “taobao” 这个值存在。";
}


