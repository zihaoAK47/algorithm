package com.niugiaogiao.linked;

/**
 * @author zi hao
 * @version 1.0
 * @date 2022-05-04 11:41
 */

import java.util.HashMap;
import java.util.Map;

/**
 * rand 指针是单链表节点结构中新增的指针，rand 可能指向链表中的任意一个节点，也可能指向 null
 * 给定一个由 Node 节点类型组成的无环单链表的头结点 head，请实现一个函数完成链表的复制
 * 并返回复制的新链表头结点
 * 时间复杂度 O(N)，额外空间复杂度 O(1)
 */
public class LinkedListCopy {

    static class Node {
        public int val;
        public Node next;
        public Node rand;

        public Node(int val) {
            this.val = val;
        }
    }


    public static Node test(Node head) {
        if (null == head) {
            return null;
        }

        Node tempHead = head;
        Node resPre = null;
        Node resBack = null;
        while (tempHead != null) {
            Node nNode = new Node(tempHead.val);
            nNode.rand = tempHead.next;
            if (null == resPre) {
                resPre = nNode;
                resBack = nNode;
            } else {
                resBack.next = nNode;
                resBack = nNode;
            }

            tempHead = tempHead.next;
        }

        return resPre;
    }

    /**
     * 根据 hash 表的方式构建
     *
     * @param head
     * @return
     */
    public static Node copyHash(Node head) {
        if (head == null) {
            return null;
        }

        Map<Node, Node> dict = new HashMap<>();
        Node temp = head;
        while (temp != null) {
            dict.put(temp, new Node(temp.val));
            temp = temp.next;
        }
        temp = head;
        while (temp != null) {
            dict.get(temp).next = dict.get(temp.next);
            dict.get(temp).rand = dict.get(temp.rand);
            temp = temp.next;
        }

        return dict.get(head);
    }

    /**
     * 不用 Hash 表创建
     *
     * @param head
     * @return
     */
    public static Node copy(Node head) {
        if (null == head) {
            return null;
        }

        // 构建克隆节点
        Node temp = head;
        while (temp != null) {
            Node backNode = temp.next;
            temp.next = new Node(temp.val);
            temp.next.next = backNode;
            temp = backNode;
        }
        temp = head;
        Node copyNode = null;
        while (temp != null) {
            Node backNode = temp.next.next;
            copyNode = temp.next;
            copyNode.rand = copyNode.rand == null ? null : copyNode.rand.next;
            temp = backNode;
        }
        temp = head;
        Node res = head.next;
        while (temp != null) {
            Node backNode = temp.next.next;
            copyNode = temp.next;
            temp.next = backNode;
            copyNode.next = backNode != null ? backNode.next : null;
            temp = backNode;
        }
        return res;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        Node node1 = new Node(2);
        Node node2 = new Node(3);
        head.next = node1;
        node1.next = node2;

        head.rand = node2;
        node1.rand = head;
        node2.rand = node2;

//        test(head);
        copyHash(head);
    }
}
