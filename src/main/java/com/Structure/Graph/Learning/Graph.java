package com.Structure.Graph.Learning;

import java.util.*;

/**
 * 图元素：
 * 顶点
 * 边
 *
 *
 * 图的存储方法：
 *    邻接表
 *    邻接矩阵
 *
 * 本例代码是“有向权重图”
 */
public class Graph{
    public static void main(String[] args) {
        Graph graph = new Graph();
        GraphItem item1 = new GraphItem(1);
        GraphItem item2 = new GraphItem(2);
        GraphItem item3 = new GraphItem(3);
        GraphItem item4 = new GraphItem(4);
        GraphItem item5 = new GraphItem(5);
        graph.addEdge(new GraphEdge(item1,item2,1));
        graph.addEdge(new GraphEdge(item1,item3,1));
        graph.addEdge(new GraphEdge(item1,item4,2));
        graph.addEdge(new GraphEdge(item2,item3,3));
        graph.addEdge(new GraphEdge(item3,item5,4));
        graph.addEdge(new GraphEdge(item4,item5,5));
        graph.addEdge(new GraphEdge(item4,item1,6));
        graph.show();
    }


    // 邻接表的表达方式，同节点之间只保留一条边
    private Map<GraphItem,Map<GraphItem,Integer>> graph= new HashMap<GraphItem, Map<GraphItem,Integer>>();
    // 添加节点，如果有多个子图情况下，会出现单节点图
    public void addItem(GraphItem item){
        if(!this.graph.containsKey(item)){
            this.graph.put(item,new HashMap<GraphItem,Integer>());
        }
    }
    // 添加边
    public void addEdge(GraphEdge edge){
        GraphItem source = edge.getSource();
        GraphItem target = edge.getTarget();
        Integer value = (Integer) edge.getValue();
        // 添加邻接表的Key值
        if(!this.graph.containsKey(source)){
            this.graph.put(source,new HashMap<GraphItem,Integer>());
        }
        // 添加图中的边
        Map<GraphItem,Integer> linkKey = this.graph.get(source);
        linkKey.put(target,value);
    }

    // 打印图
    public void show(){
        for(GraphItem key:this.graph.keySet()){
            System.out.print("Item:" + key.getValue() );
            Map<GraphItem,Integer> links = this.graph.get(key);
            for (GraphItem linkkey:links.keySet()){
                System.out.printf("--> item-%s:%d ",linkkey.getValue(),links.get(linkkey));
            }
            System.out.println();
        }
    }
}

/**
 * 图中的节点
 */
class GraphItem {
    // 节点数值
    private int value;
    // 节点名称：非必须
    private String name;
    public GraphItem(int value){
        this.value = value;
    }
    public int getValue(){
        return this.value;
    }

}

/**
 * 图的边
 */
class GraphEdge{
    // 边的权重
    private int value;
    private GraphItem source;
    private GraphItem target;
    public GraphEdge(GraphItem source,GraphItem target,int value){
        this.source = source;
        this.target = target;
        this.value = value;
    }

    public GraphItem getTarget(){
        return this.target;
    }

    public GraphItem getSource() {
        return source;
    }

    public int getValue() {
        return value;
    }
}
