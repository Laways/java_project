package 图;

import java.util.ArrayList;
import java.util.List;

//采用邻接矩阵实现一个稠密图
public class DenseGraph implements Graph{
    private int n;//图的节点个数
    private int m;//图中边的个数
    private boolean directed;//是否是有向的图
    private boolean[][] matrix;//采用邻接矩阵存储

    public DenseGraph(int n, boolean directed){
        this.n = n;
        this.m = 0;
        this.directed = directed;
        matrix = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = false;
            }
        }
    }
    @Override
    public int getNodeCount(){
        return this.n;
    }
    @Override
    public List<Integer> ergodicNode(int w){
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (this.matrix[w][i]) {
                list.add(i);
            }
        }
        return list;
    }

    @Override
    public boolean hasEdge(int x, int w){

        return this.matrix[x][w];
    }

    @Override
    public void show() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(this.matrix[i][j] +" ");
            }
            System.out.println();
        }
    }

    @Override
    public void addEdge(int x, int w){
        if (x < 0 || x >= n || w < 0 || w >= n) {
            throw new IllegalArgumentException("参数错误");
        }
        if (hasEdge(x,w)) {
            return;
        }else {
            this.matrix[x][w] = true;
            if (!this.directed && x != w) {
                this.matrix[w][x] =true;
            }
            this.m++;
        }
    }

    @Override
    public void addEdge(int x, int w, Object weight) {
        addEdge(x,w);
    }
}












