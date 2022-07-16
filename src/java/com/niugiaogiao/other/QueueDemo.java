package com.niugiaogiao.other;

import java.lang.reflect.Array;

/**
 * @author zi hao
 * @version 1.0
 * @date 2022-04-23 21:58
 */
public class QueueDemo {

    static class Queue<T> {
        private int start;
        private int end;
        private int size;
        private int limit;
        private T[] data;


        Queue(int limit, Class<T> tClass) {
            if (limit < 1) {
                throw new IllegalArgumentException("limit error");
            }
            this.start = 0;
            this.end = 0;
            this.size = 0;
            this.data = (T[]) Array.newInstance(tClass, limit);
            this.limit = limit;
        }

        public void push(T tData) {
            if (size == limit) {
                throw new RuntimeException("full");
            }

            size++;
            data[end] = tData;
            end = getIndex(end);
        }

        public int size() {
            return start;
        }

        public T pop() {
            if (size == 0) {
                throw new RuntimeException("empty");
            }

            size--;
            T res = data[start];
            start = getIndex(start);
            return res;
        }

        private int getIndex(int i) {
            return i >= limit - 1 ? 0 : i + 1;
        }

        public boolean isFull() {
            return size == limit - 1;
        }

        public boolean isEmpty() {
            return size == 0;
        }
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<>(3, Integer.class);
        System.err.println(queue.isFull());
        System.err.println(queue.isEmpty());
        queue.push(1);
        queue.push(2);
        queue.push(4);
        System.err.println(queue.pop());
        System.err.println(queue.pop());
        System.err.println(queue.pop());
        queue.push(7);
        queue.push(8);
        queue.push(9);
        System.err.println(queue.pop());
        System.err.println(queue.pop());
        System.err.println(queue.pop());
    }
}
