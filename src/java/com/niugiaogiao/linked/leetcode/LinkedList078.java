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
}
