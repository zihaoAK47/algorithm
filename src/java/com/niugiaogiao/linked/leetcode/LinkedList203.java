package com.niugiaogiao.linked.leetcode;

/**
 * 给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-06-08 15:35
 */
public class LinkedList203 {

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


    public static ListNode removeElements1(ListNode head, int val) {
        if (head == null || (head.val == val && head.next == null)) {
            return null;
        }

        ListNode newHead = new ListNode(0);
        ListNode preNode = newHead;
        ListNode temp = head;
        while (temp != null) {
            ListNode next = temp.next;
            temp.next = null;
            if (temp.val == val) {
                temp = next;
                continue;
            }

            preNode.next = temp;
            preNode = preNode.next;
            temp = next;
        }

        return newHead.next;
    }

    public static ListNode removeElements2(ListNode head, int val) {
        if (head == null) {
            return head;
        }

        head.next = removeElements2(head.next, val);
        return head.val == val ? head.next : head;
    }

    public static ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return head;
        }

        ListNode tempHead = new ListNode(0);
        tempHead.next = head;
        ListNode preNode = tempHead;
        while (preNode.next != null) {
            if (preNode.next.val == val) {
                preNode.next = preNode.next.next;
            } else {
                preNode = preNode.next;
            }
        }

        return tempHead.next;
    }

    public static void main(String[] args) {
        ListNode r1 = new ListNode(7);
        ListNode r2 = new ListNode(7);
        r1.next = r2;

        System.err.println(removeElements(r1, 7));
    }
}
