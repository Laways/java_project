package 图.utils;

import 优先队列和堆.MinHeap;
import 图.Edge;
import 图.Graph;
import 图.SparseWeightedGraph;
import 并查集.*;

import java.util.*;

public class GraphUtils {
    private int [] nodes;
    private boolean[] states;
    private int cCount;
    private Graph graph;

    public GraphUtils(Graph graph){
        cCount = 0;
        this.graph = graph;
        int nodeCount = graph.getNodeCount();
        nodes = new int [nodeCount];
        states = new boolean[nodeCount];
        for (int i = 0; i < nodeCount; i++) {
            states[i] = false;
            nodes[i] = cCount;
        }
        for (int i = 0; i < nodeCount; i++) {
            if (!states[i]) {
                depthErgodic(graph,i);
                this.cCount++;
            }
        }
        System.out.println(Arrays.toString(nodes));
    }

    //返回两个节点是否连通
    public boolean isConnected(int w, int x){
        int nodeCount = this.graph.getNodeCount();
        if (w < 0 || w > nodeCount || x < 0 || x > nodeCount) {
            throw new IllegalArgumentException("参数错误");
        }
        return nodes[w] == nodes[x];
    }

    //返回两个节点之间的路径
    public String path (int from, int to){
        if (!isConnected(from,to)) {
            return new String(from+" 到 "+to+" 之间不存在路径");
        }
        LinkedList<Integer> paths = new LinkedList<>();
        paths.addLast(from);
        findPath(paths,from,to);
        StringBuilder sb = new StringBuilder();
        sb.append(from + "-->" + to + "  paths: [");
        for (Integer num : paths) {
            sb.append(num).append(' ');
        }
        return sb.substring(0,sb.length() - 1)+']';
    }

    private boolean findPath(LinkedList<Integer> paths, int from, int to) {
        List<Integer> lists = graph.ergodicNode(from);
        System.out.println(lists.contains(to));
        if (lists.contains(to)) {
            paths.addLast(to);
            return true;
        }
        for (Integer num : lists) {
            paths.addLast(num);
            if (findPath(paths,num, to)) {
                return true;
            }
            paths.removeLast();
        }
        return false;
    }

    //返回该图中的连通分量
    public int count(){
        return this.cCount;
    }

    private void depthErgodic(Graph graph, int i) {
        //节点本身置为已访问过
        states[i] = true;
        nodes[i] = this.cCount;
        List<Integer> lists = graph.ergodicNode(i);
        Stack<Integer> stack = new Stack<>();
        for (Integer e : lists) {
            //将没有访问过的值入栈
            if (!states[e]) {
                stack.push(e);
            }
        }
        while(stack.size() != 0){
            Integer pop = stack.pop();
            //已访问过的元素直接跳过
            if (states[pop]) {
                continue;
            }
            //将末访问过的设置为已访问过
            states[pop] = true;
            nodes[pop] = this.cCount;
            List<Integer> tempList = graph.ergodicNode(pop);
            for (Integer e : tempList) {
                if (!states[e]) {
                    stack.push(e);
                }
            }
        }
    }

    //最小生成树
    public static <T extends Comparable<T>> String lazyPrimTree(SparseWeightedGraph<T> graph){
        MinHeap minHeap = new MinHeap<>();
        StringBuilder sb = new StringBuilder();
        int nodeCount = graph.getNodeCount();
        boolean[] redArr = new boolean[nodeCount];
        for (int i = 0; i < nodeCount; i++) {
            redArr[i] = false;
        }
        addNodeEdges(minHeap,graph.ergodicNode(0),redArr,0);
        while(minHeap.size() != 0){
            Edge minEdge = (Edge) minHeap.extractMin();
            if (!redArr[minEdge.getTo()]) {
                addNodeEdges(minHeap,graph.ergodicNode(minEdge.getTo()),redArr, minEdge.getTo());
                sb.append(minEdge.toString()).append("\r\n");
            }
        }
        
        return sb.toString();
    }

    private static <T extends Comparable<T>> void addNodeEdges(MinHeap<T> minHeap, List<Edge> list, boolean[] redArr, int index) {
        redArr[index] = true;
        for (int i = 0 ; i < list.size() ; i++) {
            Edge edge = list.get(i);
            if (!redArr[edge.getTo()]) {
                minHeap.add((T) edge);
            }
        }
    }

    public static <T extends  Comparable<T>> String krusk(SparseWeightedGraph<T> graph){
        System.out.println("krusk");
        StringBuilder sb = new StringBuilder();
        int nodeCount = graph.getNodeCount();
        UnionFindRankCompress unionFind = new UnionFindRankCompress(nodeCount);
        MinHeap heap = new MinHeap();
        initHeap(heap,graph);
        int count = 0;
        while(heap.size() != 0 && count < nodeCount - 1){
            Edge minEdge = (Edge) heap.extractMin();
            if (!unionFind.isConnect(minEdge.getFrom(),minEdge.getTo())) {
                sb.append(minEdge.toString()).append("\r\n");
                unionFind.unionElements(minEdge.getFrom(),minEdge.getTo());
                count++;
            }else{
                continue;
            }
        }
        return sb.toString();
    }

    private static <T extends Comparable<T>> void initHeap(MinHeap heap, SparseWeightedGraph<T> graph) {
        for (int i = 0; i < graph.getNodeCount(); i++) {
            List<Edge> edgeLists = graph.ergodicNode(i);
            for(Edge edge : edgeLists){
                //无向图存在重复边，去除重复边
                if (edge.getFrom() < edge.getTo()) {
                    heap.add(edge);
                }
            }
        }
    }
}
