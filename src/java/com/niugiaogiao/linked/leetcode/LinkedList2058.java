package com.niugiaogiao.linked.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * 链表中的 临界点 定义为一个 局部极大值点 或 局部极小值点
 *
 * 如果当前节点的值 严格大于 前一个节点和后一个节点，那么这个节点就是一个 局部极大值点
 *
 * 如果当前节点的值 严格小于 前一个节点和后一个节点，那么这个节点就是一个 局部极小值点
 *
 * 注意：节点只有在同时存在前一个节点和后一个节点的情况下，才能成为一个 局部极大值点 / 极小值点
 *
 * 给你一个链表 head ，返回一个长度为 2 的数组 [minDistance, maxDistance] ，其中 minDistance 是任意两个不同临界点之间的最小距离，maxDistance 是任意两个不同临界点之间的最大距离。如果临界点少于两个，则返回 [-1，-1]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/find-the-minimum-and-maximum-number-of-nodes-between-critical-points
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处
 *
 * @author niugiaogiao
 */
public class LinkedList2058 {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static int[] nodesBetweenCriticalPoints(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return new int[]{-1, -1};
        }
        // 慢指针
        ListNode back = head;
        // 快指针
        ListNode pre = head.next;
        // 节点位置
        int position = 2;
        // 节点首次出现的位置
        int firstDistance = -1;
        int distance = -1;
        int lastDistance = -1;
        while (pre.next != null) {
            int midVal = pre.val;
            if (back.val > midVal && midVal < pre.next.val) {
                firstDistance = firstDistance == -1 ? position : firstDistance;
                lastDistance = distance;
                distance = position;
            } else if (back.val < midVal && midVal > pre.next.val) {
                firstDistance = firstDistance == -1 ? position : firstDistance;
                lastDistance = distance;
                distance = position;
            }

            position++;
            pre = pre.next;
            back = back.next;
        }

        if (firstDistance == -1 || firstDistance == distance) {
            return new int[]{-1, -1};
        }

        return new int[]{distance - lastDistance, distance - firstDistance};
    }

    public static int[] nodesBetweenCriticalPoints1(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return new int[]{-1, -1};
        }
        ListNode temp = head;
        List<Integer> list = new ArrayList<>();
        while (temp != null) {
            list.add(temp.val);
            temp = temp.next;
        }
        // 记录最开始的位置
        int firstDistance = -1;
        int lastDistance = -1;
        int distance = -1;
        for (int i = 1 ; i < list.size() - 1 ; i++) {
            int midVal = list.get(i);
            if (list.get(i - 1) < midVal && list.get(i + 1) < midVal) {
                // max
                lastDistance = distance;
                distance = i;
                firstDistance = firstDistance == -1 ? i : firstDistance;
            } else if (list.get(i - 1) > midVal && midVal < list.get(i + 1)) {
                // min
                lastDistance = distance;
                distance = i;
                firstDistance = firstDistance == -1 ? i : firstDistance;
            }
        }
        if (firstDistance == -1 || firstDistance == distance) {
            return new int[]{-1, -1};
        }

        return new int[]{distance - lastDistance, distance - firstDistance};
    }

    public static ListNode createNode(int[] data) {
        ListNode res = new ListNode();
        ListNode pre = res;
        for (int item : data) {
            pre.next = new ListNode(item);
            pre = pre.next;
        }

        return res.next;
    }

    public static void main(String[] args) {
        // 2 4 5 8
        // 1 3 4 7
        ListNode node = createNode(new int[]{6, 8, 4, 1, 9, 6, 6, 10, 6});
        System.err.println(Arrays.toString(nodesBetweenCriticalPoints(node)));
        System.err.println(Arrays.toString(nodesBetweenCriticalPoints1(node)));
        Random random = new Random();
        int success = 0;
        int field = 0;
        long start = System.currentTimeMillis();
//        for (int i = 0 ; i < 1000 ; i++) {
//            int dataSize = random.nextInt(100000);
//            int[] data = new int[dataSize];
//            for (int j = 0 ; j < dataSize ; j ++) {
//                data[j] = random.nextInt(Integer.MAX_VALUE);
//            }
//
//            ListNode node = createNode(data);
//            String res1 = Arrays.toString(nodesBetweenCriticalPoints(node));
//            String res2 = Arrays.toString(nodesBetweenCriticalPoints1(node));
//            if (!res1.equals(res2)) {
//                field++;
//            } else {
//                success++;
//            }
//        }
        System.err.println("time: " + (System.currentTimeMillis() - start) + " success:" + success + " field: " + field);
    }
}
