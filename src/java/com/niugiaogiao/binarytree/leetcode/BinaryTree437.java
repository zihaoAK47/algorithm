package com.niugiaogiao.binarytree.leetcode;

/**
 * 给定一个二叉树的根节点 root，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
 * 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 * <p>
 * 链接：https://leetcode.cn/problems/path-sum-iii
 *
 * @author zihao
 */
public class BinaryTree437 {

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

    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) return 0;
        return run(root, targetSum) + pathSum(root.left, targetSum) + pathSum(root.right, targetSum);
    }

    public  int run(TreeNode root, long targetSum) {
        if (root == null) return 0;
        return ((targetSum == root.val) ? 1 : 0)
                + run(root.left, targetSum - root.val)
                + run(root.right, targetSum - root.val);
    }


    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(10);
        TreeNode t2 = new TreeNode(5);
        TreeNode t3 = new TreeNode(-3);
        TreeNode t4 = new TreeNode(3);
        TreeNode t5 = new TreeNode(2);
        TreeNode t6 = new TreeNode(11);
        TreeNode t7 = new TreeNode(3);
        TreeNode t8 = new TreeNode(-2);
        TreeNode t9 = new TreeNode(1);
        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t2.right = t5;
        t4.left = t7;
        t4.right = t8;
        t5.right = t9;
        t3.right = t6;
    }

}
