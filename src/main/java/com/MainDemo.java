package com;

import java.util.*;

public class MainDemo {
    public static void main(String[] args) {
        System.out.println("主函数");
//        System.out.println((2+1)/2);
//
//        System.out.println(MainDemo.check(12,34));
//        System.out.println(MainDemo.check(0,0));
//        System.out.println(MainDemo.check(10,2));
        MainDemo md = new MainDemo();
//        System.out.println(md.movingCount(100,100,20));
//
//        System.out.println(md.movingCount2(100,100,20));
//        System.out.println(md.myPow(3.3,2));
//        System.out.println(md.myPow(3.3,-2));
//        System.out.println(md.myPow(3.3,1));
//        System.out.println(md.myPow(3.3,0));
//        int[] data = {1,2,3,4};
//        System.out.println(md.exchange(data));
//        int[] in = {1,0};
//        int[] out = {1,0};
//        System.out.println(md.validateStackSequences(in, out));
        //"678","45"
//        System.out.println(md.multiply("498828660196", "840477629533"));
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(3);
        System.out.println(md.isSymmetric(root));
    }

    /***
     * 判断对称二叉树
     */
    public boolean isSymmetric(TreeNode root) {
        // 思路：使用队列实现按层遍历，使用栈判断是否对称，需要注意的点：元素值一样，但是左右子树不一样
        Queue<TreeNode> queues = new LinkedList<TreeNode>();
        queues.add(root.left);
        queues.add(root.right);

        while (!queues.isEmpty()){
            Stack<TreeNode> stack = new Stack<TreeNode>();
            int len = queues.size();
            for(int i = 0 ; i < len ; i ++){
                TreeNode tmp = queues.poll();
                if(tmp != null) {
                    queues.add(tmp.left);
                    queues.add(tmp.right);
                }
                if (i < len / 2){
                    // 前一半元素添加到栈中
                    stack.add(tmp);
                } else {
                    // 后一半元素出栈判断
                    TreeNode stack_tmp = stack.pop();
                    if ((tmp == null && stack_tmp == null) ){
                        continue;
                    }else if (tmp != null && stack_tmp != null && tmp.val == stack_tmp.val){
                        continue;
                    } else {
                        return false;
                    }
                }
            }
            if( !stack.isEmpty() ){
                return false;
            }
        }
        return true;
    }

    /***
     * 字符串数字相乘
     */
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")){
            return "0";
        } else if (num1.equals("1")){
            return num2;
        } else if (num2.equals("1")){
            return num1;
        }
        StringBuilder sb = new StringBuilder();
        int num1_l = num1.length();
        int num2_l = num2.length();
        // 乘法计算最终结果的记录数组
        int[] res = new int[num1_l + num2_l];
        // 模拟乘法，两个数字的计算
        for( int i = num1_l - 1; i >= 0; i --){
            for (int j = num2_l - 1; j >= 0; j --){
                // 当前计算影响到的最终结果数组位置,总共两位
                // 影响的最小位数下标是：(res.length - 1) - [(num1_l - 1 - i) + (num2_l - 1 - j)] ; 简化计算后是下列公式
                int idx = i + j + 1;
                int val = (int)(num1.charAt(i) - '0') * (int)(num2.charAt(j) - '0');
                // 需要考虑 相加后进一位的情况
                res[idx] += val % 10;
                if (res[idx] >= 10){
                    res[idx - 1] += 1;
                    res[idx] = res[idx] - 10;
                }
                res[idx - 1] += val / 10;
                if (res[idx - 1] >= 10){
                    res[idx - 2] += 1;
                    res[idx - 1] = res[idx - 1] - 10;
                }
            }
        }
        // 计算最终结果的起始下标，第一个数字为0，则从1下标开始
        int start = res[0] == 0 ? 1:0;
        for(int i = start; i < res.length; i ++){
            sb.append(res[i]);
        }
        return sb.toString();
    }
    /**
     * 按层打印二叉树
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queues = new LinkedList<TreeNode>();
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        queues.add(root);
        while(!queues.isEmpty()){
            ArrayList<Integer> tmp = new ArrayList<Integer>();
            int s = queues.size();
            for (int i = 0; i < s; i ++){
                TreeNode tmp_node = queues.poll();
                if(tmp_node.left != null) queues.add(tmp_node.left);
                if(tmp_node.right != null) queues.add(tmp_node.right);
                tmp.add(tmp_node.val);
            }
            res.add(tmp);
        }

        return res;
    }

    /**
     * 模拟栈的压入，转出
     * @param pushed
     * @param popped
     * @return
     */
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        /**
         * 思路：模拟压栈、出栈的操作
         */
        if(pushed.length == 0 && popped.length == 0){
            return true;
        }
        int p = 0, q = 0;
        LinkedList<Integer> stack = new LinkedList<Integer>();
        stack.add(pushed[p]);
        p += 1;
        while(p < pushed.length || q < popped.length){
            while(p < pushed.length && (stack.size() == 0 || stack.get(stack.size() - 1) != popped[q])){
                stack.add(pushed[p]);
                p += 1;
            }

            while (q < popped.length && stack.size() > 0 && stack.get(stack.size() - 1) == popped[q]){
                stack.removeLast();
                q += 1;
            }
            if (p == pushed.length &&q < popped.length && stack.get(stack.size() - 1) != popped[q]){
                return false;
            }
        }

        return true;
//        if(stack.size() >= 0){
//            return false;
//        } else {
//            return true;
//        }
    }


    /**
     * 剑指offer第21题，调整数组顺序使奇数位于偶数前面
     * @param nums
     * @return
     */
    public int[] exchange(int[] nums) {
        // 使用双指针的方法
        int p = 0, q = nums.length -1;
        while(p < q){
            while(p < q && (nums[p] & 1) == 1){
                p += 1;
            }
            while (p < q && ((nums[q] & 1) == 0)){
                q -= 1;
            }
            if((nums[p] & 1) == 0 && (nums[q] & 1) == 1){
                int tmp = nums[p];
                nums[p] = nums[q];
                nums[q] = tmp;
                p += 1;
                q -= 1;
            }
        }
        return nums;
    }


    // 实现次方函数
    public double myPow(double x, int n) {
        // 重点关注负数的次方问题，以及大数问题
        if (n == 0){
            return 1;
        }

        if (n == 1)
            return x;
        if (n == -1)
            return 1 / x;
        boolean flag = true;

        if (n < 0) {
            flag = false;
            n = -1 * n;
        }
        double res = myPow(x,n >> 1);
        res = res * res;

        if(flag){
            return res;
        } else {
            return 1/ res;
        }
    }

    // 机器人运动范围，方法一：使用回溯法
    public int movingCount(int m, int n, int k) {
        int[][] flag = new int[m][n];
        for(int i = 0; i < m ; i++){
            for (int j = 0; j < n;j ++){
                flag[i][j] = -1;
            }
        }
        move(m,n,k,0,0,flag);

        int res = 0;
        for(int i = 0; i < m ; i++){
            for (int j = 0; j< n;j ++){
                if (flag[i][j] >= 0){
                    res ++;
                }
            }
        }
        return res;
    }
    // 注：这个方法用的是回溯算法
    private void move(int m, int n,int k,int r,int c,int[][] flag){
        if (r < 0 || r >= m || c < 0 || c >= n){
            return;
        }
        int v = check(r,c);
        if (v > k || flag[r][c] >= 0){
            return;
        }
        flag[r][c] = v;
        move(m,n,k,r - 1,c,flag);
        move(m,n,k,r + 1,c,flag);
        move(m,n,k,r,c + 1,flag);
        move(m,n,k,r,c - 1,flag);
    }

    // 判断当前位置是否符合条件
    private static int check(int row,int col){
        int res = 0;
        while (row >= 10){
            res += row % 10;
            row = row / 10;
        }
        res += row;

        while (col >= 10){
            res += col % 10;
            col = col / 10;
        }
        res += col;
        return res;
    }


    // 机器人运动范围的方法二：使用广度优先算法遍历图
    public int movingCount2(int m, int n, int k) {
        int[][] flag = new int[m][n];
        // flag数组记录三种状态，-1表示不可到达，0表示可到达，1表示已经遍历
        for(int i = 0; i < m ; i++){
            for (int j = 0; j < n;j ++){
                int v = check(i,j);
                if (v > k ){
                    flag[i][j] = -1;
                } else {
                    flag[i][j] = 0;
                }
            }
        }

        int res = 0;
        Queue<HashMap<String,Integer>> queues = new ArrayDeque<HashMap<String, Integer>>();
        if(flag[0][0] == 0){

            HashMap item = new HashMap<String, Integer>();
            item.put("row",0);
            item.put("col",0);
            queues.add(item);
            flag[0][0] = 1;
        }
        while (queues.size() > 0){
            if(queues.isEmpty()){
                break;
            }
            HashMap<String,Integer> tmp = queues.poll();
            res += 1;
            int row = tmp.get("row");
            int col = tmp.get("col");
            if(row >= 0 && row + 1 < m && col >= 0 && col < n && flag[row + 1][col] == 0){
                HashMap item = new HashMap<String, Integer>();
                item.put("row",row + 1);
                item.put("col",col);
                queues.add(item);
                flag[row + 1][col] = 1;
            }
            if(row - 1 >= 0 && row < m && col >= 0 && col < n && flag[row - 1][col] == 0){
                HashMap item = new HashMap<String, Integer>();
                item.put("row",row - 1);
                item.put("col",col);
                queues.add(item);
                flag[row - 1][col] = 1;
            }
            if(row >= 0 && row < m && col >= 0 && col + 1 < n && flag[row][col + 1] == 0){
                HashMap item = new HashMap<String, Integer>();
                item.put("row",row);
                item.put("col",col + 1);
                queues.add(item);
                flag[row][col + 1] = 1;
            }
            if(row >= 0 && row - 1 < m && col - 1 >= 0 && col < n && flag[row][col - 1] == 0){
                HashMap item = new HashMap<String, Integer>();
                item.put("row",row);
                item.put("col",col - 1);
                queues.add(item);
                flag[row][col - 1] = 1;
            }
        }

        return res;
    }

}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
 }
