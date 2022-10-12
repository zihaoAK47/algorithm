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

    static int min = Integer.MAX_VALUE;
    // 选择第一个小的数
    // 选择第二个小的数
    static Integer minA = null;
    static Integer minB = null;

    public static int minDiffInBST(TreeNode root) {
        run(root);
        System.err.println(minA);
        System.err.println(minB);
        return 0;
    }

    public static void run(TreeNode node) {
        if (node == null) return;
        run(node.left);
        run(node.right);
        if (minA == null) {
            minA = node.val;
            return;
        } else if (minB == null) {
            minB = node.val;
            return;
        }
        if (node.val < minA) {
            minA = node.val;
            return;
        }
        if (node.val < minB) {
            minB = node.val;
        }
    }

    public static void main(String[] args) {
//        TreeNode t1 = new TreeNode(4);
//        TreeNode t2 = new TreeNode(2);
//        TreeNode t3 = new TreeNode(6);
//        TreeNode t4 = new TreeNode(1);
//        TreeNode t5 = new TreeNode(3);
//        t1.left = t2;
//        t1.right = t3;
//        t2.left = t4;
//        t2.right = t5;
        TreeNode t1 = new TreeNode();
        TreeNode t2 = new TreeNode();
        TreeNode t3 = new TreeNode();
        TreeNode t4 = new TreeNode();
        TreeNode t5 = new TreeNode();

        minDiffInBST(t1);
    }
}
