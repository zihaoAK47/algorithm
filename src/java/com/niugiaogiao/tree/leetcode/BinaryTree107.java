package com.niugiaogiao.tree.leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 给你二叉树的根节点 root ，返回其节点值 自底向上的层序遍历 。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 * https://leetcode.cn/problems/binary-tree-level-order-traversal-ii/
 */
public class BinaryTree107 {

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

    public List<List<Integer>> levelOrderBottomBFS(TreeNode root) {
        LinkedList<List<Integer>> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> itemRes = new LinkedList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                itemRes.add(poll.val);
                if (poll.left != null) {
                    queue.add(poll.left);
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                }
            }
            res.addFirst(itemRes);
        }
        return res;
    }

    static LinkedList<List<Integer>> res = new LinkedList<>();

    public static List<List<Integer>> levelOrderBottomDFS(TreeNode root) {
        if (root == null) {
            return res;
        }
        run(root, 1);
        return res;
    }

    static int maxDeep = 0;
    public static void run(TreeNode node, int index) {
        if (node == null) {
            return;
        }
        if (res.size() < index) {
            res.addFirst(new LinkedList<>());
        }
        maxDeep = Math.max(maxDeep, index);
        run(node.left, index + 1);
        run(node.right, index + 1);
        res.get(maxDeep - index).add(node.val);
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);

        t1.left = t2;
        t1.right = t3;
//        t2.left = t4;
        t3.left = t5;
        levelOrderBottomDFS(t1);
        System.err.println(maxDeep);
    }
}
