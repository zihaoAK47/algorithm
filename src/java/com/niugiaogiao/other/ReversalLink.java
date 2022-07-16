package com.niugiaogiao.other;

/**
 * @author zi hao
 * @version 1.0
 * @date 2022-04-23 17:14
 */

public class ReversalLink {

    static class Node {

        public int val;
        public Node next;

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }

        public Node(int val) {
            this.val = val;
        }

        public int getVal() {
            return val;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public void setVal(int val) {
            this.val = val;
        }
    }

    // 反转链表
    public static Node reversalNode(Node head) {
        Node pre = null;
        Node next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }

        return pre;
    }

    // 删除链表
    public static Node delNode(Node node, int num) {
        while (null != node) {
            if (node.val != num) {
                break;
            }
            node = node.next;
        }
        Node pre = node;
        Node next = node;
        while (next != null) {
            if (next.val == num) {
                pre.next = next.next;
            } else {
                pre = next;
            }
            next = next.next;
        }

        return node;
    }

    public static void main1(String[] args) {
        Node root = new Node(1);
        Node node1 = new Node(2);
        Node node2 = new Node(3);
        root.next = node1;
        node1.next = node2;
//        root = reversalNode(root);
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        Node node1 = new Node(2);
        Node node2 = new Node(3);
        root.next = node1;
        node1.next = node2;
        root = delNode(root, 1);
        System.err.println("");
    }
}
