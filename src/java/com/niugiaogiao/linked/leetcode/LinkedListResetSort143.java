package com.niugiaogiao.linked.leetcode;

import java.util.Stack;

/**
 * 给定一个单链表 L 的头节点 head ，单链表 L 表示为：
 * L0 → L1 → … → Ln - 1 → Ln
 * 请将其重新排列后变为：
 * L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
 * 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-06-05 23:10
 */
public class LinkedListResetSort143 {

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

    public static void reorderList1(ListNode head) {
        // find midpoint
        ListNode back = head;
        ListNode pre = head.next;
        while (pre != null && pre.next != null) {
            back = back.next;
            pre = pre.next.next;
        }
        ListNode stackNode = back.next;
        back.next = null;
        Stack<ListNode> stack = new Stack<>();
        while (stackNode != null) {
            stack.push(stackNode);
            stackNode = stackNode.next;
        }

        pre = head;
        while (!stack.isEmpty()) {
            ListNode stackItem = stack.pop();
            stackItem.next = pre.next;
            pre.next = stackItem;
            pre = stackItem.next;
        }
    }

    public static void reorderList(ListNode head) {
        ListNode back = head;
        ListNode pre = head;
        while (pre != null && pre.next != null) {
            back = back.next;
            pre = pre.next.next;
        }
        // back is midpoint
        pre = null;
        ListNode mid = back;
        back = back.next;
        mid.next = null;
        while (back != null) {
            ListNode temp = back.next;
            back.next = pre;
            pre = back;
            back = temp;
        }

        back = head;
        while (back != null && pre != null) {
            ListNode tb = back.next;
            ListNode tp = pre.next;
            back.next = pre;
            pre.next = tb;
            pre = tp;
            back = tb;
        }
    }


    public static void main(String[] args) {
        ListNode r1 = new ListNode(1);
        ListNode r2 = new ListNode(2);
        ListNode r3 = new ListNode(3);
        ListNode r4 = new ListNode(4);
        ListNode r5 = new ListNode(5);
        r1.next = r2;
        r2.next = r3;
        r3.next = r4;
        r4.next = r5;
        reorderList(r1);
    }
}
