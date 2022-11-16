package com.niugiaogiao.greedy.leetcode;

import java.util.*;

public class Greedy179 {

    static Set<Integer> pos = new HashSet<>();
    static List<String> res = new ArrayList<>();

    public static String largestNumber1(int[] nums) {
        run(nums, "");
        String max = "";
        for (String item : res) {
            if (max.compareTo(item) < 0) {
                max = item;
            }
        }
        return max;
    }

    public static void run(int[] num, String path) {
        if (pos.size() == num.length) {
            res.add(path);
            return;
        }

        for (int i = 0; i < num.length; ++i) {
            if (pos.contains(i)) {
                continue;
            }
            pos.add(i);
            run(num, path + num[i]);
            pos.remove(i);
        }
    }

    static class TestComparator implements Comparator<String> {
        @Override
        public int compare(String t1, String t2) {
            return (t1 + t2).compareTo(t2 + t1);
        }
    }

    public static String largestNumber(int[] nums) {
        String[] strNum = new String[nums.length];
        for (int i = 0; i < nums.length; ++i) {
            strNum[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(strNum, new TestComparator());
        if (strNum[0].equals("0")) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        for (String itemStr : strNum) {
            sb.append(itemStr);
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        int[] t = new int[]{10, 2};
        System.err.println(largestNumber1(t));
    }
}
