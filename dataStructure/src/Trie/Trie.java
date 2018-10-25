package Trie;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

public class Trie {

    private Node root;

    private int size;

    private class Node {
        //当前元素
        public char c;
        //是不是字符串
        public boolean isword;
        //下个节点的指针集合
        public Map<Character,Node> next;

        public Node(boolean isword, char c){
            this.c = c;
            this.isword = isword;
            this.next = new HashMap<>();
        }

        public Node(){
            this(false, ' ');
        }
        public Node(char c){
            this(false, c);
        }
    }

    public Trie (){
        root = new Node();
    }

    //返回数量
    public int size (){
        return size;
    }

    //增加元素
    public void add (String str){
        Node node = root;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (node.next.get(c) == null) {
                node.next.put(c,new Node(c));
            }
            node = node.next.get(c);
        }
        if (!node.isword) {
            node.isword = true;
            size++;
        }
    }

    @Override
    //打印 -- 递归实现
    public String toString (){
        StringBuilder sb = new StringBuilder();
        sb.append("Trie:").append("\r\n").append('[');
        LinkedList<Character> list = new LinkedList<>();
        printTrie(root,sb,list);
        sb.append(']');
        return sb.toString();
    }

    private void printTrie(Node node, StringBuilder sb, LinkedList<Character> list) {
        list.addLast(node.c);
        if (node.isword) {
            for (Character c : list) {
                sb.append(c);
            }
            if (node.next.size() == 0) {
                list.removeLast();
                return ;
            }
        }
        for (Character c:node.next.keySet()) {
            printTrie(node.next.get(c),sb,list);
        }
        list.removeLast();
    }

    //删除元素 -- 非递归实现
    public void delete (String str){
        Stack<Node> stack = new Stack<>();
        Node node = root;
        stack.push(node);
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (node.next.containsKey(c)) {
                stack.push(node.next.get(c));
            } else {
                new RuntimeException("删除的元素不存在");
            }
            node = node.next.get(c);
        }
        if (node.isword) {
            node.isword = false;
            Node end = stack.pop();
            //如果节点是最后一个节点，需要特殊处理，如果是中间结节，直接修改isword就行了
            if (node.next.size() == 0) {
                char removeKey = end.c;
                while(stack.size() != 0){
                    Node temp = stack.pop();
                    //遇到root节点、字符串节点、被其它字符串占用的节点，退过节点删除
                    if (temp == root || temp.isword || temp.next.size() > 1) {
                        break;
                    }
                    //删除节点
                    temp.next.remove(removeKey);
                    removeKey = temp.c;
                }
            }
            size--;
        } else {
            new RuntimeException("删除的元素不存在");
        }
    }

    //是否包含
    public boolean contains (String str){
        Node node = root;
        for (int i = 0; i < str.length(); i++) {
            if (node.next.containsKey(str.charAt(i))) {
                node = node.next.get(str.charAt(i));
            } else {
                return false;
            }
        }
        return node.isword;
    }

    //查询在Trie中是否有单词以prefix为前缀
    public boolean isPrefix (String prefix){
        Node node = root;
        return prefixFunction(node , 0, prefix);

    }

    private boolean prefixFunction(Node node, int index, String prefix) {
        //递归到底的情况
        if (index == prefix.length() - 1) {
            if ('.' == prefix.charAt(index)) {
                return true;
            } else {
                return node.next.containsKey(prefix.charAt(index));
            }
        }
        //递归中。。。。。
        if ('.' != prefix.charAt(index)) {
            if (node.next.get(prefix.charAt(index)) == null) {
                return false;
            }
            return prefixFunction(node.next.get(prefix.charAt(index)),index + 1 , prefix);
        } else {
            for (Character c : node.next.keySet()) {
                if (prefixFunction(node.next.get(c),index + 1 , prefix)) {
                    return true;
                }
            }
            return false;
        }
    }

    public static void main(String [] args){
        Trie trie = new Trie();
        trie.add("abc");
        trie.add("abf");
        trie.add("abcd");
        trie.add("abccde");
        trie.add("bcda");
        System.out.println(trie.size());
        System.out.println(trie.toString());
        trie.delete("abccde");
        System.out.println(trie.size());
        System.out.println(trie.toString());
        System.out.println(trie.contains("abc"));
        System.out.println(trie.isPrefix("c"));
    }
}










