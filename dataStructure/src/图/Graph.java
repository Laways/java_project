package 图;

import java.util.List;

public interface Graph <T>{
    int getNodeCount();
    void addEdge(int x, int w);
    void addEdge(int x, int w,T weight);
    List<Integer> ergodicNode(int w);
    boolean hasEdge(int x, int ws);

    void show();
}
