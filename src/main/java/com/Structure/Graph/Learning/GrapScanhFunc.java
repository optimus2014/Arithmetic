package com.Structure.Graph.Learning;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 图的遍历算法：
 *   - 深度优先算法：DFS
 *   - 广度优先算法：BFS
 *
 * 总结：
 * DFS可以有递归和非递归两种实现逻辑，
 *    递归需要维护递归函数处理节点标识位和最终结果，
 *    非递归方法使用栈
 * BFS一般来说，只能用非递归方法，使用队列实现。（也有用伪递归的方法，没啥意义）
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
        graph.addEdge(item4,item6,6);
        graph.addEdge(item6,item7,3);
        graph.addEdge(item4,item7,4);
        show(BFS(graph));
        show(BFS(graph,item1));
        show(DFS(graph,item1));
        show(DFS_r(graph,item1));
        graph.show();

        System.out.println("最短路径：" + GraphAlgorithms.shortestPaths(graph,item1,item5, GraphAlgorithms.ALGORITHMS.Dijkstra));

    }

    /**
     * 深度优先算法：遍历图，从一个节点起步，一直走到图的尽头
     * 核心点：
     * 两种解法：递归法 & 非递归法
     * 1. 使用递归算法
     * 2. 维护一个状态位表，标识当前节点是否已遍历
     *
     * 非递归方法：
     * 使用栈,每次取栈最后一个节点，判断是否已扫描，没扫描则添加到结果队列中，并将后置节点依次添加到栈中
     */
    public static List<GraphItem> DFS(Graph graph,GraphItem start){
        List<GraphItem> result = new ArrayList<GraphItem>();
        // 图元素标识位
        Map<GraphItem,Boolean> itemKeys = new HashMap<GraphItem, Boolean>();
        for(GraphItem item : graph.getItems()){
            itemKeys.put(item,Boolean.FALSE);
        }

        // 注List接口，没有removeLast()等函数
        LinkedList<GraphItem> stack = new LinkedList<GraphItem>();
        stack.add(start);
        while(stack.size() > 0){
            GraphItem item = stack.removeLast();
            if(!itemKeys.get(item)){
                // 当前元素不存在
                result.add(item);
                itemKeys.put(item,Boolean.TRUE);
                for(GraphItem tem_item: graph.getGraph().get(item).keySet()){
                    stack.add(tem_item);
                }
            } else {
                // 元素已经处理，跳过
                continue;
            }
        }
        return result;
    }

    /**
     * DFS：递归方法处理做图的遍历
     * @param graph
     * @param start
     * @return
     */
    public static List<GraphItem> DFS_r(Graph graph,GraphItem start){
        List<GraphItem> result = new ArrayList<GraphItem>();
        Map<GraphItem,Boolean> itemKeys = new HashMap<GraphItem, Boolean>();
        for(GraphItem item : graph.getItems()){
            itemKeys.put(item,Boolean.FALSE);
        }

        // 从起始节点，开始遍历整张图
        dfs_re(graph,start,itemKeys,result);
        return result;
    }
    // 深度优先遍历递归方法
    private static void dfs_re(Graph graph, GraphItem start, Map<GraphItem,Boolean> itemKeys,List<GraphItem> result){
        if(itemKeys.get(start)){
            // 处理过的节点直接跳过
            return;
        }
        itemKeys.put(start,Boolean.TRUE);
        result.add(start);
        for(GraphItem item : graph.getGraph().get(start).keySet()){
            dfs_re(graph,item,itemKeys,result);
        }
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
