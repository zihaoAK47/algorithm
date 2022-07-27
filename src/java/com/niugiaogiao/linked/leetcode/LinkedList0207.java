package com.niugiaogiao.linked.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表没有交点，返回 null
 * https://leetcode.cn/problems/intersection-of-two-linked-lists-lcci/
 * @author zi hao
 * @version 1.0
 * @date 2022-07-27 21:33
 */
public class LinkedList0207 {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode pointA = headA;
        ListNode pointB = headB;
        while (pointA != pointB) {
            pointA = pointA != null ? pointA.next : headB;
            pointB = pointB != null ? pointB.next : headA;
        }

        return pointA;
    }

    public ListNode getIntersectionNode1(ListNode headA,ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        Set<ListNode> dict = new HashSet<>();
        while (headA != null) {
            dict.add(headA);
            headA = headA.next;
        }
        while (headB != null) {
            if (dict.contains(headB)) {
                return headB;
            }

            headB = headB.next;
        }

        return null;
    }

}
