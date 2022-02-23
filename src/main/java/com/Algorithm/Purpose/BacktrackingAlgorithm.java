package com.Algorithm.Purpose;

import java.util.*;

import static javax.print.attribute.standard.MediaSizeName.A;

/**
 * 回溯算法：
 *
 * 经典回溯算法问题：
 * 全排列问题
 * 0-1背包问题
 * 八皇后问题
 * 正则表达式
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
//        List<List<Integer>> res = permute(new int[]{1,2,3,4});
//        showList(res);
//        int[] queues = {1,2,4,5,7,6,0,3};
        BacktrackingAlgorithm ba = new BacktrackingAlgorithm();
////        ba.printQueues(queues);
//
//        System.out.println("解法个数：" + ba.calNQueens(0));

        char[][] board = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        String word = "ABCCED";
        boolean res = ba.exist(board,word);
        System.out.println("查找结果：" + res);
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

    /**************
     * LeetCode [39. 组合总和]
     */
    List<List<Integer>> res = new ArrayList();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        cal(0,0,new LinkedList<Integer> (),candidates,target);
        return res;
    }

    private void cal(int idx,int sum,LinkedList<Integer> track,int[] candidates,int target){
        if(idx >= candidates.length || sum > target){
            // 已经遍历到队尾，或者最大值超标，终止回溯
            return ;
        }

        if(sum == target){
            // 筛选完成
            res.add(new ArrayList<Integer>(track));
            return;
        }
        // 使用当前元素
        track.addLast(candidates[idx]);
        cal(idx,sum + candidates[idx],track,candidates,target);

        // 不使用当前元素
        track.removeLast();
        cal(idx + 1,sum,track,candidates,target);
    }

    /****************************************
     * 八皇后问题
     * 可以使用回溯法和动态规划法
     * 引申处理N皇后问题，返回可以实现的结果个数
     */
    public int calNQueens(int n){
        // 计算n皇后问题
        int res = 0;
        int[] queensRes =  new int[n];   // 八皇后问题的最终结果，记录每行元素放置在哪一列，使用全局变量
        res = calQueens(n,queensRes,0,res);
        return res;
    }

    /***********************
     *
     */

    public int calQueens(int N,int[] queens,int row,int res){
//        for(int i = 0; i< queuesRes.length;i ++){
//            System.out.print(queuesRes[i] + ",");
//        }
//        System.out.println();
        // row是行号，每次判断一行的可行值
        if(row == N){
            // 8行已经执行完毕，打印输出
            printQueues(queens);
            res += 1;
            return res;
        }

        for(int col = 0; col < N ; ++ col ){
            if(is_ok(queens,row,col)){
                queens[row] = col;
                res = calQueens(N,queens,row + 1,res);
            }
        }
        return res;
    }
    private boolean is_ok(int[] queens,int row,int col){
        // 判断当前列位置是否ok，N皇后问题的规则是斜着，横竖都不能有重复值
        int left = col - 1, right = col + 1;   // 判断左右斜面是否可行
        for(int i = row - 1;i >= 0; i --){

            if (left == queens[i] && left >= 0){
                return false;
            }
            if (right == queens[i] && right < 8){
                return false;
            }
            if (queens[i] == col){
                return false;
            }
            -- left;
            ++ right;
        }
        return true;
    }

    private void printQueues(int[] res){
        System.out.println("==========================================");
        for(int i = 0; i < res.length; i ++){
            for (int j = 0; j < res.length ;j ++ ){
                if (j == res[i]){
                    System.out.print("@ ");
                } else {
                    System.out.print("* ");
                }
            }
            System.out.println();
        }
    }

    /********************************
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


    /*****
     * LeetCode 剑指 Offer 12. 矩阵中的路径
     * 采用回溯算法
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        // 思路：回溯法，相同元素逐一尝试，看是否可到达，
        // 难点：同一单元格只能用一次，方法是建一个相同的位置矩阵，采用就置1，否则默认是0
        if(word.length() == 0){
            return true;
        }
        boolean[][] flag = new boolean[board.length][board[0].length];
        // 标签数组默认置为false
        for(int i = 0;i < board.length;i ++){
            for (int j = 0; j < board[i].length;j ++){
                flag[i][j] = false;
            }
        }
        // 遍历元素
        for(int i = 0; i < board.length;i ++){
            for (int j = 0; j < board[i].length;j ++){
                if(scan(board,word,flag,0,i,j)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean scan(char[][] board, String word,
                         boolean[][] flag,int idx,
                         int row,int col){

        if(idx >= word.length()){
            return true;
        }
        if(board[row][col] != word.charAt(idx))
            return false;

        flag[row][col] = true;
        boolean res = false;
        idx ++;
        // 前后左右回溯
        if(row + 1 <= board.length - 1 && !flag[row + 1][col]){
            res = scan(board,word,flag,idx,row + 1,col);
            if(res) return res;
        }
        if(row - 1 >= 0 && !flag[row - 1][col]){
            res = scan(board,word,flag,idx,row - 1,col);
            if(res) return res;
        }
        if(col + 1 <= board[row].length - 1 && !flag[row][col + 1]){
            res = scan(board,word,flag,idx,row,col + 1);
            if(res) return res;
        }
        if(col - 1 >= 0 && !flag[row][col - 1]){
            res = scan(board,word,flag,idx,row,col - 1);
            if(res) return res;
        }
        flag[row][col] = false;
        idx --;
        return res;
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
