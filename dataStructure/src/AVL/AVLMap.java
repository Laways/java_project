package AVL;

public class AVLMap<E extends Comparable<E>, V> implements TreeMap<E , V> {

    private AVLTree<E,V> avlTree;

    //判断是否是一个平衡二叉树
    public boolean isBalanced (){

        return avlTree.isBalanced();
    }

    public AVLMap(){
        avlTree = new AVLTree();
    }

    @Override
    public void put(E key, V value) {
        avlTree.put2(key,value);
    }



    @Override
    public boolean contains(E key) {
        return avlTree.contains(key);
    }

    @Override
    public V get(E key) {
        return avlTree.get(key);
    }

    @Override
    public boolean isEmpty() {
        return avlTree.isEmpty();
    }

    @Override
    public int size() {
        return avlTree.getSize();
    }

    @Override
    public String  toString(){

        return avlTree.preOrderTraversal();
    }
}
