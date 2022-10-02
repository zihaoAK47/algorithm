package com.niugiaogiao.binarytree.leetcode;

/**
 * 二叉树的直径
 * <p>
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
 * https://leetcode.cn/problems/diameter-of-binary-tree/
 */
public class BinaryTree543 {

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

    int max = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        run(root);
        return max;
    }

    public int run(TreeNode node) {
        if (node == null) return 0;
        int L = run(node.left);
        int R = run(node.right);
        max = Math.max(L + R, max);
        return Math.max(L, R) + 1;
    }

}
