package com.niugiaogiao.binarytree.leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树的层平均值
 * <p>
 * https://leetcode.cn/problems/average-of-levels-in-binary-tree/
 */

public class BinaryTree637 {

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

    public static List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new LinkedList<>();
        if (root == null) return res;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            double sum = 0;
            for (int i = 0 ; i < size ; ++i) {
                TreeNode item = queue.poll();
                sum += item.val;
                if (item.left != null) {
                    queue.add(item.left);
                }
                if (item.right != null) {
                    queue.add(item.right);
                }
            }
            res.add(sum / size);
        }

        return res;
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(3);
        TreeNode t2 = new TreeNode(9);
        TreeNode t3 = new TreeNode(20);
        TreeNode t4 = new TreeNode(15);
        TreeNode t5 = new TreeNode(7);
        t1.left = t2;
        t1.right = t3;
        t3.left = t4;
        t3.right = t5;
        averageOfLevels(t1);
    }
}
