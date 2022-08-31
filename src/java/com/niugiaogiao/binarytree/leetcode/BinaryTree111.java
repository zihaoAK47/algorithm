package com.niugiaogiao.binarytree.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个二叉树，找出其最小深度
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量
 * 说明：叶子节点是指没有子节点的节点
 * https://leetcode.cn/problems/minimum-depth-of-binary-tree/
 */
public class BinaryTree111 {

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

    static int minDeep = Integer.MAX_VALUE;

    public static int minDepthDFS(TreeNode root) {
        if (root == null) {
            return 0;
        }
        deep(root, 1);
        return minDeep;
    }

    public static void deep(TreeNode node, int deep) {
        if (node == null) {
            return;
        }
        if (node.left == null && node.right == null) {
            minDeep = deep;
        } else {
            deep(node.left, deep + 1);
            deep(node.right, deep + 1);
        }
    }

    public int minDepthBFS(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int count = 0;
        while (!queue.isEmpty()) {
            count++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if (poll.left == null && poll.right == null) {
                    return count;
                }
                if (poll.left != null) {
                    queue.add(poll.left);
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                }
            }
        }

        return count;
    }

    public int minDepthBFS1(TreeNode node) {
        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            return 1;
        }
        int min = Integer.MAX_VALUE;
        if (node.left != null) {
            min = Math.min(min, minDepthBFS1(node.left));
        }
        if (node.right != null) {
            min = Math.min(min, minDepthBFS1(node.right));
        }

        return min + 1;
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t2.right = t5;

        System.err.println(minDeep);
    }
}
