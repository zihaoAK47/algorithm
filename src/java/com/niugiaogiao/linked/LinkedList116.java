package com.niugiaogiao.linked;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author zi hao
 * @version 1.0
 * @date 2022-07-12 9:37
 */
public class LinkedList116 {

    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    /**
     * 实现方式一
     *
     * @param root
     * @return
     */
    public static Node connect(Node root) {
        if (root == null) {
            return null;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                Node itemNode = queue.poll();
                if (itemNode != null && i < queueSize - 1) {
                    itemNode.next = queue.peek();
                }
                if (itemNode != null && itemNode.left != null) {
                    queue.add(itemNode.left);
                }
                if (itemNode != null && itemNode.right != null) {
                    queue.add(itemNode.right);
                }
            }
        }

        return root;
    }

    /**
     * 实现方式二
     *
     * @param root
     * @return
     */
    public static Node connect1(Node root) {
        if (root == null) {
            return null;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        Node curRight = root;
        Node nextRight = null;
        while (!queue.isEmpty()) {
            Node itemNode = queue.poll();
            if (itemNode != null && itemNode.left != null) {
                nextRight = itemNode.left;
                queue.add(itemNode.left);
            }
            if (itemNode != null && itemNode.right != null) {
                nextRight = itemNode.right;
                queue.add(itemNode.right);
            }
            if (itemNode == curRight) {
                curRight = nextRight;
            } else {
                itemNode.next = queue.peek();
            }
        }

        return root;
    }

    public static Node connect3(Node root) {
        if (root == null) {
            return null;
        }
        Node backNode = root;
        while (backNode.left != null) {
            Node itemNode = backNode;
            while (itemNode != null) {
                itemNode.left.next = itemNode.right;
                if (itemNode.next != null) {
                    itemNode.right.next = itemNode.next.left;
                }

                itemNode = itemNode.next;
            }
            backNode = backNode.left;
        }

        return root;
    }

    public Node connect4(Node root) {
        if(root==null){
            return root;
        }
        if(root.left!=null){
            root.left.next=root.right;
            root.right.next=root.next!=null?root.next.left:null;
            connect4(root.left);
            connect4(root.right);
        }
        return root;
    }

    public static void main(String[] args) {
        Node note1 = new Node(1);
        Node note2 = new Node(2);
        Node note3 = new Node(3);
        note1.left = note2;
        note1.right = note3;
        connect(note1);
    }

}
