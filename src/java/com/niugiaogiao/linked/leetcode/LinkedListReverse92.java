package com.niugiaogiao.linked.leetcode;

/**
 * 给你单链表的头指针 head 和两个整数left 和 right ，其中left <= right
 * 请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-05-31 10:51
 */
public class LinkedListReverse92 {

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

    public static void reverseNode(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
    }

    public static ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || head.next == null || left >= right)
            return head;

        ListNode newHead = new ListNode(0);
        newHead.next = head;
        ListNode backHead = newHead;
        ListNode preNode = backHead;
        for (int i = 0; i < left - 1; i++) {
            preNode = preNode.next;
        }

        ListNode afterNode = preNode;
        for (int i = 0; i < right - left + 1; i++) {
            afterNode = afterNode.next;
        }

        ListNode leftNode = preNode.next;
        ListNode rightNode = afterNode.next;
        preNode.next = null;
        afterNode.next = null;

        reverseNode(leftNode);

        preNode.next = afterNode;
        leftNode.next = rightNode;

        return newHead.next;
    }
}
