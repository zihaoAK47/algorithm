package com.niugiaogiao.tree.leetcode;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 给你二叉树的根节点 root ，返回其节点值的 锯齿形层序遍历 。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-08-27 20:27
 */
public class BinaryTree103 {

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

    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean flag = true;
        while (!queue.isEmpty()) {
            List<Integer> itemNodes = new LinkedList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = flag ? queue.pollFirst() : queue.pollLast();
                itemNodes.add(treeNode.val);
                if (flag) {
                    if (treeNode.left != null) {
                        queue.addLast(treeNode.left);
                    }
                    if (treeNode.right != null) {
                        queue.addLast(treeNode.right);
                    }
                } else {
                    if (treeNode.right != null) {
                        queue.addFirst(treeNode.right);
                    }
                    if (treeNode.left != null) {
                        queue.addFirst(treeNode.left);
                    }
                }
            }
            flag = !flag;
            res.add(itemNodes);
        }

        return res;
    }

    static List<List<Integer>> res = new LinkedList<>();

    public static List<List<Integer>> zigzagLevelOrder1(TreeNode root) {
        if (root == null) {
            return res;
        }
        run(root, 1, true);
        return res;
    }

    public static void run(TreeNode node, int deep, boolean flag) {
        if (node == null) {
            return;
        }
        if (res.size() < deep) {
            res.add(deep - 1, new LinkedList<>());
        }
        if (flag) {
            ((LinkedList<Integer>) res.get(deep - 1)).addLast(node.val);
        } else {
            ((LinkedList<Integer>) res.get(deep - 1)).addFirst(node.val);
        }
        run(node.left, deep + 1, !flag);
        run(node.right, deep + 1, !flag);
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
        t3.right = t5;
        zigzagLevelOrder(t1);
    }
}
