package com.niugiaogiao.binarytree.leetcode;

/**
 * 给定二叉搜索树（BST）的根节点 root 和一个整数值 val。
 * <p>
 * 你需要在 BST 中找到节点值等于 val 的节点。 返回以该节点为根的子树。 如果节点不存在，则返回 null
 * <p>
 * 链接：https://leetcode.cn/problems/search-in-a-binary-search-tree
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-09-18 22:30
 */
public class BinaryTree700 {

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

    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) return null;

        while (root != null) {
            if (val == root.val) {
                return root;
            } else if (root.val < val) {
                root = root.right;
            } else {
                root = root.left;
            }
        }

        return null;
    }

    public TreeNode searchBSTDFS(TreeNode root, int val) {
        if (root == null) return null;
        if (root.val == val) return root;
        return searchBSTDFS(root.val < val ? root.right : root.left, val);
    }

}
