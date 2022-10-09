package com.niugiaogiao.binarytree.leetcode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 最大二叉树
 * <p>
 * 给定一个不重复的整数数组nums 。最大二叉树可以用下面的算法从nums 递归地构建:
 * <p>
 * 创建一个根节点，其值为nums 中的最大值。
 * 递归地在最大值左边的子数组前缀上构建左子树。
 * 递归地在最大值 右边 的子数组后缀上构建右子树。
 * 返回nums 构建的 最大二叉树 。
 * <p>
 * 链接：https://leetcode.cn/problems/maximum-binary-tree
 *
 * @author zihao
 */

public class BinaryTree654 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static TreeNode constructMaximumBinaryTree(int[] nums) {
        return run(0, nums.length, nums);
    }

    public static TreeNode run(int start, int end, int[] nums) {
        if (start >= end) {
            return null;
        }

        int max = -1;
        int maxPos = -1;
        for (int i = start; i < end; i++) {
            if (max < nums[i]) {
                max = nums[i];
                maxPos = i;
            }
        }

        TreeNode node = new TreeNode(max);
        node.left = run(start, maxPos, nums);
        node.right = run(maxPos + 1, end, nums);
        return node;
    }

    public static TreeNode constructMaximumBinaryTreeStack(int[] nums) {
        int n = nums.length;
        Deque<Integer> stack = new ArrayDeque<Integer>();
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(left, -1);
        Arrays.fill(right, -1);
        TreeNode[] tree = new TreeNode[n];
        for (int i = 0; i < n; ++i) {
            tree[i] = new TreeNode(nums[i]);
            while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
                right[stack.pop()] = i;
            }
            if (!stack.isEmpty()) {
                left[i] = stack.peek();
            }
            stack.push(i);
        }

        TreeNode root = null;
        for (int i = 0; i < n; ++i) {
            if (left[i] == -1 && right[i] == -1) {
                root = tree[i];
            } else if (right[i] == -1 || (left[i] != -1 && nums[left[i]] < nums[right[i]])) {
                tree[left[i]].right = tree[i];
            } else {
                tree[right[i]].left = tree[i];
            }
        }
        return root;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 1, 6, 0, 5};
        constructMaximumBinaryTreeStack(nums);
    }
}
