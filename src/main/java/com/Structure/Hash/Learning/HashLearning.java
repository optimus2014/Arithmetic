package com.Structure.Hash.Learning;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 构造散列函数，基本要求：
 * 1.计算得到的散列值是非负整数
 * 2.如果 key1 = key2，那 hash(key1) == hash(key2)；
 * 3.如果 key1 ≠ key2，那 hash(key1) ≠ hash(key2)。
 *
 *
 * Hash需要解决的问题：
 * 散列函数、装载因子、散列冲突
 *
 * 考点：
 * 散列因子
 * 动态扩容
 * 解决冲突的方法：开放寻址法【数组】、链表法
 *
 *
 * Hash算法
 */
public class HashLearning {

    public static void main(String[] args) {
        System.out.println("Hash函数结果：" + containsDuplicate(new int[]{1,2,3,1,5,6}));
        System.out.println("Hash函数结果：" + containsDuplicate(new int[]{1,2,3,-1,-5,6}));
        System.out.println("Hash函数结果：" + containsDuplicate(new int[]{2,14,18,22,22}));
//        System.out.println("数组交集的结果：" + intersection(new int[]{2,14,18,22,22},new int[]{2,14,14}));
        System.out.println("是否是快乐数：" + isHappy(19));

        System.out.println("求和：" + showContent(twoSum(new int[]{1,14,18,22,23},45)));

        System.out.println("求和：" + isIsomorphic("egs","asa"));
    }

    private static String showContent(int[] content){
        StringBuilder sb = new StringBuilder();
        sb.append(content[0]);
        for(int i = 1;i < content.length;i++){
            sb.append(",");
            sb.append(content[i]);
        }
        return sb.toString();
    }

    /**
     * 注意：不一定是正整数
     * @param nums
     * @return
     */
    public static boolean containsDuplicate(int[] nums) {
        // 设置分桶数
        HashSet bocket = new HashSet();
        bocket.add(nums[0]);
        for(int i = 1; i < nums.length ; i ++ ){

            if(bocket.contains(nums[i])){
                return true;
            } else {
                bocket.add(nums[i]);
            }
        }
        return false;


    }

    /**
     * 给定两个数组，编写一个函数来计算它们的交集
     * 示例 1:
     * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
     * 输出: [2,2]
     * 示例 2:
     * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
     * 输出: [4,9]
     * 说明：
     * 输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致。
     * 我们可以不考虑输出结果的顺序。
     *
     * 思路：双指针
     *
     */
    public static int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        HashSet<Integer> set = new HashSet<Integer>();
        int nums1_point = 0;
        int nums2_point = 0;
        while(nums1_point < nums1.length && nums2_point < nums2.length){

//            for(int i = nums2_point; i < nums2.length ; i ++){
//                if(nums1[nums1_point] == nums2[nums2_point]){
//                    set.add(nums1[nums1_point]);
//                    nums1_point += 1;
//                    nums2_point += 1;
//                    break;
//                } else if(i == )
//            }
        }
    return nums1;
    }

    public static boolean isHappy(int n) {
        ArrayList tmp = new ArrayList();
        while(true){
            int result = calNum(n);
            if(result == 1){
                return true;
            } else if (tmp.contains(result)){
                return false;
            } else {
                tmp.add(result);
                n = result;
            }
    }

}

    private static int calNum(int n) {
        int tmp = 0;
        while( n > 0){
            tmp = tmp + (int)Math.pow((n %10) ,2);
            n = n / 10;
        }
        return tmp;
    }

    public static int[] twoSum(int[] nums, int target) {
        // 思路：数组已排序，前后双指针

        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        for(int i = 0; i< nums.length;i++){
            int diff = target - nums[i];
            if(map.containsKey(diff)){
                return new int[]{map.get(diff),i};
            } else {
                map.put(nums[i],i);
            }
        }
        return new int[]{-1,-1};

    }

    public static boolean isIsomorphic(String s, String t) {
        HashMap<Character,Character> map = new HashMap<Character,Character>();
        HashSet<Character> set = new HashSet<Character>();
        for(int i = 0; i< s.length();i++){
            if(!map.containsKey(s.charAt(i))){
                // 判断Hash是不是一一对应
                if(set.contains(t.charAt(i))){
                    return false;
                } else{
                    map.put(s.charAt(i),t.charAt(i));
                    set.add(t.charAt(i));
                }
            } else {
                if(map.get(s.charAt(i)).equals(t.charAt(i))){
                    continue;
                } else {
                    return false;
                }
            }

        }
        return true;
    }
}
