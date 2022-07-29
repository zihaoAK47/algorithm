package com.niugiaogiao.linked.leetcode;

/**
 * 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
 * 你不需要保留每个分区中各节点的初始相对位置
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-07-28 23:35
 */
public class LinkedList0204 {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode partition(ListNode head, int x) {
        if (head == null) {
            return null;
        }

        ListNode smallHead = new ListNode(0);
        ListNode smallPre = smallHead;
        ListNode lageHead = new ListNode(0);
        ListNode lagePre = lageHead;
        while (head != null) {
            ListNode next = head.next;
            head.next = null;
            if (head.val < x) {
                smallPre.next = head;
                smallPre = smallPre.next;
            } else {
                lagePre.next = head;
                lagePre = lagePre.next;
            }
            head = next;
        }
        if (smallHead.next != null) {
            smallPre.next = lageHead.next;
            return smallHead.next;
        } else {
            return lageHead.next;
        }
    }

    public ListNode partition1(ListNode head, int x) {
        ListNode after = head;
        ListNode before = head;
        while (before != null) {
            if (before.val < x) {
                int t = before.val;
                before.val = after.val;
                after.val = t;
                before = before.next;
                after = after.next;
            } else {
                while (before != null && before.val >= x) {
                    before = before.next;
                }
            }
        }

        return head;
    }
}
