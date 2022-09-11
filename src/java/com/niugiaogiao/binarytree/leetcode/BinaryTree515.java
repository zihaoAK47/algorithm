package com.niugiaogiao.binarytree.leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 给定一棵二叉树的根节点 root ，请找出该二叉树中每一层的最大值
 * <p>
 * https://leetcode.cn/problems/find-largest-value-in-each-tree-row/submissions/
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-09-11 22:00
 */
public class BinaryTree515 {

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

    public List<Integer> largestValuesBFS(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int size = 0;
        TreeNode item = null;
        Integer max = null;
        while (!queue.isEmpty()) {
            size = queue.size();
            max = null;
            for (int i = 0; i < size; i++) {
                item = queue.poll();
                max = max == null ? item.val : Math.max(max, item.val);
                if (item.left != null) {
                    queue.add(item.left);
                }
                if (item.right != null) {
                    queue.add(item.right);
                }
            }
            res.add(max);
        }

        return res;
    }

    List<Integer> res = new LinkedList<>();
    public List<Integer> largestValuesDFS(TreeNode root) {
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
        if (res.size() < deep + 1) {
            res.add(node.val);
        } else {
            res.set(deep, res.get(deep) == null ? node.val : Math.max(res.get(deep),node.val));
        }

        run(node.left, deep + 1);
        run(node.right, deep + 1);
    }
}
