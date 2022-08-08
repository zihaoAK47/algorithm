package com.niugiaogiao.linked.leetcode;

/**
 * 给你链表的头节点 head 和一个整数 k 。
 * 交换 链表正数第 k 个节点和倒数第 k 个节点的值后，返回链表的头节点（链表 从 1 开始索引）
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-08-07 22:52
 */
public class LinkedList1721 {

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

    public static ListNode swapNodes1(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;// 因为头结点可能会发生交换，所以要构造一个哑结点
        ListNode pre1 = dummy;// pre1指向第k个节点的前一个节点
        ListNode left = dummy.next;// 第k个节点
        ListNode pre2 = dummy;// pre2指向倒数第k个节点的前一个节点
        ListNode right = dummy.next;// 倒数第k个节点
        for (int i = 1; i < k; i++) {
            pre1 = pre1.next;
            left = left.next;
        }
        ListNode cur = left;
        ListNode temp = left.next;// 第k个节点的后一个节点
        while (cur.next != null) {
            pre2 = pre2.next;
            right = right.next;
            cur = cur.next;
        }
        if (right == pre1) {// 特殊情况，倒数第k个节点在第k个节点的左侧
            right.next = temp;
            left.next = right;
            pre2.next = left;
        } else {
            left.next = right.next;
            if (pre2 == left) {
                right.next = left;
            }// 特殊情况，第k个节点在倒数第k个节点的左侧
            else {
                pre2.next = left;
                right.next = temp;
            }
            pre1.next = right;
        }
        return dummy.next;
    }

    public static ListNode swapNodes(ListNode head, int k) {
        ListNode root = new ListNode();
        root.next = head;
        int k1 = k - 1;// 正数第k的前一个
        int k2 = k + 1;// 倒数第k的前一个
        ListNode p1 = root;
        ListNode p2 = root;
        ListNode p = root;
        int cnt = 0;
        while (p != null) {
            if (cnt == k1) {
                p1 = p;
            }
            p = p.next;
            cnt++;
            if (cnt > k2) {
                p2 = p2.next;
            }
        }
        ListNode lk2 = p2.next;
        ListNode lk1 = p1.next;
        if (lk2 == lk1) {
            return root.next;
        } else if (lk1.next == lk2) {
            p1.next = lk2;
            lk1.next = lk2.next;
            lk2.next = lk1;
        } else if (lk2.next == lk1) {
            p2.next = lk1;
            lk2.next = lk1.next;
            lk1.next = lk2;
        } else {
            p2.next = lk2.next;
            p1.next = lk1.next;
            lk1.next = p2.next;
            p2.next = lk1;
            lk2.next = p1.next;
            p1.next = lk2;
        }
        return root.next;
    }

    public static ListNode createNode(int[] data) {
        ListNode result = new ListNode(0);
        ListNode pre = result;
        for (int item : data) {
            pre.next = new ListNode(item);
            pre = pre.next;
        }

        return result.next;
    }

    public static void main(String[] args) {
        ListNode node = createNode(new int[]{1, 2});
        swapNodes1(node, 2);
    }
}
