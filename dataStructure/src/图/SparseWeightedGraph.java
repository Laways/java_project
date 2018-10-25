package 图;

import java.util.ArrayList;
import java.util.List;

public class SparseWeightedGraph <T extends Comparable<T>> implements Graph<T>{
    private int n;
    private int m;
    private boolean directed;
    private ArrayList<Edge<T>>[] lists;

    public SparseWeightedGraph(int n, boolean directed){
        this.n = n ;
        this.m = 0 ;
        this.directed = directed;
        lists =new ArrayList[n];
        for (int i = 0; i < n; i++) {
            lists[i] = new ArrayList<Edge<T>>();
        }
    }

    @Override
    public int getNodeCount(){
        return this.n;
    }

    public int getEdgeCount(){
        return this.m;
    }

    @Override
    public void addEdge(int x, int w) {
        addEdge(x,w,null);
    }

    @Override
    public List ergodicNode(int w){
        return this.lists[w];
    }
    @Override
    public boolean hasEdge(int x, int w){
        for(Edge edge : this.lists[x]){
            if (edge.getTo() == w) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void addEdge(int x, int w, T weight){
        if (x < 0 || x >= n || w < 0 || w >= n) {
            throw new IllegalArgumentException("参数错误");
        }
        if (hasEdge(x,w)) {
            return ;
        }
        this.lists[x].add(new Edge<T>(x,w,weight));
        if (x != w && !this.directed) {
            this.lists[w].add(new Edge<T>(w,x,weight));
        }
        this.m++;
    }

    @Override
    public void show(){
        for (int i = 0; i < n; i++) {
            System.out.println("vertex " + i + ":"+ this.lists[i].toString());
        }
    }

}












