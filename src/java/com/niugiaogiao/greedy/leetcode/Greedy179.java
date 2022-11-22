package com.niugiaogiao.greedy.leetcode;

import java.util.*;

/**
 * 给定一组非负整数 nums，重新排列每个数的顺序（每个数不可拆分）使之组成一个最大的整数。
 * <p>
 * 注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
 * https://leetcode.cn/problems/largest-number/
 *
 * @author zihao
 */

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

    public static String largestNumber2(int[] nums) {
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

    public static String largestNumber3(int[] nums) {
        int n = nums.length;
        Integer[] numArr = new Integer[n];
        for (int i = 0; i < n; ++i) {
            numArr[i] = nums[i];
        }

        Arrays.sort(numArr, (x, y) -> {
            long sx = 10, sy = 10;
            while (sx <= x) {
                sx *= 10;
            }
            while (sy <= y) {
                sy *= 10;
            }
            return (int) (-sy * x - y + sx * y + x);
        });

        if (numArr[0] == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        for (int num : numArr) {
            sb.append(num);
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        int[] t = new int[]{10, 2};
        System.err.println(largestNumber3(t));
    }
}
