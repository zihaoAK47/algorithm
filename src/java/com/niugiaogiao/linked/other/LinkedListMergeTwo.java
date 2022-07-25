package com.niugiaogiao.linked.other;

/**
 * 将两个升序链表合并为一个新的 升序 链表并返回，新链表是通过拼接给定的两个链表的所有节点组成的
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-05-26 10:01
 */
public class LinkedListMergeTwo {

    static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int val) {
            this.val = val;
        }

        public void setNext(ListNode next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }

    public static ListNode mergeTwoLists1(ListNode list1, ListNode list2) {
        if (list1 == null)
            return list2;
        else if (list2 == null)
            return list1;

        ListNode res = null;
        ListNode back = null;
        if (list1.val < list2.val) {
            res = new ListNode(list1.val);
            list1 = list1.next;
        } else {
            res = new ListNode(list2.val);
            list2 = list2.next;
        }
        back = res;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                back.next = new ListNode(list1.val);
                back = back.next;
                list1 = list1.next;
            } else {
                back.next = new ListNode(list2.val);
                back = back.next;
                list2 = list2.next;
            }
        }

        while (list1 != null) {
            back.next = new ListNode(list1.val);
            back = back.next;
            list1 = list1.next;
        }

        while (list2 != null) {
            back.next = new ListNode(list2.val);
            back = back.next;
            list2 = list2.next;
        }

        return res;
    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        else if (list1.val < list2.val) {
            list1.next = mergeTwoLists(list1.next,list2);
            return list1;
        } else {
            list2.next = mergeTwoLists(list1,list2.next);
            return list2;
        }
    }

    public static void main(String[] args) {

    }

}
