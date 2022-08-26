package com.niugiaogiao.tree.leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
            for (int i = 0 ; i < size && !queue.isEmpty() ; i++) {
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
}
