package com.niugiaogiao.binarytree.leetcode;

import com.niugiaogiao.binarytree.struct.TreeNode;

/**
 * 剑指 Offer II 045. 二叉树最底层最左边的值
 * 给定一个二叉树的 根节点 root，请找出该二叉树的 最底层 最左边 节点的值。
 * 假设二叉树中至少有一个节点
 *
 * https://leetcode.cn/problems/LwUNpT/
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-10-29 21:20
 */
public class BinaryTree045 {

    int val = 0;
    int maxDeep = 0;
    public int findBottomLeftValue(TreeNode root) {
        run(root,1);
        return val;
    }

    public void run(TreeNode node, int deep) {
        if (node == null) return;
        if (maxDeep < deep) {
            val = node.val;
            maxDeep = deep;
            if (node.left != null) {
                val = node.left.val;
            }
        }
        run(node.left, deep + 1);
        run(node.right, deep + 1);
    }
}
