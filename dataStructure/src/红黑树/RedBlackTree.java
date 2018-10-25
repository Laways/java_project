package 红黑树;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Stack;

public class RedBlackTree <E extends Comparable<E>, V>{

    private int size;
    private Node root;
    private final static boolean RED = true;
    private final static boolean BLACK = false;

    private class Node{
        public E key;
        public V value;
        public Node left;
        public Node right;
        public boolean color;
        public Node(){
            key = null;
            value = null;
            left = null;
            right = null;
            color = RED;
        }

        public Node(E key, V value){
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            this.color = RED;
        }
    }

    public RedBlackTree(){
        this.size = 0;
        root = new Node();
    }

    public int getSize(){
        return this.size;
    }

    public boolean isEmpty(){
        return this.size == 0;
    }

    //判断是否是红色
    private boolean isRed (Node node){
        if (node == null) {
            return false;
        }
        return node.color;
    }

    //左旋转
    private Node leftRotation (Node node){
        Node rightChild = node.right;
        node.right = rightChild.left;
        rightChild.left = node;

        rightChild.color = node.color;
        node.color = this.RED;

        return rightChild;
    }

    //右旋转
    private Node rightRotation (Node node){
        Node leftChild = node.left;
        node.left = leftChild.right;
        leftChild.right = node;

        leftChild.color = node.color;
        node.color = this.RED;

        return leftChild;
    }

    //颜色的翻转
    private void flipColors(Node node){
        node.color = this.RED;
        node.left.color = this.BLACK;
        node.right.color = this.BLACK;
    }

    //递归实现添加元素
    public void add(E key, V value){
        if (isEmpty()) {
            root = new Node(key,value);
            this.size++;
        } else {
            root = add(root , key, value);
        }
        root.color = this.BLACK;
    }

    private Node add(Node node, E key, V value) {
        if (node == null) {
            this.size++;
            return new Node(key, value);
        }
        if (node.key.compareTo(key) < 0) {
            node.right = add(node.right,key,value);
        } else if (node.key.compareTo(key) > 0) {
            node.left = add(node.left,key,value);
        } else {
            node.value = value;
        }

        //需要左旋
        if (isRed(node.right) && !isRed(node.left)) {
            node = leftRotation(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rightRotation(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }

        return node;
    }

    public boolean contains(E key) {
        Node node = root;
        if (node.key == null) {
            return false;
        }
        while(true){
            if (node == null) {
                return false;
            }
            if (node.key.compareTo(key) > 0) {
                node = node.left;
            } else if (node.key.compareTo(key) < 0) {
                node = node.right;
            } else {
                return true;
            }
        }
    }

    public V get(E key){
        Node node = root;
        if (node.key == null) {
            return null;
        }
        while(true){
            if (node == null) {
                return null;
            }
            if (node.key.compareTo(key) > 0) {
                node = node.left;
            } else if (node.key.compareTo(key) < 0) {
                node = node.right;
            } else {
                return node.value;
            }
        }
    }

    //中序遍历 Middle order ergodic
    public String middleOrderTree (){
        StringBuilder sb = new StringBuilder();
        sb.append("middle order ergodic").append("\r\n");
        sb.append("Tree:[");
        middlePrint(this.root,sb);
        sb.append(']');
        return sb.toString();
    }

    //前序遍历 preOrder traversal
    public String preOrderTraversal() {
        StringBuilder sb = new StringBuilder();
        sb.append("preOrder traversal").append("\r\n");
        sb.append("Tree:[");
        preOrderPrint(this.root,sb);
        sb.append(']');
        return sb.toString();
    }

    //前序遍历非递归实现
    public String  preTraversalStack(){
        StringBuilder sb = new StringBuilder();
        sb.append("preTraversalStack").append("\r\n");
        sb.append("Tree:[");

        //借助栈
        Stack<Node> stack = new Stack<>();
        if (!isEmpty()) {
            stack.push(root);
            while(stack.size() != 0){
                Node tempNode = stack.pop();
                sb.append(tempNode.key).append(' ');
                if (tempNode.right != null) {
                    stack.push(tempNode.right);
                }
                if (tempNode.left != null) {
                    stack.push(tempNode.left);
                }
            }
        }
        sb.append(']');
        return sb.toString();
    }

    //后序遍历 postOrder traversal

    public String  postOrderTraversal(){
        StringBuilder sb = new StringBuilder();
        sb.append("postOrder traversal").append("\r\n");
        sb.append("Tree:[");
        postOrderPrint(this.root,sb);
        sb.append(']');
        return sb.toString();
    }

    private void postOrderPrint(Node node, StringBuilder sb) {
        if (node == null) {
            return ;
        }
        postOrderPrint(node.left,sb);
        postOrderPrint(node.right,sb);
        sb.append(node.key).append(' ');
    }

    private void preOrderPrint(Node node, StringBuilder sb) {
        if (node == null) {
            return;
        }
        sb.append(node.key).append(' ');
        preOrderPrint(node.left,sb);
        preOrderPrint(node.right,sb);
    }

    private void middlePrint(Node node, StringBuilder sb) {
        if (node == null) {
            return ;
        }
        middlePrint(node.left,sb);
        sb.append(node.key).append(' ');
        middlePrint(node.right,sb);
    }

    //层序遍历 level traversal
    public String levelTraversal (){
        StringBuilder sb = new StringBuilder();
        sb.append("level traversal").append("\r\n");
        sb.append("Tree:[");
        LinkedList<Node> list = new LinkedList<>();
        if (!isEmpty()) {
            list.addLast(root);
            while(list.size() != 0){
                Node node = list.removeFirst();
                sb.append(node.key).append('_').append(node.color).append(' ');
                if (node.left != null) {
                    list.addLast(node.left);
                }
                if (node.right != null) {
                    list.addLast(node.right);
                }
            }
        }
        sb.append(']');
        return sb.toString();
    }

    public Node findMin(){
        return findMin(root);
    }

    private Node findMin(Node node){
        if (isEmpty()) {
            throw new RuntimeException("空树没有最小值");
        }
        if (node.left == null) {
            return node;
        }else {
            return findMin(node.left);
        }
    }

    private Node findMax(Node node) {
        if (isEmpty()) {
            throw new RuntimeException("空树没有最大值");
        }
        if (node.right == null) {
            return node;
        }else{
            return findMax(node.right);
        }
    }

    @Test
    public void Test(){
        RedBlackTree<Integer, Integer> rbTree = new RedBlackTree<>();
        for (int i = 0; i < 10; i++) {
            rbTree.add(i,1);
        }
        System.out.println(rbTree.levelTraversal());
        System.out.println(rbTree.getSize());
    }

}

