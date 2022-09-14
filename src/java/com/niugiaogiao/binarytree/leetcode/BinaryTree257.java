package com.niugiaogiao.binarytree.leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

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

    public static List<String> binaryTreePathsDFS1(TreeNode root) {
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

    public static String getPath(List<String> path) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < path.size() - 1; ++i) {
            stringBuilder.append(path.get(i)).append("->");
        }
        stringBuilder.append(path.get(path.size() - 1));
        return stringBuilder.toString();
    }

    List<String> res1 = new LinkedList<>();
    public List<String> binaryTreePathsDFS2(TreeNode root) {
        run(root,"");
        return res;
    }

    public void run(TreeNode node, String path) {
        if (node == null) return;

        StringBuilder sb = new StringBuilder(path);
        sb.append(node.val);
        if (node.left == null && node.right == null) {
            res.add(sb.toString());
        } else {
            sb.append("->");
            run(node.left, sb.toString());
            run(node.right, sb.toString());
        }
    }

    public static List<String> binaryTreePathsBFS(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<StringBuilder> path = new LinkedList<>();
        List<String> res = new LinkedList<>();
        queue.add(root);
        path.add(new StringBuilder());
        while (!queue.isEmpty()) {
            TreeNode itemNode = queue.poll();
            StringBuilder itemPath = path.poll();
            itemPath.append(itemNode.val);
            if (itemNode.left == null && itemNode.right == null) {
                res.add(itemPath.toString());
                continue;
            }
            itemPath.append("->");
            if (itemNode.left != null) {
                path.add(new StringBuilder(itemPath.toString()));
                queue.add(itemNode.left);
            }
            if (itemNode.right != null) {
                path.add(new StringBuilder(itemPath.toString()));
                queue.add(itemNode.right);
            }
        }

        return res;
    }


    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(5);
        t1.left = t2;
        t1.right = t3;
        t2.right = t4;
        binaryTreePathsBFS(t1);
        System.err.println();
    }
}
