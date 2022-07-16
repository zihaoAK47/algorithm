package com.niugiaogiao.linked;

import java.util.Stack;

/**
 * 链表是否是回文
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-05-03 20:57
 */
public class LinkedListPalindrome {

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

    /**
     * 是否是回文 使用栈的方式
     *
     * @param head
     * @return
     */
    public static boolean isPalindromeIsStack(Node head) {
        if (head == null) {
            return false;
        }
        if (head.next == null) {
            return true;
        }
        Stack<Node> stack = new Stack<>();
        Node temp = head;
        while (temp != null) {
            stack.add(temp);
            temp = temp.next;
        }

        while (!stack.empty()) {
            if (head.val != stack.pop().val) {
                return false;
            }

            head = head.next;
        }

        return true;
    }

    /**
     * 是否是回文 使用栈，只压入一半
     *
     * @param head
     * @return
     */
    public static boolean isPalindrome(Node head) {
        if (null == head)
            return false;
        if (head.next == null)
            return true;

        Node pre = head.next;
        Node nxt = head;
        while (nxt.next != null && nxt.next.next != null) {
            pre = pre.next;
            nxt = nxt.next.next;
        }
        Stack<Node> stack = new Stack<>();
        while (pre != null) {
            stack.push(pre);
            pre = pre.next;
        }

        while (!stack.isEmpty()) {
            if (head.val != stack.pop().val) {
                return false;
            }

            head = head.next;
        }

        return true;
    }

    public static boolean isPalindrome3(Node head) {
        if (null == head || head.next == null) {
            return true;
        }
        // 先遍历到中间节点
        // 从中间节点开始进行链表反转
        Node pre = head;
        Node nxt = head;
        while (nxt.next != null && nxt.next.next != null) {
            pre = pre.next;
            nxt = nxt.next.next;
        }
        // 调整节点指向
        Node backNode = null;
        Node tempNode;
        while (pre != null) {
            tempNode = pre.next;
            pre.next = backNode;
            backNode = pre;
            pre = tempNode;
        }
        // backNode 此时就是链表的尾巴
        tempNode = backNode;
        Node tempHead = head;
        while (tempNode != null && tempHead != null) {
            if (tempHead.val != tempNode.val) {
                return false;
            }

            tempHead = tempHead.next;
            tempNode = tempNode.next;
        }
        // 将链表复原
        tempNode = null;
        Node t;
        while (backNode != null) {
            t = backNode.next;
            backNode.next = tempNode;
            tempNode = backNode;
            backNode = t;
        }

        return true;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        Node node1 = new Node(1);
        Node node2 = new Node(1);
        Node node3 = new Node(1);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        System.err.println(isPalindrome3(head));
    }
}
