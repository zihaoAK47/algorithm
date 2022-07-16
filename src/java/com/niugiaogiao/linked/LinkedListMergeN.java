package com.niugiaogiao.linked;

import java.util.*;

/**
 * 合并 K 个升序链表
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-05-24 18:54
 */
public class LinkedListMergeN {

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

    public static ListNode mergeKLists1(ListNode[] lists) {
        if (null == lists || lists.length == 0) {
            return null;
        }

        ListNode resHead = null;
        ListNode resBack = null;
        boolean loop = true;
        while (loop) {
            Integer min = null;
            Integer pos = null;
            loop = false;
            for (int i = 0; i < lists.length; i++) {
                if (lists[i] != null) {
                    loop = true;
                    if (min == null) {
                        pos = i;
                        min = lists[i].val;
                    } else if (min > lists[i].val) {
                        pos = i;
                        min = lists[i].val;
                    }
                }
            }

            if (min != null) {
                if (resHead == null) {
                    resHead = new ListNode(min);
                    resBack = resHead;
                } else {
                    resBack.next = new ListNode(min);
                    resBack = resBack.next;
                }

                lists[pos] = lists[pos].next;
            }
        }

        return resHead;
    }

    public static ListNode mergeKLists2(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o));
        //push queue
        for (int i = 0; i < lists.length; i++) {
            while (lists[i] != null) {
                queue.add(lists[i].val);
                lists[i] = lists[i].next;
            }
        }
        // result
        if (queue.isEmpty()) {
            return null;
        }
        ListNode resHead = new ListNode(queue.poll());
        ListNode resBack = resHead;
        while (!queue.isEmpty()) {
            resBack.next = new ListNode(queue.poll());
            resBack = resBack.next;
        }

        return resHead;
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0)
            return null;

        return merge(lists, 0, lists.length - 1);
    }

    public static ListNode merge(ListNode[] lists, int left, int right) {
        if (left == right)
            return lists[left];
        int mid = left + ((right - left) >> 1);
        ListNode l1 = merge(lists, left, mid);
        ListNode l2 = merge(lists, mid + 1, right);
        return mergeTwoList(l1, l2);
    }

    public static ListNode mergeTwoList(ListNode l1, ListNode l2) {
        if (l1 == null)
            return null;
        if (l2 == null)
            return null;
        if (l1.val < l2.val) {
            l1.next = mergeTwoList(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoList(l1, l2.next);
            return l2;
        }
    }

    public static void main(String[] args) {
        ListNode[] nodes = new ListNode[3];
        // node 1
        ListNode rootA1 = new ListNode(1);
        ListNode nodeA2 = new ListNode(5);
        ListNode nodeA3 = new ListNode(6);
        rootA1.next = nodeA2;
        nodeA2.next = nodeA3;

        // node 2
        ListNode rootB1 = new ListNode(2);
        ListNode nodeB2 = new ListNode(7);
        ListNode nodeB3 = new ListNode(8);
        rootB1.next = nodeB2;
        nodeB2.next = nodeB3;

        // node 3
        ListNode rootC1 = new ListNode(3);
        ListNode rootC2 = new ListNode(9);
        ListNode rootC3 = new ListNode(10);
        rootC1.next = rootC2;
        rootC2.next = rootC3;

        nodes[0] = rootA1;
        nodes[1] = rootB1;
        nodes[2] = rootC1;

        System.err.println(mergeKLists(nodes));
    }
}
