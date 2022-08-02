package com.niugiaogiao.linked.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 给定链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-08-01 23:28
 */
public class LinkedList077 {

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

    public ListNode sortList1(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        return splitList(head, null);
    }

    public ListNode splitList(ListNode start, ListNode end) {
        if (start == null) {
            return start;
        } else if (start.next == end) {
            start.next = null;
            return start;
        }

        ListNode temp = start;
        ListNode next = start;
        while (temp != next) {
            temp = temp.next;
            next = next.next;
            if (temp != next) {
                next = next.next;
            }
        }

        ListNode mid = temp;
        ListNode list1 = splitList(start, mid);
        ListNode list2 = splitList(mid, end);
        return merge(list1, list2);
    }

    public ListNode merge(ListNode head1, ListNode head2) {
        ListNode result = new ListNode(0);
        ListNode pre = result;
        ListNode temp1 = head1;
        ListNode temp2 = head2;
        while (temp1 != null && temp2 != null) {
            if (temp1.val <= temp2.val) {
                pre.next = temp1;
                temp1 = temp1.next;
            } else {
                pre.next = temp2;
                temp2 = temp2.next;
            }
            pre = pre.next;
        }

        if (temp1 != null) {
            pre.next = temp1;
        } else if (temp2 != null) {
            pre.next = temp2;
        }

        return result.next;
    }



    public ListNode sortListT(ListNode head) {
        return sortList(head, null);
    }

    public ListNode sortList(ListNode head, ListNode tail) {
        if (head == null) {
            return head;
        }
        if (head.next == tail) {
            head.next = null;
            return head;
        }
        ListNode slow = head, fast = head;
        while (fast != tail) {
            slow = slow.next;
            fast = fast.next;
            if (fast != tail) {
                fast = fast.next;
            }
        }
        ListNode mid = slow;
        ListNode list1 = sortList(head, mid);
        ListNode list2 = sortList(mid, tail);
        ListNode sorted = merge(list1, list2);
        return sorted;
    }

    public ListNode mergeT(ListNode head1, ListNode head2) {
        ListNode dummyHead = new ListNode(0);
        ListNode temp = dummyHead, temp1 = head1, temp2 = head2;
        while (temp1 != null && temp2 != null) {
            if (temp1.val <= temp2.val) {
                temp.next = temp1;
                temp1 = temp1.next;
            } else {
                temp.next = temp2;
                temp2 = temp2.next;
            }
            temp = temp.next;
        }
        if (temp1 != null) {
            temp.next = temp1;
        } else if (temp2 != null) {
            temp.next = temp2;
        }
        return dummyHead.next;
    }


}
