package 优先队列和堆;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MaxHeap <E extends Comparable<E>> {

    private List<E> data;
    public MaxHeap(){
        data = new ArrayList<>();
    }

    public MaxHeap(int capacity){ data = new ArrayList<>(capacity);}

    //heapify:将任意数组转换成最大堆
    // 将数组看成一个完全二叉树，找到最后一个非叶子节点，从后向前执行下沉操作
    public MaxHeap(E[] arr){
        List<E> list = Arrays.asList(arr);
        this.data = new ArrayList<>(list);
        for (int i = parent(data.size() - 1); i >= 0; i--) {
            siftDown(i);
        }
    }

    //返回堆中的元素个数
    public int size (){
        return data.size();
    }

    //堆是否为空
    public boolean isEmpty (){
        return data.isEmpty();
    }

    //返回这个索引的父节点索引
    private int  parent(int index){
        if (index == 0) {
            throw new IllegalArgumentException("根节点没有父节点");
        }
        return (index - 1) / 2;
    }

    //返回这个索引左孩子节点索引
    private int leftChild (int index){
        return index * 2 + 1;
    }

    //返回这个索引右孩子节点索引
    private int rightChild (int index){
        return index * 2 + 2;
    }

    //向堆中添加元素
    public void add (E e){
        int index = this.data.size();
        this.data.add(e);
        siftUp(index);
    }

    private void siftUp(int index) {
        if (index == 0) {
            return ;
        }
        int parentIndex = parent(index);
        if (this.data.get(parentIndex).compareTo(this.data.get(index)) < 0) {
            swapData(parentIndex,index);
            siftUp(parentIndex);
        }
    }
    
    //交换元素位置方法
    private void swapData (int a, int b){
        E temp = this.data.get(a);
        this.data.set(a , this.data.get(b));
        this.data.set(b, temp);
    }

    public E extractMax(){
        if (isEmpty()) {
            throw new IllegalArgumentException("堆为空");
        }
        E max = this.data.get(0);
        this.data.set(0, this.data.get(size() - 1));
        this.data.remove(size() - 1);
        siftDown(0);
        return max;
    }

    private E getData(int index){
        return this.data.get(index);
    }

    private void siftDown(int index) {
        int leftIndex = leftChild(index);
        int rightIndex = rightChild(index);
        int maxChildIndex = 0;
        if (leftIndex <= size() - 1 && rightIndex <= size() - 1) {
            maxChildIndex = getData(leftIndex).compareTo(getData(rightIndex)) < 0 ? rightIndex : leftIndex;
        }else if (leftIndex <= size() - 1 && rightIndex > size() - 1) {
            maxChildIndex = leftIndex;
        } else {
            return ;
        }
        if (getData(index).compareTo(getData(maxChildIndex)) < 0) {
            swapData(index,maxChildIndex);
            siftDown(maxChildIndex);
        }

    }

    //取出最大元素后，放入一个新元素
    public E replace(E e){
        E max = findMax();
        this.data.set(0,e);
        siftDown(0);
        return max;
    }

    private E findMax(){
        if (isEmpty()) {
            throw new RuntimeException("堆为空");
        }
        return this.data.get(0);
    }

    @Override
    public String toString (){
        return this.data.toString();
    }

    public static void main(String [] args){
        MaxHeap<Integer> maxHeap = new MaxHeap<>();

        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            maxHeap.add(random.nextInt(50));
        }
        System.out.println(maxHeap.toString());
        for (int i = 0; i < 10; i++) {
            System.out.println(maxHeap.extractMax());
            System.out.println(maxHeap.toString());
        }
        Integer [] arr = {15,58,49,5,63,87,26,14};
        MaxHeap<Integer> heap = new MaxHeap<>(arr);
        System.out.println(heap.toString());
        for (int i = 0; i < 8; i++) {
            System.out.println(heap.extractMax());
        }
    }

}









