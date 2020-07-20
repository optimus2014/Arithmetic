package com.Algorithm.Purpose;

import java.util.LinkedList;
import java.util.List;

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
        List<List<Integer>> res = new LinkedList();
        LinkedList<Integer> lk = new LinkedList<Integer>();
        backTrack(nums,lk,res);
        return res;
    }

    private static void backTrack(int[] nums,LinkedList<Integer> track,List<List<Integer>> res){
        if(nums.length == track.size()){
            // 已经完成全量全速入队
            res.add(new LinkedList<Integer>(track));
            return;
        }

        for(int i = 0; i < nums.length ; i ++){
            // 当前元素已存在
            if(track.contains(nums[i])){
                continue;
            }
            // 当前元素入队
            track.add(nums[i]);
            backTrack(nums,track,res);
            track.removeLast();
        }
    }
}
