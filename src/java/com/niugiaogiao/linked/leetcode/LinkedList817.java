package com.niugiaogiao.linked.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定链表头结点head，该链表上的每个结点都有一个 唯一的整型值 。同时给定列表nums，该列表是上述链表中整型值的一个子集。
 * 返回列表nums中组件的个数，这里对组件的定义为：链表中一段最长连续结点的值（该值必须在列表nums中）构成的集合
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-07-24 21:52
 */
public class LinkedList817 {

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

    public static int numComponents(ListNode head, int[] nums) {
        if (head == null || nums == null || nums.length == 0) {
            return 0;
        }
        int arrayLength = nums.length;
        Map<Integer, Integer> dict = new HashMap<>(arrayLength);
        for (int item : nums) {
            dict.put(item, item);
        }

        int componentsCount = 0;
        for (ListNode temp = head; temp != null; temp = temp.next) {
            if (!dict.containsKey(temp.val)) {
                continue;
            }
            // temp 走到下一个没有的值
            componentsCount++;
            while (temp.next != null && dict.containsKey(temp.val)) {
                temp = temp.next;
            }
        }

        return componentsCount;
    }

    public int numComponents2(ListNode head, int[] nums) {
        boolean[] dict = new boolean[1000_0];
        for (int item : nums) {
            dict[item] = true;
        }

        int componentCount = 0;
        boolean flag = false;
        for (ListNode pre = head ; pre != null ; pre = pre.next) {
            if (dict[pre.val] && !flag) {
                flag = true;
                componentCount++;
                continue;
            }

            if (!dict[pre.val]) {
                flag = false;
            }
        }

        return componentCount;
    }

}
