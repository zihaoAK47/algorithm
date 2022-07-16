package com.niugiaogiao.other;

import java.util.Arrays;

/**
 * @author zi hao
 * @version 1.0
 * @date 2022-04-25 13:05
 */
public class QuickSort {

    public static void quickSort(int[] num, int left, int right) {
        if (left > right) {
            return;
        }
        swap(num, left + (int) (Math.random() * (right - left + 1)), right);
        int[] array = getArray(num, left, right);
        quickSort(num, left, array[0] - 1);
        quickSort(num, array[1] + 1, right);
    }

    public static int[] getArray(int[] num, int left, int right) {
        if (left > right) {
            return new int[]{-1, -1};
        }
        if (left == right) {
            return new int[]{left, left};
        }

        int index = left - 1;
        int L = left;
        int R = right;
        while (L < R) {
            if (num[L] == num[right]) {
                L++;
            } else if (num[L] < num[right]) {
                swap(num, L++, ++index);
            } else if (num[L] > num[right]) {
                swap(num, L, --R);
            }
        }
        swap(num, R, right);
        return new int[]{index + 1, R};
    }

    public static void swap(int[] num, int i, int j) {
        int t = num[i];
        num[i] = num[j];
        num[j] = t;
    }

    public static void main(String[] args) {
        int[] num = new int[]{1, 2, 4, 5, 7, 0, 9};
        quickSort(num, 0, num.length - 1);
        System.err.println(Arrays.toString(num));
    }
}
