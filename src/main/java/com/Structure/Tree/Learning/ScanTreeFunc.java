package com.Structure.Tree.Learning;

import java.util.ArrayList;

/**
 * 树的遍历算法：
 * 中序遍历
 * 前序遍历
 * 后序遍历
 * 按层遍历
 */
public class ScanTreeFunc {

    public static void main(String[] args) {
        // 初始化一棵树
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        // 中序遍历一棵树
        ArrayList<Integer> midRes = new ArrayList<Integer>();
        inorderTraversal(root,midRes);
        showArray(midRes);

        // 前序遍历一棵树
        ArrayList<Integer> preoRes = new ArrayList<Integer>();
        preoScan(root,preoRes);
        showArray(preoRes);
        // 后序遍历一棵树
        ArrayList<Integer> lastRes = new ArrayList<Integer>();
        lastScan(root,lastRes);
        showArray(lastRes);
    }
    private static void showArray(ArrayList<Integer> res){
        StringBuilder sb = new StringBuilder();
        for (Integer i:res){
            sb.append("-->");
            sb.append(i);
        }
        System.out.println("遍历结果：" + sb.toString());
    }

    // 中序遍历
    public static void inorderTraversal(TreeNode root,ArrayList<Integer> res){
        if(root == null){
            return;
        }
        inorderTraversal(root.left,res);
        res.add(root.value);
        inorderTraversal(root.right,res);

    }

    // 前序遍历
    public static void preoScan(TreeNode root,ArrayList<Integer> res){
        if(root == null){
            return;
        }
        res.add(root.value);
        preoScan(root.left,res);
        preoScan(root.right,res);
    }

    // 后序遍历
    public static void lastScan(TreeNode root,ArrayList<Integer> res){
        if(root == null){
            return;
        }
        lastScan(root.left,res);
        lastScan(root.right,res);
        res.add(root.value);
    }

}

class TreeNode{
    int value;
    TreeNode left;
    TreeNode right;
    public TreeNode(int value){
        this.value = value;
    }
}
