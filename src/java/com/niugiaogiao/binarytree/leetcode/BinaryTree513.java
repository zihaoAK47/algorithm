package com.niugiaogiao.binarytree.leetcode;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个二叉树的 根节点 root，请找出该二叉树的 最底层 最左边 节点的值。
 * <p>
 * 假设二叉树中至少有一个节点
 * <p>
 * https://leetcode.cn/problems/find-bottom-left-tree-value/
 *
 * @author zihao
 */
public class BinaryTree513 {

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

    public int findBottomLeftValueBFS(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int val = root.val;
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean flag = true;
            for (int i = 0; i < size; i++) {
                TreeNode item = queue.poll();
                if (flag && item.left != null) {
                    val = item.left.val;
                    flag = false;
                } else if (flag && item.right != null) {
                    val = item.right.val;
                    flag = false;
                }
                if (item.left != null) {
                    queue.add(item.left);
                }
                if (item.right != null) {
                    queue.add(item.right);
                }
            }
        }

        return val;
    }

    public int findBottomLeftValueBFS1(TreeNode root) {
        int ret = 0;
        Queue<TreeNode> queue = new ArrayDeque<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode p = queue.poll();
            if (p.right != null) {
                queue.offer(p.right);
            }
            if (p.left != null) {
                queue.offer(p.left);
            }
            ret = p.val;
        }
        return ret;
    }


    int curHeight = 0;
    int curVal = 0;
    public int findBottomLeftValueDFS(TreeNode root) {
        run(root, 0);
        return curVal;
    }

    public void run(TreeNode node, int height) {
        if (node == null) return;

        height++;
        run(node.left, height);
        run(node.right, height);
        if (height > curHeight) {
            curHeight = height;
            curVal = node.val;
        }
    }
}
