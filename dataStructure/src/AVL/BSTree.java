package AVL;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Random;
import java.util.Stack;

public class BSTree<E extends Comparable<E>, V>{

    private int size;

    //长度
    private int length = 0;

    private Node root;

    public BSTree(){
        this.size = 0;
        root = new Node();
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

    private class Node{
        public E key;
        public V value;
        public Node left;
        public Node right;
        public Node(){
            key = null;
            value = null;
            left = null;
            right = null;
        }

        public Node(E key, V value){
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    public int getSize(){
        return this.size;
    }

    public boolean isEmpty(){
        return this.size == 0;
    }

    //非递归实现添加元素
    public void put (E key, V value){
        if (isEmpty()) {
            root = new Node(key, value);
            this.size++;
        }else {
            Stack<Node> stack = new Stack<>();
            stack.push(root);
            Node node;
            while(stack.size() != 0){
                node = stack.pop();
                if (node.key.compareTo(key) < 0) {
                    if (node.right == null) {
                        node.right = new Node(key,value);
                        this.size++;
                        return ;
                    }
                    stack.push(node.right);
                } else if (node.key.compareTo(key) > 0) {
                    if (node.left == null) {
                        node.left = new Node(key,value);
                        this.size++;
                        return ;
                    }
                    stack.push(node.left);
                } else {
                    node.value = value;
                    return ;
                }
            }
        }
    }

    //递归实现添加元素
    public void add(E key, V value){
        if (isEmpty()) {
            root = new Node(key, value);
            this.size++;
        } else {
            root = add(root , key, value);
        }
    }

    private Node add(Node node, E key, V value) {
        if (node == null) {
            this.size++;
            return new Node(key,value);
        }
        if (node.key.compareTo(key) < 0) {
            node.right = add(node.right,key, value);
        } else if (node.key.compareTo(key) > 0) {
            node.left = add(node.left,key, value);
        } else {
            node.value = value;
        }
       return node;
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
    public String preOrderTraversal (){
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
                sb.append(tempNode.key).append("-->").append(tempNode.value).append(' ');
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
        sb.append(node.key).append("-->").append(node.value).append(' ');
    }

    private void preOrderPrint(Node node, StringBuilder sb) {
        if (node == null) {
            return;
        }
        sb.append(node.key).append("-->").append(node.value).append(' ');
        preOrderPrint(node.left,sb);
        preOrderPrint(node.right,sb);
    }

    private void middlePrint(Node node, StringBuilder sb) {
        if (node == null) {
            return ;
        }
        middlePrint(node.left,sb);
        sb.append(node.key).append("-->").append(node.value).append(' ');
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
                sb.append(node.key).append("-->").append(node.value).append(' ');
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

    public Node removeMin(){
        Node min =  findMin();
        root = removeMin(root);
        return min;
    }

    private Node removeMin(Node node) {
        if (node.left == null) {
            Node tempNode = node.right;
            node = null;
            size--;
            return tempNode;
        }else{
            node.left = removeMin(node.left);
            return node;
        }
    }

    public Node removeMax(){
        Node max = findMax(root);
        root = removeMax(root);
        return max;
    }

    private Node removeMax(Node node) {
        if (node.right == null) {
            Node tempNode = node.left;
            node = null;
            size--;
            return tempNode;
        }else {
            node.right = removeMax(node.right);
            return node;
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

    //删除任意节点
    public void remove (E key){
        root = remove(root, key);
    }

    private Node remove(Node node, E key) {
        if (node == null) {
            return null;
        } else if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left,key);
            return node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right,key);
            return node;
        } else {
            //左子树为空
            if (node.left == null) {
                Node tempNode = node.right;
                size--;
                node = null;
                return tempNode;
            }
            //右子树为空
            if (node.right == null) {
                Node tempNode = node.left;
                size--;
                node = null;
                return tempNode;
            }
            //有左右孩子
            Node rightMin = findMin(node.right);
            rightMin.right = removeMin(node.right);
            rightMin.left = node.left;
            node.right = node.left = node = null;
            return rightMin;
        }
    }

    @Test
    public void Test(){
        BSTree<Integer,Integer> bTree = new BSTree<>();
        Random random = new Random();
        for (int i = 0; i <6; i++) {
            bTree.add(random.nextInt(100),1);
        }
        bTree.add(1000,1);
        System.out.println(bTree.getSize());
        System.out.println(bTree.middleOrderTree());
        System.out.println(bTree.preOrderTraversal());
        System.out.println(bTree.levelTraversal());
        bTree.remove(57);
        System.out.println(bTree.middleOrderTree());
        System.out.println(bTree.removeMin().key);
        System.out.println(bTree.middleOrderTree());
        System.out.println(bTree.removeMax().key);
        System.out.println(bTree.middleOrderTree());
    }

}

