package com.niugiaogiao.binarytree.leetcode;

import java.util.LinkedList;
import java.util.List;

public class BinaryTree257 {

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

    public static List<String> binaryTreePaths(TreeNode root) {
        runDfs(root);
        return null;
    }

    static List<String> res = new LinkedList<>();

    static List<String> temp = new LinkedList<>();

    public static void runDfs(TreeNode node) {
        if (node == null) return;
        temp.add(node.val + "");
        runDfs(node.left);
        runDfs(node.right);

        if (node.left == null && node.right == null) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < temp.size() - 1; i++) {
                stringBuilder.append(temp.get(i)).append("->");
            }
            stringBuilder.append(temp.get(temp.size() - 1));
            res.add(stringBuilder.toString());
        }
        temp.remove(temp.size() - 1);
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(5);
        t1.left = t2;
        t1.right = t3;
        t2.right = t4;
        binaryTreePaths(t1);
        System.err.println();
    }
}
