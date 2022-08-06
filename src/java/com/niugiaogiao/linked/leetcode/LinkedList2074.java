package com.niugiaogiao.linked.leetcode;

/**
 * @author zi hao
 * @version 1.0
 * @date 2022-08-06 20:59
 */
public class LinkedList2074 {

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

    public static ListNode reverseEvenLengthGroups1(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        int group = 1;
        int preStep = 0;
        ListNode lastNode = null;
        ListNode tempHead = head;
        while (tempHead != null) {
            if (group % 2 != 0) {
                preStep = 0;
                while (preStep < group && tempHead != null) {
                    preStep++;
                    lastNode = tempHead;
                    tempHead = tempHead.next;
                }
                group++;
                continue;
            }
            ListNode start = tempHead;
            ListNode end = tempHead;
            preStep = 0;
            while (preStep < group && end != null) {
                preStep++;
                end = end.next;
            }

            // 反转
            ListNode pre = null;
            while (start != null && start != end) {
                ListNode next = start.next;
                start.next = pre;
                pre = start;
                start = next;
            }

            lastNode.next = pre;
            tempHead.next = end;
            tempHead = tempHead.next;
            group++;
        }

        return head;
    }

    public static ListNode reverseEvenLengthGroups2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        int group = 1;
        ListNode tempHead = head;
        ListNode lastNode = null;
        while (tempHead != null) {
            // 当前组节点数量
            int groupCount = getGroupNode(tempHead, group);
            // 当前组最后一个节点
            ListNode groupLastNode = getGroupLastNode(tempHead, group);
            // tempHead 前进 group
            for (int i = 0; i < group && tempHead != null; i++) {
                tempHead = tempHead.next;
            }
            if (groupCount % 2 != 0) {
                lastNode = groupLastNode;
                group++;
            } else {
                // 反转
                ListNode pre = null;
                ListNode backStart = lastNode.next;
                ListNode splitStart = lastNode.next;
                while (splitStart != tempHead) {
                    ListNode next = splitStart.next;
                    splitStart.next = pre;
                    pre = splitStart;
                    splitStart = next;
                }
                lastNode.next = pre;
                backStart.next = tempHead;
                lastNode = backStart;
                group++;
            }
        }

        return head;
    }

    private static int getGroupNode(ListNode head, int group) {
        int step = 0;
        while (head != null && step < group) {
            step++;
            head = head.next;
        }

        return step;
    }

    private static ListNode getGroupLastNode(ListNode head, int group) {
        int step = 0;
        ListNode lastNode = null;
        while (head != null && step < group) {
            step++;
            lastNode = head;
            head = head.next;
        }

        return lastNode;
    }

    public static ListNode reverseEvenLengthGroups(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        int group = 1;
        int count = 0;
        ListNode tempHead = head;
        ListNode lastNode = null;
        ListNode tempLast = null;
        while (tempHead != null) {
            count = 0;
            for (int i = 0; i < group && tempHead != null; i++) {
                count++;
                tempLast = tempHead;
                tempHead = tempHead.next;
            }
            if (count % 2 != 0) {
                lastNode = tempLast;
                group++;
            } else {
                // 反转
                ListNode pre = null;
                ListNode backStart = lastNode.next;
                ListNode splitStart = lastNode.next;
                while (splitStart != tempHead) {
                    ListNode next = splitStart.next;
                    splitStart.next = pre;
                    pre = splitStart;
                    splitStart = next;
                }
                lastNode.next = pre;
                backStart.next = tempHead;
                lastNode = backStart;
                group++;
            }
        }

        return head;
    }

    public static ListNode create(int[] data) {
        ListNode t = new ListNode(0);
        ListNode tH = t;
        for (int i : data) {
            tH.next = new ListNode(i);
            tH = tH.next;
        }

        return t.next;
    }

    public static void main(String[] args) {
//        int[] data = new int[]{5, 2, 6, 3, 9, 1, 7, 3, 8, 4};
        int[] data = new int[]{1, 1, 0, 6, 5};
        ListNode listNode = create(data);
        reverseEvenLengthGroups(listNode);
    }
}
