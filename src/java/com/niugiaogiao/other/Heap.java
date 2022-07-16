package com.niugiaogiao.other;

import java.util.Arrays;

/**
 * @author zi hao
 * @version 1.0
 * @date 2022-04-26 12:39
 */
public class Heap {


    static class HeapData {
        private int limit;
        private int size;
        private int[] data;

        public HeapData(int limit) {
            this.limit = limit;
            this.data = new int[limit];
        }

        public void push(int number) {
            if (size == limit) {
                return;
            }
            data[size] = number;
            heapRun(size++);
        }

        // 插入数据
        public void heapRun(int R) {
            while (data[R] > data[(R - 1) / 2]) {
                swap(R, (R - 1) / 2);
                R = (R - 1) / 2;
            }
        }

        public int pop() {
            if (size == 0) {
                return -1;
            }

            int numData = data[0];
            swap(0, --size);
            ify(data,size);
            return numData;
        }

        // 调整堆
        public void ify(int[] d,int R) {
            int start = 0;
            int left = start * 2 + 1;
            while (left < R) {
                // 左节点和当前节点对比
                int maxValIndex = left + 1 < R && d[left] < d[left + 1] ? left + 1 : left;
                maxValIndex = d[start] > d[maxValIndex] ? start : maxValIndex;
                if (maxValIndex == start) {
                    break;
                }
                swap(start, maxValIndex);
                start = maxValIndex;
                left = start * 2 + 1;
            }
        }

        private void swap(int i, int j) {
            int t = data[i];
            data[i] = data[j];
            data[j] = t;
        }

        public void toData() {
            System.err.println(Arrays.toString(data));
        }

        /**
         * 只是变成堆数据结构
         *
         * @param data
         */
        public void runJob(int[] data) {
            for (int i = data.length - 1; i >= 0; i--) {

            }
        }
    }


    public static void main(String[] args) {
        int[] num = new int[]{1, 2, 3, 5, 4, 7, 6,6};
        HeapData data = new HeapData(10);
        for (int value : num) {
            data.push(value);
        }
        data.toData();
        for (int val : num) {
            System.err.println(data.pop());
        }
    }
}
