package com.niugiaogiao.linked.leetcode;


/**
 * 给你链表的头节点 head ，每k个节点一组进行翻转，请你返回修改后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是k的整数倍，那么请将最后剩余的节点保持原有顺序。
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-05-27 11:07
 */
public class LinkedListReverseByK25 {

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

    public static ListNode reverseKGroup1(ListNode head, int k) {
        if (head == null || head.next == null || k <= 1) {
            return head;
        }

        ListNode newHead = null;
        ListNode tHead = head;
        while (tHead != null) {
            int i = 0;
            ListNode overNode = tHead;
            while (i < k && overNode != null) {
                i++;
                overNode = overNode.next;
            }

            if (overNode == null && i != k) {
                ListNode temp = newHead;
                while (temp != null && temp.next != null) {
                    temp = temp.next;
                }
                temp.next = tHead;
                return newHead;
            }

            ListNode preNode = null;
            ListNode tempNode = null;
            while (tHead != overNode) {
                tempNode = tHead.next;
                tHead.next = preNode;
                preNode = tHead;
                tHead = tempNode;
            }

            if (newHead == null) {
                newHead = preNode;
            } else {
                ListNode temp = newHead;
                while (temp.next != null) {
                    temp = temp.next;
                }
                temp.next = preNode;
            }
        }

        return newHead;
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode hair = new ListNode(0);
        hair.next = head;
        ListNode pre = hair;

        while (head != null) {
            ListNode tail = pre;
            // 查看剩余部分长度是否大于等于 k
            for (int i = 0; i < k; ++i) {
                tail = tail.next;
                if (tail == null) {
                    return hair.next;
                }
            }
            ListNode nex = tail.next;
            ListNode[] reverse = myReverse(head, tail);
            head = reverse[0];
            tail = reverse[1];
            // 把子链表重新接回原链表
            pre.next = head;
            tail.next = nex;
            pre = tail;
            head = tail.next;
        }

        return hair.next;
    }

    public static ListNode[] myReverse(ListNode head, ListNode tail) {
        ListNode prev = tail.next;
        ListNode p = head;
        while (prev != tail) {
            ListNode nex = p.next;
            p.next = prev;
            prev = p;
            p = nex;
        }
        return new ListNode[]{tail, head};
    }

    /**
     * -_-  -_- -_- -_- -_- -_-
     */
    public static void main(String[] args) {
        ListNode root = new ListNode(1);
        ListNode t1 = new ListNode(2);
        ListNode t2 = new ListNode(3);
        ListNode t3 = new ListNode(4);
        root.next = t1;
        t1.next = t2;
        t2.next = t3;

        System.err.println(reverseKGroup(root, 3));
    }
}
