package com.niugiaogiao.binarytree.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点
 * https://leetcode.cn/problems/invert-binary-tree/
 */
public class BinaryTree226 {

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

    public TreeNode invertTreeBFS(TreeNode root) {
        if (root == null) {
            return root;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode itemNode = queue.poll();
            TreeNode temp = itemNode.left;
            itemNode.left = itemNode.right;
            itemNode.right = temp;
            if (itemNode.left != null) {
                queue.add(itemNode.left);
            }
            if (itemNode.right != null) {
                queue.add(itemNode.right);
            }
        }

        return root;
    }

    public TreeNode invertTreeDFS(TreeNode root) {
        if (root == null) {
            return null;
        }
        run(root);
        return root;
    }

    public void run(TreeNode node) {
        if (node == null) {
            return;
        }
        TreeNode t = node.left;
        node.left = node.right;
        node.right = t;
        invertTreeDFS(node.left);
        invertTreeDFS(node.right);
    }
}
