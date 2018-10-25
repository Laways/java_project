package AVL;

import java.util.LinkedList;
import java.util.Random;
import java.util.Stack;

public class AVLTree<E extends Comparable<E> , V>{

    private int size;

    private Node root;

    public AVLTree(){
        this.size = 0;
        root = new Node();
    }



    private class Node{
        public E key;
        public V value;
        public Node left;
        public Node right;
        public int length;
        public Node(){
            this(null,null);
            length = 0;
        }

        public Node(E key, V value){
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            length = 1;
        }
    }

    public int getSize(){
        return this.size;
    }

    public boolean isEmpty(){
        return this.size == 0;
    }
    //非递归实现(有BUG)
    public void put(E key, V value) {
        if (isEmpty()) {
            root = new Node(key,value);
            this.size++;
        } else {
            Stack<Node> stack = new Stack<>();
            Stack<Node> parentStack = new Stack<>();
            stack.push(root);
            parentStack.push(root);
            while(stack.size() != 0){
                Node node = stack.pop();
                if (node.key.compareTo(key) < 0) {
                    if (node.right == null) {
                        node.right = new Node(key,value);
                        this.size++;
                        keepBalance(parentStack);
                        break;
                    }
                    stack.push(node.right);
                    parentStack.push(node.right);
                } else if (node.key.compareTo(key) > 0) {
                    if (node.left == null) {
                        node.left = new Node(key,value);
                        this.size++;
                        keepBalance(parentStack);
                        break;
                    }
                    stack.push(node.left);
                    parentStack.push(node.left);
                } else {
                    node.value = value;
                    break;
                }
            }
        }
    }

    private void keepBalance(Stack<Node> parentStack) {
        int balanceFactor = 0;
        while(parentStack.size() != 0){
            Node node = parentStack.pop();
            balanceFactor = getBalanceFactor(node);
            if (Math.abs(balanceFactor) > 1) {
                //LL
                if (getLength(node.left) > getLength(node.right) && getLength(node.left.left) > getLength(node.left.right)) {
                    //需要右旋转right rotation
                    doRightRotation(node,parentStack);
                }
                if (getLength(node.right) > getLength(node.left) && getLength(node.right.right) > getLength(node.right.left)) {
                    //需要左旋left rotation
                    doLeftRotation(node,parentStack);
                }
                if (getLength(node.left) > getLength(node.right) && getLength(node.left.right) > getLength(node.left.left)) {
                    //需要先以node.left为根左旋，再以node为根右旋
                    doLeftRotationLR(node.left, node);
                    doRightRotation(node, parentStack);
                }
                if (getLength(node.right) > getLength(node.left) && getLength(node.right.left) > getLength(node.right.right)) {
                    //需要先以node.right为根发生右旋，再以node为根左旋
                    doRightRotationRL(node.right, node);
                    doLeftRotation(node, parentStack);
                }
            }
            updateLength(node);

        }
    }

    private void updateLength(Node node) {
        node.length = maxChildLength(node.left,node.right) + 1;
    }

    private void doRightRotation(Node node, Stack<Node> parentStack) {
        Node leftChild = node.left;
        Node temp = leftChild.right;

        leftChild.right = node;
        node.left = temp;

        updateLength(node);
        updateLength(leftChild);
        if (parentStack.size() == 0) {
            root = leftChild;
            return;
        }else {
            Node parent = parentStack.peek();
            parent.left = leftChild;
        }
    }

    private void doRightRotationRL(Node node, Node parent) {
        Node leftChild = node.left;
        Node temp = leftChild.right;

        leftChild.right = node;
        node.left = temp;

        parent.right = leftChild;

        updateLength(node);
        updateLength(leftChild);
    }

    private void doLeftRotation(Node node, Stack<Node> parentStack) {
        Node rightChild = node.right;
        Node temp = rightChild.left;

        rightChild.left = node;
        node.right = temp;

        updateLength(node);
        updateLength(rightChild);

        if (parentStack.size() == 0) {
            root = rightChild;
            return;
        }else{
            Node parent = parentStack.peek();
            parent.right = rightChild;
        }
    }

    private void doLeftRotationLR(Node node, Node parent) {
        Node rightChild = node.right;
        Node temp = rightChild.left;

        rightChild.left = node;
        node.right = temp;

        parent.left = rightChild;
        updateLength(node);
        updateLength(rightChild);

    }

    //递归实现添加元素
    public void put2(E key, V value){
        if (isEmpty()) {
            root = new Node(key, value);
            this.size++;
        } else {
            root = put2(root , key, value);
        }
    }

    private int getLength(Node node){
        if (node == null) {
            return 0;
        }
        return node.length;
    }

    private Node put2(Node node, E key , V value) {
        if (node == null) {
            this.size++;
            return new Node(key, value);
        }
        if (node.key.compareTo(key) < 0) {
            node.right = put2(node.right,key , value);
        } else if (node.key.compareTo(key) > 0) {
            node.left = put2(node.left,key, value);
        } else {
            node.value = value;
        }
        //更新length
        node.length = maxChildLength(node.left,node.right) + 1;
        node = balanceFunction(node);
        return node;
    }

    private Node balanceFunction(Node node) {
        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1) {
            //判断是哪种不平衡情况：LL、RR、LR、RL
            //LL
            if (getLength(node.left) > getLength(node.right) && getLength(node.left.left) > getLength(node.left.right)) {
                //需要右旋转right rotation
               node = rightRotation(node);
            }
            if (getLength(node.right) > getLength(node.left) && getLength(node.right.right) > getLength(node.right.left)) {
                //需要左旋left rotation
                node = leftRotation(node);
            }
            if (getLength(node.left) > getLength(node.right) && getLength(node.left.right) > getLength(node.left.left)) {
                //需要先以node.left为根左旋，再以node为根右旋
                node.left = leftRotation(node.left);
                node = rightRotation(node);
            }
            if (getLength(node.right) > getLength(node.left) && getLength(node.right.left) > getLength(node.right.right)) {
                //需要先以node.right为根发生右旋，再以node为根左旋
                node.right = rightRotation(node.right);
                node = leftRotation(node);
            }

        }
        return node;
    }

    private Node leftRotation(Node node) {
        Node rightChild = node.right;
        Node temp = rightChild.left;
        //旋转
        rightChild.left = node;
        node.right = temp;
        //维护高度
        node.length = maxChildLength(node.left,node.right) + 1;
        rightChild.length = maxChildLength(rightChild.left,rightChild.right) + 1;
        return rightChild;
    }

    private Node rightRotation(Node node) {
        Node leftChild = node.left;
        Node temp = leftChild.right;
        //旋转
        leftChild.right = node;
        node.left = temp;
        //维护高度
        node.length = maxChildLength(node.left,node.right) + 1;
        leftChild.length = maxChildLength(leftChild.left,leftChild.right) + 1;
        return leftChild;
    }

    // 判断该二叉树是否是一棵平衡二叉树
    public boolean isBalanced(){
        return isBalanced(root);
    }

    // 判断以Node为根的二叉树是否是一棵平衡二叉树，递归算法
    private boolean isBalanced(Node node){

        if(node == null)
            return true;

        int balanceFactor = getBalanceFactor(node);
        if(Math.abs(balanceFactor) > 1)
            return false;
        return isBalanced(node.left) && isBalanced(node.right);
    }

    private int getBalanceFactor(Node node) {
        return getLength(node.left) - getLength(node.right);
    }

    private int maxChildLength(Node left, Node right) {
        return getLength(left) > getLength(right) ? getLength(left) : getLength(right);
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
                sb.append(tempNode.key).append('_').append(tempNode.value).append(' ');
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
        sb.append(node.key).append('_').append(node.value).append(' ');
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
        sb.append(node.key).append('_').append(node.value).append(' ');
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
                sb.append(node.key).append(' ');
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
        Node returnNode;
        if (node == null) {
            return null;
        } else if (e.compareTo(node.key) < 0) {
            node.left = remove(node.left,e);
            returnNode = node;
        } else if (e.compareTo(node.key) > 0) {
            node.right = remove(node.right,e);
            returnNode = node;
        } else {
            //左子树为空
            if (node.left == null) {
                Node tempNode = node.right;
                size--;
                node = null;
                returnNode = tempNode;
            }
            //右子树为空
             else if (node.right == null) {
                Node tempNode = node.left;
                size--;
                node = null;
                returnNode = tempNode;
            } else{
                //有左右孩子
                Node rightMin = findMin(node.right);
                rightMin.right = remove(node.right,rightMin.key);
                rightMin.left = node.left;
                node.right = node.left = node = null;
                returnNode = rightMin;
            }
        }
        if (returnNode == null) {
            return null;
        }
        returnNode.length = maxChildLength(returnNode.left,returnNode.right) + 1;
        returnNode = balanceFunction(returnNode);

        return returnNode;
    }

    //是否包含Key
    public boolean contains (E key){
        Node node = root;
        if (node.key == null) {
            return false;
        }
        while(true){
            if (node == null) {
                return false;
            }
            if (key.compareTo(node.key) < 0) {
                node = node.left;
            } else if (key.compareTo(node.key) > 0) {
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
            if (key.compareTo(node.key) < 0) {
                node = node.left;
            } else if (key.compareTo(node.key) > 0) {
                node = node.right;
            } else {
                return node.value;
            }
        }
    }

    public static void main(String [] args){
        AVLTree<Integer, Integer> avl = new AVLTree<>();
        avl.put2(4,1);
        avl.put2(2,1);
        avl.put2(3,1);
        avl.put2(1,1);
        avl.put2(5,1);
        avl.put2(9,1);
        avl.put2(6,1);
        avl.put2(7,1);
        avl.put2(0,1);
        avl.put2(11,1);
        Random random = new Random();
/*        for (int i = 0; i < 15; i++) {
            avl.put(random.nextInt(100), 1);
        }*/
        System.out.println(avl.preOrderTraversal());
        System.out.println(avl.size);
        avl.remove(3);
        System.out.println(avl.preOrderTraversal());
        System.out.println(avl.isBalanced());
        System.out.println(avl.size);
    }
}

