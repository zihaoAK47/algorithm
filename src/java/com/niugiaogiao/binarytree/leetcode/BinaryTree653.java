package com.niugiaogiao.binarytree.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 两数之和五 - 输入二叉搜索树
 * 给定一个二叉搜索树 root 和一个目标结果 k，如果二叉搜索树中存在两个元素且它们的和等于给定的目标结果，则返回 true
 *
 * https://leetcode.cn/problems/two-sum-iv-input-is-a-bst/
 *
 * @author zihao
 */

public class BinaryTree653 {

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

    Map<Integer, TreeNode> dict = new HashMap<>();
    public boolean findTarget(TreeNode root, int k) {
        saveNode(root);
        for (Map.Entry<Integer, TreeNode> item : dict.entrySet()) {
            if (dict.containsKey(k - item.getKey()) && dict.get(k - item.getKey()) != dict.get(item.getKey())) {
                return true;
            }
        }

        return false;
    }

    public void saveNode(TreeNode node) {
        if (node == null) return;
        saveNode(node.left);
        saveNode(node.right);
        dict.put(node.val,node);
    }
}
