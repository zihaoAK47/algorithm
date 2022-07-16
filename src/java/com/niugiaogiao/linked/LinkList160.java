package com.niugiaogiao.linked;

import java.util.HashSet;
import java.util.Set;

/**
 * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null
 * 题目数据 保证 整个链式结构中不存在环。
 * 注意，函数返回结果后，链表必须 保持其原始结构
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-06-08 15:00
 */
public class LinkList160 {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        ListNode tempA = headA;
        ListNode tempB = headB;
        Set<ListNode> dict = new HashSet<>();
        while (tempA != null) {
            dict.add(tempA);
            tempA = tempA.next;
        }

        while (tempB != null) {
            if (dict.contains(tempB)) {
                return tempB;
            }

            tempB = tempB.next;
        }

        return null;
    }

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        ListNode t1 = headA;
        ListNode t2 = headB;
        while (t1 != t2) {
            t1 = t1 == null ? headB : t1.next;
            t2 = t2 == null ? headA : t2.next;
        }

        return t1;
    }

    public static void main(String[] args) {
        ListNode r1 = new ListNode(4);
        ListNode r2 = new ListNode(1);
        ListNode r3 = new ListNode(8);
        ListNode r4 = new ListNode(4);
        ListNode r5 = new ListNode(5);

        r1.next = r2;
        r2.next = r3;
        r3.next = r4;
        r4.next = r5;

        ListNode l1 = new ListNode(3);

        System.err.println(getIntersectionNode(r1, l1));

    }
}