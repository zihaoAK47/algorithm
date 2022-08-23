package com.niugiaogiao.linked.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个链表的头节点head，请你编写代码，反复删去链表中由 总和值为 0 的连续节点组成的序列，直到不存在这样的序列为止。
 * <p>
 * 删除完毕后，请你返回最终结果链表的头节点。
 * <p>
 * 你可以返回任何满足题目要求的答案。
 * <p>
 * （注意，下面示例中的所有序列，都是对ListNode对象序列化的表示。
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-06-15 13:09
 */
public class LinkedList1171 {

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

    public static ListNode removeZeroSumSublists(ListNode head) {
        int sum = 0;
        ListNode virtualNode = new ListNode(0);
        virtualNode.next = head;
        Map<Integer, ListNode> dict = new HashMap<>();
        for (ListNode itemNode = virtualNode; itemNode != null; itemNode = itemNode.next) {
            sum += itemNode.val;
            dict.put(sum, itemNode);
        }

        sum = 0;
        for (ListNode itemNode = virtualNode; itemNode != null; itemNode = itemNode.next) {
            sum += itemNode.val;
            itemNode.next = dict.get(sum).next;
        }

        return virtualNode.next;
    }

    public static void main(String[] args) {
        ListNode r1 = new ListNode(1);
        ListNode r2 = new ListNode(2);
        ListNode r3 = new ListNode(3);
        ListNode r4 = new ListNode(-3);
        ListNode r5 = new ListNode(4);
        r1.next = r2;
        r2.next = r3;
        r3.next = r4;
        r4.next = r5;

        removeZeroSumSublists(r1);
    }
}
