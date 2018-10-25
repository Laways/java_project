package 并查集;

public class UnionFindTreeSize implements UnionFind {

    private int [] arr;
    private int [] size;

    public UnionFindTreeSize(int capacity){
        arr = new int[capacity];
        size = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            arr[i] = i;
            size[i] = 1;
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
        if (size[pData] < size[qData]) {
            arr[pData] = qData;
            size[qData] += size[pData];
        } else {
            arr[qData] = pData;
            size[pData] += size[qData];
        }

    }

    @Override
    public int getSize() {
        return arr.length;
    }
}
