package 并查集;

/**
 * 路径压缩,递归实现完美压缩
 * 降低树的高度
 */
public class UnionFindRankCompress2 implements UnionFind {

    private int [] arr;
    private int [] rank;

    public UnionFindRankCompress2(int capacity){
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
        if(index != arr[index]){
            arr[index] = find(arr[index]);
        }
        return index;
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
