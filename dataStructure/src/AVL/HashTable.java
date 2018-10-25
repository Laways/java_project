package AVL;


import java.util.Map;
import java.util.TreeMap;

public class HashTable<E,V>  {
    private TreeMap<E,V>[] hashTable;
    private int capacity;
    private int size;

    //每棵树平均哈希冲突的上界
    private final static int upperTol = 10;
    //每棵对平均哈希冲突的下界
    private final static int lowerTol = 2;
    //初始数组的大小
    private final static int initCapacity = 7;

    //计算哈希值
    private int hash(E key){
        return (key.hashCode() & 0x7fffffff) % capacity;
    }

    public HashTable(int capacity){
        this.capacity = capacity;
        hashTable = new TreeMap[capacity];
        for (int i = 0; i < capacity; i++) {
            hashTable[i] = new TreeMap<>();
        }
        size = 0;
    }

    public HashTable(){
        this(initCapacity);
    }

    public void put(E key, V value) {
        TreeMap<E,V> map = hashTable[hash(key)];
        if (map.containsKey(key)) {
            map.put(key,value);
        } else {
            map.put(key,value);
            size++;
            if (size >= capacity * upperTol) {
                resize(2*capacity);
            }
        }
    }

    public boolean contains(E key) {
        return hashTable[hash(key)].containsKey(key);
    }

    public V get(E key) {
        return hashTable[hash(key)].get(key);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return this.size;
    }

    public V remove(E key){
        V returnData = null;
        TreeMap<E,V> map = hashTable[hash(key)];
        if (map.containsKey(key)) {
            returnData = map.remove(key);
            size--;
            if (lowerTol < size / capacity && capacity / 2>= initCapacity) {
                resize(capacity / 2);
            }
        }
        return returnData;
    }

    private void resize(int newCapacity) {
        TreeMap[] mapArr = new TreeMap[newCapacity];
        for (int i = 0; i < newCapacity; i++) {
            mapArr[i] = new TreeMap();
        }
        int oldCapacity = capacity;
        //重新设置新的容量，用于生成每个元素key的hashCode值
        this.capacity = newCapacity;
        for (int i = 0; i < oldCapacity; i++) {
            for (Map.Entry<E,V> entry : this.hashTable[i].entrySet()) {
                mapArr[hash(entry.getKey())].put(entry.getKey(),entry.getValue());
            }
        }
        this.hashTable = mapArr;

    }

    public static void main(String [] args){
        HashTable<Integer, Integer> hashTable = new HashTable<>();
        for (int i = 0; i < 100000; i++) {
            hashTable.put(i,1);
        }
        System.out.println(hashTable.contains(888));
        System.out.println(hashTable.size());
    }
}









