package com.niugiaogiao.other;


import java.util.Arrays;

/**
 * @author zi hao
 * @version 1.0
 * @date 2022-04-24 9:39
 */
public class MergeSort {

    public static void sortArray(int[] num, int left, int mid, int right) {
        // 左边 和 右边 对比
        int Z = 0;
        int L = left;
        int R = mid + 1;
        int[] temp = new int[right - left + 1];
        while (L <= mid && R <= right) {
            temp[Z++] = num[L] <= num[R] ? num[L++] : num[R++];
        }

        while (L <= mid) {
            temp[Z++] = num[L++];
        }

        while (R <= right) {
            temp[Z++] = num[R++];
        }

        for (Z = 0; Z < temp.length; Z++) {
            num[left + Z] = temp[Z];
        }
    }

    public static void merge(int[] num, int left, int right) {
        if (left == right) {
            return;
        }
        int mid = left + ((right - left) >> 1);
        merge(num, left, mid);
        merge(num, mid + 1, right);
        sortArray(num, left, mid, right);
    }

    // loop
    public static void loopMerge(int[] num) {
        if (num == null || num.length == 0)
            return;

        int N = 1;
        int len = num.length;
        while (N < len) {
            int L = 0;
            while (L < len) {
                int M = N + L - 1;
                if (M >= len)
                    break;

                int R = Math.min(M + N, len - 1);
                sortArray(num, L, M, R);
                L = R + 1;
            }

            if (N > (len >> 1))
                break;

            N <<= 1;
        }
    }

    public static void main(String[] args) {
        int[] num = new int[]{4, 2, 1, 6, 7, 9, 1, 0};
//        merge(num, 0, num.length - 1);
        loopMerge(num);
        System.err.println(Arrays.toString(num));
    }
}

//    public static void merge(int[] arr, int left, int mid, int right) {
//        int i = left;
//        int j = mid + 1;
//        int z = 0;
//        int[] temp = new int[right - left + 1];
//        while (i <= mid && j <= right)
//            temp[z++] = (arr[i] >= arr[j]) ? arr[j++] : arr[i++];
//
//        while (i <= mid)
//            temp[z++] = arr[i++];
//
//        while (j <= right)
//            temp[z++] = arr[j++];
//
//        for (z = 0; z < temp.length; z++)
//            arr[left + z] = temp[z];
//    }
//
//    public static int run(int[] arr, int left, int right) {
//        if (left == right)
//            return 0;
//
//        int mid = left + ((right - left) >> 1);
//        return run(arr, left, mid) + run(arr, mid + 1, right) + mergeNew(arr, left, mid, right);
//    }
//
//    public static int mergeNew(int[] arr, int left, int mid, int right) {
//        int[] temp = new int[right - left + 1];
//        int z = 0;
//        int i = left;
//        int j = mid + 1;
//        int res = 0;
//        // 发现
//        while (i <= mid && j <= right) {
//            res += arr[i] < arr[j] ? (right - j + 1) * arr[i] : 0;
//            temp[z++] = arr[i] < arr[j] ? arr[i++] : arr[j++];
//        }
//
//        while (i <= mid)
//            temp[z++] = arr[i++];
//
//        while (j <= right)
//            temp[z++] = arr[j++];
//
//        for (z = 0; z < temp.length; z++) {
//            arr[left + z] = temp[z];
//        }
//
//        return res;
//    }
//
//    public static void goMerge(int[] arr, int L, int M, int R) {
//        int[] temp = new int[R - L + 1];
//        int i = L;
//        int j = M + 1;
//        int z = 0;
//        while (i <= M && j <= R)
//            temp[z++] = arr[i] < arr[j] ? arr[i++] : arr[j++];
//
//        while (i <= L)
//            temp[z++] = arr[i++];
//
//        while (j <= R)
//            temp[z++] = arr[j++];
//
//        for (z = 0; z < temp.length; z++)
//            arr[L + z] = temp[z];
//    }
//
//    // 非递归版本
//    public static void go(int[] arr, int left, int right) {
//        if (null == arr || arr.length < 2) {
//            return;
//        }
//        int len = arr.length;
//        int N = 1;
//        while (N < len) {
//            int L = 0;
//            while (L < len) {
//                int M = L + N - 1;
//                if (M >= len) {
//                    break;
//                }
//                int R = Math.min(M + N, len - 1);
//                goMerge(arr, L, M, R);
//                L = R + 1;
//            }
//            if (N > (len >> 1))
//                break;
//            N <<= 1;
//        }
//    }
//
//    public static void main(String[] args) {
////        int[] data = new int[]{1, 3, 4, 2, 6, 9, 7, 8, 0};
//        int[] data = new int[]{1, 3, 4, 2, 5};
////        run(data, 0, data.length - 1);
////        go(data, 0, data.length);
////        System.err.println(Arrays.toString(data));
//        System.err.println(run(data, 0, data.length - 1));
//    }