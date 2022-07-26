package com.niugiaogiao.linked.leetcode;

/**
 * 面试题 02.02. 返回倒数第 k 个节点
 * https://leetcode.cn/problems/kth-node-from-end-of-list-lcci/
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-07-26 23:31
 */
public class LinkedList0202 {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public int kthToLast(ListNode head, int k) {
        if (head == null) {
            return -1;
        }
        ListNode pre = null;
        while (head != null) {
            ListNode temp = head.next;
            head.next = pre;
            pre = head;
            head = temp;
        }
        for (int i = 1; i < k; i++) {
            pre = pre.next;
        }

        return pre.val;
    }

    // 快慢指针
    public int kthToLast1(ListNode head, int k) {
        if (head == null) {
            return -1;
        }
        ListNode pre = head;
        ListNode back = head;
        while (k-- != 0) {
            pre = pre.next;
        }
        while (pre != null) {
            pre = pre.next;
            back = back.next;
        }

        return back.val;
    }

}
