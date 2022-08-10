package com.niugiaogiao.linked.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 请实现 copyRandomList 函数，复制一个复杂链表。在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，
 * 还有一个 random 指针指向链表中的任意节点或者 null
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/fu-za-lian-biao-de-fu-zhi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-08-10 21:47
 */
public class LinkedList35 {

    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Map<Node, Node> dict = new HashMap<>();
        Node temp = head;
        while (temp != null) {
            dict.put(temp, new Node(temp.val));
            temp = temp.next;
        }
        temp = head;
        while (temp != null) {
            dict.get(temp).next = dict.get(temp.next);
            dict.get(temp).random = dict.get(temp.random);
            temp = temp.next;
        }

        return dict.get(head);
    }

    public Node copyRandomList1(Node head) {
        if (head == null) {
            return null;
        }

        Node temp = head;
        while (temp != null) {
            Node newNode = new Node(temp.val);
            newNode.next = temp.next;
            temp.next = newNode;
            temp = newNode.next;
        }
        temp = head;
        Node copyNode = null;
        while (temp != null) {
            Node backNode = temp.next.next;
            copyNode = temp.next;
            copyNode.random = copyNode.random == null ? null : copyNode.random.next;
            temp = backNode;
        }

        temp = head;
        Node res = head.next;
        while (temp != null) {
            Node backNode = temp.next.next;
            copyNode = temp.next;
            temp.next = backNode;
            copyNode.next = backNode != null ? backNode.next : null;
            temp = backNode;
        }


        return res;
    }
}
