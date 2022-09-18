package com.niugiaogiao.binarytree.leetcode;

import java.util.Stack;

/**
 * 给你一棵二叉搜索树的 root ，请你 按中序遍历 将其重新排列为一棵递增顺序搜索树
 * 使树中最左边的节点成为树的根节点，并且每个节点没有左子节点，只有一个右子节点。
 * <p>
 * 链接：https://leetcode.cn/problems/increasing-order-search-tree
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-09-18 22:20
 */
public class BinaryTree897 {


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


    TreeNode head = null;
    TreeNode pre = null;
    public TreeNode increasingBSTDFS(TreeNode root) {
        if (root == null) return null;
        run(root);
        return head;
    }

    public void run(TreeNode node) {
        if (node == null) return;

        run(node.left);

        if (head == null) {
            head = node;
            pre = node;
        } else {
            pre.right = node;
            pre = pre.right;
        }
        node.left = null;
        run(node.right);
    }

    public TreeNode increasingBSTBFS(TreeNode root) {
        if (root == null) return null;
        TreeNode res = null;
        TreeNode pre = null;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                TreeNode popNode = stack.pop();
                if (res == null) {
                    res = popNode;
                    pre = popNode;
                } else {
                    pre.right = popNode;
                    pre = pre.right;
                }
                popNode.left = null;
                root = popNode.right;
            }
        }

        return res;
    }

}
