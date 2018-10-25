package 并查集;

import java.util.Arrays;

public class UnionFindTree implements UnionFind {

    private int[] arr;

    public UnionFindTree (int capacity){
        this.arr = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            arr[i] = i;
        }
    }

    //根据索引找到所属集合
    private int find (int index){
        if (index < 0 || index >= this.arr.length) {
            throw new IllegalArgumentException("参数错误");
        }
        while(index != arr[index]){
            index = arr[index];
        }
        return index;
    }

    @Override
    public boolean isConnect(int p, int q) {
        int pData = find(p);
        int qData = find(q);
        return pData == qData;
    }

    @Override
    public void unionElements(int p, int q) {
        int pData = find(p);
        int qData = find(q);
        if (pData == qData) {
            return ;
        }
        this.arr[pData] = qData;

    }

    @Override
    public String toString(){
        return Arrays.toString(this.arr);
    }

    @Override
    public int getSize() {
        return this.arr.length;
    }

    public static void main(String [] args){
        UnionFindTree union = new UnionFindTree(8);
        System.out.println(union.toString());
        System.out.println("===================");
        
        union.unionElements(0,7);
        System.out.println(union.toString());
        union.unionElements(2,3);
        System.out.println(union.toString());
        union.unionElements(1,7);
        System.out.println(union.toString());
        union.unionElements(0,2);
        System.out.println(union.toString());
        union.unionElements(5,7);
        System.out.println(union.toString());

    }

}
