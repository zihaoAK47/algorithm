package com.niugiaogiao.linked.leetcode;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

/**
 * @author zi hao
 * @version 1.0
 * @date 2022-08-14 12:58
 */
public class LinkedList706 {

    static class Data {
        public int key;
        public int val;

        public Data(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    static class MyHashMap {
        private static final int BASE = 769;
        private LinkedList[] hashData;

        public MyHashMap() {
            hashData = new LinkedList[BASE];
            for (int i = 0; i < BASE; i++) {
                hashData[i] = new LinkedList<Data>();
            }
        }

        public void put(int key, int value) {
            int position = getPosition(key);
            Iterator<Data> iterator = hashData[position].iterator();
            while (iterator.hasNext()) {
                Data next = iterator.next();
                if (next.key == key) {
                    next.val = value;
                    return;
                }
            }
            hashData[position].offerLast(new Data(key, value));
        }

        public int get(int key) {
            int position = getPosition(key);
            Iterator<Data> iterator = hashData[position].iterator();
            while (iterator.hasNext()) {
                Data next = iterator.next();
                if (next.key == key) {
                    return next.val;
                }
            }

            return -1;
        }

        public void remove(int key) {
            int position = getPosition(key);
            Iterator<Data> iterator = hashData[position].iterator();
            while (iterator.hasNext()) {
                Data next = iterator.next();
                if (next.key == key) {
                    iterator.remove();
                    return;
                }
            }
        }

        public int getPosition(Integer hash) {
            return hash.hashCode() % BASE;
        }
    }

    public static int getRandNumber() {
        return new Random().nextInt(9999999);
    }


    public static void main(String[] args) {
        Map<Integer, Integer> map = new HashMap<>();
        MyHashMap testMap = new MyHashMap();
        int testNumber = 100000;

        long start = System.currentTimeMillis();
        for (int i = 0; i < testNumber; i++) {
            // 执行相关操作
            int operatorNumber = new Random().nextInt(9999999);
            Random random = new Random();
            for (int j = 0; j < operatorNumber; j++) {
                // put , get , del
                int choose = random.nextInt(3);
                int key = getRandNumber();
                int val = getRandNumber();
                switch (choose) {
                    // put
                    case 0:
                        map.put(key, val);
                        testMap.put(key, val);
                        break;
                    // get
                    case 1:
                        key = getRandNumber();
                        Integer mapKey = map.get(key);
                        int data = testMap.get(key);
                        if (mapKey == null && data == -1) {
                            continue;
                        } else if (mapKey == data) {
                            continue;
                        } else {
                            System.err.println("error");
                        }
                        break;
                    case 2:
                        key = getRandNumber();
                        map.remove(key);
                        testMap.remove(key);
                        break;
                }
            }
        }

        System.err.println(System.currentTimeMillis() - start);
    }
}
