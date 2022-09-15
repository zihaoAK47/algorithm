package com.niugiaogiao.binarytree.leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

/**
 * 请考虑一棵二叉树上所有的叶子，这些叶子的值按从左到右的顺序排列形成一个 叶值序列
 * https://leetcode.cn/problems/leaf-similar-trees/
 *
 * @author zihao
 */
public class BinaryTree872 {

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

    public static boolean leafSimilar(TreeNode root1, TreeNode root2) {
        String r1 = getLeavesVal(root1);
        String r2 = getLeavesVal(root2);
        System.err.println(r1);
        System.err.println(r2);

        return r1.equals(r2);
    }

    public static String getLeavesVal(TreeNode node) {
        Queue<TreeNode> queue = new LinkedList<>();
        StringBuilder stringBuilder = new StringBuilder();
        queue.add(node);
        while (!queue.isEmpty()) {
            TreeNode item = queue.poll();
            if (item.left == null && item.right == null) {
                stringBuilder.append(item.val);
            }
            if (item.left != null) {
                queue.add(item.left);
            }
            if (item.right != null) {
                queue.add(item.right);
            }
        }

        return stringBuilder.toString();
    }

    public static boolean leafSimilarDFS(TreeNode root1, TreeNode root2) {
        List<Integer> res1 = new LinkedList<>();
        run(root1, res1);
        List<Integer> res2 = new LinkedList<>();
        run(root2, res2);

        if(res1.size() == res2.size()) {
            boolean flag = false;
            for (int i = 0; i < res1.size(); ++i) {
                if (!Objects.equals(res1.get(i), res2.get(i))) {
                    return false;
                }
            }

            return true;
        }
        return false;
    }

    public static void run(TreeNode root, List<Integer> res) {
        if (root == null) return;
        if (root.left == null && root.right == null) res.add(root.val);
        run(root.left, res);
        run(root.right, res);
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(3);
        TreeNode t2 = new TreeNode(5);
        TreeNode t3 = new TreeNode(1);
        TreeNode t4 = new TreeNode(6);
        TreeNode t5 = new TreeNode(2);
        TreeNode t6 = new TreeNode(9);
        TreeNode t7 = new TreeNode(8);
        TreeNode t8 = new TreeNode(7);
        TreeNode t9 = new TreeNode(14);
        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t2.right = t5;
        t5.left = t8;
        t5.right = t9;
        t3.left = t6;
        t3.right = t7;

        TreeNode r1 = new TreeNode(3);
        TreeNode r2 = new TreeNode(5);
        TreeNode r3 = new TreeNode(1);
        TreeNode r4 = new TreeNode(6);
        TreeNode r5 = new TreeNode(71);
        TreeNode r6 = new TreeNode(4);
        TreeNode r7 = new TreeNode(2);
        TreeNode r8 = new TreeNode(9);
        TreeNode r9 = new TreeNode(8);
        r1.left = r2;
        r1.right = r3;
        r2.left = r4;
        r2.right = r5;
        r3.left = r6;
        r3.right = r7;
        r7.left = r8;
        r7.right = r9;

//        System.err.println(leafSimilar(t1, r1));
        System.err.println(leafSimilarDFS(t1, r1));
    }
}
