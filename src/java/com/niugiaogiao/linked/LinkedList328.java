package com.niugiaogiao.linked;

/**
 * 给定单链表的头节点 head ，将所有索引为奇数的节点和索引为偶数的节点分别组合在一起，然后返回重新排序的列表。
 * <p>
 * 第一个节点的索引被认为是 奇数 ， 第二个节点的索引为 偶数 ，以此类推。
 * <p>
 * 请注意，偶数组和奇数组内部的相对顺序应该与输入时保持一致。
 * <p>
 * 你必须在 O(1) 的额外空间复杂度和 O(n) 的时间复杂度下解决这个问题
 * <p>
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-06-10 9:41
 */
public class LinkedList328 {

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

    public static ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode evenHead = head.next;
        ListNode odd = head;
        ListNode even = evenHead;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }

    public static void main(String[] args) {
        ListNode r1 = new ListNode(1);
        ListNode r2 = new ListNode(2);
        ListNode r3 = new ListNode(1);
        ListNode r4 = new ListNode(4);
        ListNode r5 = new ListNode(1);
        ListNode r6 = new ListNode(1);

        r1.next = r2;
        r2.next = r3;
        r3.next = r4;
        r4.next = r5;
        r5.next = r6;

        System.err.println(oddEvenList(r1));
    }
}
