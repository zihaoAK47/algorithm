package com.niugiaogiao.binarytree.leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 * https://leetcode.cn/problems/binary-tree-right-side-view/
 */
public class BinaryTree199 {

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

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            TreeNode end = null;
            for (int i = 0; i < size; i++) {
                end = queue.poll();
                if (end.left != null) {
                    queue.add(end.left);
                }
                if (end.right != null) {
                    queue.add(end.right);
                }
            }
            res.add(end.val);
        }

        return res;
    }

    List<Integer> res = new LinkedList<>();
    public List<Integer> rightSideViewDFS(TreeNode root) {
        if (root == null) {
            return res;
        }
        run(root, 0);
        return res;
    }

    public void run(TreeNode node, int deep) {
        if (node == null) {
            return;
        }

        if (res.size() == deep) {
            res.add(node.val);
        }
        run(node.right, deep + 1);
        run(node.left, deep + 1);
    }

}
