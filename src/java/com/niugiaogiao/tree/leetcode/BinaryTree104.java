package com.niugiaogiao.tree.leetcode;


import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个二叉树，找出其最大深度。
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * 就是求出二叉树最大深度基层
 * 说明: 叶子节点是指没有子节点的节点
 */
public class BinaryTree104 {

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

    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode currentEnd = root;
        TreeNode nextEnd = null;
        int deep = 1;
        while (!queue.isEmpty()) {
            TreeNode itemNode = queue.poll();
            if (itemNode.left != null) {
                queue.add(itemNode.left);
                nextEnd = itemNode.left;
            }
            if (itemNode.right != null) {
                queue.add(itemNode.right);
                nextEnd = itemNode.right;
            }
            if (currentEnd == itemNode) {
                // end
                deep++;
                currentEnd = nextEnd;
            }
        }

        return deep;
    }

    public static int maxDepth1(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int left = maxDepth1(root.left);
            int right = maxDepth1(root.right);
            return Math.max(left,right) + 1;
        }

    }
}
