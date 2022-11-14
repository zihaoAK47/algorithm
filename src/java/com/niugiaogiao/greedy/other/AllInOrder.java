package com.niugiaogiao.greedy.other;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * 全排列 - 给定一个由字符串组成的数组 strs
 * 必须把所有的字符串拼接起来，返回所有可能拼接结果中，字典序最小的结果
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-11-14 21:28
 */
public class AllInOrder {
    // a b c
    // a c b
    // b a c
    // b c a
    // c a b
    // c b a
    static String[] str = new String[5];

    static Set<Integer> pos = new HashSet<>();
    static List<String> res = new ArrayList<>();

    public static void run(String path) {
        if (pos.size() == str.length) {
            res.add(path);
            return;
        }

        for (int i = 0; i < str.length; ++i) {
            if (pos.contains(i)) {
                continue;
            }
            pos.add(i);
            run(path + str[i]);
            pos.remove(i);
        }
    }

    public static String allInResult() {
        run("");
        String maxRes = "";
        for (String re : res) {
            if (re.compareTo(maxRes) > 0) {
                maxRes = re;
            }
        }

        return maxRes;
    }

    public static String methodTwo() {
        Arrays.sort(str);
        String maxRes = "";
        for (String re : res) {
            if (re.compareTo(maxRes) > 0) {
                maxRes = re;
            }
        }

        return maxRes;
    }

    public static void create() {
        String dict = "abcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        for (int i = 0; i < str.length; ++i) {
            StringBuilder randStr = new StringBuilder();
            for (int j = 0; j < random.nextInt(7); j++) {
                randStr.append(dict.charAt(new Random().nextInt(dict.length())));
            }
            str[i] = new String(randStr);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1000; ++i) {
            create();
            String resA = allInResult();
            String resB = methodTwo();
            if (!resA.equals(resB)) {
                System.err.println("error  resA:" + resA + " resB" + resB);
            } else {
                System.err.println("resA: " + resA + "  resB: " + resB);
            }
        }
    }
}
