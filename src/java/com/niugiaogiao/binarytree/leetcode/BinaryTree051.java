package com.niugiaogiao.binarytree.leetcode;

import com.niugiaogiao.binarytree.struct.TreeNode;

/**
 * 节点之和最大的路径
 * 路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。
 * 路径和 是路径中各节点值的总和。
 * 给定一个二叉树的根节点 root ，返回其 最大路径和，即所有路径上节点值之和的最大值
 * <p>
 * 链接：https://leetcode.cn/problems/jC7MId
 *
 * @author zihao
 */

public class BinaryTree051 {

    int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        run(root);
        return max;
    }

    public int run(TreeNode node) {
        if (node == null) return 0;
        int l = Math.max(run(node.left), 0);
        int r = Math.max(run(node.right), 0);
        int self = l + r + node.val;
        max = Math.max(max, self);
        return node.val + Math.max(l, r);
    }
}
