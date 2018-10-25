package 线段树;

public class SegmentTree<E> {
    //线段树存储数组
    private E[] tree;
    //元素存储数组
    private E[] data;
    //容合器
    private Merger<E> merger;

    public SegmentTree(E[] arr , Merger merger){
        this.merger = merger;
        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
        tree = (E[]) new Object[arr.length * 4];
        initSegmentTree(0, 0, data.length - 1);
    }

    //leftChild_index
    private int leftChild (int index){
        return index * 2 + 1;
    }

    //rightChild_index
    private int rightChild (int index){
        return index * 2 + 2;
    }

    //parent
    private int parent (int index){
        return (index - 1) / 2;
    }

    private E initSegmentTree(int index, int begin, int end) {
        if (begin == end) {
            tree[index] = data[begin];
            return tree[index];
        }
        int leftIndex = leftChild(index);
        int rightIndex = rightChild(index);
        int midIndex = begin + (end - begin) / 2;

        E leftChild = initSegmentTree(leftIndex, begin , midIndex);
        E rightChild = initSegmentTree(rightIndex,midIndex + 1, end);
        tree[index] = merger.merger(leftChild,rightChild);
        return tree[index];
    }

    //查找区间【begin,end】的值
    public E query(int begin , int end){
        return query(0,data.length - 1, begin, end);
    }

    private E query(int a, int b, int begin, int end) {
        if (begin < a || end > b) {
            throw new IllegalArgumentException("参数错误");
        }
        if (begin == end) {
            return data[begin];
        }
        int mid = a + (b - a) / 2;
        if (mid >= end) {
            return query(a,mid , begin , end);
        }
        if (mid < begin) {
            return query(mid + 1 , b , begin , end);
        }
        E leftChild = query(a, mid, begin, mid);
        E rightChild = query(mid + 1 , b ,mid + 1, end);
        return merger.merger(leftChild,rightChild);
    }

    //更新指定位置的值
    public void set (int index , E e){
        set(0,0,data.length - 1, index, e);
    }

    private void set(int nowIndex,int begin, int end, int index, E e) {
        //找到后更新值
        if (begin == end ) {
            data[index] = e;
            tree[nowIndex] = e;
            return ;
        }
        int leftIndex = leftChild(nowIndex);
        int rightIndex = rightChild(nowIndex);
        int mid = begin + ( end - begin ) / 2;
        //向下寻找需要更新的节点
        if (index <= mid) {
            set(leftIndex,begin, mid, index, e);
        }
        if (index > mid) {
            set(rightIndex, mid + 1 , end ,index , e);
        }
        //更新相关区间的值
        tree[nowIndex] = merger.merger(tree[leftIndex],tree[rightIndex]);
    }

    @Override
    public String  toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("SegmentTree:").append("\r\n").append('[');
        for (int i = 0; i < this.tree.length; i++) {
            if (tree[i] != null) {
                sb.append(tree[i]);
            } else {
                sb.append("null");
            }
            if (i != this.tree.length - 1) {
                sb.append(' ');
            } else {
                sb.append(']');
            }
        }
        return sb.toString();
    }

    public static void main(String [] args){
        Integer[] arr = {1,2,3,4,5,6,7,8};
        SegmentTree<Integer> segmentTree = new SegmentTree<>(arr, new Merger<Integer>() {
            @Override
            public Integer merger(Integer a, Integer b) {
                return a + b;
            }
        });

        System.out.println(segmentTree.toString());
        System.out.println(segmentTree.query(0, 4));
        //将索引3的元素改为7
        segmentTree.set(3,7);
        System.out.println(segmentTree.toString());
        System.out.println(segmentTree.query(0, 4));
    }


}
