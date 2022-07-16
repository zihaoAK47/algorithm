package com.niugiaogiao.other;

/**
 * @author zi hao
 * @version 1.0
 * @date 2022-04-23 10:38
 */
public class Exor {

    //    寻找数组中出现两个奇数次数的数
    public static void main1(String[] args) {
        int[] number = new int[]{1, 1, 2, 2, 3, 3, 4, 4, 4, 5, 5, 5};
        int ex = 0;
        for (int item : number) {
            ex ^= item;
        }

        // a ^ b 一定不相等，找到某一位为 1 的数
        int t = ex & (~ex + 0x1);
        int ex1 = 0;
        for (int item : number) {
            if ((item & t) != 0) {
                ex1 ^= item;
            }
        }

        System.err.println(ex1 + "---" + (ex ^ ex1));
    }

    // 一个数二进制有多少个1
    public static void main(String[] args) {
        int num = 10;
        int count = 0;
        while (num != 0) {
            num ^= num & (~num + 1);
            count++;
        }
        System.err.println(count);
    }

}
