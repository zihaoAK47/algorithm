package com.niugiaogiao.linked.leetcode;

/**
 * 给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
 * <p>
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-06-20 10:24
 */
public class LinkedList445 {

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

    private static ListNode reverse(ListNode tHead) {
        ListNode pre = null;
        while (tHead != null) {
            ListNode nextNode = tHead.next;
            tHead.next = pre;
            pre = tHead;
            tHead = nextNode;
        }

        return pre;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null)
            return null;

        ListNode pre1 = reverse(l1);
        ListNode pre2 = reverse(l2);

        int carry = 0;
        ListNode newNode = null;
        while (pre1 != null && pre2 != null) {
            int res = pre1.val + pre2.val;
            res += carry;

            ListNode itemNode = new ListNode(res % 10);
            itemNode.next = newNode;
            newNode = itemNode;

            carry = res / 10;

            pre1 = pre1.next;
            pre2 = pre2.next;
        }

        while (pre1 != null) {
            ListNode itemNode = new ListNode((pre1.val + carry) % 10);
            itemNode.next = newNode;
            newNode = itemNode;
            carry = (pre1.val + carry) / 10;
            pre1 = pre1.next;
        }

        while (pre2 != null) {
            ListNode itemNode = new ListNode((pre2.val + carry) % 10);
            itemNode.next = newNode;
            newNode = itemNode;
            carry = (pre2.val + carry) / 10;
            pre2 = pre2.next;
        }

        if (carry != 0) {
            ListNode itemNode = new ListNode(carry);
            itemNode.next = newNode;
            newNode = itemNode;
        }

        return newNode;
    }
}
