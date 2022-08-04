package com.niugiaogiao.linked.leetcode;

/**
 * https://leetcode.cn/problems/4ueAj6/
 * 给定循环单调非递减列表中的一个点，写一个函数向这个列表中插入一个新元素 insertVal ，使这个列表仍然是循环升序的。
 * 给定的可以是这个列表中任意一个顶点的指针，并不一定是这个列表中最小元素的指针。
 * 如果有多个满足条件的插入位置，可以选择任意一个位置插入新的值，插入后整个列表仍然保持有序。
 * 如果列表为空（给定的节点是 null），需要创建一个循环有序列表并返回这个节点。否则。请返回原先给定的节点
 *
 * @author zihao
 */
public class LinkedList029 {

    static class Node {
        public int val;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _next) {
            val = _val;
            next = _next;
        }
    }

    public static Node insert(Node head, int insertVal) {
        if (head == null) {
            return emptyNode(insertVal);
        }
        if (head.next == head) {
            return singleNode(head, insertVal);
        }
        Node after = head;
        Node before = head.next;
        Node newNode;
        while (head != before) {
            if (after.val <= insertVal && insertVal <= before.val) {
                break;
            } else if (after.val > before.val && after.val < insertVal) {
                break;
            } else if (after.val > before.val && before.val > insertVal) {
                break;
            }
            before = before.next;
            after = after.next;
        }

        newNode = new Node(insertVal);
        after.next = newNode;
        newNode.next = before;
        return head;
    }

    public static Node insert1(Node head, int insertVal) {
        Node node = new Node(insertVal);
        if (head == null) {
            node.next = node;
            return node;
        }
        if (head.next == head) {
            head.next = node;
            node.next = head;
            return head;
        }
        Node curr = head, next = head.next;
        while (next != head) {
            if (insertVal >= curr.val && insertVal <= next.val) {
                break;
            }
            if (curr.val > next.val) {
                if (insertVal > curr.val || insertVal < next.val) {
                    break;
                }
            }
            curr = curr.next;
            next = next.next;
        }
        curr.next = node;
        node.next = next;
        return head;
    }

    private static Node emptyNode(int val) {
        Node head = new Node(val);
        head.next = head;
        return head;
    }

    private static Node singleNode(Node head, int val) {
        Node newNode = new Node(val);
        head.next = newNode;
        newNode.next = head;
        return head;
    }

    public static void main(String[] args) {
        Node r1 = new Node(3);
        Node r2 = new Node(3);
        Node r3 = new Node(3);
        r1.next = r2;
        r2.next = r3;
        r3.next = r1;
        System.err.println(insert1(r1, 0));
    }
}
