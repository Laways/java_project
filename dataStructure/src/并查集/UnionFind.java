package 并查集;

public interface UnionFind {
    boolean isConnect(int p , int q);
    void unionElements(int p , int q);
    int getSize();
}
