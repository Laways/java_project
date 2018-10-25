package 图;

import 图.utils.GraphFileUtils;
import 图.utils.GraphUtils;

import java.util.Random;

public class MainTestGraph {
    public static void main(String [] args){
/*
        int n = 15;
        int m = 100;
        Random random = new Random();
        DenseGraph denseGraph = new DenseGraph(n,false);
        int w ,x;
        for (int i = 0; i < m; i++) {
            w = random.nextInt(15);
            x = random.nextInt(15);
            denseGraph.addEdge(x,w);
        }
        System.out.println("======================DenseGraph=======================");
        for (int i = 0; i < n; i++) {
            System.out.println(i+":"+denseGraph.ergodicNode(i));
        }

        SparseGraph sparseGraph = new SparseGraph(n,false);
        for (int i = 0; i < m; i++) {
            w = random.nextInt(15);
            x = random.nextInt(15);
            sparseGraph.addEdge(x,w);
        }
        System.out.println("======================SparseGraph=======================");
        for (int i = 0; i < n; i++) {
            System.out.println(i+":"+sparseGraph.ergodicNode(i));
        }
*/
        String url = Class.class.getClass().getResource("/").getPath();

        /*DenseGraph denseGraph1 = new DenseGraph(13,false);
        System.out.println(url.substring(1,url.length())+"src/graph2.txt");
        if (GraphFileUtils.initGraph(denseGraph1,url.substring(1,url.length())+"graph2.txt")) {
            denseGraph1.show();
        }
        SparseGraph sparseGraph1 = new SparseGraph(13,false);
        if (GraphFileUtils.initGraph(sparseGraph1,url.substring(1,url.length())+"graph2.txt")) {
            sparseGraph1.show();
            GraphUtils graphUtils = new GraphUtils(sparseGraph1);
            System.out.println(graphUtils.count());
            System.out.println(graphUtils.isConnected(0, 4));
            System.out.println(graphUtils.isConnected(0, 8));
            System.out.println(graphUtils.isConnected(1, 12));
            System.out.println(graphUtils.path(0, 4));
            System.out.println(graphUtils.path(0, 6));
            System.out.println(graphUtils.path(0, 5));
            System.out.println(graphUtils.path(0, 3));
            System.out.println(graphUtils.path(0, 10));
            System.out.println(graphUtils.path(9, 10));
        }*/
        SparseWeightedGraph<Double> sparseWeightGraph1 = new SparseWeightedGraph<>(8,false);
        if (GraphFileUtils.initGraph(sparseWeightGraph1,url.substring(1,url.length())+"weightGraph4.txt")) {
            System.out.println("success");
            sparseWeightGraph1.show();
            System.out.println(GraphUtils.lazyPrimTree(sparseWeightGraph1));
            System.out.println(GraphUtils.krusk(sparseWeightGraph1));
        }
    }
}
