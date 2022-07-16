package com.niugiaogiao.linked;

/**
 * @author zi hao
 * @version 1.0
 * @date 2022-06-27 21:40
 */
public class LinkedList025 {

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

    public ListNode reverse(ListNode head) {
        ListNode pre = null;
        while (head != null) {
            ListNode nextNode = head.next;
            head.next = pre;
            pre = head;
            head = nextNode;
        }

        return pre;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        }

        // reverse
        ListNode newHead = null;
        ListNode pre1 = reverse(l1);
        ListNode pre2 = reverse(l2);
        int flag = 0;
        while (pre1 != null && pre2 != null) {
            int res = pre1.val + pre2.val + flag;
            flag = res / 10;
            ListNode itemNode = new ListNode(res % 10);
            itemNode.next = newHead;
            newHead = itemNode;
            pre1 = pre1.next;
            pre2 = pre2.next;
        }

        while (pre1 != null) {
            int res = pre1.val + flag;
            flag = res / 10;
            ListNode itemNode = new ListNode(res % 10);
            itemNode.next = newHead;
            newHead = itemNode;
            pre1 = pre1.next;
        }

        while (pre2 != null) {
            int res = pre2.val + flag;
            flag = res / 10;
            ListNode itemNode = new ListNode(res % 10);
            itemNode.next = newHead;
            newHead = itemNode;
            pre2 = pre2.next;
        }

        return newHead;
    }
}
