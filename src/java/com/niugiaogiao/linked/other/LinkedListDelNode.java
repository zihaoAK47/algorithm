package com.niugiaogiao.linked.other;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 删除链表的倒数第 N 个节点
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-05-25 17:23
 */
public class LinkedListDelNode {

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

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || n <= 0)
            return null;

        ListNode preNode = head;
        while (n-- != 0 && preNode != null) {
            preNode = preNode.next;
        }

        // del head node
        if (preNode == null)
            return head.next;

        ListNode afterNode = head;
        while (preNode.next != null) {
            afterNode = afterNode.next;
            preNode = preNode.next;
        }
        afterNode.next = afterNode.next.next;

        return head;
    }

    public static ListNode removeCompare(ListNode head, int n) {
        if (head == null || n <= 0)
            return null;

        ListNode backHead = head;
        List<ListNode> data = new ArrayList<>();
        while (backHead != null) {
            data.add(backHead);
            backHead = backHead.next;
        }

        int listLen = data.size();
        if (listLen == n) {
            // del head
            head = head.next;
            return head;
        } else if (listLen < n) {
            return null;
        }
        data.get(listLen - 1 - n).next = data.get(listLen - 1 - n).next.next;

        return head;
    }

    public static ListNode createNode() {
        Random random = new Random();
        int loop = random.nextInt(99999);
        ListNode head = new ListNode(random.nextInt(99999));
        ListNode back = head;
        while (loop-- != 0) {
            back.next = new ListNode(random.nextInt(99999));
            back = back.next;
        }

        return head;
    }

    public static ListNode copyNode(ListNode head) {
        ListNode newNode = null;
        ListNode newBack = null;
        ListNode backNode = head;
        while (backNode != null) {
            if (newNode == null) {
                newNode = new ListNode(backNode.val);
                newBack = newNode;
            } else {
                newBack.next = new ListNode(backNode.val);
                newBack = newBack.next;
            }

            backNode = backNode.next;
        }

        return newNode;
    }

    public static boolean isEquals(ListNode head1, ListNode head2) {
        boolean listEquals = true;
        ListNode backHead1 = head1;
        ListNode backHead2 = head2;
        while (backHead1 != null && backHead2 != null && listEquals) {
            if (backHead1.val != backHead2.val) {
                listEquals = false;
                continue;
            }

            backHead1 = backHead1.next;
            backHead2 = backHead2.next;
        }

        return listEquals;
    }

    public static ListNode removeNthFromEnd1(ListNode head, int n) {
        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode start = pre, end = pre;
        while (n != 0) {
            start = start.next;
            n--;
        }
        while (start.next != null) {
            start = start.next;
            end = end.next;
        }
        end.next = end.next.next;
        return pre.next;
    }

    public static void main(String[] args) {
        Random random = new Random();
        System.err.println("start test.");
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            // 创建数据节点
            ListNode node1 = createNode();
            ListNode node2 = copyNode(node1);
            int delPosition = random.nextInt(99999);
            ListNode listNodeT1 = removeNthFromEnd(node1, delPosition);
            ListNode listNodeT2 = removeCompare(node2, delPosition);
            if (!isEquals(listNodeT1, listNodeT2)) {
                System.err.println("error");
                break;
            }
        }
        System.err.println("end test." + (System.currentTimeMillis() - start));
    }
}
