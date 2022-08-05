package com.niugiaogiao.linked.leetcode;

/**
 * 给你一个链表的头节点 head 。删除 链表的 中间节点 ，并返回修改后的链表的头节点 head 。
 * 长度为 n 链表的中间节点是从头数起第 ⌊n / 2⌋ 个节点（下标从 0 开始），其中 ⌊x⌋ 表示小于或等于 x 的最大整数。
 * 对于 n = 1、2、3、4 和 5 的情况，中间节点的下标分别是 0、1、1、2 和 2
 */

public class LinkedList2095 {

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

    public static ListNode deleteMiddle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        ListNode tHead = new ListNode(0);
        tHead.next = head;
        ListNode node = head;
        ListNode pre = head;
        while (pre != null && pre.next != null) {
            pre = pre.next.next;
            node = node.next;
            tHead = tHead.next;
        }
        tHead.next = node.next;

        return head;
    }

    public static ListNode createNode(int[] data) {
        ListNode res = new ListNode();
        ListNode pre = res;
        for (int item : data) {
            pre.next = new ListNode(item);
            pre = pre.next;
        }

        return res.next;
    }

    public static void main(String[] args) {
        ListNode node = createNode(new int[]{1, 3, 4, 7, 1, 2, 6});
//        ListNode node = createNode(new int[]{1, 3, 4});
        deleteMiddle(node);
    }


}
