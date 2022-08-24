package com.niugiaogiao.tree.leetcode;

import java.util.Deque;
import java.util.LinkedList;

public class BinaryTree101 {

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

    public static boolean isSymmetric(TreeNode root) {
        Deque<TreeNode> left = new LinkedList<>();
        Deque<TreeNode> right = new LinkedList<>();
        left.addFirst(root);
        right.addFirst(root);
        while (!left.isEmpty() && !right.isEmpty()) {
            TreeNode leftNode = left.poll();
            TreeNode rightNode = right.poll();
            if (leftNode == null && rightNode != null || leftNode != null && rightNode == null) {
                return false;
            }
            // 左轴不对称
            if (leftNode.left == null && rightNode.right != null || leftNode.left != null && rightNode.right == null) {
                return false;
            }
            // 右轴不对称
            if (leftNode.right == null && rightNode.left != null || leftNode.right != null && rightNode.left == null) {
                return false;
            }
            if (leftNode.left != null && rightNode.right != null) {
                left.add(leftNode.left);
                right.add(rightNode.right);
            }
            if (leftNode.right != null && rightNode.left != null) {
                left.add(leftNode.right);
                right.add(rightNode.left);
            }
        }

        return left.isEmpty() && right.isEmpty();
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode();
        TreeNode t3 = new TreeNode();
        TreeNode t4 = new TreeNode();
        TreeNode t5 = new TreeNode();
        TreeNode t6 = new TreeNode();
        TreeNode t7 = new TreeNode();

        System.err.println(isSymmetric(t1));
    }
}
