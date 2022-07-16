package com.niugiaogiao.linked;

/**
 * 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
 * <p>
 * 你应当 保留 两个分区中每个节点的初始相对位置
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-05-31 9:52
 */
public class LinkedListSplit86 {

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

    public static ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode headA = new ListNode(-1);
        ListNode tempA = headA;
        ListNode headB = new ListNode(-1);
        ListNode tempB = headB;
        ListNode preHead = head;
        while (preHead != null) {
            ListNode lastNode = preHead.next;
            preHead.next = null;
            if (preHead.val < x) {
                tempA.next = preHead;
                tempA = tempA.next;
            } else {
                tempB.next = preHead;
                tempB = tempB.next;
            }
            preHead = lastNode;
        }
        tempA.next = headB.next;

        return headA.next;
    }

    public static void main(String[] args) {
        ListNode r1 = new ListNode(1);
        ListNode r2 = new ListNode(4);
        ListNode r3 = new ListNode(3);
        ListNode r4 = new ListNode(2);
        ListNode r5 = new ListNode(5);
        ListNode r6 = new ListNode(2);
        r1.next = r2;
        r2.next = r3;
        r3.next = r4;
        r4.next = r5;
        r5.next = r6;
        System.err.println(partition(r1, 3));
    }
}
