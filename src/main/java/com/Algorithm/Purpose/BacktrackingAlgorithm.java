package com.Algorithm.Purpose;

import java.util.*;

import static javax.print.attribute.standard.MediaSizeName.A;

/**
 * 回溯算法：
 * 全排列问题
 * 0-1背包问题
 * 八皇后问题
 *
 * 回溯算法可以简单归结为：
 *
 * def backTrack(路径，选择列表[维护的状态]){
 *     if 满足条件：
 *         result.add(路径)
 *         return;
 *
 *     for 选择 in 选择列表：
 *         已存在：
 *              continue
 *         做选择（添加到选择列表中）
 *         backTrack(路径，选择列表)
 *         撤销选择（回溯）
 * }
 */
public class BacktrackingAlgorithm {

    public static void main(String[] args) {
        List<List<Integer>> res = permute(new int[]{1,2,3,4});

        showList(res);
    }
    private static void showList(List<List<Integer>> res){
        for (int i = 0 ; i < res.size() ; i ++){
            StringBuilder sb = new StringBuilder();
            for(int j = 0;j < res.get(i).size() ; j ++){
                sb.append(" "+ res.get(i).get(j));
            }
            System.out.println("-->"+sb.toString());
        }
    }


    /**
     * 数组全排列问题
     *
     */


    public static List<List<Integer>> permute(int[] nums) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList();
        if(len == 0){
            return res;
        }

        Deque<Integer> path = new ArrayDeque<Integer>();
        boolean[] flag = new boolean[len];  // default false
        backTrack(nums,len,0,path,flag,res);
        return res;
    }

    private static void backTrack(int[] nums,int len,int depth,Deque<Integer> path,boolean[] flag,List<List<Integer>> res){
        if(depth == len){
            res.add(new ArrayList<Integer>(path));
            return;
        }

        for(int i = 0; i < len ; i++){
            if(flag[i]){
                continue;
            }
            flag[i] = true;
            path.addLast(nums[i]);
            backTrack(nums,len,depth + 1,path,flag,res);
            flag[i] = false;
            path.removeLast();
        }

    }


    public String[] permutation(String s) {
        HashSet<String> res = new HashSet<String>();
        int len = s.length();
        if(len == 0){
            String[] array = new String[]{};
            return array;
        }


        boolean[] flag = new boolean[len];
        StringBuilder sb = new StringBuilder();
        backTrack(s,len , 0 ,flag ,sb ,res);

        String[] array = (String[])res.toArray(new String[res.size()]);
        return array;
    }

    private void backTrack(String s,int len ,int depth,boolean[] flag,StringBuilder sb ,HashSet<String> res){
        if(len == depth){
            res.add(sb.toString());
            return;
        }

        for(int i = 0; i< len; i ++){
            if(flag[i]){
                continue;
            }

            flag[i] = true;
            sb.append(s.charAt(i));
            backTrack(s,len , depth + 1 ,flag ,sb ,res);
            sb.deleteCharAt(sb.length()-1);
            flag[i] = false;
        }
    }
}

// 字符串排列的高级写法
class Solution {
    List<String> result=new ArrayList();
    char[] c;
    public String[] permutation(String s) {
        c=s.toCharArray();
        dfs(0);
        return result.toArray(new String[result.size()]);
    }
    public void dfs(int x){
        if (x==c.length-1){
            result.add(String.valueOf(c));
            return;
        }
        HashSet<Character> set=new HashSet();       // 貌似只能处理没有重复元素的值
        for (int i=x;i<c.length;i++){
            if (set.contains(c[i])){
                continue;
            }
            set.add(c[i]);
            swap(i,x);      // 交换待分支节点和其他节点
            dfs(x+1);
            swap(i,x);
        }
    }
    public void swap(int a,int b){
        char temp=c[a];
        c[a]=c[b];
        c[b]=temp;
    }
}
