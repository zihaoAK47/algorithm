package com.niugiaogiao.binarytree.leetcode;

import com.niugiaogiao.binarytree.struct.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * LCP 67. 装饰树
 * https://leetcode.cn/problems/KnLfVT/
 * 力扣嘉年华上的 DIY 手工展位准备了一棵缩小版的 二叉 装饰树 root 和灯饰，你需要将灯饰逐一插入装饰树中，要求如下：
 * <p>
 * 完成装饰的二叉树根结点与 root 的根结点值相同
 * 若一个节点拥有父节点，则在该节点和他的父节点之间插入一个灯饰（即插入一个值为 -1 的节点）。具体地：
 * 在一个 父节点 x 与其左子节点 y 之间添加 -1 节点， 节点 -1、节点 y 为各自父节点的左子节点，
 * 在一个 父节点 x 与其右子节点 y 之间添加 -1 节点， 节点 -1、节点 y 为各自父节点的右子节点，
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-12-10 23:19
 */
public class BinaryTree67 {

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

    public TreeNode expandBinaryTree(TreeNode root) {
        if (root != null) {
            if (root.left != null) {
                root.left = new TreeNode(-1, expandBinaryTree(root.left), null);
            }
            if (root.right != null) {
                root.right = new TreeNode(-1, null, expandBinaryTree(root.right));
            }
        }
        return root;
    }

    // 实现方式2

    public TreeNode expandBinaryTreeBFS(TreeNode root) {
        if (root == null) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode tempNode;
        TreeNode newNode;
        while (!queue.isEmpty()) {
            TreeNode itemNode = queue.poll();
            if (itemNode.left == null && itemNode.right == null) {
                continue;
            }
            if (itemNode.left != null) {
                tempNode = itemNode.left;
                newNode = new TreeNode(-1);
                newNode.left = tempNode;
                itemNode.left = newNode;
                queue.add(newNode.left);
            }
            if (itemNode.right != null) {
                tempNode = itemNode.right;
                newNode = new TreeNode(-1);
                newNode.right = tempNode;
                itemNode.right = newNode;
                queue.add(newNode.right);
            }
        }

        return root;
    }

    // 实现方式3
    public TreeNode expandBinaryTree3(TreeNode root) {
        if (root == null) {
            return null;
        }
        run(root);
        return root;
    }

    public void run(TreeNode node) {
        if (node == null) return;
        if (node.left == null && node.right == null) return;
        if (node.left != null) {
            TreeNode tempNode = node.left;
            TreeNode newNode = new TreeNode(-1);
            node.left = newNode;
            newNode.left = tempNode;
            run(newNode.left);
        }
        if (node.right != null) {
            TreeNode tempNode = node.right;
            TreeNode newNode = new TreeNode(-1);
            node.right = newNode;
            newNode.right = tempNode;
            run(newNode.right);
        }
    }
}
