package com.niugiaogiao.linked;

import java.util.Arrays;

/**
 * 给你一个头结点为 head 的单链表和一个整数 k ，请你设计一个算法将链表分隔为 k 个连续的部分。
 * <p>
 * 每部分的长度应该尽可能的相等：任意两部分的长度差距不能超过 1 。这可能会导致有些部分为 null 。
 * <p>
 * 这 k 个部分应该按照在链表中出现的顺序排列，并且排在前面的部分的长度应该大于或等于排在后面的长度。
 * <p>
 * 返回一个由上述 k 部分组成的数组
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-07-23 22:40
 */
public class LinkedList725 {

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

        @Override
        public String toString() {
            return val + "";
        }
    }

    public static ListNode[] splitListToParts(ListNode head, int k) {
        ListNode[] result = new ListNode[k];
        if (head == null || k > 50) {
            return result;
        }

        int nodeLen = getNodeLen(head);

        if (nodeLen < k) {
            splitDefault(head, result, nodeLen);
        } else {
            splitGroup(head, result, nodeLen, k);
        }

        return result;
    }

    private static int getNodeLen(ListNode head) {
        int len = 0;
        for (ListNode temp = head ; temp != null ; temp = temp.next) {
            len++;
        }
        return len;
    }

    private static void splitDefault(ListNode node, ListNode[] result, int end) {
        for (int i = 0 ; i < end ; i ++) {
            ListNode next = node.next;
            node.next = null;
            result[i] = node;
            node = next;
        }
    }

    private static void splitGroup(ListNode node, ListNode[] result, int nodeLen, int k) {
        int pos = 0;
        int avgSplitCount = nodeLen / k;
        int diff = nodeLen % k;
        while (node != null) {
            ListNode splitHead = node;
            for (int i = 1 ; i < avgSplitCount + (diff != 0 ? 1 : 0) ; i++) {
                node = node.next;
            }
            ListNode next = node.next;
            node.next = null;
            node = next;
            result[pos++] = splitHead;
            diff = diff != 0 ? diff - 1 : diff;
        }
    }

    public static void main(String[] args) {
        ListNode r1 = new ListNode(1);
        ListNode r2 = new ListNode(2);
        ListNode r3 = new ListNode(3);
        ListNode r4 = new ListNode(4);
        ListNode r5 = new ListNode(5);
        ListNode r6 = new ListNode(6);
        ListNode r7 = new ListNode(7);
        ListNode r8 = new ListNode(8);
        ListNode r9 = new ListNode(9);
        ListNode r10 = new ListNode(10);
        r1.next = r2;
        r2.next = r3;
        r3.next = r4;
        r4.next = r5;
        r5.next = r6;
        r6.next = r7;
        r7.next = r8;
        r8.next = r9;
        r9.next = r10;

        System.err.println(Arrays.toString(splitListToParts(null, 3)));
    }

}
