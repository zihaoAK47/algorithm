package com.niugiaogiao.other;

import java.util.*;

/**
 * 无重复字符的最长子串
 * by:ZiHao
 * date:2020-07-22
 */

//p w   w   k   e   w
//0 1   1   2   3   1
//[0] => p = 1
//[1] => w = 4
//[3] => k = 3
//[4] => e = 4

//记录每个字符,分别出现的位置
//p 0
//w 1,2,5
//k 3
//e 4

//bbbbb
//p 0,1,2,3,4

//abcabcbb
//a 0,3
//b 1,4,6,7
//c 2,5

public class NoRepetitionSubString {

    public static void main(String[] args) {

//        String sourceStr = "bbbbbbbbbbbbbb";
//        Scanner in = new Scanner(System.in);
        String sourceStr = "pwwkew";
//                String sourceStr = "aaa";
//        String sourceStr = "''abcd";
//                String sourceStr = "aabc";
//        String sourceStr = "abcdefgbcdefghijklmn";//7
//        String sourceStr = "abcdefgbcdefgNhijklmn";
        //System.out.println(lengthOfLongestSubstring(sourceStr));
//        String sourceStr = "abcdefghijkldadf";
//        System.out.println(giao(sourceStr));
//
//        System.out.println(giao4(sourceStr));
//        String sourceStr = "aabc";
//          String sourceStr = "aaa";
        System.out.println(lengthOfLongestSubstring(sourceStr));
    }

    public static int lengthOfLongestSubstring(String s) {
        int len = s.length();
        if (0 == len) {
            return len;
        }

        Map<Character,Integer> hash = new HashMap<>();
        int start = 0;
        int end = 0;
        int result = 0;
        while (end < len) {
            char nowRead = s.charAt(end);
            Integer pos = hash.get(nowRead);
            if (null != pos && pos >= start) {
                start = pos + 1;
            }
            hash.put(nowRead,end++);
            result = Math.max(end - start,result);
        }

        return result;
    }

    public static int giao7(String s) {
        // 记录字符上一次出现的位置
        int[] last = new int[128];
        for(int i = 0; i < 128; i++) {
            last[i] = -1;
        }
        int n = s.length();

        int res = 0;
        int start = 0; // 窗口开始位置
        for(int i = 0; i < n; i++) {
            int index = s.charAt(i);
            start = Math.max(start, last[index] + 1);
            res   = Math.max(res, i - start + 1);
            last[index] = i;
        }

        return res;
    }

    /**
     * 解题思路,滑动窗口,用hash进行优化
     * 在进行前进的时候,首先判断该字符是否在hash表中出现
     * 如果出现,检测当前重复字符位置是否>=开始位置
     * 更新位置,以及更新hash表
     * @param s
     * @return
     */
    public static int giao6(String s) {
        Map<Character,Integer> count = new HashMap<>();

        int result = 0;
        int len = 0;

        int start = 0;
        int end = 0;
        int strLen = s.length();
        while (end < strLen) {

            char c = s.charAt(end);
            Integer pos = count.get(c);
            if (null != pos && pos >= start) {
                start = pos + 1;
                len = end - start;
            }

            len++;
            count.put(c,end++);
            result = Math.max(result,len);
        }

        return result;
    }

    /**
     * 自己写的
     * @param s
     * @return
     */
    public static int giao5(String s) {
        int max = 0;
        int number = 0;
        Map<Character,Integer> count = new HashMap<>();
        for (int i = 0 ; i < s.length() ; i ++) {
            Integer pos = count.get(s.charAt(i));
            if (null != pos) {
                if (max < number) {
                    max = number;
                }
                count.clear();
                i = pos + 1;
                number = 0;
            }
            number ++;
            count.put(s.charAt(i),i + 1);
        }
        max = (number > max) ? number: max;
        return max;
    }

    public static int giao4(String s) {
        int max = 0;
        int number = 0;
        Map<Character,Integer> count = new HashMap<>();
        for (int i = 0 ; i < s.length() ; i ++) {
            Integer pos = count.get(s.charAt(i));
            if (null != pos) {
                if (max < number) {
                    max = number;
                }
                count.clear();
                i = pos;
                number = 0;
            }
            number ++;
            count.put(s.charAt(i),i + 1);
        }
        max = (number > max) ? number: max;
        return max;
    }

    public static int giao2(String s) {
        int max = 0;
        int number = 0;
        Map<Character,Integer> count = new HashMap<>();
        for (int i = 0 ; i < s.length() ; i ++) {
            Integer pos = count.get(s.charAt(i));
            if (null != pos) {   //表示当前表已存在
                if (max < count.size()) {
                    max = count.size();
                }
                count.clear();
                i = pos;
            }
            number ++;
            count.put(s.charAt(i),i + 1);
        }
        max = (count.size() > max) ? count.size() : max;
        return max;
    }

    public static int giao(String s) {

        Map<Character,List<Integer>> count = new HashMap<>();
        for (int i = 0 ; i < s.length() ; i ++) {
            char c = s.charAt(i);
            if (count.containsKey(c)) {
                count.get(c).add(i);
            } else {
                List<Integer> number = new ArrayList<>();
                number.add(i);
                count.put(c,number);
            }
        }

        for (int i = 0 ; i < s.length() ; i ++) {

            List<Integer> result = count.get(s.charAt(i));
            System.out.println(s.charAt(i) + "," + result);
        }
        return 0;
    }


}
