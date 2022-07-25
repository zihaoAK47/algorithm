package com.niugiaogiao.linked.other;

import java.util.ArrayList;
import java.util.List;

/**
 * 链表旋转
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-05-28 23:23
 */
public class LinkedListRotating {

    static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int val) {
            this.val = val;
        }

        public void setNext(ListNode next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }

    public static ListNode rotateRight1(ListNode head, int k) {
        if (head == null || head.next == null || k <= 0) {
            return head;
        }
        ListNode tempHead = head;
        List<ListNode> list = new ArrayList<>();
        while (tempHead != null) {
            list.add(tempHead);
            tempHead = tempHead.next;
        }
        int len = list.size();
        ListNode[] newNode = new ListNode[len];
        // start position
        int startPos = k % len - 1;
        for (int i = 0; i < len; i++) {
            newNode[startPos = startPos + 1 < len ? startPos + 1 : 0] = list.get(i);
        }

        ListNode resHead = new ListNode(-1);
        ListNode resBack = resHead;
        for (int i = 0; i < len; i++) {
            resBack.next = new ListNode(newNode[i].val);
            resBack = resBack.next;
        }

        return resHead.next;
    }

    public static ListNode rotateRight(ListNode head, int k) {
        if (null == head || null == head.next || k <= 0) {
            return head;
        }
        int len = 1;
        // 统计链表长度并变成环
        ListNode iter = head;
        while (iter.next != null) {
            len++;
            iter = iter.next;
        }
        int position = len - k % len;
        if (position == len) {
            // 是否回到了自身
            return head;
        }

        iter.next = head;
        while (position-- > 0) {
            iter = iter.next;
        }
        ListNode newHead = iter.next;
        iter.next = null;

        return newHead;
    }

    public static void main(String[] args) {
        // (i + k) % 5
        ListNode r1 = new ListNode(1);
        ListNode r2 = new ListNode(2);
        ListNode r3 = new ListNode(2);
        ListNode r4 = new ListNode(4);
        ListNode r5 = new ListNode(5);
        r1.next = r2;
//        r2.next = r3;
//        r3.next = r4;
//        r4.next = r5;
        System.err.println(rotateRight(r1, 1));

    }
}
