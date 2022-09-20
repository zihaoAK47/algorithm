package com.niugiaogiao.binarytree.leetcode;

import java.util.Stack;

/**
 * 530. 二叉搜索树的最小绝对差
 * 给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值 。
 * 差值是一个正数，其数值等于两值之差的绝对值。
 * <p>
 * https://leetcode.cn/problems/minimum-absolute-difference-in-bst/
 *
 * @author zihao
 */

public class BinaryTree530 {

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

    static int min = Integer.MAX_VALUE;
    static int pre = -1;

    public static int getMinimumDifferenceDFS(TreeNode root) {
        if (root == null) return 0;
        getMin(root);
        return min;
    }

    public static void getMin(TreeNode node) {
        if (node == null) return;
        getMin(node.left);
        if (pre != -1) {
            min = Math.min(min, node.val - pre);
        }
        pre = node.val;
        getMin(node.right);
    }

    public static int getMinimumDifferenceBFS(TreeNode root) {
        if (root == null) return 0;

        Stack<TreeNode> stack = new Stack<>();
        int pre = -1;
        int min = Integer.MAX_VALUE;
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                TreeNode poll = stack.pop();
                if (pre != -1) {
                    min = Math.min(min, poll.val - pre);
                }
                pre = poll.val;
                root = poll.right;
            }
        }

        return min;
    }


    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(236);
        TreeNode t2 = new TreeNode(104);
        TreeNode t3 = new TreeNode(701);
        TreeNode t4 = new TreeNode(227);
        TreeNode t5 = new TreeNode(911);
        t1.left = t2;
        t1.right = t3;
        t2.right = t4;
        t3.right = t5;
    }
}
