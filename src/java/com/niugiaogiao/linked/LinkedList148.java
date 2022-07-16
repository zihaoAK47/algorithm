package com.niugiaogiao.linked;

/**
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-06-07 17:08
 */
public class LinkedList148 {

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

    public static ListNode merge(ListNode left, ListNode right) {
        ListNode newNode = new ListNode(0);
        ListNode temp = newNode;
        ListNode head1 = left;
        ListNode head2 = right;
        while (head1 != null && head2 != null) {
            if (head1.val >= head2.val) {
                temp.next = head2;
                temp = head2;
            } else {
                temp.next = head1;
                temp = head1;
            }

            head1 = head1.next;
            head2 = head2.next;
        }

        if (head1 != null) {
            temp.next = head1;
        } else if (head2 != null) {
            temp.next = head2;
        }

        return newNode.next;
    }

    public static ListNode sortList(ListNode head, ListNode tail) {
        if (head == null) {
            return null;
        }
        if (head.next == tail) {
            return head;
        }

        // find mid
        ListNode before = head;
        ListNode after = head.next;
        while (after != null && after.next != null) {
            before = before.next;
            after = after.next.next;
        }

        ListNode mid = before;
        ListNode list1 = sortList(head, mid);
        ListNode list2 = sortList(mid, tail);

        return merge(list1, list2);
    }

    public static void main(String[] args) {
        ListNode r1 = new ListNode(4);
        ListNode r2 = new ListNode(2);
        ListNode r3 = new ListNode(1);
        ListNode r4 = new ListNode(3);
        r1.next = r2;
        r2.next = r3;
        r3.next = r4;
        sortList(r1, null);
    }
}
