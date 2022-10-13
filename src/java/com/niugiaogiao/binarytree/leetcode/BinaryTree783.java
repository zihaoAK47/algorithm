package com.niugiaogiao.binarytree.leetcode;

import java.util.Stack;

/**
 * 二叉搜索树节点最小距离
 * <p>
 * 给你一个二叉搜索树的根节点 root ，返回 树中任意两不同节点值之间的最小差值
 * 差值是一个正数，其数值等于两值之差的绝对值
 *
 * @author zihao
 */
public class BinaryTree783 {

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

    static Integer pre = null;
    static Integer res = Integer.MAX_VALUE;
    public static int minDiffInBSTDFS(TreeNode root) {
        run(root);
        return res;
    }

    public static void run(TreeNode node) {
        if (node == null) return;
        run(node.left);
        if (pre != null) {
            res = Math.min(res, Math.abs(node.val - pre));
        }
        pre = node.val;
        run(node.right);
    }

    public static int minDiffInBSTBFS(TreeNode root) {
        Integer pre = null;
        int res = Integer.MAX_VALUE;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                TreeNode item = stack.pop();
                if (pre != null) {
                    res = Math.min(res,Math.abs(item.val - pre));
                }
                pre = item.val;
                root = item.right;
            }
        }

        return res;
    }


    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(27);
        TreeNode t2 = new TreeNode(34);
        TreeNode t3 = new TreeNode(58);
        TreeNode t4 = new TreeNode(50);
        TreeNode t5 = new TreeNode(44);
        t1.right = t2;
        t2.right = t3;
        t3.left = t4;
        t4.right = t5;
        System.err.println(minDiffInBSTBFS(t1));
    }
}
