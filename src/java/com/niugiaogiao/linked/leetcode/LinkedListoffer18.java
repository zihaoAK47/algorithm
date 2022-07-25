package com.niugiaogiao.linked.leetcode;

/**
 * @author zi hao
 * @version 1.0
 * @date 2022-06-23 18:58
 */
public class LinkedListoffer18 {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }


    public ListNode deleteNode(ListNode head, int val) {
        if (head == null) {
            return null;
        }

        ListNode newHead = new ListNode(0);
        newHead.next = head;
        ListNode backNode = newHead;
        ListNode pre = head;
        while (head != null) {
            if (head.val == val) {
                backNode.next = head.next;
                head = head.next;
            } else {
                head = head.next;
                backNode = backNode.next;
            }
        }

        return newHead.next;
    }
}
