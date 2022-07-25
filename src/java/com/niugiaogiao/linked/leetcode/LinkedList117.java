package com.niugiaogiao.linked.leetcode;

/**
 * leetcode 117
 * 填充每个节点的下一个右侧节点指针 2
 * 只能使用常量级额外空间
 */

public class LinkedList117 {

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

    Node pre = null;
    Node head = null;

    public Node connect(Node root) {
        if (root == null) {
            return null;
        }

        Node tempRoot = root;
        while (tempRoot != null) {
            pre = null;
            head = null;
            for (Node p = tempRoot ; p != null ; p = p.next) {
                if (p.left != null) {
                    linked(p.left);
                }
                if (p.right != null) {
                    linked(p.right);
                }
            }

            tempRoot = head;
        }

        return root;
    }

    /**
     * connect1(root.right);
     * connect1(root.left);
     * 为什么先从右节点递归，在从左边节点递归
     * [2,1,3,0,7,9,1,2,null,1,0,null,null,8,8,null,null,null,null,7]
     * 每次递归执行更改的 next 指向 left -> right -> left 函数就进行下次递归了
     * 由于节点 9 叶子节点当 7.right 进行关联的时候就关联不到 8 因为 9 没有连接 1
     */
    public static Node connect1(Node root) {
        if (root == null) {
            return root;
        }
        if (root.left != null && root.right != null) {
            root.left.next = root.right;
        }
        if (root.left != null && root.right == null) {
            root.left.next = getNext(root.next);
        }
        if (root.right != null) {
            root.right.next = getNext(root.next);
        }
        connect1(root.right);
        connect1(root.left);
        return root;
    }

    public static Node getNext(Node root) {
        if (root == null) {
            return null;
        }
        if (root.left != null) {
            return root.left;
        }
        if (root.right != null) {
            return root.right;
        }
        return getNext(root.next);
    }

    public void linked(Node node) {
        if (head == null) {
            head = node;
            pre = head;
        } else {
            pre.next = node;
            pre = pre.next;
        }
    }

    public static void main(String[] args) {
        Node r1 = new Node(2);
        Node r2 = new Node(1);
        Node r3 = new Node(3);
        Node r4 = new Node(0);
        Node r5 = new Node(7);
        Node r6 = new Node(9);
        Node r7 = new Node(1);
        Node r8 = new Node(2);
        Node r9 = new Node(1);
        Node r10 = new Node(0);
        Node r11 = new Node(8);
        Node r12 = new Node(8);
        Node r13 = new Node(7);
        r1.left = r2;
        r1.right = r3;
        r2.left = r4;
        r2.right = r5;
        r3.left = r6;
        r3.right = r7;
        r4.left = r8;
        r5.left = r9;
        r5.right = r10;
        r7.left = r11;
        r7.right = r12;
        r10.left = r13;
        Node node = connect1(r1);
        System.err.println(node);
    }

}
