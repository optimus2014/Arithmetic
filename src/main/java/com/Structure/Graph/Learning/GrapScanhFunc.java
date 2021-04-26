package com.Structure.Graph.Learning;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 图的遍历算法：
 *   - 深度优先算法：DFS
 *   - 广度优先算法：BFS
 */
public class GrapScanhFunc {
    public static void main(String[] args) {
        System.out.println("图遍历算法：DFS 、 BFS，根据不同的存储结构，设置不同的算法");

        Graph graph = new Graph();
        GraphItem item1 = new GraphItem(1);
        GraphItem item2 = new GraphItem(2);
        GraphItem item3 = new GraphItem(3);
        GraphItem item4 = new GraphItem(4);
        GraphItem item5 = new GraphItem(5);
        GraphItem item6 = new GraphItem(6);
        GraphItem item7 = new GraphItem(7);
        graph.addEdge(item1,item2,1);
        graph.addEdge(item1,item3,1);
        graph.addEdge(item1,item4,2);
        graph.addEdge(item2,item3,3);
        graph.addEdge(item3,item5,4);
        graph.addEdge(item4,item5,5);
        graph.addEdge(item4,item1,6);
        graph.addEdge(item6,item7,3);
        graph.addEdge(item4,item7,4);
        show(BFS(graph));
        show(BFS(graph,item1));
        show(graph.getStart());
        graph.show();

    }

    /**
     * 深度优先算法：遍历图，从一个节点起步，一直走到图的尽头
     * 核心点：
     * 1. 使用递归算法
     * 2. 维护一个状态位表，标识当前节点是否已遍历
     * @param graph
     * @return
     */
    public static List<GraphItem> DFS(Graph graph){
        List<GraphItem> result = new ArrayList<GraphItem>();
        return result;
    }




    /**
     * 广度优先算法，根据构建的邻接表的图存储，返回图遍历的结果数据
     * 核心点：
     * 1.队列：取队列第一个节点，逐一将当前节点的连接节点添加队列中，当队列为空时，结束。
     * 2.元素标识位：标识当前元素是否已经遍历，key-value型；节点添加到结果数组之前遍历，避免重复扫描。
     * @param graph
     * @return
     */
    public static List<GraphItem> BFS(Graph graph){
        List<GraphItem> result = new ArrayList<GraphItem>();
        // 图元素标识位
        Map<GraphItem,Boolean> itemKeys = new HashMap<GraphItem, Boolean>();
        for(GraphItem item : graph.getItems()){
            itemKeys.put(item,Boolean.FALSE);
        }

        // 按照出度最多的节点作为图的遍历起点
        for(GraphItem start:graph.getStart()){
            for(GraphItem item:BFS(graph,start)){
                if(!itemKeys.get(item)){
                    result.add(item);
                    itemKeys.put(item,Boolean.TRUE);
                }
            }

        }
        return result;
    }

    /**
     * 以某一个顶点为起点，开始遍历，在有向图的环境下，会出现只能遍历部分图的情况，并不能找到连通子图的全部节点
     * @param graph
     * @param start
     * @return
     */
    public static List<GraphItem> BFS(Graph graph,GraphItem start){
        Queue<GraphItem> queue = new LinkedList<GraphItem>() ;
        List<GraphItem> result = new ArrayList<GraphItem>();
        // 图元素标识位
        Map<GraphItem,Boolean> itemKeys = new HashMap<GraphItem, Boolean>();
        for(GraphItem item : graph.getItems()){
            itemKeys.put(item,Boolean.FALSE);
        }

        queue.add(start);
        while(queue.size() > 0){
            GraphItem item = queue.poll();
            if(itemKeys.get(item)){
                // 元素已处理
                continue;
            } else {
                // 元素未处理，获取当前元素的连接元素
                result.add(item);
                itemKeys.put(item,Boolean.TRUE);
                // 关联元素添加到队列中
                for(GraphItem i : graph.getGraph().get(item).keySet()){
                    queue.add(i);
                }
            }
        }
        return result;
    }

    public static void show(List<GraphItem> graph){
        System.out.println("************ 开始打印图遍历结果 ************");
        for(GraphItem item: graph){
            System.out.printf("--> %s ",item.getValue());
        }
        System.out.println("\n************ 打印图遍历结果结束 ************\n");
    }

    /***
     * 广度优先搜索算法(BFS)
     * 使用邻接矩阵方式存储
     */
    public static void BFS(int[][] graph){

    }

    /**
     * 深度优先搜索算法
     * 递归算法
     * */
    public void DFS(int[][] graph){

    }
}
