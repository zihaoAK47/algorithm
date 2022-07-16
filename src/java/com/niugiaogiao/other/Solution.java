package com.niugiaogiao.other;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zi hao
 * @version 1.0
 * @date 2021/5/27 21:58
 * */

class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public ListNode getNext() {
        return next;
    }

    public void setNext(ListNode next) {
        this.next = next;
    }
}

public class Solution {

    public static ListNode addList(ListNode head,int val) {
        if (null == head) {
            return new ListNode(val);
        }

        ListNode tempNode = head;
        while (tempNode.next != null) {
            tempNode = tempNode.next;
        }
        tempNode.next = new ListNode(val);
        return head;
    }

    public static void show(ListNode head) {
        while (head != null) {
            System.err.println(head.val);
            head = head.next;
        }
    }


    public ListNode reverse(ListNode head) {
        if (null == head || head.next == null) {
            return head;
        }
        ListNode node = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return node;
    }

    public ListNode ReverseList(ListNode head) {
        if (null == head) {
            return null;
        }
        ListNode result = new ListNode();
        result = reverse(head);
        return result;
    }
    // 问题 链表反转过后最后一个指针的指向有问题
    public static int[] twoSum (int[] numbers, int target) {
        if (null == numbers || numbers.length == 0) {
            return null;
        }
        Map<Integer,Integer> hashNumber = new HashMap<>();
        for (int i = 0 ; i < numbers.length ; i ++) {
            Integer findNum = hashNumber.get(target - numbers[i]);
            if (null == findNum) {
                hashNumber.put(numbers[i],i);
            } else {
                return new int[]{findNum + 1,i + 1};
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int[] number = {3,2,4};
        System.err.println(Arrays.toString(twoSum(number,6)));
    }
}
