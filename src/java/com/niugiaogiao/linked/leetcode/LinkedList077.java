package com.niugiaogiao.linked.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 给定链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-08-01 23:28
 */
public class LinkedList077 {

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

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        PriorityQueue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(item -> item.val));
        while (head != null) {
            queue.add(head);
            head = head.next;
        }

        ListNode result = new ListNode(0);
        ListNode resultPre = result;
        while (!queue.isEmpty()) {
            resultPre.next = queue.poll();
            resultPre = resultPre.next;
        }
        resultPre.next = null;

        return result;
    }

    public ListNode sortList1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        return splitList(head, null);
    }

    public ListNode splitList(ListNode start, ListNode end) {
        if (start == null) {
            return start;
        } else if (start.next == end) {
            start.next = null;
            return start;
        }

        ListNode temp = start;
        ListNode next = start;
        while (next != end) {
            temp = temp.next;
            next = next.next;
            if (next != end) {
                next = next.next;
            }
        }
        return merge(splitList(start, temp), splitList(temp, end));
    }

    public ListNode merge(ListNode head1, ListNode head2) {
        ListNode result = new ListNode(0);
        ListNode pre = result;
        ListNode temp1 = head1;
        ListNode temp2 = head2;
        while (temp1 != null && temp2 != null) {
            if (temp1.val <= temp2.val) {
                pre.next = temp1;
                temp1 = temp1.next;
            } else {
                pre.next = temp2;
                temp2 = temp2.next;
            }
            pre = pre.next;
        }

        if (temp1 != null) {
            pre.next = temp1;
        } else if (temp2 != null) {
            pre.next = temp2;
        }

        return result.next;
    }
}
