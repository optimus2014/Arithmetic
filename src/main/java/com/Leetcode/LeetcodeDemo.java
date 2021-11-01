package com.Leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class LeetcodeDemo {
    public static void main(String[] args) {

        LeetcodeDemo.show(LeetcodeDemo.twoSum(new int[]{3,2,4},6));
        LeetcodeDemo.show(LeetcodeDemo.generateParenthesis(2));
        LeetcodeDemo.show(LeetcodeDemo.generateParenthesis(3));
        LeetcodeDemo.show(LeetcodeDemo.generateParenthesis(4));

        /**
         * "(())","()()"
         * ["((()))","(()())","(())()","()(())","()()()"]
         * ["(((())))","((()()))","((())())","((()))()","(()(()))","(()()())","(()())()","(())(())","(())()()","()((()))","()(()())","()(())()","()()(())","()()()()"]
         */

    }
    public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        map.put(nums[0],0);
        for(int i = 1; i < nums.length ; i ++){
            int diff = target - nums[i];
            if (map.containsKey(diff)){
                return new int[]{map.get(diff),i};
            } else {
                map.put(nums[i],i);
            }
        }
        return new int[]{-1,-1};
    }

    private static void show(int[] data){
        for(int d : data){
            System.out.print(d + "-> ");
        }
        System.out.println();
    }
    private static void show(List data){
        for(Object d : data){
            System.out.print(d + "-> ");
        }
        System.out.println();
    }


    public static List<String> generateParenthesis(int n) {
        // 归纳法
        LinkedList<String> result = new LinkedList<String>();
        result.add("()");
        for (int i = 2;i <= n ; i++){
            int len = result.size();
            for(int j = 0; j < len; j ++){
                result.add("(" + result.get(j) + ")");
                if(!(result.get(j) + "()").equals("()" + result.get(j))){
                    result.add("()" + result.get(j));
                    result.add(result.get(j) + "()");
                } else {
                    result.add(result.get(j) + "()");
                }

            }
            // 删除无效
            for(int j = 0; j < len; j ++){
                result.remove(0);
            }
        }
        return result;
    }


    /***************************
     * 单词转换问题，[面试题 17.22]
     */
    /***
     * 图的连接性问题
     * 使用深度优先算法（DFS）进行图遍历
     */
    List<String> wl;
    String bw;
    String ew;
    List<String> result =  new ArrayList<String>();
    List<String> fake =  new LinkedList<String>();  // 临时队列
    boolean[] flag; // 标识当前元素是否已遍历到
    public List<String> findLadders(String beginWord,
                                    String endWord, List<String> wordList) {
        bw = beginWord;
        ew = endWord;
        wl = wordList;
        dfs(bw); // 从起始字段开始搜索
        return result;
    }

    // 从元素str，开始深度优先遍历
    private boolean dfs(String str) {
        List<String> nears = near(str);
        // 如果队列为空
        if (nears.size() == 0) {
            return false;
        }

        for (String s : nears) {
            fake.add(s);
            if (s.equals(ew)) {
                result.add(bw); // 已经找到可达路径，添加起始字段
                for (String f: fake){
                    result.add(f);
                }
                return true;
            }

            if(dfs(s)){
                // 如果dfs算法返回结果是true，透传出去
                return true;
            } else{
                // dfs没有找到指定元素，删除临时队列队尾值
                fake.remove(fake.size() - 1);
            }
        }
        //nears 不为空，但是没有找到指定的元素
        return false;
    }

    // 找出原始数组中，元素的邻近节点
    private List<String> near(String str){
        List<String> res = new ArrayList<String>();
        for (int i = 0; i <wl.size() ; i++ ){
            if (flag[i] || str.length() != wl.get(i).length()){
                // 长度不一致，或者当前元素已访问过，跳过
                continue;
            }
            int diff = 0;
            for(int j = 0; j < str.length();j ++){
                if(str.charAt(j) != wl.get(i).charAt(j)){
                    diff ++;
                }
                if (diff == 2){
                    break;
                }
            }

            if (diff == 1){
                // 只有一个元素不同，符合邻居节点属性
                res.add(wl.get(i));
                flag[i] = true;
            }
        }
        return res;
    }
}
