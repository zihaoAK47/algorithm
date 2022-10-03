package com.niugiaogiao.binarytree.leetcode;

/**
 * 最长同值路径
 * 给定一个二叉树的 root ，返回 最长的路径的长度 ，这个路径中的 每个节点具有相同值 。 这条路径可以经过也可以不经过根节点。
 * <p>
 * 两个节点之间的路径长度 由它们之间的边数表示。
 * <p>
 * https://leetcode.cn/problems/longest-univalue-path/
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-09-30 6:39
 */
public class BinaryTree687 {

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

    public static int longestUnivaluePath(TreeNode root) {
        if (root == null) return 0;
        run(root);
        return max - 1;
    }

    static int max = 0;

    static class Info {
        int pathLen;
        int selfVal;

        Info(int pathLen, int self) {
            this.pathLen = pathLen;
            this.selfVal = self;
        }
    }

    public static int run(TreeNode node) {
        if (node == null) return 0;
        int left = run(node.left);
        int right = run(node.right);
        int left1 = 0;
        int right1 = 0;
        if (node.left != null && node.left.val == node.val) {
            left1 = left + 1;
        }
        if (node.right != null && node.right.val == node.val) {
            right1 = right + 1;
        }
        max = Math.max(max, left1 + right1);
        return Math.max(left1, right1);
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(1);
        TreeNode t3 = new TreeNode(1);
        TreeNode t4 = new TreeNode(1);
        TreeNode t5 = new TreeNode(1);
        TreeNode t6 = new TreeNode(1);
        t1.left = t2;
        t2.left = t3;
        t3.left = t4;
        t4.left = t5;
        t5.left = t6;
        System.err.println(longestUnivaluePath(t1));
    }

}
