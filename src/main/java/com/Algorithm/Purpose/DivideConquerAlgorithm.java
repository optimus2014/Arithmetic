package com.Algorithm.Purpose;


import java.util.ArrayList;
import java.util.HashMap;

/****
 * 分治算法
 * 5大算法之一，采用分而治之的思路，将所有问题拆分成多个相同计算逻辑的小问题，分别计算
 * 核心：
 * 1.拆分成相同计算逻辑的小问题
 * 2.确定终止边界
 *
 * 分治算法属于递归的一种，数据量较大时候需要考虑溢出问题，采用尾递归进行规避溢出
 * 样例：
 * 1.MapReduce算法就是应用了分治算法，是采用外部多节点的分治；
 * 2.快速排序算法也是分治算法
 */
public class DivideConquerAlgorithm {

    public static void main(String[] args) {
        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};

        DivideConquerAlgorithm dca = new DivideConquerAlgorithm();

        TreeNode root = dca.buildTree(preorder,inorder);

        ArrayList<Integer> res = new ArrayList<Integer>();
        lastScan(root,res);
        showArray(res);

        int a = 0,  b = 1;
    }

    public HashMap<Integer,Integer> keys = new HashMap<Integer,Integer>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // 思路：分治算法，找到根节点，前序变量之前的元素都是左子树，后面的元素都是右子树。

        // 把中序遍历位置构建字典型数据结构，便于查找。
        for(int idx = 0; idx < inorder.length; idx ++){
            keys.put(inorder[idx], idx);
        }

        TreeNode root = getRoot(preorder,0, preorder.length - 1,
                inorder, 0,  inorder.length - 1);
        return root;
    }

    private TreeNode getRoot(int[] preorder,int p_start,int p_end,
                             int[] inorder ,int i_start,int i_end){
        if(p_start > p_end){
            return null;
        }
        // 找到根节点的元素
        int left_r = preorder[p_start];
        TreeNode root = new TreeNode(left_r);


        int root_addr = keys.get(left_r);  // 中序遍历根节点的位置
        int left_len = root_addr - i_start;  // 左子树长度
        // 左子树
        root.left = getRoot(preorder,p_start+1,p_start + left_len,
                inorder,i_start,root_addr - 1);
        // 右子树
        root.right = getRoot(preorder,p_start + left_len + 1, p_end,
                inorder,root_addr + 1, i_end);

        return root;
    }


    class TreeNode{
        TreeNode(int value){
            this.value = value;
        }
        int value = 0;
        TreeNode left = null;
        TreeNode right = null;
    }

    // 后序遍历
    public static void lastScan(TreeNode root, ArrayList<Integer> res){
        if(root == null){
            return;
        }
        lastScan(root.left,res);
        lastScan(root.right,res);
        res.add(root.value);
    }

    private static void showArray(ArrayList<Integer> res){
        StringBuilder sb = new StringBuilder();
        for (Integer i:res){
            sb.append("-->");
            sb.append(i);
        }
        System.out.println("遍历结果：" + sb.toString());
    }
}


