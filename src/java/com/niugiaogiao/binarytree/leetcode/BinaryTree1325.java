package com.niugiaogiao.binarytree.leetcode;

/**
 * 删除给定值的叶子节点
 * <p>
 * <p>
 * 给你一棵以 root 为根的二叉树和一个整数 target ，请你删除所有值为 target 的 叶子节点 。
 * <p>
 * 注意，一旦删除值为 target 的叶子节点，它的父节点就可能变成叶子节点；如果新叶子节点的值恰好也是 target ，那么这个节点也应该被删除。
 * <p>
 * 也就是说，你需要重复此过程直到不能继续删除。
 * <p>
 * 链接：https://leetcode.cn/problems/delete-leaves-with-a-given-value
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-09-22 21:09
 */
public class BinaryTree1325 {

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

    public static TreeNode removeLeafNodes(TreeNode root, int target) {
        if (root == null) {
            return null;
        }

        return removeNode(root, target);
    }

    public static TreeNode removeNode(TreeNode node, int target) {
        if (node == null) return null;

        TreeNode l1 = removeNode(node.left, target);
        TreeNode l2 = removeNode(node.right, target);
        node.left = l1;
        node.right = l2;
        if (node.left == null && node.right == null && node.val == target) {
            return null;
        }

        return node;
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(2);
        TreeNode t5 = new TreeNode(2);
        TreeNode t6 = new TreeNode(4);
        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t3.left = t5;
        t3.right = t6;
        removeLeafNodes(t1, 2);
    }
}
