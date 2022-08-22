package com.niugiaogiao.linked.leetcode;


import java.util.LinkedList;

/**
 * 请你设计一个队列，支持在前，中，后三个位置的 push 和 pop 操作。
 *
 * 请你完成 FrontMiddleBack 类：
 *
 * FrontMiddleBack() 初始化队列。
 * void pushFront(int val) 将 val 添加到队列的 最前面 。
 * void pushMiddle(int val) 将 val 添加到队列的 正中间 。
 * void pushBack(int val) 将 val 添加到队里的 最后面 。
 * int popFront() 将 最前面 的元素从队列中删除并返回值，如果删除之前队列为空，那么返回 -1 。
 * int popMiddle() 将 正中间 的元素从队列中删除并返回值，如果删除之前队列为空，那么返回 -1 。
 * int popBack() 将 最后面 的元素从队列中删除并返回值，如果删除之前队列为空，那么返回 -1 。
 * 请注意当有 两个 中间位置的时候，选择靠前面的位置进行操作。比方说：
 *
 * 将 6 添加到 [1, 2, 3, 4, 5] 的中间位置，结果数组为 [1, 2, 6, 3, 4, 5] 。
 * 从 [1, 2, 3, 4, 5, 6] 的中间位置弹出元素，返回 3 ，数组变为 [1, 2, 4, 5, 6] 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/design-front-middle-back-queue
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author zi hao
 * @version 1.0
 * @date 2022-08-20 20:15
 */
public class LinkedList1670 {

    static class FrontMiddleBackQueue {

        int size = 0;
        LinkedList<Integer> linkedList = new LinkedList<>();

        public FrontMiddleBackQueue() {
            linkedList = new LinkedList<>();
        }

        public void pushFront(int val) {
            linkedList.addFirst(val);
            size++;
        }

        public void pushMiddle(int val) {
            linkedList.add(size++ / 2, val);
        }

        public void pushBack(int val) {
            size++;
            linkedList.addLast(val);
        }

        public int popFront() {
            if (linkedList.isEmpty()) {
                return -1;
            }
            size--;
            return linkedList.pollFirst();
        }

        public int popMiddle() {
            if (linkedList.isEmpty()) {
                return -1;
            }
            size--;
            int val = linkedList.remove(size / 2);
            return val;
        }

        public int popBack() {
            if (linkedList.isEmpty()) {
                return -1;
            }
            size--;
            return linkedList.pollLast();
        }
    }

    public static void main(String[] args) {
//        ["popFront","popMiddle","popMiddle","popBack","popFront"]
//          [],[],[],[],[]]

        FrontMiddleBackQueue frontMiddleBackQueue = new FrontMiddleBackQueue();
        frontMiddleBackQueue.pushFront(1);
        frontMiddleBackQueue.pushBack(2);
        frontMiddleBackQueue.pushMiddle(3);
        frontMiddleBackQueue.pushMiddle(4);
        System.err.println(frontMiddleBackQueue.popFront());
        System.err.println(frontMiddleBackQueue.popMiddle());
        System.err.println(frontMiddleBackQueue.popMiddle());
        System.err.println(frontMiddleBackQueue.popBack());
        System.err.println(frontMiddleBackQueue.popFront());
    }
}
