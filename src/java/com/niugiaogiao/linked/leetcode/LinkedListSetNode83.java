package com.niugiaogiao.linked.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个已排序的链表的头 head ， 删除所有重复的元素，使每个元素只出现一次 。返回 已排序的链表 。
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-05-30 9:33
 */
public class LinkedListSetNode83 {

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

    /**
     * 使用 set 做辅助
     *
     * @param head
     * @return
     */
    public static ListNode deleteDuplicates1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode newHead = new ListNode(-1);
        ListNode preHead = newHead;
        Set<Integer> dict = new HashSet<>();
        while (head != null) {
            ListNode backNode = head.next;
            head.next = null;
            if (!dict.contains(head.val)) {
                dict.add(head.val);
                preHead.next = head;
                preHead = preHead.next;
            }

            head = backNode;
        }

        return newHead.next;
    }

    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode newHead = new ListNode(-1);
        newHead.next = head;
        ListNode preHead = newHead.next;
        while (preHead != null && preHead.next != null) {
            if (preHead.val != preHead.next.val) {
                preHead = preHead.next;
                continue;
            }

            ListNode backNode = preHead.next;
            while (backNode != null && backNode.val == preHead.val) {
                backNode = backNode.next;
            }
            preHead.next = backNode;
            preHead = backNode;
        }

        return newHead.next;
    }

    public static void main(String[] args) {
//        ListNode r1 = new ListNode(1);
//        ListNode r2 = new ListNode(1);
//        ListNode r3 = new ListNode(2);
//        ListNode r4 = new ListNode(3);
//        ListNode r5 = new ListNode(3);
//        r1.next = r2;
//        r2.next = r3;
//        r3.next = r4;
//        r4.next = r5;
//        System.err.println(deleteDuplicates(r1));
    }
}
