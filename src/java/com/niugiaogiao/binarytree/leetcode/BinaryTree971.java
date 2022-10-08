package com.niugiaogiao.binarytree.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 翻转二叉树以匹配先序遍历
 * 给你一棵二叉树的根节点 root ，树中有 n 个节点，每个节点都有一个不同于其他节点且处于 1 到 n 之间的值。
 * <p>
 * 另给你一个由 n 个值组成的行程序列 voyage ，表示 预期 的二叉树 先序遍历 结果。
 * <p>
 * 链接：https://leetcode.cn/problems/flip-binary-tree-to-match-preorder-traversal
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-10-08 22:08
 */
public class BinaryTree971 {

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

    static int index = 0;
    static List<Integer> res = new LinkedList<>();

    public static List<Integer> flipMatchVoyageDFS1(TreeNode root, int[] voyage) {
        return run1(root, voyage) ? res : null;
    }

    public static boolean run1(TreeNode node, int[] voyage) {
        if (node == null) return true;
        int tIndex = index;
        if (voyage[index++] != node.val) return false;
        if (run1(node.left, voyage) && run1(node.right, voyage)) return true;
        index = tIndex + 1;
        boolean leftRes = run1(node.right, voyage);
        boolean rightRes = run1(node.left, voyage);
        if (leftRes && rightRes) res.add(node.val);
        return leftRes && rightRes;
    }


    static List<Integer> res1 = new LinkedList<>();
    static int index1 = 0;
    public static List<Integer> flipMatchVoyageDFS2(TreeNode root, int[] voyage) {
        run(root, voyage);
        if (!res1.isEmpty() && res1.get(0) == -1) {
            res1.clear();
            res1.add(-1);
        }
        return res1;
    }

    public static void run(TreeNode node, int[] voyage) {
        if (node != null) {
            if (node.val != voyage[index1++]) {
                res1.clear();
                res1.add(-1);
                return;
            }
            if (index1 < voyage.length && node.left != null && node.left.val != voyage[index1]) {
                res1.add(node.val);
                run(node.right, voyage);
                run(node.left, voyage);
            } else {
                run(node.left, voyage);
                run(node.right, voyage);
            }
        }
    }
}
