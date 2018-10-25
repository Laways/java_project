package 图;

import java.util.ArrayList;
import java.util.List;

public class SparseGraph implements Graph{
    private int n;
    private int m;
    private boolean directed;
    private ArrayList<Integer>[] lists;

    public SparseGraph(int n, boolean directed){
        this.n = n ;
        this.m = 0 ;
        this.directed = directed;
        lists =new ArrayList[n];
        for (int i = 0; i < n; i++) {
            lists[i] = new ArrayList<>();
        }
    }

    @Override
    public int getNodeCount(){
        return this.n;
    }
    @Override
    public List<Integer> ergodicNode(int w){
        return this.lists[w];
    }
    @Override
    public boolean hasEdge(int x, int w){
        return this.lists[x].contains(w);
    }
    @Override
    public void addEdge(int x , int w){
        if (x < 0 || x >= n || w < 0 || w >= n) {
            throw new IllegalArgumentException("参数错误");
        }
        if (hasEdge(x,w)) {
            return ;
        }
        this.lists[x].add(w);
        if (x != w && !this.directed) {
            this.lists[w].add(x);
        }
        this.m++;
    }

    @Override
    public void addEdge(int x, int w, Object weight) {
        addEdge(x,w);
    }

    @Override
    public void show(){
        for (int i = 0; i < n; i++) {
            System.out.println("vertex " + i + ":"+ this.lists[i].toString());
        }
    }

}












