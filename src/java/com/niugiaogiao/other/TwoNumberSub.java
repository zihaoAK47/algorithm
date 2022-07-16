package com.niugiaogiao.other;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zi hao
 * @version 1.0
 * @date 2020/10/29 19:38
 */
public class TwoNumberSub {

    /**
     * 给定一个整数数组 nums 和一个目标值 target
     * 请你在该数组中找出和为目标值的那两个整数,并返回他们的数组下标
     * 你可以假设每种输入只会对应一个答案,但是,数组中同一个元素不能使用两遍
     *
     * 给定 nums = [2, 7, 11, 15], target = 9
     * 因为 nums[0] + nums[1] = 2 + 7 = 9
     * 所以返回 [0, 1]
     */
    public static int[] twoSum1(int[] nums, int target) {
        if (null == nums || 0 >= nums.length) {
            return null;
        }

        int[] arrayPos = new int[2];
        for (int i = 0 ; i < nums.length; i ++) {
            for (int j = 0 ; j < nums.length ; j ++) {
                if (i != j && target == nums[i] + nums[j]) {
                    arrayPos[0] = i;
                    arrayPos[1] = j;
                    return arrayPos;
                }
            }
        }

        return arrayPos;
    }

    /**
     * 解题思路:
     *  1.暴力穷举
     *      时间复杂度: O(n2) n为数组的长度
     *      空间复杂度: O(1) 只用到常数个临时变量
     *      逐步匹配,两层循环外层 len - 1
     *  2.查表法
     *      思路:
     *          在遍历的同时,记录一些信息,以省去一层循环,这是以"空间换时间"的想法
     *          需要记录已经遍历过的数值和它所对应的下标,可以借助查找表实现
     *
     *     查找表有两个常用的实现
     *      哈希表
     *          key -> 数组元素
     *          value -> 存储数组所在下标
     *          nums[x] + nums[y] = target
     *
     *          target - nums[i] = key
     *          拿 key 去查表,如果hash key存在则说明 nums[i] + key = target 成立,取其下标返回,题解
     *     平衡二叉搜索树
     */
    public static int[] twoSum2(int[] nums,int target) {
        int len = nums.length;
        if (0 >= len) {
            return null;
        }

        /* 为了防止扩容,手动指定最大容量 */
        Map<Integer,Integer> hash = new HashMap<>(len - 1);
        for (int i = 0 ; i < len; i ++) {
            int key = target - nums[i];
            if (hash.containsKey(key)) {
                return new int[]{hash.get(key),i};
            }
            hash.put(nums[i],i);
        }

        return null;
    }

    public static int[] getArray(int[] nums,int target) {
        if (null != nums) {
            Map<Integer,Integer> hashNums = new HashMap<>();
            for (int i = 0 ; i < nums.length ; i ++) {
                Integer val = hashNums.get(target - nums[i]);
                if (val == null) {
                    hashNums.put(nums[i],i);
                } else {
                    return new int[]{val,i};
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int[] date = new int[]{3,2,4};
        System.out.println(Arrays.toString(twoSum2(date,7)));
    }
}
