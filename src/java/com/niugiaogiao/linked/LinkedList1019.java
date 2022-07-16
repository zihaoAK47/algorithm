package com.niugiaogiao.linked;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author zi hao
 * @version 1.0
 * @date 2022-06-16 9:45
 */
public class LinkedList1019 {

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


    public static int[] nextLargerNodes1(ListNode head) {
        if (head == null) {
            return null;
        }
        int size = 0;
        for (ListNode tempHead = head; tempHead != null; tempHead = tempHead.next)
            size++;

        int[] result = new int[size];
        size = 0;
        for (ListNode pre = head; pre != null; pre = pre.next) {
            boolean flag = false;
            for (ListNode itemNode = pre.next; itemNode != null; itemNode = itemNode.next) {
                if (itemNode.val > pre.val) {
                    flag = true;
                    result[size++] = itemNode.val;
                    break;
                }
            }

            if (!flag)
                result[size++] = 0;
        }

        return result;
    }

    public static int[] nextLargerNodes(ListNode head) {
        if (head == null) {
            return null;
        }

        int len = 0;
        ListNode pre = null;
        ListNode tempHead = head;
        while (tempHead != null) {
            len++;
            ListNode nextNode = tempHead.next;
            tempHead.next = pre;
            pre = tempHead;
            tempHead = nextNode;
        }

        // init result array
        int[] result = new int[len];
        Deque<ListNode> stack = new LinkedList<>();
        while (pre != null) {
            while (!stack.isEmpty() && pre.val >= stack.peek().val) {
                stack.pop();
            }

            result[--len] = stack.isEmpty() ? 0 : stack.peek().val;
            stack.push(pre);
            pre = pre.next;
        }

        return result;
    }
}
