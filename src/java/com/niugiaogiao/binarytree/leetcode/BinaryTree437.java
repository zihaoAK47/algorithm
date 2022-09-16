package com.niugiaogiao.binarytree.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个二叉树的根节点 root，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
 * 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 * <p>
 * 链接：https://leetcode.cn/problems/path-sum-iii
 *
 * @author zihao
 */
public class BinaryTree437 {

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

    public int pathSum(TreeNode root, int targetSum) {
        // 以每个节点为起始节点向下搜索所有的可能性 穷举法
        if (root == null) return 0;

        return pathSum(root.left, targetSum) + pathSum(root.right, targetSum) + run(root, targetSum);
    }

    public int run(TreeNode node, long targetSum) {
        // 假设以当前节点开始进行遍历
        if (node == null) return 0;

        return ((targetSum == node.val) ? 1 : 0)
                + run(node.left, node.val - targetSum)
                + run(node.right, node.val - targetSum);
    }

    public static int pathSum1(TreeNode root, int targetSum) {
        Map<Long, Integer> prefix = new HashMap<Long, Integer>();
        prefix.put(0L, 1);
        return dfs(root, prefix, 0, targetSum);
    }

    public static int dfs(TreeNode root, Map<Long, Integer> prefix, long curr, int targetSum) {
        if (root == null) {
            return 0;
        }

        int ret = 0;
        curr += root.val;

        ret = prefix.getOrDefault(curr - targetSum, 0);
        prefix.put(curr, prefix.getOrDefault(curr, 0) + 1);
        ret += dfs(root.left, prefix, curr, targetSum);
        ret += dfs(root.right, prefix, curr, targetSum);
        prefix.put(curr, prefix.getOrDefault(curr, 0) - 1);

        return ret;
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(10);
        TreeNode t2 = new TreeNode(5);
        TreeNode t3 = new TreeNode(-3);
        TreeNode t4 = new TreeNode(3);
        TreeNode t5 = new TreeNode(2);
        TreeNode t6 = new TreeNode(11);
        TreeNode t7 = new TreeNode(3);
        TreeNode t8 = new TreeNode(-2);
        TreeNode t9 = new TreeNode(1);
        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t2.right = t5;
        t4.left = t7;
        t4.right = t8;
        t5.right = t9;
        t3.right = t6;
    }
}
