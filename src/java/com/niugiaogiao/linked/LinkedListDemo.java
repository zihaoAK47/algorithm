package com.niugiaogiao.linked;

/**
 * @author zi hao
 * @version 1.0
 * @date 2022-05-03 14:21
 */

import java.util.ArrayList;

public class LinkedListDemo {

    static class Node {
        public int val;
        public Node next;

        public Node(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }

    // 给定链表头结点，奇数长度返回中点，偶数长度返回上中点
    public static Node test1(Node head) {
        if (null == head || head.next == null || head.next.next == null) {
            return head;
        }

        Node pre = head.next;
        Node nex = head.next.next;

        while (nex.next != null && nex.next.next != null) {
            pre = pre.next;
            nex = nex.next.next;
        }

        return pre;
    }

    // 给定链表头结点，奇数长度返回中点，偶数长度返回下中点
    public static Node test2(Node head) {
        if (null == head || head.next == null) {
            return head;
        }

        Node pre = head.next;
        Node nxt = head.next;
        while (nxt.next != null && nxt.next.next != null) {
            pre = pre.next;
            nxt = nxt.next.next;
        }

        return pre;
    }

    // 奇数返回中点的前一个，偶数返回上中点的前一个
    public static Node test3(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }

        Node pre = head;
        Node nxt = head.next.next;
        while (nxt.next != null && nxt.next.next != null) {
            pre = pre.next;
            nxt = nxt.next.next;
        }

        return pre;
    }

    // 奇数返回中点前一个，偶数返回下中点前一个
    public static Node test4(Node head) {
        if (head == null || head.next == null) {
            return null;
        }

        if (head.next.next == null) {
            return head;
        }

        Node pre = head;
        Node nxt = head.next;

        while (nxt.next != null && nxt.next.next != null) {
            pre = pre.next;
            nxt = nxt.next.next;
        }

        return pre;
    }

    public static Node right1(Node head) {
        if (head == null) {
            return null;
        }

        Node cur = head;
        ArrayList<Node> arr = new ArrayList<>();
        while (cur != null) {
            arr.add(cur);
            cur = cur.next;
        }

        return arr.get((arr.size() - 1) / 2);
    }

    public static Node right2(Node head) {
        if (head == null) {
            return null;
        }
        Node cur = head;
        ArrayList<Node> arr = new ArrayList<>();
        while (cur != null) {
            arr.add(cur);
            cur = cur.next;
        }
        return arr.get(arr.size() / 2);
    }

    public static Node right3(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }
        Node cur = head;
        ArrayList<Node> arr = new ArrayList<>();
        while (cur != null) {
            arr.add(cur);
            cur = cur.next;
        }

        return arr.get((arr.size() - 3) / 2);
    }

    public static Node right4(Node head) {
        if (head == null || head.next == null) {
            return null;
        }

        Node cur = head;
        ArrayList<Node> arr = new ArrayList<>();
        while (cur != null) {
            arr.add(cur);
            cur = cur.next;
        }
        return arr.get((arr.size() - 2) / 2);
    }


    public static void main(String[] args) {
        Node head = new Node(1);
        Node node1 = new Node(2);
        Node node2 = new Node(3);
        Node node3 = new Node(4);
        Node node4 = new Node(5);
        Node node5 = new Node(6);
        Node node6 = new Node(7);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
//        node3.next = node4;
//        node4.next = node5;
//        node5.next = node6;
//        System.err.println(test1(head));
//        System.err.println(test2(head));
//        System.err.println(test3(head));
        System.err.println(test4(head));
    }
}
