package com.niugiaogiao.linked.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

public class LinkedList078 {

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


    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        Comparator<ListNode> comparator = Comparator.comparingInt(listNode -> listNode.val);
        PriorityQueue<ListNode> queue = new PriorityQueue<>(comparator);
        for (ListNode item : lists) {
            while (item != null) {
                queue.add(item);
                item = item.next;
            }
        }

        ListNode newHead = new ListNode(0);
        ListNode newPre = newHead;
        while (!queue.isEmpty()) {
            ListNode poll = queue.poll();
            poll.next = null;
            newPre.next = poll;
            newPre = newPre.next;
        }

        return newHead.next;
    }

    public ListNode mergeKLists1(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        return merge(lists, 0, lists.length - 1);
    }

    public ListNode merge(ListNode[] lists, int start, int end) {
        if (start == end) {
            return lists[start];
        }
        int mid = start + ((end - start) >> 1);
        return mergeRun(merge(lists, start, mid), merge(lists, mid + 1, end));
    }

    public ListNode mergeRun(ListNode nodeA, ListNode nodeB) {
        ListNode pre = new ListNode(0);
        ListNode backPre = pre;
        while (nodeA != null && nodeB != null) {
            if (nodeA.val < nodeB.val) {
                backPre.next = nodeA;
                nodeA = nodeA.next;
            } else {
                backPre.next = nodeB;
                nodeB = nodeB.next;
            }
            backPre = backPre.next;
        }
        backPre.next = nodeA != null ? nodeA : nodeB;
        return pre.next;
    }
}
