package com.niugiaogiao.binarytree.leetcode;

import java.util.*;

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

    public static TreeNode constructMaximumBinaryTreeStack1(int[] nums) {
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

    public static TreeNode constructMaximumBinaryTreeStack2(int[] nums) {
        // 对于当前节点 i 寻找到左边第一个比他大的数 和 右边第一个比他大的数
        int len = nums.length;
        int[] leftRes = new int[len];
        int[] rightRes = new int[len];
        TreeNode[] nodes = new TreeNode[len];
        Arrays.fill(leftRes, -1);
        Arrays.fill(rightRes, -1);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < len; ++i) {
            nodes[i] = new TreeNode(nums[i]);
            while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
                // 破坏了栈的单调性
                rightRes[stack.pop()] = i;
            }
            // 栈顶元素是当前元素左边的第一个最大值
            if (!stack.isEmpty()) {
                leftRes[i] = stack.peek();
            }
            stack.push(i);
        }
        TreeNode root = null;
        for (int i = 0; i < len; ++i) {
            if (leftRes[i] == -1 && rightRes[i] == -1) {
                root = nodes[i];
            } else if (rightRes[i] == -1 || (leftRes[i] != -1 && nums[leftRes[i]] < nums[rightRes[i]])) {
                nodes[leftRes[i]].right = nodes[i];
            } else {
                nodes[rightRes[i]].left = nodes[i];
            }
        }

        return root;
    }

    // 单调递减，当前元素 i ，栈顶元素是 i 左边第一个比他大的数
    // 若当前 i 破坏了单调性则当前 i 是栈中所有元素右边第一个比他大的数
    public static TreeNode constructMaximumBinaryTree3(int[] nums) {
        int len = nums.length;
        TreeNode[] nodes = new TreeNode[len];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < len; ++i) {
            nodes[i] = new TreeNode(nums[i]);
            while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
                nodes[i].left = nodes[stack.pop()];
            }
            if (!stack.isEmpty()) {
                nodes[stack.peek()].right = nodes[i];
            }
            stack.push(i);
        }

        return nodes[stack.get(0)];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 1, 6, 0, 5};
        constructMaximumBinaryTree3(nums);
    }
}
