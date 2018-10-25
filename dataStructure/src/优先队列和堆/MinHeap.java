package 优先队列和堆;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MinHeap<E extends Comparable<E>> {
    private List<E> data;

    public MinHeap(){
        data = new ArrayList<>();
    }

    public MinHeap(List<E> list){
        this.data = list;
        heapify(data);
    }

    private void heapify(List<E> list) {
        int lastIndex = list.size() - 1;
        int beginIndex = parent(lastIndex);
        for (int i = beginIndex; i >=0 ; i--) {
            siftDown(i);

        }
    }

    public MinHeap(int capacity){
        data = new ArrayList<>(capacity);
    }

    public MinHeap(E [] arr){
        this.data = new ArrayList<>(Arrays.asList(arr));
        heapify(data);
    }

    private int parent(int index){
        if (index == 0) {
            throw new IllegalArgumentException("根节点没有父节点");
        }
        return (index - 1)/2;
    }

    private int leftChild(int index) {
        return index * 2 + 1;
    }

    private int rightChild (int index) {
        return index * 2 + 2;
    }

    private void swap(int x, int w){
        E temp = this.data.get(w);
        this.data.set(w, this.data.get(x));
        this.data.set(x, temp);
    }

    private void siftUp(int index){
        if (index <= 0) {
            return ;
        }
        int parentIndex = parent(index);
        if (this.data.get(index).compareTo(this.data.get(parentIndex)) < 0) {
            swap(index, parentIndex);
            siftUp(parentIndex);
        }
    }

    private void siftDown(int index){
        if (index >= this.data.size()) {
            return ;
        }
        int leftChildIndex = leftChild(index);
        int rightChildIndex = rightChild(index);
        int minChild;
        if (leftChildIndex >= this.data.size()) {
            return ;
        } else if (leftChildIndex < this.data.size() && rightChildIndex >= this.data.size()) {
            minChild = leftChildIndex;
        } else {
            minChild = this.data.get(leftChildIndex).compareTo(this.data.get(rightChildIndex)) < 0 ? leftChildIndex : rightChildIndex;
        }
        if (this.data.get(index).compareTo(this.data.get(minChild)) > 0) {
            swap(index,minChild);
            siftDown(minChild);
        }
    }

    public void add(E e){
        this.data.add(e);
        siftUp(this.data.size() - 1);
    }

    public E extractMin(){
        if (isEmpty()) {
            return null;
        }
        int index = this.data.size() - 1;
        E min = this.data.get(0);
        swap(0,index);
        this.data.remove(index);
        siftDown(0);
        return min;
    }

    public int size(){
        return this.data.size();
    }

    public boolean isEmpty(){
        return this.data.size() == 0;
    }
    @Override
    public String toString (){
        return this.data.toString();
    }

    public static void main(String [] args){
        MinHeap<Integer> minHeap = new MinHeap<>();

        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            minHeap.add(random.nextInt(50));
        }
        System.out.println(minHeap.toString());
        for (int i = 0; i < 10; i++) {
            System.out.println(minHeap.extractMin());
        }
        Integer [] arr = {15,58,49,5,63,87,26,14};
        MinHeap<Integer> heap = new MinHeap<>(arr);
        System.out.println(heap.toString());
        for (int i = 0; i < 8; i++) {
            System.out.println(heap.extractMin());
        }
    }
}
