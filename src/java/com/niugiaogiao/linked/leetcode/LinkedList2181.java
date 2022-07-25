package com.niugiaogiao.linked.leetcode;

/**
 * 给你一个链表的头节点 head ，该链表包含由 0 分隔开的一连串整数。链表的 开端 和 末尾 的节点都满足 Node.val == 0 。
 * <p>
 * 对于每两个相邻的 0 ，请你将它们之间的所有节点合并成一个节点，其值是所有已合并节点的值之和。然后将所有 0 移除，修改后的链表不应该含有任何 0 。
 * <p>
 *  返回修改后链表的头节点 head
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-07-05 18:25
 */
public class LinkedList2181 {

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


    public static ListNode mergeNodes(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode node = head;
        ListNode preNode = head.next;
        ListNode lastNode = null;
        int res = 0;
        while (preNode != null) {
            if (preNode.val == 0) {
                lastNode = node;
                node.val = res;
                node.next = preNode;
                node = preNode;
                res = 0;
            } else {
                res += preNode.val;
                node.next = preNode;
            }
            preNode = preNode.next;
        }
        lastNode.next = null;

        return head;
    }

}
