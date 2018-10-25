package AVL;

import 红黑树.RedBlackTree;

public class RBTreeMap<E extends  Comparable<E> , V> implements TreeMap<E, V> {

    private RedBlackTree<E, V> rbTree;

    public RBTreeMap(){
        rbTree = new RedBlackTree<>();
    }

    @Override
    public void put(E key, V value) {
        rbTree.add(key,value);
    }

    @Override
    public boolean contains(E key) {
        return rbTree.contains(key);
    }

    @Override
    public V get(E key) {
        return rbTree.get(key);
    }

    @Override
    public boolean isEmpty() {
        return rbTree.isEmpty();
    }

    @Override
    public int size() {
        return rbTree.getSize();
    }
}
