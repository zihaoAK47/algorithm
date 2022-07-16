package com.niugiaogiao.linked;

/**
 * 将单向链表按照某值划分，左边小，中间相等，右边大
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-05-03 22:12
 */
public class LinkedListPartition {

    static class Node {
        public int val;
        public Node next;

        public Node(int val) {
            this.val = val;
        }
    }

    public static Node sortLinked(Node head, int val) {
        if (null == head || head.next == null) {
            return head;
        }
        int size = 0;
        Node tempHead = head;
        while (tempHead != null) {
            size++;
            tempHead = tempHead.next;
        }
        Node[] data = new Node[size];
        size = 0;
        tempHead = head;
        while (tempHead != null) {
            data[size++] = tempHead;
            tempHead = tempHead.next;
        }
        sort(data, val);
        for (size = 1; size != data.length; size++) {
            data[size - 1].next = data[size];
        }
        data[size - 1].next = null;
        return data[0];
    }

    public static void sort(Node[] data, int val) {
        int i = 0;
        int j = data.length;
        int less = -1;
        while (i < j) {
            if (val == data[i].val) {
                i++;
            } else if (data[i].val > val) {
                swap(data, i, --j);
            } else if (data[i].val < val) {
                swap(data, i++, ++less);
            }
        }
    }

    public static void swap(Node[] data, int i, int j) {
        Node t = data[i];
        data[i] = data[j];
        data[j] = t;
    }

    public static Node sortLinkedByRed(Node head, int val) {
        if (null == head || head.next == null) {
            return head;
        }
        Node leftNode = null;
        Node leftHead = null;
        Node midNode = null;
        Node midHead = null;
        Node rightNode = null;
        Node rightHead = null;
        while (head != null) {
            Node temp = head.next;
            head.next = null;
            if (head.val == val) {
                if (midHead == null) {
                    midHead = head;
                    midNode = head;
                } else {
                    head.next = midHead;
                    midHead = head;
                }
            } else if (head.val < val) {
                if (leftHead == null) {
                    leftHead = head;
                    leftNode = head;
                } else {
                    head.next = leftHead;
                    leftHead = head;
                }
            } else {
                if (rightHead == null) {
                    rightHead = head;
                    rightNode = head;
                } else {
                    head.next = rightHead;
                    rightHead = head;
                }
            }
            head = temp;
        }

        if (leftHead != null) {
            leftNode.next = midHead;
            midNode = midHead == null ? leftNode : midNode;
        }
        if (midNode != null) {
            midNode.next = rightHead;
        }

        return leftHead != null ? leftHead : (midHead != null ? midHead : rightHead);
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        Node node1 = new Node(4);
        Node node2 = new Node(5);
        Node node3 = new Node(2);
        Node node4 = new Node(3);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
//        Node node = sortLinked(head, 3);
        Node node = sortLinkedByRed(head, 3);
        System.err.println("");
    }
}
