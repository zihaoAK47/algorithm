package com.niugiaogiao.binarytree.leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 给出一棵二叉树，其上每个结点的值都是 0 或 1 。每一条从根到叶的路径都代表一个从最高有效位开始的二进制数
 * <p>
 * 例如，如果路径为 0 -> 1 -> 1 -> 0 -> 1，那么它表示二进制数 01101，也就是 13
 * 对树上的每一片叶子，我们都要找出从根到该叶子的路径所表示的数字
 * <p>
 * 返回这些数字之和。题目数据保证答案是一个 32 位 整数
 * <p>
 * 链接：https://leetcode.cn/problems/sum-of-root-to-leaf-binary-numbers
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-09-19 22:05
 */
public class BinaryTree1022 {

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

    public static int sumRootToLeaf(TreeNode root) {
        run(root);
        return res;
    }

    static List<Integer> saveNode = new LinkedList<>();
    static int res = 0;

    public static void run(TreeNode root) {
        if (root == null) return;
        saveNode.add(root.val);
        run(root.left);
        run(root.right);
        if (root.left == null && root.right == null) {
            StringBuilder stringBuilder = new StringBuilder();
            for (Integer item : saveNode) {
                stringBuilder.append(item);
            }
            res += Integer.parseInt(stringBuilder.toString(), 2);
        }
        saveNode.remove(saveNode.size() - 1);
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(0);
        TreeNode t3 = new TreeNode(1);
        TreeNode t4 = new TreeNode(0);
        TreeNode t5 = new TreeNode(1);
        TreeNode t6 = new TreeNode(0);
        TreeNode t7 = new TreeNode(1);
        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t2.right = t5;
        t3.left = t6;
        t3.right = t7;
        sumRootToLeaf(t1);
    }
}
