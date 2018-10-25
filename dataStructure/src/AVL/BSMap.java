package AVL;

public class BSMap<E extends Comparable<E>, V> implements TreeMap<E,V>{

    private BSTree<E,V> bsTree;

    public BSMap(){
        bsTree = new BSTree<>();
    }

    @Override
    public void put(E key, V value) {
        bsTree.put(key,value);
    }

    @Override
    public boolean contains(E key) {
        return bsTree.contains(key);
    }

    @Override
    public V get(E key) {
        return bsTree.get(key);
    }

    @Override
    public boolean isEmpty() {
        return bsTree.isEmpty();
    }

    @Override
    public int size() {
        return bsTree.getSize();
    }
}
