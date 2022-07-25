package com.niugiaogiao.linked.leetcode;

import java.util.Stack;

/**
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表
 * @author zi hao
 * @version 1.0
 * @date 2022-06-09 15:22
 */
public class LinkedList206 {

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

    public static ListNode reverseList1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode pre = null;
        ListNode temp = head;
        while (temp != null) {
            ListNode back = temp.next;
            temp.next = pre;
            pre = temp;
            temp = back;
        }

        return pre;
    }

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode temp = head;
        Stack<ListNode> stack = new Stack<>();
        while(temp != null) {
            stack.push(temp);
            temp = temp.next;
        }

        ListNode newHead = new ListNode(0);
        ListNode preNode = newHead;
        while(!stack.isEmpty()) {
            ListNode itemNode = stack.pop();
            itemNode.next = null;
            preNode.next = itemNode;
            preNode = preNode.next;
        }

        return newHead.next;
    }


    public ListNode reverseList12(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseList12(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}
