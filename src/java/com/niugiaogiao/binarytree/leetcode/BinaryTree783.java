package com.niugiaogiao.binarytree.leetcode;

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
    static Integer min = Integer.MAX_VALUE;
    public static int minDiffInBST(TreeNode root) {
        run(root);
        return min;
    }

    public static void run(TreeNode node) {
        if (node == null) return;
        run(node.left);
        if (pre == null) {
            pre = node.val;
        } else {
            min = Math.min(min, Math.abs(node.val - pre));
            pre = node.val;
        }
        run(node.right);
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
        System.err.println(minDiffInBST(t1));
    }
}
