package com.niugiaogiao.binarytree.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 二叉树的层平均值
 * <p>
 * https://leetcode.cn/problems/average-of-levels-in-binary-tree/
 *
 * @author zihao
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
            for (int i = 0; i < size; ++i) {
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

    List<Double> result = new ArrayList<>();
    List<Integer> count = new ArrayList<>();
    List<Double> avg = new ArrayList<>();
    public List<Double> averageOfLevelsDFS(TreeNode root) {
        if (root == null) return result;
        run(root, 0);
        for (int i = 0 ; i < result.size() ; ++i) {
            avg.add(result.get(i) / count.get(i));
        }
        return avg;
    }

    public void run(TreeNode root, int index) {
        if (root == null) return;
        if (index < result.size()) {
            result.set(index, result.get(index) + root.val);
            count.set(index, count.get(index) + 1);
        } else {
            result.add(root.val * 1.0);
            count.add(1);
        }

        run(root.left, index + 1);
        run(root.right, index + 1);
    }

    public static void main(String[] args) {
        List<Integer> list = new LinkedList<>();
        System.err.println(list.size());

        TreeNode t1 = new TreeNode(3);
        TreeNode t2 = new TreeNode(9);
        TreeNode t3 = new TreeNode(20);
        TreeNode t4 = new TreeNode(15);
        TreeNode t5 = new TreeNode(7);
        t1.left = t2;
        t1.right = t3;
        t3.left = t4;
        t3.right = t5;
    }
}
