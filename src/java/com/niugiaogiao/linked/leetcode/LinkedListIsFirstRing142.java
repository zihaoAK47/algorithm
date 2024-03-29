package com.niugiaogiao.linked.leetcode;

/**
 * 给定一个链表的头节点 head，返回链表开始入环的第一个节点。如果链表无环，则返回null。
 * <p>
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环
 * 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
 * <p>
 * 不允许修改 链表。
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-06-04 23:41
 */
public class LinkedListIsFirstRing142 {


    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }

        ListNode aNode = head.next;
        ListNode bNode = head.next.next;
        while (aNode != bNode) {
            if (bNode.next == null || bNode.next.next == null) {
                return null;
            }

            bNode = bNode.next.next;
            aNode = aNode.next;
        }

        bNode = head;
        while (bNode != null && aNode != null && bNode != aNode) {
            bNode = bNode.next;
            aNode = aNode.next;
        }

        return aNode;
    }

    public static void main(String[] args) {
        ListNode r1 = new ListNode(1);
        ListNode r2 = new ListNode(2);
        r1.next = r2;
        r2.next = r1;
        System.err.println(detectCycle(r1).val);
    }
}
