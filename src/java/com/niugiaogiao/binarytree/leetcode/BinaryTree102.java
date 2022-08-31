package com.niugiaogiao.binarytree.leetcode;

import java.util.*;

/**
 * 给你二叉树的根节点 root ，返回其节点值的 层序遍历 。 （即逐层地，从左到右访问所有节点）
 *
 * @author zihao
 */
public class BinaryTree102 {

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

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> itemResult = new LinkedList<>();
            int size = queue.size();
            for (int i = 0; i < size && !queue.isEmpty(); i++) {
                TreeNode nodeItem = queue.poll();
                itemResult.add(nodeItem.val);
                if (nodeItem.left != null) {
                    queue.add(nodeItem.left);
                }
                if (nodeItem.right != null) {
                    queue.add(nodeItem.right);
                }
            }
            result.add(itemResult);
        }
        return result;
    }

    static List<List<Integer>> res = new ArrayList<>();

    public static List<List<Integer>> levelOrder1(TreeNode root) {
        if (root == null) {
            return res;
        }
        run(root, 1);
        return res;
    }

    public static void run(TreeNode node, int index) {
        if (node == null) {
            return;
        }
        if (res.size() < index) {
            res.add(index - 1, new ArrayList<>());
        }
        res.get(index - 1).add(node.val);
        run(node.left, index + 1);
        run(node.right, index + 1);
    }

    public static void main(String[] args) {
        TreeNode r1 = new TreeNode(1);
        TreeNode r2 = new TreeNode(2);
        TreeNode r3 = new TreeNode(3);
        TreeNode r4 = new TreeNode(4);
        TreeNode r5 = new TreeNode(5);
        TreeNode r6 = new TreeNode(6);
        TreeNode r7 = new TreeNode(7);

        r1.left = r2;
        r1.right = r3;
        r2.left = r4;
        r2.right = r5;
        r3.left = r6;
        r3.right = r7;
        levelOrder1(r1);
        System.err.println("");
    }
}
