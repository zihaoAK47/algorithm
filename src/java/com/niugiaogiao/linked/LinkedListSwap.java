package com.niugiaogiao.linked;

/**
 * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。
 * 你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-05-26 11:07
 */
public class LinkedListSwap {

    static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int val) {
            this.val = val;
        }

        public void setNext(ListNode next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }

    public static ListNode swapPairs1(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode tempNode = pre;
        while (tempNode.next != null && tempNode.next.next != null) {
            ListNode startNode = tempNode.next;
            ListNode endNode = tempNode.next.next;
            tempNode.next = endNode;
            startNode.next = endNode.next;
            endNode.next = startNode;
            tempNode = startNode;
        }

        return pre.next;
    }

    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode newHead = head.next;
        head.next = swapPairs(newHead.next);
        newHead.next = head;
        return newHead;
    }

    public static void main(String[] args) {
        ListNode root = new ListNode(1);
        ListNode r1 = new ListNode(2);
        ListNode r3 = new ListNode(3);
        ListNode r4 = new ListNode(4);
        root.next = r1;
        r1.next = r3;
//        r3.next = r4;
        System.err.println(swapPairs(root));
    }
}
