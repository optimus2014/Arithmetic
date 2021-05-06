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
 *
 * 经典算法：
 * 1.PageRank
 * 2.Louvain 社区发现
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
                return shortestDijkstra(graph,start, end);
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
     * 算法思路：
     * 1.维护两个数组，数组S标识计算出来的最终结果，记录各个节点到end节点的最短距离，D数组表示中间状态，维护D中的当前元素到S数组中各个节点距离的最小值；
     * 2.每轮计算过程，抽取D中的最小值，添加到S中，并更新D中各个节点的最小距离。
     * 3.找到起始节点，并返回。循环结束还未找到，说明两个节点没有连接。
     * */
    private static Integer shortestDijkstra(Graph graph, GraphItem start,GraphItem end){
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
        // 打印初始化的函数
//        showMap(S,"初始化的S数组");
//        showMap(D,"初始化的D数组");

        // 添加S数组(D数组中最小值)
        while(D.size() > 0){
            // 选取D数组中的最小值
            GraphItem min = null;
            for(GraphItem tmp:D.keySet()){
                if(min == null || D.get(min) > D.get(tmp)){
                    min = tmp;
                }
            }
            if(min == start){
                // 找到元素
                return D.get(min);
            }
            // 更新S数组
            S.put(min,D.get(min));
//            showMap(S,"更新中的S数组");
            // 删除D数组中的最小元素
            D.remove(min);


            // 更新各个节点的数值
            for(GraphItem d: D.keySet()){
                for(GraphItem s:S.keySet()){
                    if(!graph.getGraph().get(d).containsKey(s)){
                        // d节点到s节点不直连，默认还是无限远，跳过
                        continue;
                    } else{
                        // D队列中元素和S队列元素累加，
                        if(graph.getGraph().get(d).get(s) + S.get(s)< D.get(d)){
                            D.put(d,graph.getGraph().get(d).get(s) + S.get(s));
                        }
                    }
                }
            }
//            showMap(D,"更新中的D数组");
        }
        // 起点和终点没有连接，默认返回无限远
        return Integer.MAX_VALUE;
    }
    /**
     * Floyd算法：
     * 插点法.
     * */
    private static int shortestFloyd(Graph graph,GraphItem start, GraphItem end){
        return 0;
    }
    // Bellman算法
    private static int shortestBellman(Graph graph,GraphItem start, GraphItem end){
        return 0;
    }

    private static void showMap(Map<GraphItem,Integer> data,String name){
        System.out.printf("****************** Map-%s 开始展示 *****************\n",name);
        for(GraphItem item : data.keySet()){
            System.out.printf("%s:%d,  ",item.getValue(),data.get(item));
        }
        System.out.printf("\n****************** Map-%s 展示结束 *****************\n\n",name);

    }
}
