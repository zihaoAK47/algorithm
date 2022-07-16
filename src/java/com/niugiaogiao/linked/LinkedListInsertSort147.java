package com.niugiaogiao.linked;

/**
 * 给定单个链表的头 head ，使用 插入排序 对链表进行排序，并返回 排序后链表的头
 * 插入排序 算法的步骤:
 * <p>
 * 插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
 * 每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
 * 重复直到所有输入数据插入完为止。
 * 下面是插入排序算法的一个图形示例。部分排序的列表(黑色)最初只包含列表中的第一个元素。每次迭代时，从输入数据中删除一个元素(红色)，并就地插入已排序的列表中。
 * <p>
 * 对链表进行插入排序。
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-06-06 16:40
 */
public class LinkedListInsertSort147 {

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

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }

    public static void sortNode(ListNode node, ListNode targetNode) {
        // find insert position
        targetNode.next = null;
        if (node.next == null) {
            node.next = targetNode;
            return;
        }

        while (node.next != null) {
            if (node.next.val > targetNode.val) {
                ListNode backNode = node.next;
                node.next = targetNode;
                targetNode.next = backNode;
                return;
            }

            node = node.next;
        }

        node.next = targetNode;
    }

    public static ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode loopNode = head;
        ListNode newNode = new ListNode(0);
        while (loopNode != null) {
            ListNode targetNode = loopNode.next;
            loopNode.next = null;
            sortNode(newNode, loopNode);
            loopNode = targetNode;
        }

        return newNode.next;
    }

    public static void main(String[] args) {
        ListNode r1 = new ListNode(-1);
        ListNode r2 = new ListNode(5);
        ListNode r3 = new ListNode(3);
        ListNode r4 = new ListNode(4);
        ListNode r5 = new ListNode(0);
        r1.next = r2;
        r2.next = r3;
        r3.next = r4;
        r4.next = r5;
        System.err.println(insertionSortList(r1));
    }
}
