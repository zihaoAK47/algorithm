package com.niugiaogiao.binarytree.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 给你一个含重复值的二叉搜索树（BST）的根节点 root ，找出并返回 BST 中的所有 众数（即，出现频率最高的元素）。
 * <p>
 * 如果树中有不止一个众数，可以按 任意顺序 返回。
 * <p>
 * 假定 BST 满足如下定义：
 * <p>
 * 结点左子树中所含节点的值 小于等于 当前节点的值
 * 结点右子树中所含节点的值 大于等于 当前节点的值
 * 左子树和右子树都是二叉搜索树
 * <p>
 * 链接：https://leetcode.cn/problems/find-mode-in-binary-search-tree
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-09-17 14:15
 */
public class BinaryTree501 {

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


    int count = 0;
    Map<Integer, Integer> dict = new HashMap<>(16);
    public int[] findMode(TreeNode root) {
        inorder(root);
        List<Integer> res = new ArrayList<>();
        for (Map.Entry<Integer, Integer> item : dict.entrySet()) {
            if (item.getValue() == count) {
                res.add(item.getKey());
            }
        }

        int[] tRes = new int[res.size()];
        for (int i = 0; i < res.size(); ++i) {
            tRes[i] = res.get(i);
        }

        return tRes;
    }

    public void inorder(TreeNode node) {
        if (node == null) return;

        inorder(node.left);
        dict.put(node.val, dict.getOrDefault(node.val, 0) + 1);
        count = Math.max(count, dict.get(node.val));
        inorder(node.right);
    }

}
