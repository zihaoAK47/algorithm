package com.niugiaogiao.linked;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * leet code 82
 * 给定一个已排序的链表的头 head ， 删除原始链表中所有重复数字的节点，只留下不同的数字 。返回 已排序的链表
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-05-29 21:12
 */
public class LinkedListDelRepeatNode {

    static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int val) {
            this.val = val;
        }

        public void setNext(ListNode next) {
            this.next = next;
        }

        public ListNode getNext() {
            return next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }

    public static ListNode deleteDuplicates1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        } else if (head.val == head.next.val && head.next.next == null) {
            return null;
        }

        // 建立 hash 表，key 是具体值，value 是上一个节点
        Map<Integer, ListNode> dict = new HashMap<>(16);
        ListNode pre = head;
        ListNode newHead = new ListNode(-1);
        ListNode backHead = newHead;
        newHead.next = head;

        while (pre != null) {
            if (dict.containsKey(pre.val)) {
                // 获取上一个节点
                ListNode listNode = dict.get(pre.val);
                pre = pre.next;
                while (pre != null && dict.containsKey(pre.val)) {
                    pre = pre.next;
                }
                // remove
                listNode.next = pre;
                backHead = listNode;
            } else {
                dict.put(pre.val, backHead);
                backHead = pre;
                pre = pre.next;
            }
        }

        return newHead.next;
    }

    public static ListNode deleteDuplicates2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        } else if (head.val == head.next.val && head.next.next == null) {
            return null;
        }
        // 这一步明确知道链表有两个不相等的节点
        ListNode newHead = new ListNode(-1);
        newHead.next = head;
        // 最慢指针
        ListNode backHead = newHead;
        // 中间指针
        ListNode midNode = head;
        // 最右边指针
        ListNode rightNode = head.next;
        while (rightNode != null) {
            if (rightNode.val == midNode.val) {
                rightNode = rightNode.next;
                while (rightNode != null && rightNode.val == midNode.val) {
                    rightNode = rightNode.next;
                }
                backHead.next = rightNode;
                if (rightNode == null) {
                    return newHead.next;
                }

                midNode = rightNode;
                rightNode = midNode.next;
            } else {
                rightNode = rightNode.next;
                midNode = midNode.next;
                backHead = backHead.next;
            }
        }

        return newHead.next;
    }

    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode cur = dummy;
        while (cur.next != null && cur.next.next != null) {
            if (cur.next.val == cur.next.next.val) {
                int x = cur.next.val;
                while (cur.next != null && cur.next.val == x) {
                    cur.next = cur.next.next;
                }
            } else {
                cur = cur.next;
            }
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode r1 = new ListNode(1);
        ListNode r2 = new ListNode(2);
        ListNode r3 = new ListNode(3);
        ListNode r4 = new ListNode(3);
        ListNode r5 = new ListNode(4);
        ListNode r6 = new ListNode(4);
        ListNode r7 = new ListNode(5);

        r1.next = r2;
        r2.next = r3;
        r3.next = r4;
        r4.next = r5;
        r5.next = r6;
        r6.next = r7;

        System.err.println(deleteDuplicates(r1));
    }
}
