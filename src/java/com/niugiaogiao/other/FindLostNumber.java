package com.niugiaogiao.other;

import java.util.Arrays;

/**
 * @author zi hao
 * @version 1.0
 * @date 2021/3/18 14:05
 *
 * 找到丢失的数字
 */
public class FindLostNumber {

    public static void main(String[] args) {

        int[] data = {3,4,1,2,5,8,6,9};
        int res = 0;
        Arrays.sort(data);
        for (int i = data[0] ; i < data.length; ++i) {
            res ^= i;
            res ^= data[i - 1];
        }
        System.out.println(res ^ data.length);
    }
}
