package com.Structure.Graph.Learning;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
                return shortestDijkstra(graph,start, end).get(start);
            case Floyd:
                return shortestFloyd(graph, start, end);
            case BellmanFord:
                return shortestBellman(graph, start, end);
            default:
                return 0;
        }
    }
    /**
     * 最短路径：Dijkstra算法
     * 递归算法
     * */
    private static Map<GraphItem,Integer> shortestDijkstra(Graph graph, GraphItem start,GraphItem end){
        Map<GraphItem,Integer> S = new HashMap<GraphItem,Integer>();
        Map<GraphItem,Integer> D = new HashMap<GraphItem,Integer>();

        // 初始化D数组
        for(GraphItem item:graph.getGraph().keySet()){
            if(item == end){
                // 剔除当前节点
                continue;
            }
            if(graph.getGraph().get(item).containsKey(end)){
                D.put(item,graph.getGraph().get(item).get(end));
            } else{
                // 当前节点不可达
                D.put(item,Integer.MAX_VALUE);
            }
        }
        // 初始化S数组
        S.put(end,0);

        // 添加S数组(D数组中最小值)
        while(D.size() > 0){
            // 选取最小值
            GraphItem min = null;
            for(GraphItem tmp:D.keySet()){
                if(min == null || min.getValue() > tmp.getValue()){
                    min = tmp;
                }
            }
            // TODO:error，https://blog.csdn.net/wang13342322203/article/details/89377256
//            if(min.getValue() == start.getValue()){
//                // 找到元素
//                return min.getValue();
//            }
            // 更新S数组
            S.put(min,min.getValue());
            // 删除D数组中的最小元素
            D.remove(min);


            // 更新各个节点的数值
            for(GraphItem d: D.keySet()){
                for(GraphItem s:S.keySet()){
                    if(!graph.getGraph().get(d).containsKey(s)){
                        continue;
                    } else{
                        // D队列中元素和S队列元素累加，
                        if(graph.getGraph().get(d).get(s) + S.get(s)< D.get(d)){
                            D.put(d,graph.getGraph().get(d).get(s) + S.get(s));
                        }
                    }
                }
            }
        }
        System.out.println(S);
        return S;
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
