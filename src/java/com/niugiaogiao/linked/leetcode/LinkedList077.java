package com.niugiaogiao.linked.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 给定链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表
 * @author zi hao
 * @version 1.0
 * @date 2022-08-01 23:28
 */
public class LinkedList077 {

      public static class ListNode {
          int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
      }

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        PriorityQueue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(item -> item.val));
        while (head != null) {
            queue.add(head);
            head = head.next;
        }

        ListNode result = new ListNode(0);
        ListNode resultPre = result;
        while (!queue.isEmpty()) {
            resultPre.next = queue.poll();
            resultPre = resultPre.next;
        }
        resultPre.next = null;

        return result;
    }


}
