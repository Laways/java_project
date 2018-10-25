package 二分搜索树;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Random;
import java.util.Stack;

public class BinarySearchTree <E extends Comparable<E>>{

    private int size;

    //长度
    private int length = 0;

    private Node root;

    public BinarySearchTree(){
        this.size = 0;
        root = new Node();
    }

    private class Node{
        public E e;
        public Node left;
        public Node right;
        public Node(){
            e = null;
            left = null;
            right = null;
        }

        public Node(E e){
            this.e = e;
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

    //递归实现添加元素
    public void add(E e){
        if (isEmpty()) {
            root = new Node(e);
            this.size++;
        } else {
            root = add(root , e);
        }
    }

    private Node add(Node node, E e) {
        if (node == null) {
            this.size++;
            return new Node(e);
        }
        if (node.e.compareTo(e) < 0) {
            node.right = add(node.right,e);
        } else if (node.e.compareTo(e) > 0) {
            node.left = add(node.left,e);
        }
       return node;
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
                sb.append(tempNode.e).append(' ');
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
        sb.append(node.e).append(' ');
    }

    private void preOrderPrint(Node node, StringBuilder sb) {
        if (node == null) {
            return;
        }
        sb.append(node.e).append(' ');
        preOrderPrint(node.left,sb);
        preOrderPrint(node.right,sb);
    }

    private void middlePrint(Node node, StringBuilder sb) {
        if (node == null) {
            return ;
        }
        middlePrint(node.left,sb);
        sb.append(node.e).append(' ');
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
                sb.append(node.e).append(' ');
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
    public void remove (E e){
        root = remove(root, e);
    }

    private Node remove(Node node, E e) {
        if (node == null) {
            return null;
        } else if (e.compareTo(node.e) < 0) {
            node.left = remove(node.left,e);
            return node;
        } else if (e.compareTo(node.e) > 0) {
            node.right = remove(node.right,e);
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
        BinarySearchTree<Integer> bTree = new BinarySearchTree<>();
        Random random = new Random();
        for (int i = 0; i <6; i++) {
            bTree.add(random.nextInt(100));
        }
        bTree.add(57);
        System.out.println(bTree.getSize());
        System.out.println(bTree.middleOrderTree());
        System.out.println(bTree.preOrderTraversal());
        System.out.println(bTree.levelTraversal());
        bTree.remove(57);
        System.out.println(bTree.middleOrderTree());
        System.out.println(bTree.removeMin().e);
        System.out.println(bTree.middleOrderTree());
        System.out.println(bTree.removeMax().e);
        System.out.println(bTree.middleOrderTree());
    }

}

