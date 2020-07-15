package com.Structure.Queue.LeetCode;

public class Solution {
    public static void main(String[] args) {
        char[][] grid = {{'1','1','0','0','0'},
                        {'1','1','0','0','0'},
                        {'0','0','1','0','0'},
                        {'0','0','0','1','1'}};
        Solution sl = new Solution();
        int count = sl.numIslands(grid);
        System.out.println("岛屿数量：" + count);

    }
    /**
     * 岛屿数量
     * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
     * 岛屿总是被水包围，并且每座岛屿只能由水平方向或竖直方向上相邻的陆地连接形成。
     * 此外，你可以假设该网格的四条边均被水包围。
     * 输入:
     * [
     * ['1','1','0','0','0'],
     * ['1','1','0','0','0'],
     * ['0','0','1','0','0'],
     * ['0','0','0','1','1']
     * ]
     * 输出: 3
     * 解释: 每座岛屿只能由水平和/或竖直方向上相邻的陆地连接而成。
     *
     * 结题思路：
     * 1.使用广度优先，勾勒出岛屿边界，计数为1
     * 2.扫描过的岛屿均置为0，放置回溯，每次从零遍历，直至全部归零。
     */
     public int numIslands(char[][] grid) {
        int count = 0;
        for(int i = 0; i < grid.length ; i ++){
            for (int j = 0; j < grid[i].length;j ++){
                if(grid[i][j] == '1'){
                    searchIslands(grid,i,j);
                    count = count + 1;
                }
            }
        }
        return count;
     }
     private void searchIslands(char[][] grid,int i,int j){
        if(grid == null || (i < 0 ||i >= grid.length) || (j < 0 || j >= grid[i].length)){
            return;
        }
        if(grid[i][j] != '1'){
            return;
        }

        grid[i][j] = '0';
        // 对当前结点上下左右扫描
        searchIslands(grid,i + 1,j);
         searchIslands(grid,i - 1,j);
         searchIslands(grid,i ,j + 1);
         searchIslands(grid,i,j - 1);
     }
}
