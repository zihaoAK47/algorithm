package com.niugiaogiao.linked;

import java.util.Stack;

/**
 * @author zi hao
 * @version 1.0
 * @date 2022-06-09 15:32
 */
public class LinkedList234 {

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

    public boolean isPalindrome1(ListNode head) {
        if (head == null) {
            return false;
        }

        ListNode tempNode = head;
        Stack<ListNode> stack = new Stack<>();
        while (tempNode != null) {
            stack.push(tempNode);
            tempNode = tempNode.next;
        }

        tempNode = head;
        while (!stack.isEmpty()) {
            if (tempNode.val == stack.pop().val) {
                tempNode = tempNode.next;
            } else {
                return false;
            }
        }

        return true;
    }

    public static boolean isPalindrome(ListNode head) {
        if (head == null) {
            return false;
        }
        // find mid node
        ListNode temp = head;
        ListNode pre = head;
        while (pre != null && pre.next != null) {
            temp = temp.next;
            pre = pre.next.next;
        }
        // reverse node
        ListNode backMid = temp;
        ListNode t = null;
        while (temp != null) {
            ListNode back = temp.next;
            temp.next = t;
            t = temp;
            temp = back;
        }
        // compare
        ListNode tempRightNode = t;
        temp = head;
        boolean result = true;
        while (temp != null && t != null) {
            if (temp.val != t.val) {
                result = false;
                break;
            }
            temp = temp.next;
            t = t.next;
        }
        // 恢复链表
        t = null;
        while (tempRightNode != null) {
            ListNode back = tempRightNode.next;
            tempRightNode.next = t;
            t = tempRightNode;
            tempRightNode = back;
        }
        backMid.next = t;
        return result;
    }

    public static void main(String[] args) {
        ListNode r1 = new ListNode(1);
        ListNode r2 = new ListNode(2);
        ListNode r3 = new ListNode(3);
        r1.next = r2;
        r2.next = r3;

        isPalindrome(r1);
    }
}
