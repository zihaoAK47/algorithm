package com.niugiaogiao.linked.leetcode;

import java.util.*;

/**
 * 设计链表的实现。您可以选择使用单链表或双链表。单链表中的节点应该具有两个属性：val 和 next
 * val 是当前节点的值，next 是指向下一个节点的指针/引用。如果要使用双向链表
 * 则还需要一个属性 prev 以指示链表中的上一个节点。假设链表中的所有节点都是 0-index 的
 * <p>
 * 在链表类中实现这些功能：
 * <p>
 * get(index)：获取链表中第 index 个节点的值。如果索引无效，则返回-1
 * addAtHead(val)：在链表的第一个元素之前添加一个值为 val 的节点。插入后，新节点将成为链表的第一个节点
 * addAtTail(val)：将值为 val 的节点追加到链表的最后一个元素
 * addAtIndex(index,val)：在链表中的第 index 个节点之前添加值为 val  的节点
 * 如果 index 等于链表的长度，则该节点将附加到链表的末尾。如果 index 大于链表长度，则不会插入节点。如果index小于0，则在头部插入节点
 * deleteAtIndex(index)：如果索引 index 有效，则删除链表中的第 index 个节点
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/design-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LinkedList707 {

    static class SingleLinkedListData {
        public int data;
        public SingleLinkedListData next;

        public SingleLinkedListData(int newData) {
            this.data = newData;
        }
    }

    /**
     * 单向链表
     */
    static class SingleLinkedList {
        int size;
        /**
         * 前驱后继节点
         */
        SingleLinkedListData head;
        SingleLinkedListData end;

        public SingleLinkedList() {
        }

        public int get(int index) {
            if (head == null || index >= size) {
                return -1;
            }
            SingleLinkedListData headTemp = head;
            int tempIndex = 0;
            while (tempIndex != index) {
                tempIndex++;
                headTemp = headTemp.next;
            }

            return headTemp.data;
        }

        public void addHead(int val) {
            size++;
            SingleLinkedListData newData = new SingleLinkedListData(val);
            if (head == null || end == null) {
                head = newData;
                end = newData;
                newData.next = newData;
                return;
            }
            newData.next = head;
            end.next = newData;
            head = newData;
        }

        public void addTail(int val) {
            size++;
            SingleLinkedListData newData = new SingleLinkedListData(val);
            if (head == null || end == null) {
                head = newData;
                end = newData;
                newData.next = newData;
                return;
            }
            end.next = newData;
            newData.next = head;
            end = newData;
        }

        public void addAtIndex(int index, int val) {
            if (index > size) {
                return;
            }
            if (index == size) {
                addTail(val);
                return;
            }
            if (index <= 0) {
                addHead(val);
                return;
            }
            size++;
            int pos = 1;
            SingleLinkedListData newData = new SingleLinkedListData(val);
            SingleLinkedListData tempHead = head;
            while (pos < index) {
                pos++;
                tempHead = tempHead.next;
            }
            newData.next = tempHead.next;
            tempHead.next = newData;
        }

        public void deleteAtIndex(int index) {
            if (index < 0 || index >= size || head == null) {
                return;
            }
            size--;
            if (size == 0) {
                head = null;
                end = null;
                return;
            }
            // is del head
            if (index == 0) {
                head = head.next;
                end.next = head;
                return;
            }
            int pos = 1;
            SingleLinkedListData tempHead = head;
            while (pos < index) {
                pos++;
                tempHead = tempHead.next;
            }
            tempHead.next = tempHead.next.next;
            if (tempHead.next == head) {
                end = tempHead;
            }
        }
    }

    static class MyLinkedList {
        SingleLinkedList singleLinkedList;

        public MyLinkedList() {
            singleLinkedList = new SingleLinkedList();
        }

        public int get(int index) {
            return singleLinkedList.get(index);
        }

        public void addAtHead(int val) {
            singleLinkedList.addHead(val);
        }

        public void addAtTail(int val) {
            singleLinkedList.addTail(val);
        }

        public void addAtIndex(int index, int val) {
            singleLinkedList.addAtIndex(index, val);
        }

        public void deleteAtIndex(int index) {
            singleLinkedList.deleteAtIndex(index);
        }
    }

    public static void main(String[] args) {
        // ["deleteAtIndex","deleteAtIndex","get","deleteAtIndex","get"]
        // [[3],[0],[0],[0],[0]]
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.addAtHead(1);
        myLinkedList.addAtTail(3);
        myLinkedList.addAtIndex(1, 2);
        System.err.println(myLinkedList.get(1));
        myLinkedList.deleteAtIndex(1);
        System.err.println(myLinkedList.get(1));
        System.err.println(myLinkedList.get(3));
        myLinkedList.deleteAtIndex(3);
        myLinkedList.deleteAtIndex(0);
        System.err.println(myLinkedList.get(0));
        myLinkedList.deleteAtIndex(0);
        System.err.println(myLinkedList.get(0));
    }

    public static void main1(String[] args) {
        int testCount = 100000;
        int successCount = 0;
        int errorCount = 0;
        int operatorSuccess = 0;
        Random random = new Random();
        for (int i = 0; i < testCount; i++) {
            int operatorCount = random.nextInt(1000);
            LinkedList<Integer> list = new LinkedList<>();
            MyLinkedList myLinkedList = new MyLinkedList();
            for (int j = 0; j < operatorCount; j++) {
                int choose = random.nextInt(5);
                switch (choose) {
                    case 0:
                        // add head
                        int insertHead = random.nextInt(1000);
                        list.addFirst(insertHead);
                        myLinkedList.addAtHead(insertHead);
                        break;
                    case 1:
                        // add tail
                        int insertTail = random.nextInt(1000);
                        list.addLast(insertTail);
                        myLinkedList.addAtTail(insertTail);
                        break;
                    case 2:
                        // get index
                        if (myLinkedList.singleLinkedList.head == null) {
                            continue;
                        }
                        int randData = random.nextInt(myLinkedList.singleLinkedList.size);
                        if (list.get(randData) != myLinkedList.get(randData)) {
                            System.err.println("get error");
                        }
                        break;
                    case 3:
                        // add index
                        if (myLinkedList.singleLinkedList.head == null) {
                            continue;
                        }
                        int index = random.nextInt(1000);
                        int insertData = random.nextInt(1000);
                        if (index < myLinkedList.singleLinkedList.size) {
                            myLinkedList.addAtIndex(index, insertData);
                            list.add(index, insertData);
                        }
                        break;
                    case 4:
                        // remove index
                        if (myLinkedList.singleLinkedList.head == null) {
                            continue;
                        }
                        int removeIndex = random.nextInt(myLinkedList.singleLinkedList.size);
                        if (removeIndex < myLinkedList.singleLinkedList.size) {
                            myLinkedList.deleteAtIndex(removeIndex);
                            list.remove(removeIndex);
                        }
                        break;
                }
            }
            while (!list.isEmpty() && myLinkedList.singleLinkedList.size != 0) {
                if (list.getFirst() != myLinkedList.get(0)) {
                    System.err.println("loop error");
                    errorCount++;
                    return;
                }
                list.removeFirst();
                myLinkedList.deleteAtIndex(0);
            }
            operatorSuccess += operatorCount;
            successCount++;
        }
        System.err.println("testCount: " + testCount + " successCount: " + successCount + " operatorSuccess: " + operatorSuccess);
    }
}
