package com.niugiaogiao.linked.leetcode;

/**
 * 给你一个单链表的引用结点 head。链表中每个结点的值不是 0 就是 1。已知此链表是一个整数数字的二进制表示形式
 * 请你返回该链表所表示数字的 十进制值
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-06-18 23:24
 */
public class LinkedList1290 {

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
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }


    public static int getDecimalValue1(ListNode head) {
        if (head == null)
            return 0;

        // 反转链表
        ListNode pre = null;
        while (head != null) {
            ListNode nex = head.next;
            head.next = pre;
            pre = head;
            head = nex;
        }

        int ci = 0;
        int result = 0;
        while (pre != null) {
            if (pre.val != 0)
                result += pre.val * (int) Math.pow(2, ci);

            ci++;
            pre = pre.next;
        }

        return result;
    }

    public static int getDecimalValue(ListNode head) {
        if (head == null)
            return 0;

        int res = 0;
        while (head != null) {
            res = res << 1 | head.val;
            head = head.next;
        }

        return res;
    }

    public static ListNode getHead(int[] num) {
        ListNode head = new ListNode(0);
        ListNode back = head;
        for (int i : num) {
            back.next = new ListNode(i);
            back = back.next;
        }

        return head.next;
    }


    public static void main(String[] args) {
        int[] data = new int[]{1, 0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0};
        ListNode head = getHead(data);
//        System.err.println(getDecimalValue1(head));
//        System.err.println(getDecimalValue(head));
    }
}
