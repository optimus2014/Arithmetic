package com.Algorithm.Search;

/***
 * 7大查找算法：(需要已排序列表)
 * 1.二分查找：需要有序
 * 2.插值插值？这是啥
 * 3.查找二叉树：中序遍历得到有序数列
 * 4.Hash查找
 * 5.分块查找
 * 6.斐波那契查找
 * 加分题：
 * 1.红黑树：平衡查找树
 * 2.B+树
 *
 *
 * redis中使用跳表实现有序集合
 */
public class SearchFunc {
    public static void main(String[] args) {
        // 未找到返回-1

//        System.out.println(1/2);
//        System.out.println("二分查找算法结果：index=" + binarySearch(new int[]{1,2,3,4},3));
        SearchFunc sf = new SearchFunc();
        int[][] matrix = {
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}};
        boolean res = sf.findNumberIn2DArray(matrix,17);
        System.out.println("结果：" + res);
    }

    // [0,1] [0]
    public static int binarySearch(int[] data,int value){
        int front = 0;
        int tail = data.length - 1;
        while (front <= tail){
            int tmp_i = (front + tail) / 2;
            if(value < data[tmp_i]){
                tail = tmp_i - 1;
            } else if(value > data[tmp_i]){
                front = tmp_i + 1;
            } else{
                return tmp_i;
            }
        }
        return -1;
    }


    /**
     * 使用二分查找，计算一个数的平方根，要求精确到小数点后6位。
     * 使用近似法
     */
    public static float sqrtByBinary(int num){
        return 0;
    }


    /**
     * 二维矩阵，二分查找算法
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        // 思路：二分查找，二维空间上的二分查找算法
        if(matrix.length == 0){
            return false;
        }

        int row_f = 0,col_f = 0;  // 横纵坐标下标
        int row_t = matrix.length - 1;
        int col_t = matrix[0].length - 1;

        while (col_f < col_t){
            if (target > matrix[0][(col_f + col_t) / 2]){
                col_f = (col_f + col_t) / 2 + 1;
            } else if (target < matrix[0][(col_f + col_t) / 2]) {
                col_t = (col_f + col_t) / 2 - 1;
            } else {
                return true;
            }
        }

        while(row_f < row_t){
            if (target > matrix[(row_f + row_t) / 2][0]){
                row_f = (row_f + row_t) / 2 + 1;
            } else if (target < matrix[(row_f + row_t) / 2][0]) {
                row_t = (row_f + row_t) / 2 - 1;
            } else {
                return true;
            }
        }

        for(int i = col_t; i >= 0; i --){
            for (int j = row_t;j >= 0; j--){
                if(target == matrix[j][i]){
                    return true;
                }
            }
        }

        return false;
    }
}
