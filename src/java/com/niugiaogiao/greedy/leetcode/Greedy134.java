package com.niugiaogiao.greedy.leetcode;

/**
 * 134. 加油站
 * 在一条环路上有 n 个加油站，其中第 i 个加油站有汽油 gas[i] 升
 * <p>
 * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升
 * 你从其中的一个加油站出发，开始时油箱为空
 * <p>
 * 给定两个整数数组 gas 和 cost ，如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1
 * 如果存在解，则 保证 它是 唯一 的
 * <p>
 * https://leetcode.cn/problems/gas-station/
 *
 * @author zihao
 */

public class Greedy134 {

    // 暴露求解
    public static int canCompleteCircuit1(int[] gas, int[] cost) {
        for (int i = 0; i < gas.length; ++i) {
            if (gas[i] - cost[i] < 0) {
                continue;
            }
            int blank = gas[i] - cost[i];
            if (testRun(gas, cost, blank, i, i)) {
                return i;
            }
        }

        return -1;
    }

    public static boolean testRun(int[] gas, int[] cost, int blank, int start, int end) {
        while (true) {
            start = start + 1 >= gas.length ? 0 : start + 1;
            blank = gas[start] - cost[start] + blank;
            if (blank < 0) {
                return false;
            }
            if (start == end) {
                return true;
            }
        }
    }

    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int i = 0;
        while (i < n) {
            int cur = 0;
            int sumGas = 0;
            int sumCos = 0;
            while (cur < n) {
                int j = (i + cur) % n;
                sumCos += cost[j];
                sumGas += gas[j];
                if (sumCos > sumGas) {
                    break;
                }
                cur++;
            }

            if (cur == n) {
                return i;
            } else {
                i = cur + i + 1;
            }
        }

        return -1;
    }

    public int canCompleteCircuit2(int[] gas, int[] cost) {
        //假设油箱里的汽油可以为负数，找到最小的负数就是出发点

        int n = gas.length;
        int cur_gas = 0, min_gas = 0, min_index = 0;//默认从0出发

        for (int i = 0; i < n; i++) {
            cur_gas = cur_gas + gas[i] - cost[i];//走过了第i段路后邮箱里的油
            if (cur_gas < min_gas) {
                min_gas = cur_gas;
                min_index = i + 1;//这里i如果是n-1的话，说明当前汽油比0小，返回-1,不会返回错误的n。
            }
        }

        return cur_gas < 0 ? -1 : min_index;//油箱为负值返回-1；

    }

    public static void main(String[] args) {
        int[] gas = new int[]{1, 2, 3, 4, 5};
        int[] cost = new int[]{3, 4, 5, 1, 2};

//        int[] gas = new int[]{2, 3, 4};
//        int[] cost = new int[]{3, 4, 3};
        canCompleteCircuit(gas, cost);
    }
}
