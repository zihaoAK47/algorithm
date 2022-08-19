package com.niugiaogiao.linked.leetcode;

import java.util.*;

/**
 * 在一个大小为 n 且 n 为 偶数 的链表中，对于 0 <= i <= (n / 2) - 1 的 i ，第 i 个节点（下标从 0 开始）的孪生节点为第 (n-1-i) 个节点 。
 * <p>
 * 比方说，n = 4 那么节点 0 是节点 3 的孪生节点，节点 1 是节点 2 的孪生节点。这是长度为 n = 4 的链表中所有的孪生节点。
 * 孪生和 定义为一个节点和它孪生节点两者值之和。
 * <p>
 * 给你一个长度为偶数的链表的头节点 head ，请你返回链表的 最大孪生和
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/maximum-twin-sum-of-a-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LinkedList2130 {
    static class ListNode {
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

    public static int pairSum(ListNode head) {
        if (head == null) {
            return 0;
        }
        if (head.next == null) {
            return head.val;
        }

        int pos = 0;
        int size = 0;
        ListNode temp = head;
        Map<Integer, Integer> dict = new HashMap<>();
        while (temp != null) {
            size++;
            dict.put(pos++, temp.val);
            temp = temp.next;
        }

        pos = 0;
        temp = head;
        int max = 0;
        int target = 0;
        while (temp != null) {
            target = (size - 1 - (pos++));
            if (!dict.containsKey(target)) {
                return max;
            }
            max = Math.max(dict.get(target) + temp.val, max);
            temp = temp.next;
        }

        return max;
    }

    public static int pairSum1(ListNode head) {
        if (head == null || head.next == null) {
            return head == null ? 0 : head.val;
        }
        ListNode back = head;
        ListNode pre = head.next;
        while (pre.next != null) {
            pre = pre.next.next;
            back = back.next;
        }
        // 反转
        ListNode mid = null;
        ListNode newHead = back.next;
        back.next = null;
        while (newHead != null) {
            ListNode next = newHead.next;
            newHead.next = mid;
            mid = newHead;
            newHead = next;
        }
        // 计算
        int max = 0;
        while (head != null && mid != null) {
            max = Math.max(max, head.val + mid.val);
            head = head.next;
            mid = mid.next;
        }

        return max;
    }

    public static int pairSum2(ListNode head) {
        if (head == null || head.next == null) {
            return head == null ? 0 : head.val;
        }
        Deque<Integer> deque = new LinkedList<>();
        ListNode temp = head;
        while (temp != null) {
            deque.addLast(temp.val);
            temp = temp.next;
        }

        int max = 0;
        while (!deque.isEmpty()) {
            max = Math.max(max, deque.getFirst() + deque.getLast());
            deque.pollLast();
            deque.pollFirst();
        }

        return max;
    }

    public static int pairSum3(ListNode head) {
        if (head == null || head.next == null) {
            return head == null ? 0 : head.val;
        }
        List<Integer> list = new ArrayList<>();
        ListNode temp = head;
        while (temp != null) {
            list.add(temp.val);
            temp = temp.next;
        }
        int max = 0;
        for (int i = 0; i < list.size() / 2; i++) {
            max = Math.max(max, list.get(i) + list.get(list.size() - 1 - i));
        }
        return max;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(100000);
        l1.next = l2;
        System.err.println(pairSum1(l1));
    }

}
