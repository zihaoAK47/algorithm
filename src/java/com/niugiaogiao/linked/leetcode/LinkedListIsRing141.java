package com.niugiaogiao.linked.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * 给你一个链表的头节点 head ，判断链表中是否有环。
 * <p>
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。注意：pos 不作为参数进行传递 。仅仅是为了标识链表的实际情况。
 * <p>
 * 如果链表中存在环 ，则返回 true 。 否则，返回 false 。
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-06-03 14:45
 */
public class LinkedListIsRing141 {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static boolean hasCycle1(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        HashSet<ListNode> dict = new HashSet<>();

        for (ListNode node = head; node != null; node = node.next) {

            if (dict.contains(node))
                return true;
            else
                dict.add(node);
        }

        return false;
    }

    public static boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        ListNode aNode = head;
        ListNode bNode = head;
        while (bNode != null && bNode.next != null) {
            aNode = aNode.next;
            bNode = bNode.next.next;
            if (aNode == bNode) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {

    }
}
