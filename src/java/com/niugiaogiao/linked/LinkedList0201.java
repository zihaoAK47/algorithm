package com.niugiaogiao.linked;

import java.util.HashSet;
import java.util.Set;

/**
 * 编写代码，移除未排序链表中的重复节点。保留最开始出现的节点
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-06-21 19:17
 */
public class LinkedList0201 {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }


    public static ListNode removeDuplicateNodes(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = new ListNode(0);
        newHead.next = head;
        ListNode nodeA = head;
        ListNode nodeB = head.next;
        Set<Integer> dict = new HashSet<>();
        dict.add(nodeA.val);
        while (nodeB != null) {
            if (dict.contains(nodeB.val)) {
                nodeA.next = nodeB.next;
                nodeB = nodeB.next;
            } else {
                dict.add(nodeB.val);
                nodeA = nodeA.next;
                nodeB = nodeB.next;
            }
        }

        return newHead.next;
    }

    public static void main(String[] args) {
        ListNode r1 = new ListNode(1);
        ListNode r2 = new ListNode(2);
        ListNode r3 = new ListNode(3);
        ListNode r4 = new ListNode(3);
        ListNode r5 = new ListNode(2);
        ListNode r6 = new ListNode(1);
        r1.next = r2;
        r2.next = r3;
        r3.next = r4;
        r4.next = r5;
        r5.next = r6;
        removeDuplicateNodes(r1);
    }

}
