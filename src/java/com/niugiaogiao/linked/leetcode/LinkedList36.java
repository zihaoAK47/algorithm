package com.niugiaogiao.linked.leetcode;

public class LinkedList36 {

    static class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }

    public static Node treeToDoublyList(Node root) {
        return null;
    }

    static Node head = null;
    static Node headPre = null;

    public static void run(Node root) {
        if (root == null) {
            return;
        }
        run(root.left);
        if (head == null) {
            head = root;
        } else {
            headPre.right = root;
            root.left = headPre;
        }
        headPre = root;
        run(root.right);
    }

    public static void main(String[] args) {
        Node r1 = new Node(4);
        Node r2 = new Node(2);
        Node r3 = new Node(5);
        Node r4 = new Node(1);
        Node r5 = new Node(3);
        r1.left = r2;
        r1.right = r3;
        r2.left = r4;
        r2.right = r5;
        run(r1);
        head.left = headPre;
        headPre.right = head;
        System.err.println("");
    }

}
