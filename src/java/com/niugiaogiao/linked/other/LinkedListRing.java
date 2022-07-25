package com.niugiaogiao.linked.other;

/**
 * @author zi hao
 * @version 1.0
 * @date 2022-05-06 15:25
 */

import java.util.HashSet;
import java.util.Set;

/**
 * 给定两个可能有环也可能无环的单链表
 * 头结点 head1 和 head2 请实现一个函数，如果两个链表相交请返回相交的第一个节点
 * 如果不相交 返回 null
 * 时间复杂度O(N) 额外空间复杂度 O(1)
 * <p>
 * 三个面试题合集：
 * 1.给定链表返回第一个入环的结点
 * 2.两个无环链表相交返回第一个相交的节点
 * 3.两个有环链表返回第一个相交的节点
 */
public class LinkedListRing {

    /**
     * 如何找到两个链表相交的点
     * 1. 使用 set 将其中一个链表放入 set 中
     * 遍历另外一个链表如果设置不成功则当前节点就是相交的节点
     * 2. 准备快慢指针，快指针走两步 满指针走一步，只要它们两个不相等就继续走
     * 当相遇的时候代表着有环，此时让快指针回到开头，变成一次走一步
     * 慢指针停在原地维持一次走一步，它们一定会再次相遇而且在第一个节点相遇
     * 小学奥赛的追击问题
     */

    static class Node {
        public int val;
        public Node next;

        public Node(int val) {
            this.val = val;
        }

    }

    /**
     * 使用 set 来获取第一个入环的节点
     *
     * @param root
     * @return
     */
    public static Node getRingNode(Node root) {
        if (root == null) {
            return null;
        }

        Set<Node> cache = new HashSet<>();
        Node tempHead1 = root;
        while (tempHead1 != null) {
            if (!cache.contains(tempHead1)) {
                cache.add(tempHead1);
            } else {
                return tempHead1;
            }

            tempHead1 = tempHead1.next;
        }

        return null;
    }

    /**
     * 使用快慢指针来获取第一个入环的节点数据
     *
     * @param root
     * @return
     */
    public static Node getRingRef(Node root) {
        if (null == root || root.next == null || root.next.next == null) {
            return null;
        }

        Node before = root.next.next;
        Node after = root.next;

        while (before != after) {
            if (before.next == null || before.next.next == null) {
                return null;
            }

            before = before.next.next;
            after = after.next;
        }
        before = root;
        while (before != null && after != null && before != after) {
            before = before.next;
            after = after.next;
        }

        return after;
    }

    public static Node noLoop1(Node head1, Node head2) {
        if (null == head1 || head2 == null) {
            return null;
        }

        Node tempHead1 = head1;
        Node tempHead2 = head2;
        int n = 0;
        while (tempHead1.next != null) {
            n++;
            tempHead1 = tempHead1.next;
        }
        while (tempHead2.next != null) {
            n--;
            tempHead2 = tempHead2.next;
        }

        tempHead1 = n > 0 ? head1 : head2;
        tempHead2 = tempHead1 == head1 ? head2 : head1;
        n = Math.abs(n);
        while (n-- != 0) {
            tempHead1 = tempHead1.next;
        }
        while (tempHead1 != tempHead2) {
            tempHead1 = tempHead1.next;
            tempHead2 = tempHead2.next;
        }

        return tempHead1;
    }

    /**
     * 无环链表取节点
     *
     * @param head1
     * @param head2
     * @return
     */
    public static Node noLoop(Node head1, Node head2) {
        int len1 = 0;
        Node tempHead1 = head1;
        while (tempHead1.next != null) {
            len1++;
            tempHead1 = tempHead1.next;
        }

        int len2 = 0;
        Node tempHead2 = head2;
        while (tempHead2.next != null) {
            len2++;
            tempHead2 = tempHead2.next;
        }

        // 必然不相交
        if (tempHead1 != tempHead2) {
            return null;
        }

        int difference = Math.abs(len1 - len2);
        // 重新遍历最长的那个链表
        tempHead1 = (len1 > len2) ? head1 : head2;
        tempHead2 = (len1 > len2) ? head2 : head1;
        while (tempHead1 != tempHead2 && difference != 0) {
            difference--;
            tempHead1 = tempHead1.next;
        }

        while (tempHead1 != tempHead2) {
            tempHead1 = tempHead1.next;
            tempHead2 = tempHead2.next;
        }

        return tempHead1;
    }


    public static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
        Node tempHead1 = null;
        Node tempHead2 = null;
        if (loop1 == loop2) {
            tempHead1 = head1;
            tempHead2 = head2;
            int n = 0;
            while (tempHead1.next != loop1) {
                n++;
                tempHead1 = tempHead1.next;
            }
            while (tempHead2.next != loop2) {
                n--;
                tempHead2 = tempHead2.next;
            }
            tempHead1 = n > 0 ? head1 : head2;
            tempHead2 = tempHead1 == head1 ? head2 : head1;
            n = Math.abs(n);
            while (n-- != 0) {
                tempHead1 = tempHead1.next;
            }
            while (tempHead1 != tempHead2) {
                tempHead1 = tempHead1.next;
                tempHead2 = tempHead2.next;
            }
            return tempHead1;
        } else {
            Node tempLoop = loop1.next;
            while (tempLoop != loop1) {
                if (tempLoop == loop2) {
                    return tempLoop;
                }

                tempLoop = tempLoop.next;
            }

            return null;
        }
    }

    /**
     * 获取两个链表相交的节点
     *
     * @param head1
     * @param head2
     * @return
     */
    public static Node getIntersectNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }

        Node ringHead1 = getRingRef(head1);
        Node ringHead2 = getRingRef(head2);
        // 两个都是无环链表
        if (ringHead1 == null && ringHead2 == null) {
            return noLoop1(head1, head2);
        }
        // 如果一个有环另一个无环不可能相交，如果一个节点出去只有一个 next 指针一定会破坏
        // 如果两个链表都是环，分三个情况
        // 1. 各自独立的环链表
        // 2. 两个链表入环节点为同一个 （即 rightHead1 == rightHead2）
        // 3. 两个链表入环节点不是同一个
        if (ringHead1 != null && ringHead2 != null) {
            return bothLoop(head1, ringHead1, head2, ringHead2);
        }

        return null;
    }

    /**
     * 求出两个链表的入环第一个节点，分情况讨论
     * 1. 如果 loop1 == null && loop2 == null，单独讨论两个无环链表是否相交的问题
     * 如果不相交就是两个独立的线，如果相交了一定是公共的线。如何找？其中一个链表放入 set 中，另一个链表遍历
     * 不用 hash 表怎么找？分别遍历两个链表到最后一个不为 null 的节点并记录长度，对比两个节点是否相等
     * 如果不相等则一定不相交，如果相等较长的链表开始从头走，走到两个链表相减的差值开始，两个链表同时进行走
     * 则一定会来到相交的位置
     *
     * @param args
     */
    public static void main(String[] args) {
        Node root = new Node(1);
        Node n1 = new Node(2);
        Node n2 = new Node(3);
        Node n3 = new Node(4);
        root.next = n1;
        n1.next = n2;
        n2.next = n3;
        n3.next = n1;

        Node root1 = new Node(5);
        Node r2 = new Node(6);
        Node r3 = new Node(7);
        root1.next = r2;
        r2.next = r3;
        r3.next = n2;

//        System.err.println(getIntersectNode(root, root1).val);
        Node head1 = root;
        Node head2 = root1;
        while (head1 != head2) {
            head1 = head1 == null ? root1 : head1.next;
            head2 = head2 == null ? root : head2.next;
        }
        System.err.println(head1);
    }
}
