package com.niugiaogiao.linked;

/**
 * 请编写一个函数，用于 删除单链表中某个特定节点 。在设计函数时需要注意，你无法访问链表的头节点 head ，只能直接访问 要被删除的节点
 * 题目数据保证需要删除的节点 不是末尾节点
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-06-10 9:27
 */
public class LinkedList237 {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
