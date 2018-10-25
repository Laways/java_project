package AVL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int size = 10000;
        HashMap map = new HashMap();
        Random random = new Random();
        List<Integer> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
        AVLMap<Integer, Integer> avlMap = new AVLMap<>();
        System.out.println("avlMap最差情况用时："+testTreeMap(avlMap,list));
        System.out.println(avlMap.isBalanced());
        BSMap<Integer,Integer> bsMap = new BSMap<>();
        System.out.println("BSMap最差情况用时："+testTreeMap(bsMap,list));
        RBTreeMap<Integer,Integer> rbTreeMap = new RBTreeMap<>();
        System.out.println("RBTreeMap最差情况用时："+testTreeMap(rbTreeMap,list));
        wordTest();
    }

    private static void wordTest() {
        System.out.println("Pride and Prejudice");
        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile("D:/workspace/java_project/dataStructure/src/pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());
            // Test AVL
            long startTime = System.nanoTime();

            AVLMap<String, Integer> avl = new AVLMap<>();
            for (String word : words) {
                  if (avl.contains(word))
                    avl.put(word, avl.get(word) + 1);
                else
                    avl.put(word, 1);
            }

            for(String word: words)
                avl.contains(word);

            long endTime = System.nanoTime();

            double time = (endTime - startTime) / 1000000000.0;
            System.out.println("AVL: " + time + " s");
            System.out.println("AVL_size: " + avl.size() + " s");
            System.out.println("pride-->"+avl.get("pride"));
            System.out.println("prejudice-->"+avl.get("prejudice"));
            startTime = System.nanoTime();
            BSMap<String, Integer> bsMap = new BSMap<>();
            for (String word : words) {
                if (bsMap.contains(word))
                    bsMap.put(word, bsMap.get(word) + 1);
                else
                    bsMap.put(word, 1);
            }

            for(String word: words)
                bsMap.contains(word);

            endTime = System.nanoTime();

            double time2 = (endTime - startTime) / 1000000000.0;
            System.out.println("BSTree: " + time2 + " s");
            System.out.println("BS_size: " + bsMap.size() + " s");
            System.out.println("pride-->"+bsMap.get("pride"));
            System.out.println("prejudice-->"+bsMap.get("prejudice"));
            startTime = System.nanoTime();
            RBTreeMap<String, Integer> rbTreeMap = new RBTreeMap<>();
            for (String word : words) {
                if (rbTreeMap.contains(word))
                    rbTreeMap.put(word, rbTreeMap.get(word) + 1);
                else
                    rbTreeMap.put(word, 1);
            }
            for(String word: words)
                rbTreeMap.contains(word);
            endTime = System.nanoTime();
            double time3 = (endTime - startTime) / 1000000000.0;
            System.out.println("RBTree: " + time3 + " s");
            System.out.println("RB_size: " + rbTreeMap.size() + " s");
            System.out.println("pride-->"+rbTreeMap.get("pride"));
            System.out.println("prejudice-->"+rbTreeMap.get("prejudice"));
            startTime = System.nanoTime();

            HashTable<String, Integer> hashTable = new HashTable<>();
            for (String word : words) {
                if (hashTable.contains(word))
                    hashTable.put(word, rbTreeMap.get(word) + 1);
                else
                    hashTable.put(word, 1);
            }
            for(String word: words)
                hashTable.contains(word);
            endTime = System.nanoTime();
            double time4 = (endTime - startTime) / 1000000000.0;
            System.out.println("hashTable: " + time4 + " s");
            System.out.println("hashTable_size: " + rbTreeMap.size() + " s");
            System.out.println("pride-->"+hashTable.get("pride"));
            System.out.println("prejudice-->"+hashTable.get("prejudice"));
        }
    }

    public static<E extends Comparable<E>,V> double testTreeMap(TreeMap<E ,V> map, List<E> list){
        long startTime = System.nanoTime();

        for (E temp : list) {
            if (map.contains(temp))
                map.put(temp, map.get(temp));
            else
                map.put(temp, (V) new Object());
        }

        for(E temp: list)
            map.contains(temp);

        long endTime = System.nanoTime();

        double time = (endTime - startTime) / 1000000000.0;
        return time;
    }
}