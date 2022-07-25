package com.niugiaogiao.linked.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你两个链表 list1 和 list2 ，它们包含的元素分别为 n 个和 m 个。
 * 请你将 list1 中下标从 a 到 b 的全部节点都删除，并将list2 接在被删除节点的位置
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-06-27 22:02
 */
public class LinkedList1669 {

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

    public ListNode mergeInBetween1(ListNode list1, int a, int b, ListNode list2) {
        if (list1 == null || a > b) {
            return null;
        }

        List<ListNode> listNodes = new ArrayList<>();
        ListNode temp = list1;
        while (temp != null) {
            listNodes.add(temp);
            temp = temp.next;
        }

        // get last node
        listNodes.get(a - 1).next = list2;
        ListNode tempList2 = list2;
        while (tempList2.next != null) {
            tempList2 = tempList2.next;
        }

        tempList2.next = listNodes.get(b + 1);

        return list1;
    }

    public static ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        if (list1 == null || a > b) {
            return null;
        }

        int splitPosition = 0;
        ListNode start = list1;
        while (start != null) {
            if (++splitPosition == a) {
                break;
            }

            start = start.next;
        }

        ListNode end = start;
        while (end != null) {
            end = end.next;
            if (splitPosition++ == b) {
                break;
            }
        }

        start.next = list2;
        while (start.next != null) {
            start = start.next;
        }
        start.next = end.next;

        return list1;
    }

    public static void main(String[] args) {
        ListNode r1 = new ListNode(0);
        ListNode r2 = new ListNode(1);
        ListNode r3 = new ListNode(2);
        ListNode r4 = new ListNode(3);
        ListNode r5 = new ListNode(4);
        ListNode r6 = new ListNode(5);
        r1.next = r2;
        r2.next = r3;
        r3.next = r4;
        r4.next = r5;
        r5.next = r6;

        ListNode l1 = new ListNode(1000000);
        ListNode l2 = new ListNode(1000001);
        ListNode l3 = new ListNode(1000002);
        ListNode l4 = new ListNode(1000003);
        ListNode l5 = new ListNode(1000004);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;

        mergeInBetween(r1, 3, 4, l1);
    }
}
