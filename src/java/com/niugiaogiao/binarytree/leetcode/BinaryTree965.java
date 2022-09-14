package com.niugiaogiao.binarytree.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 如果二叉树每个节点都具有相同的值，那么该二叉树就是单值二叉树。
 * <p>
 * 只有给定的树是单值二叉树时，才返回 true；否则返回 false
 * <p>
 * https://leetcode.cn/problems/univalued-binary-tree/
 *
 * @author zihao
 */
public class BinaryTree965 {

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

    public boolean isUnivalTreeBFS(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int val = root.val;
        while(!queue.isEmpty()) {
            TreeNode item = queue.poll();
            if (item.val != val) {
                return false;
            }
            if (item.left != null) {
                queue.add(item.left);
            }
            if (item.right != null) {
                queue.add(item.right);
            }
        }

        return true;
    }

    int rootVal = 0;
    public boolean isUnivalTreeDFS(TreeNode root) {
        rootVal = root.val;
        return run(root);
    }

    public boolean run(TreeNode node) {
        if (node == null) return true;
        if (node.val != rootVal) return false;
        return run(node.left) && run(node.right);
    }
}
