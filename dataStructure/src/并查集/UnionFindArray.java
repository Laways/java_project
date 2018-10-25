package 并查集;

public class UnionFindArray implements UnionFind {

    private int [] arr;

    public UnionFindArray(int capacity){
        arr = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            arr[i] = i;
        }
    }
    @Override
    public boolean isConnect(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void unionElements(int p, int q) {
        int qData = find(q);
        int pData = find(p);
        if (qData == pData) {
            return ;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i]  == pData) {
                arr[i] = qData;
            }
        }
    }

    //找到对应元素所属集合
    private int find (int index){
        return arr[index];
    }

    @Override
    public int getSize() {
        return arr.length;
    }
}
