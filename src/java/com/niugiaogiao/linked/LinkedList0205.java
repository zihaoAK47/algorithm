package com.niugiaogiao.linked;

/**
 * 面试题 02.05 链表求和
 * <p>
 * 给定两个用链表表示的整数，每个节点包含一个数位。
 * <p>
 * 这些数位是反向存放的，也就是个位排在链表首部。
 * <p>
 * 编写函数对这两个整数求和，并用链表形式返回结果
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-06-15 16:58
 */
public class LinkedList0205 {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }

    public static int disposeNode(ListNode backNode, ListNode preNode, int carry) {
        while (preNode != null) {
            int sum = preNode.val + carry;
            carry = sum / 10;
            ListNode newNode = new ListNode(sum % 10);
            backNode.next = newNode;
            backNode = newNode;
            preNode = preNode.next;
        }

        return carry;
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode tempL1 = l1;
        ListNode tempL2 = l2;
        ListNode result = new ListNode(1);
        ListNode back = result;
        while (tempL1 != null && tempL2 != null) {
            int sum = tempL1.val + tempL2.val + carry;
            carry = sum / 10;
            ListNode newNode = new ListNode(sum % 10);
            back.next = newNode;
            back = newNode;
            tempL1 = tempL1.next;
            tempL2 = tempL2.next;
        }

        carry = disposeNode(back, tempL1, carry);
        carry = disposeNode(back, tempL2, carry);

        if (carry != 0)
            back.next = new ListNode(carry);

        return result.next;
    }

    public static ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode tempL1 = l1;
        ListNode tempL2 = l2;
        ListNode result = new ListNode(0);
        ListNode back = result;
        while (tempL1 != null && tempL2 != null) {
            int sum = tempL1.val + tempL2.val + carry;
            carry = sum / 10;
            ListNode newNode = new ListNode(sum % 10);
            back.next = newNode;
            back = newNode;
            tempL1 = tempL1.next;
            tempL2 = tempL2.next;
        }

        while (tempL1 != null) {
            int sum = tempL1.val + carry;
            carry = sum / 10;
            ListNode newNode = new ListNode(sum % 10);
            back.next = newNode;
            back = newNode;
            tempL1 = tempL1.next;
        }

        while (tempL2 != null) {
            int sum = tempL2.val + carry;
            carry = sum / 10;
            ListNode newNode = new ListNode(sum % 10);
            back.next = newNode;
            back = newNode;
            tempL2 = tempL2.next;
        }

        if (carry != 0)
            back.next = new ListNode(carry);

        return result.next;
    }

    public static void main(String[] args) {
//        ListNode r1 = new ListNode(6);
//        ListNode r2 = new ListNode(1);
//        ListNode r3 = new ListNode(7);
//        r1.next = r2;
//        r2.next = r3;
//
//        ListNode l1 = new ListNode(2);
//        ListNode l2 = new ListNode(9);
//        ListNode l3 = new ListNode(5);
//        l1.next = l2;
//        l2.next = l3;

        ListNode r1 = new ListNode(1);

        ListNode l1 = new ListNode(9);
        ListNode l2 = new ListNode(9);
        l1.next = l2;

        System.err.println(addTwoNumbers(r1, l1));
    }
}
