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
public final class Graph{
    public static void main(String[] args) {
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
        graph.show();

        graph.removeEdge(item1,item2);
        graph.show();

        graph.removeItem(item4);
        graph.show();

        graph.addEdge(item6,item7,10);
        graph.show();
        graph.addEdge(item6,item7,5);
        graph.show();

        System.out.println("起始节点：" + graph.getStart().get(2).getValue());
        // 测试图的遍历方法
        GrapScanhFunc.BFS(graph);
    }

    /************************************************************
     * 构建图的链接表存储方式
     * 包含方法：
     * 1. 添加节点（要求必须先添加节点，再添加边）
     * 2. 添加边（权重累加）
     * 3. 删除节点
     * 4. 删除边(不考虑权重值，删除两点之间的边)
      */
    // 邻接表的表达方式，同节点之间只保留一条边
    private Map<GraphItem,Map<GraphItem,Integer>> graph= new HashMap<GraphItem, Map<GraphItem,Integer>>();

    // 添加节点，如果有多个子图情况下，会出现单节点图
    public void addItem(GraphItem item){
        if(!this.graph.containsKey(item)){
            this.graph.put(item,new HashMap<GraphItem,Integer>());
        }
    }
    // 添加边
    public void addEdge(GraphItem source,GraphItem target ,Integer value){

        // 添加邻接表的Key值，分别在Key中添加source和target
        if(!this.graph.containsKey(source)){
            this.graph.put(source,new HashMap<GraphItem,Integer>());
        }
        if(!this.graph.containsKey(target)){
            this.graph.put(target,new HashMap<GraphItem,Integer>());
        }

        // 更新图中的边
        Map<GraphItem,Integer> linkKey = this.graph.get(source);
        if(linkKey.containsKey(target)){
            linkKey.put(target,linkKey.get(target) + value);
        } else{
            linkKey.put(target,value);
            // 更新节点的度
            source.setOut(source.getOut() + 1);
            target.setIn(target.getIn() + 1);
        }


    }

    // 删除顶点
    public void removeItem(GraphItem item){
        // 遍历所有元素，删除以item为终点的所有边
        for(GraphItem key: this.graph.keySet()){
            if(key != item && this.graph.get(key).containsKey(item)){
                this.removeEdge(key,item);
            }
        }

        // 删除以item为起点的所有边
        if(this.graph.containsKey(item)){
//            System.out.printf("当前Item存在:%s.\n", item.getValue());
            // 遍历当前节点的入度节点，修改入度值
            for(GraphItem key: this.graph.get(item).keySet()){
                key.setIn(key.getIn() - 1);
            }
            this.graph.remove(item);
        }
    }

    // 删除边
    public void removeEdge(GraphItem source,GraphItem target){
        // 删除边
        if(this.graph.containsKey(source)) {
            this.graph.get(source).remove(target);
            source.setOut(source.getOut() - 1);
            target.setIn(target.getIn() - 1);
        }
    }

    // 获取图中的所有顶点
    public Set<GraphItem> getItems(){
        return this.graph.keySet();
    }

    // 获取图中所有的边
//    public List<Map<>GraphEdge> getEdge(){
//        return null;
//    }
    // 获取图的存储对象
    public Map<GraphItem,Map<GraphItem,Integer>> getGraph(){
        return this.graph;
    }

    // 打印图
    public void show(){
        System.out.println("************** Graph 开始打印 ****************");
        for(GraphItem key:this.graph.keySet()){
            System.out.printf("Item:%s(in:%d,out:%d) " , key.getValue() , key.getIn(), key.getOut() );
            Map<GraphItem,Integer> links = this.graph.get(key);
            for (GraphItem linkkey:links.keySet()){
                System.out.printf("--> item-%s:%d ",linkkey.getValue(),links.get(linkkey));
            }
            System.out.println();
        }
        System.out.println("************** Graph 打印结束 ****************\n");
    }

    /**
     * 获取图的起始节点
     * 默认选择入度为0的节点（List，元素个数表示子图个数），或者入度最小、出度最大的节点。
     */
    public List<GraphItem> getStart(){
        List<GraphItem> start = new ArrayList<GraphItem>();
        // 当前入度最小的元素
        GraphItem mini_item = null;
        for(GraphItem key: this.graph.keySet()){
            if (key.getIn() == 0){
                // 入度为0的节点，默认为子图的起始节点
                start.add(key);
            }
            if (mini_item == null || (key.getIn() < mini_item.getIn()  && key.getOut() > mini_item.getOut())){
                mini_item = key;
            }
        }
        if(start.size() == 0 && mini_item != null){
            start.add(mini_item);
        }
        return start;
    }
}



/**
 * 图中的节点
 */
class GraphItem {
    // 节点数值
    private int value = 0;
    // 节点名称：非必须
    private String name;
    // 节点的入度
    private int in = 0;
    // 节点的出度
    private int out = 0;

    public GraphItem(int value){
        this.value = value;
    }
    public int getIn() {
        return in;
    }
    public void setIn(int in) {
        this.in = in;
    }
    public void setValue(int value) {
        this.value = value;
    }
    public int getValue(){
        return this.value;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getOut() {
        return out;
    }
    public void setOut(int out) {
        this.out = out;
    }
}


