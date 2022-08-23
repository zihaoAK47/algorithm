package com.niugiaogiao.linked.leetcode;

/**
 * 给定一个单链表 L 的头节点 head ，单链表 L 表示为：
 * <p>
 * L0→ L1→ … → Ln-1→ Ln
 * 请将其重新排列后变为：
 * <p>
 * L0→Ln→L1→Ln-1→L2→Ln-2→ …
 * <p>
 * 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-06-26 11:51
 */
public class LinkedList026 {

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

    public static void reorderList(ListNode head) {
        if (null == head || null == head.next) {
            return;
        }

        // find mid node
        ListNode backNode = head;
        ListNode preNode = head;
        while (null != preNode && null != preNode.next) {
            backNode = backNode.next;
            preNode = preNode.next.next;
        }

        ListNode revserHead = null;
        ListNode midNextNode = backNode.next;
        backNode.next = null;
        while (null != midNextNode) {
            ListNode temp = midNextNode.next;
            midNextNode.next = revserHead;
            revserHead = midNextNode;
            midNextNode = temp;
        }

        while (revserHead != null) {
            ListNode tempHead = head.next;
            ListNode tempRevser = revserHead.next;
            revserHead.next = null;

            head.next = revserHead;
            revserHead.next = tempHead;
            revserHead = tempRevser;
            head = tempHead;
        }
    }

    public static void main(String[] args) {
        ListNode r1 = new ListNode(1);
        ListNode r2 = new ListNode(2);
        ListNode r3 = new ListNode(3);
        ListNode r4 = new ListNode(4);
        r1.next = r2;
        r2.next = r3;
        r3.next = r4;

        reorderList(r1);

    }
}
