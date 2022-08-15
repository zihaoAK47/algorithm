package com.niugiaogiao.linked.leetcode;

import java.util.LinkedList;
import java.util.Random;

/**
 * 设计实现双端队列。
 *
 * 实现 MyCircularDeque 类:
 *
 * MyCircularDeque(int k)：构造函数,双端队列最大为 k 。
 * boolean insertFront()：将一个元素添加到双端队列头部。 如果操作成功返回 true，否则返回 false 。
 * boolean insertLast()：将一个元素添加到双端队列尾部。如果操作成功返回 true，否则返回 false 。
 * boolean deleteFront()：从双端队列头部删除一个元素。 如果操作成功返回 true，否则返回 false 。
 * boolean deleteLast()：从双端队列尾部删除一个元素。如果操作成功返回 true，否则返回 false 。
 * int getFront())：从双端队列头部获得一个元素。如果双端队列为空，返回 -1。
 * int getRear()：获得双端队列的最后一个元素。如果双端队列为空，返回 -1 。
 * boolean isEmpty()：若双端队列为空，则返回true，否则返回 false 。
 * boolean isFull()：若双端队列满了，则返回true，否则返回 false
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/design-circular-deque
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LinkedList641 {

    static class Node {
        public int val;
        public Node pre;
        public Node sub;

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

    static class MyCircularDeque {

        NodeOperator nodeOperator;

        public MyCircularDeque(int k) {
            nodeOperator = new NodeOperator(k);
        }

        public boolean insertFront(int value) {
            if (tryInsert()) {
                return false;
            }
            nodeOperator.size++;
            Node node = new Node(value);
            if (nodeOperator.pre == null && nodeOperator.sub == null) {
                nodeOperator.pre = node;
                nodeOperator.sub = node;
                return true;
            }
            nodeOperator.sub.pre = node;
            node.sub = nodeOperator.sub;
            nodeOperator.sub = node;
            return true;
        }

        public boolean insertLast(int value) {
            if (tryInsert()) {
                return false;
            }
            nodeOperator.size++;
            Node node = new Node(value);
            if (nodeOperator.pre == null && nodeOperator.sub == null) {
                nodeOperator.pre = node;
                nodeOperator.sub = node;
                return true;
            }
            node.pre = nodeOperator.pre;
            nodeOperator.pre.sub = node;
            nodeOperator.pre = node;
            return true;
        }

        public boolean deleteFront() {
            if (tryDelete()) {
                return false;
            }
            nodeOperator.size--;
            if (nodeOperator.size == 0) {
                nodeOperator.pre = null;
                nodeOperator.sub = null;
                return true;
            }
            nodeOperator.sub.sub.pre = null;
            nodeOperator.sub = nodeOperator.sub.sub;
            return true;
        }

        public boolean deleteLast() {
            if (tryDelete()) {
                return false;
            }
            nodeOperator.size--;
            if (nodeOperator.size == 0) {
                nodeOperator.pre = null;
                nodeOperator.sub = null;
                return true;
            }

            nodeOperator.pre.pre.sub = null;
            nodeOperator.pre = nodeOperator.pre.pre;
            return true;
        }

        public int getFront() {
            return isEmpty() ? -1 : nodeOperator.sub.val;
        }

        public int getRear() {
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

    public static void main1(String[] args) {
        MyCircularDeque myCircularDeque = new MyCircularDeque(3);
        myCircularDeque.insertLast(1);
        myCircularDeque.insertLast(2);
        myCircularDeque.deleteLast();
        myCircularDeque.deleteLast();
        System.err.println("");
    }

    public static void main(String[] args) {
        int testNumber = 100000;
        int successCount = 0;
        int errorCount = 0;
        Random random = new Random();
        for (int i = 0; i < testNumber; i++) {
            int dataSize = 0;
            int operatorNumber = random.nextInt(2000);
            LinkedList<Integer> list = new LinkedList<>();
            MyCircularDeque myCircularDeque = new MyCircularDeque(dataSize);
            for (int j = 0; j < operatorNumber; j++) {
                int pollData = random.nextInt(1000);
                int operator = random.nextInt(4);
                switch (operator) {
                    case 0:
                        // 头部插入
                        list.addFirst(pollData);
                        myCircularDeque.insertFront(pollData);
                        break;
                    case 1:
                        // 尾部插入
                        list.addLast(pollData);
                        myCircularDeque.insertLast(pollData);
                        break;
                    case 2:
                        // 头部获取
                        if (list.getFirst() == myCircularDeque.getFront()) {
                            continue;
                        } else {
                            System.err.println("get first error");
                        }
                        break;
                    case 3:
                        // 尾部获取
                        if (list.getLast() == myCircularDeque.getRear()) {
                            continue;
                        } else {
                            System.err.println("get last error");
                        }
                        break;
                    case 4:
                        // 头部删除
                        list.removeFirst();
                        myCircularDeque.deleteFront();
                        break;
                    case 5:
                        // 尾部删除
                        list.removeLast();
                        myCircularDeque.deleteLast();
                        break;
                }
            }
            while (!list.isEmpty() && !myCircularDeque.isEmpty()) {
                if (list.getFirst() != myCircularDeque.getFront()) {
                    System.err.println("error");
                    return;
                }
                list.removeFirst();
                myCircularDeque.deleteFront();
            }
            if (list.isEmpty() && myCircularDeque.isEmpty()) {
                successCount++;
            } else {
                errorCount++;
            }
        }
        System.err.println("数据集：" + testNumber + "成功：" + successCount + "失败：" + errorCount);
    }
}
