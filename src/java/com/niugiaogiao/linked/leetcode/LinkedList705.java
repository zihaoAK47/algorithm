package com.niugiaogiao.linked.leetcode;

/**
 * 不使用任何内建的哈希表库设计一个哈希集合（HashSet）
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-06-17 9:47
 */

public class LinkedList705 {

    private int[] dict = new int[40000];

    public void add(int key) {
        int pos = key / 32;
        int bitPos = key % 32;
        setBit(pos, bitPos, true);
    }

    public void remove(int key) {
        int pos = key / 32;
        int bitPos = key % 32;
        setBit(pos, bitPos, false);
    }

    public boolean contains(int key) {
        int pos = key / 32;
        int bitPos = key % 32;

        int res = dict[pos] & (1 << bitPos);
        return res == 1;
    }

    private void setBit(final int pos, final int bitPos, final boolean flag) {
        if (flag) {
            // 将当前 bit 位置设置为 1
            int data = dict[pos] | (1 << bitPos);
            dict[pos] = data;
        } else {
            // 取反位与运算，那个位一定会是 0
            int data = dict[pos] & ~(1 << bitPos);
            dict[pos] = data;
        }
    }
}

// 思路3
//public class LinkedList705 {
//
//    private static final int LEN = 10009;
//    private LinkedList<Integer>[] data = new LinkedList[10009];
//
//    private int hash(int key) {
//        int hashCode = Integer.hashCode(key);
//        hashCode ^= (hashCode >>> 16);
//        return hashCode % LEN;
//    }
//
//    public void add(int key) {
//        int hash = hash(key);
//        LinkedList<Integer> indexData = data[hash];
//        if (indexData != null) {
//            for (Integer item : indexData) {
//                if (item.equals(key)) {
//                    return;
//                }
//            }
//        } else {
//            data[hash] = new LinkedList<>();
//            data[hash].addFirst(key);
//        }
//    }
//
//    public void remove(int key) {
//        int hash = hash(key);
//        LinkedList<Integer> indexData = data[hash];
//        if (indexData != null) {
//            for (int i = 0; i < indexData.size(); i++) {
//                if (indexData.get(i).equals(key)) {
//                    indexData.remove(i);
//                    return;
//                }
//            }
//        }
//    }
//
//    public boolean contains(int key) {
//        int hash = hash(key);
//        LinkedList<Integer> indexData = data[hash];
//        if (indexData != null) {
//            for (Integer item : indexData)
//                if (item.equals(key))
//                    return true;
//        }
//
//        return false;
//    }
//}

// 思路2
//public class LinkedList705 {
//    public static final int BASE = 769;
//    private LinkedList<Integer>[] data;
//
//    public LinkedList705() {
//        data = new LinkedList[BASE];
//        for (int i = 0; i < BASE; i++) {
//            data[i] = new LinkedList<>();
//        }
//    }
//
////    public MyHashSet() {
////
////    }
//
//    private static int hash(int key) {
//        return key % BASE;
//    }
//
//    public void add(int key) {
//        int h = hash(key);
//        for (Integer integer : data[h]) {
//            if (integer.equals(key)) {
//                return;
//            }
//        }
//        data[h].offerLast(key);
//    }
//
//    public void remove(int key) {
//        int h = hash(key);
//        data[h].removeIf(integer -> integer.equals(key));
//    }
//
//    public boolean contains(int key) {
//        int h = hash(key);
//        for (Integer item : data[h]) {
//            if (item.equals(key))
//                return true;
//        }
//
//        return false;
//    }
//}

//思路一
//public class LinkedList705 {
//
//    private static final int MAX_LEN = 1000009;
//    private static final boolean[] DATA_FLAG = new boolean[MAX_LEN];
//
//    public LinkedList705() {
//    }
//
//    public void add(int key) {
//        DATA_FLAG[key] = true;
//    }
//
//    public void remove(int key) {
//        DATA_FLAG[key] = false;
//    }
//
//    public static boolean contains(int key) {
//        return DATA_FLAG[key];
//    }
//
//    public static void main(String[] args) {
//        System.err.println(contains(14));
//        System.err.println();
//    }
//}
