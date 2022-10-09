package com.niugiaogiao.linked.leetcode;

import java.util.*;

/**
 * 链表中的下一个更大节点
 * <p>
 * 给定一个长度为n的链表head
 * <p>
 * 对于列表中的每个节点，查找下一个 更大节点 的值。也就是说，对于每个节点，找到它旁边的第一个节点的值，这个节点的值 严格大于 它的值。
 * <p>
 * 返回一个整数数组 answer ，其中 answer[i] 是第 i 个节点( 从1开始 )的下一个更大的节点的值。如果第 i 个节点没有下一个更大的节点，设置answer[i] = 0。
 * <p>
 * 链接：https://leetcode.cn/problems/next-greater-node-in-linked-list
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-06-16 9:45
 */
public class LinkedList1019 {

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


    public static int[] nextLargerNodes1(ListNode head) {
        if (head == null) {
            return null;
        }
        int size = 0;
        for (ListNode tempHead = head; tempHead != null; tempHead = tempHead.next)
            size++;

        int[] result = new int[size];
        size = 0;
        for (ListNode pre = head; pre != null; pre = pre.next) {
            boolean flag = false;
            for (ListNode itemNode = pre.next; itemNode != null; itemNode = itemNode.next) {
                if (itemNode.val > pre.val) {
                    flag = true;
                    result[size++] = itemNode.val;
                    break;
                }
            }

            if (!flag)
                result[size++] = 0;
        }

        return result;
    }

    public static int[] nextLargerNodes(ListNode head) {
        if (head == null) {
            return null;
        }

        int len = 0;
        ListNode pre = null;
        ListNode tempHead = head;
        while (tempHead != null) {
            len++;
            ListNode nextNode = tempHead.next;
            tempHead.next = pre;
            pre = tempHead;
            tempHead = nextNode;
        }

        // init result array
        int[] result = new int[len];
        Deque<ListNode> stack = new LinkedList<>();
        while (pre != null) {
            while (!stack.isEmpty() && pre.val >= stack.peek().val) {
                stack.pop();
            }

            result[--len] = stack.isEmpty() ? 0 : stack.peek().val;
            stack.push(pre);
            pre = pre.next;
        }

        return result;
    }

    public int[] nextLargerNodes3(ListNode head) {
        List<Integer> list = new ArrayList<>();
        int size = 0;
        for (ListNode t = head; t != null; t = t.next, ++size) {
            list.add(t.val);
        }
        Stack<Integer> stack = new Stack<>();
        int i = 0;
        int[] res = new int[size];
        for (int j = 0; j < size; ++j) {
            while (!stack.isEmpty() && list.get(j) > list.get(stack.peek())) {
                i = stack.pop();
                res[i] = list.get(j);
            }
            stack.push(j);
        }
        return res;
    }
}
