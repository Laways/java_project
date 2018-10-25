package AVL;

public interface TreeMap <E extends Comparable<E> , V>{
    void put(E key, V value);

    boolean contains(E key);

    V get(E key);

    boolean isEmpty();

    int size();

}
