package com.niugiaogiao.linked.leetcode;

import java.util.LinkedList;
import java.util.Random;

/**
 * 设计你的循环队列实现。 循环队列是一种线性数据结构，其操作表现基于 FIFO（先进先出）原则并且队尾被连接在队首之后以形成一个循环。它也被称为“环形缓冲器”。
 * <p>
 * 循环队列的一个好处是我们可以利用这个队列之前用过的空间。在一个普通队列里，一旦一个队列满了，我们就不能插入下一个元素，即使在队列前面仍有空间。但是使用循环队列，我们能使用这些空间去存储新的值。
 * <p>
 * 你的实现应该支持如下操作：
 * <p>
 * MyCircularQueue(k): 构造器，设置队列长度为 k 。
 * Front: 从队首获取元素。如果队列为空，返回 -1 。
 * Rear: 获取队尾元素。如果队列为空，返回 -1 。
 * enQueue(value): 向循环队列插入一个元素。如果成功插入则返回真。
 * deQueue(): 从循环队列中删除一个元素。如果成功删除则返回真。
 * isEmpty(): 检查循环队列是否为空。
 * isFull(): 检查循环队列是否已满
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/design-circular-queue
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LinkedList622 {

    static class Node {
        public int val;
        public Node next;

        public Node(int val) {
            this.val = val;
        }
    }

    static class NodeOperator {
        /**
         * 保存前驱节点和后继节点
         */
        public Node pre;
        public Node sub;

        public int maxSize;
        public int size;

        public NodeOperator(int maxSize) {
            this.maxSize = maxSize;
        }
    }

    static class MyCircularQueue {

        NodeOperator nodeOperator;

        public MyCircularQueue(int k) {
            nodeOperator = new NodeOperator(k);
        }

        public boolean enQueue(int value) {
            if (tryInsert()) {
                return false;
            }
            nodeOperator.size++;
            Node node = new Node(value);
            if (nodeOperator.pre == null || nodeOperator.sub == null) {
                nodeOperator.pre = node;
                nodeOperator.sub = node;
                node.next = node;
                return true;
            }
            nodeOperator.pre.next = node;
            node.next = nodeOperator.pre;
            nodeOperator.pre = node;
            return true;
        }

        public boolean deQueue() {
            if (tryDelete()) {
                return false;
            }
            nodeOperator.size--;
            if (nodeOperator.size == 0) {
                nodeOperator.pre = null;
                nodeOperator.sub = null;
                return true;
            }
            nodeOperator.sub = nodeOperator.sub.next;
            nodeOperator.pre.next = nodeOperator.sub;
            return true;
        }

        public int Front() {
            return isEmpty() ? -1 : nodeOperator.sub.val;
        }

        public int Rear() {
            return isEmpty() ? -1 : nodeOperator.pre.val;
        }

        public boolean isEmpty() {
            return nodeOperator.size == 0;
        }

        public boolean isFull() {
            return nodeOperator.size == nodeOperator.maxSize;
        }

        public boolean tryInsert() {
            return nodeOperator.size + 1 > nodeOperator.maxSize;
        }

        public boolean tryDelete() {
            return nodeOperator.size - 1 < 0;
        }
    }

    static class MyCircularQueue1 {
        int[] data;
        int maxSize;
        int back;
        int head;

        public MyCircularQueue1(int size) {
            this.maxSize = size;
            this.data = new int[this.maxSize];
        }

        public boolean enQueue(int value) {
            if (isFull()) {
                return false;
            }
            data[back] = back;
            back = (back + 1) % maxSize;
            return true;
        }

        public boolean deQueue() {
            if (isEmpty()) {
                return false;
            }
            head = (head + 1) % maxSize;
            return true;
        }

        public int Front() {
            return data[head];
        }

        public int Rear() {
            return data[back];
        }

        public boolean isEmpty() {
            return head == back;
        }

        public boolean isFull() {
            return (this.back + 1) % maxSize == this.head;
        }
    }

    public static void main1(String[] args) {
        MyCircularQueue myCircularQueue = new MyCircularQueue(8);
        System.err.println(myCircularQueue.enQueue(3));
        System.err.println(myCircularQueue.enQueue(9));
        System.err.println(myCircularQueue.enQueue(5));
        System.err.println(myCircularQueue.enQueue(0));
        System.err.println(myCircularQueue.deQueue());
        System.err.println(myCircularQueue.deQueue());
        System.err.println(myCircularQueue.isEmpty());
        System.err.println(myCircularQueue.isEmpty());
        System.err.println(myCircularQueue.Rear());
        System.err.println(myCircularQueue.Rear());
        System.err.println(myCircularQueue.deQueue());
    }

    public static void main(String[] args) {
        Random random = new Random();
        int totalSuccess = 0;
        int totalField = 0;
        for (int i = 0; i < 1000; i++) {
            int dataSize = random.nextInt(10000);
            int operator = random.nextInt(1000);
            LinkedList<Integer> list = new LinkedList<>();
//            MyCircularQueue myCircularQueue = new MyCircularQueue(dataSize);
            MyCircularQueue1 myCircularQueue = new MyCircularQueue1(dataSize);
            for (int j = 0; j < operator; j++) {
                int choose = random.nextInt(4);
                switch (choose) {
                    case 0:
                        // insert data
                        int data = random.nextInt(2000);
                        list.addLast(data);
                        myCircularQueue.enQueue(data);
                        break;
                    case 1:
                        // delete data
                        if (!list.isEmpty() && !myCircularQueue.isEmpty()) {
                            list.removeFirst();
                            myCircularQueue.deQueue();
                        }
                        break;
                    case 2:
                        // comparator head
                        if (!(list.isEmpty() && myCircularQueue.isEmpty()) && list.getFirst() == myCircularQueue.Front()) {
                            continue;
                        }
                        break;
                    case 3:
                        // comparator back
                        if (!(list.isEmpty() && myCircularQueue.isEmpty()) && list.getLast() == myCircularQueue.Rear()) {
                            continue;
                        }
                        break;
                }
            }

            while (!list.isEmpty() && !myCircularQueue.isEmpty()) {
                if (!(list.getFirst() == myCircularQueue.Front())) {
                    System.err.println("error");
                    break;
                }
                list.removeFirst();
                myCircularQueue.deQueue();
            }

            if (list.isEmpty() && myCircularQueue.isEmpty()) {
                totalSuccess++;
            } else {
                totalField++;
            }
        }

        System.err.println("totalSuccess: " + totalSuccess + " totalField:" + totalField);
    }
}
