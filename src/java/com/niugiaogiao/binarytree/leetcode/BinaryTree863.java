package com.niugiaogiao.binarytree.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 二叉树中所有距离为 K 的结点
 * 给定一个二叉树（具有根结点root），一个目标结点target，和一个整数值 k 。
 * <p>
 * 返回到目标结点 target 距离为 k 的所有结点的值的列表。 答案可以以 任何顺序 返回。
 * <p>
 * 链接：https://leetcode.cn/problems/all-nodes-distance-k-in-binary-tree
 *
 * @author zihao
 */
public class BinaryTree863 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    Map<TreeNode, TreeNode> dict = new HashMap<>();
    List<Integer> result = new LinkedList<>();

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        setParent(root);
        dfs(target, null, k, 0);
        return result;
    }

    public void setParent(TreeNode node) {
        if (node == null) return;
        if (node.left != null) {
            dict.put(node.left, node);
            setParent(node.left);
        }
        if (node.right != null) {
            dict.put(node.right, node);
            setParent(node.right);
        }
    }

    public void dfs(TreeNode node, TreeNode from, int k, int deep) {
        if (node == null) return;

        if (k == deep) {
            result.add(node.val);
            return;
        }

        if (node.left != from) {
            dfs(node.left, node, k, deep + 1);
        }

        if (node.right != from) {
            dfs(node.right, node, k, deep + 1);
        }

        TreeNode treeNode = dict.get(node);
        if (treeNode != null && treeNode != from) {
            dfs(treeNode, node, k, deep + 1);
        }
    }
}