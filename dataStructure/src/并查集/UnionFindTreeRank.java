package 并查集;

public class UnionFindTreeRank implements UnionFind {

    private int [] arr;
    private int [] rank;

    public UnionFindTreeRank(int capacity){
        arr = new int[capacity];
        rank = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            arr[i] = i;
            rank[i] = 1;
        }
    }

    //根据索引找到集合
    private int find (int index){
        if (index < 0 || index >= arr.length) {
            throw new IllegalArgumentException("参数错误");
        }
        int p = arr[index];
        while(p != index){
            index = p;
            p = arr[index];
        }
        return p;
    }

    @Override
    public boolean isConnect(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void unionElements(int p, int q) {
        int pData = find(p);
        int qData = find(q);
        if (pData == qData) {
            return ;
        }
        if (rank[pData] > rank[qData]) {
            arr[qData] = pData;
        } else if (rank[pData] < rank[qData]) {
            arr[pData] = qData;
        } else {
            arr[pData] = qData;
            rank[qData] += 1;
        }
    }

    @Override
    public int getSize() {
        return arr.length;
    }
}
