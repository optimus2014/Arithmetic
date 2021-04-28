package com.Structure.Graph.Learning;

/**
 * 图算法：
 * 1.最短路径
 * 2.频繁子图挖掘
 * 3.社区发现
 * 4.最小生成树
 * 5.判断是否有环
 * 6.判断是否是连通图（可以连接的子图），连通分量
 * 7.拓扑排序
 */

public class GraphAlgorithms {
    public static void main(String[] args) {

    }


    enum ALGORITHMS {
        Dijkstra("最短路径","迪杰斯特拉"),
        Floyd("最短路径","弗洛伊德"),
        BellmanFord("最短路径","贝尔曼-福特");


        private String algoType;
        private String algoName;
        private ALGORITHMS(String algoType,String algoName){
            this.algoName = algoName;
            this.algoType = algoType;
        }
    }

    /****************
     * 最短路径问题：
     * 判断两点是否可到达，最少几跳可到达，或者最小权限加权
     * 经典算法：
     * 1.迪杰斯特拉 Dijkstra（解决单源最短路径）
     * 2.弗洛伊德 Floyd
     * 3.贝尔曼-福特 Bellman-Ford
     */
    public static int shortestPaths(Graph graph,GraphItem start, GraphItem end){
        // 默认使用Dijkstra算法
        return shortestPaths(graph,start,end,ALGORITHMS.Dijkstra);
    }
    public static int shortestPaths(Graph graph,GraphItem start, GraphItem end,ALGORITHMS algo){
        // 类似于工厂方法
        switch (algo) {
            case Dijkstra:
                return shortestDijkstra(graph, start, end);
            case Floyd:
                return shortestFloyd(graph, start, end);
            case BellmanFord:
                return shortestBellman(graph, start, end);
            default:
                return 0;
        }
    }
    // Dijkstra算法
    private static int shortestDijkstra(Graph graph,GraphItem start, GraphItem end){
        return 0;
    }
    // Floyd算法
    private static int shortestFloyd(Graph graph,GraphItem start, GraphItem end){
        return 0;
    }
    // Bellman算法
    private static int shortestBellman(Graph graph,GraphItem start, GraphItem end){
        return 0;
    }
}
