package com.niugiaogiao.linked.leetcode;

import java.util.Stack;

/**
 * 给定一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-07-04 16:41
 */
public class LinkedList021 {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        Stack<ListNode> stack = new Stack<>();
        for (ListNode item = head; item != null; item = item.next) {
            stack.push(item);
        }
        for (int i = 0; i < n; i++) {
            stack.pop();
        }
        if (stack.isEmpty()) {
            head = head.next;
            return head;
        } else {
            ListNode preNode = stack.pop();
            preNode.next = preNode.next.next;
            return head;
        }
    }

    public ListNode removeNthFromEnd2(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        int len = 0;
        for (ListNode temp = head ; temp != null ; temp = temp.next) {
            len++;
        }
        if (len - n == 0) {
            head = head.next;
            return head;
        }

        ListNode preNode = head;
        for (int i = 1 ; i < len - n ; i ++) {
            preNode = preNode.next;
        }
        preNode.next = preNode.next.next;
        return head;
    }

    public ListNode removeNthFromEnd3(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        ListNode newHead = new ListNode(0, head);
        ListNode tempNode = newHead;
        for (int i = 0 ; i < n ; i ++) {
            head = head.next;
        }
        while (head != null) {
            head = head.next;
            tempNode = tempNode.next;
        }
        tempNode.next = tempNode.next.next;
        return newHead.next;
    }
}
