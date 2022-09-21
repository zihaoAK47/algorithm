package com.niugiaogiao.binarytree.leetcode;

/**
 * 110. 平衡二叉树
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 * 本题中，一棵高度平衡二叉树定义为：一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1
 *
 * @author zihao
 */

public class BinaryTree110 {

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

    static boolean res = true;

    public static boolean isBalancedDFS1(TreeNode root) {
        run1(root);
        return res;
    }

    public static int run1(TreeNode node) {
        if (node == null) return 0;
        int leftHeight = run1(node.left);
        int rightHeight = run1(node.right);
        int maxHeight = Math.max(leftHeight, rightHeight);
        if (Math.abs(leftHeight - rightHeight) > 1) {
            res = false;
        }
        return maxHeight + 1;
    }

    public static boolean isBalancedDFS2(TreeNode root) {
        if (root == null) return true;

        return Math.abs(run2(root.left) - run2(root.right)) <= 1
                && isBalancedDFS2(root.left)
                && isBalancedDFS2(root.right);
    }

    public static int run2(TreeNode node) {
        if (node == null) return 0;

        return 1 + Math.max(run2(node.left), run2(node.right));
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        t1.left = t2;
        t2.left = t3;
        System.err.println(isBalancedDFS2(t1));
    }
}
