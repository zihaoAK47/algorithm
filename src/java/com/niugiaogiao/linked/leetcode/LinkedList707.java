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
            if (size == 0 && head == null) {
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
            // input error
            if (index < 0 || index == size) {
                return;
            }
            size--;
            if (index == 0 && size == 0) {
                // remove head
                head = null;
                end = null;
                return;
            } else if (index == 0) {
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
        //  ["MyLinkedList","addAtHead","deleteAtIndex","addAtHead","addAtHead","addAtHead","addAtHead","addAtHead","addAtTail","get","deleteAtIndex","deleteAtIndex"]
        //  [[],[2],[1],[2],[7],[3],[2],[5],[5],[5],[6],[4]]
        // [null,null,null,null,null,null,null,null,null,5,null,null]
        // [null,null,null,null,null,null,null,null,null,2,null,null]
        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.addAtIndex(0, 1);
        myLinkedList.addAtIndex(0, 2);
//        myLinkedList.addAtHead(2);
//        myLinkedList.addAtHead(7);
//        myLinkedList.addAtHead(3);
//        myLinkedList.addAtHead(2);
//        myLinkedList.addAtHead(5);
//        myLinkedList.addAtTail(5);
//        myLinkedList.get(5);
//        myLinkedList.deleteAtIndex(6);
//        myLinkedList.deleteAtIndex(4);
        System.err.println("");
    }

    public static void main1(String[] args) {
        int testCount = 1000;
        Random random = new Random();
        for (int i = 0; i < testCount; i++) {
            int operatorCount = random.nextInt(1000);
            LinkedList<Integer> list = new LinkedList<>();
            MyLinkedList myLinkedList = new MyLinkedList();
            StringBuffer operator = new StringBuffer();
            for (int j = 0; j < operatorCount; j++) {
                int choose = random.nextInt(5);
                switch (choose) {
                    case 0:
                        operator.append("add head");
                        // add head
                        int insertHead = random.nextInt(1000);
                        list.addFirst(insertHead);
                        myLinkedList.addAtHead(insertHead);
                        break;
                    case 1:
                        // add tail
                        operator.append("add tail");
                        int insertTail = random.nextInt(1000);
                        list.addLast(insertTail);
                        myLinkedList.addAtTail(insertTail);
                        break;
                    case 2:
                        // get index
                        System.err.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>.");
                        System.err.println(operator);
                        System.err.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>.");
                        operator = new StringBuffer();
                        if (list.isEmpty() && myLinkedList.singleLinkedList.size == 0) {
                            continue;
                        }
                        int randData = random.nextInt(myLinkedList.singleLinkedList.size);
                        System.err.println("list >" + list.get(randData) + " my >" + myLinkedList.get(randData));
                        if (list.get(randData) != myLinkedList.get(randData)) {
                            System.err.println("get error");
                        }
                        break;
                    case 3:
                        // add index
                        operator.append("add index");
                        if (myLinkedList.singleLinkedList.size == 0) {
                            myLinkedList.addAtIndex(random.nextInt(999), random.nextInt(9999));
                        } else {
                            int index = random.nextInt(1000);
                            int insertData = random.nextInt(1000);
                            myLinkedList.addAtIndex(index, insertData);
                            if (index < myLinkedList.singleLinkedList.size) {
                                list.add(index, insertData);
                            }
                        }
                        break;
                    case 4:
                        // remove index
                        operator.append("remove index");
                        if (myLinkedList.singleLinkedList.size == 0) {
                            myLinkedList.deleteAtIndex(random.nextInt(9999));
                        } else {
                            int removeIndex = random.nextInt(myLinkedList.singleLinkedList.size);
                            list.remove(removeIndex);
                            myLinkedList.deleteAtIndex(removeIndex);
                        }
                        break;
                }
            }
            while (!list.isEmpty() && myLinkedList.singleLinkedList.size != 0) {
                if (list.getFirst() != myLinkedList.get(1)) {
                    System.err.println("loop error");
                    return;
                }
                list.removeFirst();
                myLinkedList.deleteAtIndex(0);
            }
        }
    }
}
