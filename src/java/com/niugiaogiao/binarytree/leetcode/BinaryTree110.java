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

    public static boolean isBalanced(TreeNode root) {
        run(root);
        return res;
    }

    public static int run(TreeNode node) {
        if (node == null) return 0;
        int leftHeight = run(node.left);
        int rightHeight = run(node.right);
        int maxHeight = Math.max(leftHeight, rightHeight);
        if (Math.abs(leftHeight - rightHeight) > 1) {
            res = false;
        }
        return maxHeight + 1;
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        t1.left = t2;
        t2.left =t3;
        System.err.println(isBalanced(null));
    }
}
