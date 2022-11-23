package com.niugiaogiao.binarytree.leetcode;

import com.niugiaogiao.binarytree.struct.TreeNode;

/**
 * 1026. 节点与其祖先之间的最大差值
 * 给定二叉树的根节点 root，找出存在于 不同 节点 A 和 B 之间的最大值 V，其中 V = |A.val - B.val|，且 A 是 B 的祖先。
 * <p>
 * （如果 A 的任何子节点之一为 B，或者 A 的任何子节点是 B 的祖先，那么我们认为 A 是 B 的祖先）
 * https://leetcode.cn/problems/maximum-difference-between-node-and-ancestor/
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-11-23 22:29
 */
public class BinaryTree1026 {

    int max = Integer.MIN_VALUE;

    public int maxAncestorDiff(TreeNode root) {
        if (root == null) {
            return max;
        }
        run(root, root);
        maxAncestorDiff(root.left);
        maxAncestorDiff(root.right);
        return max;
    }

    public void run(TreeNode node, TreeNode parent) {
        if (node == null) {
            return;
        }
        if (node.left != null) {
            max = Math.max(Math.abs(parent.val - node.left.val), max);
            run(node.left, parent);
        }
        if (node.right != null) {
            max = Math.max(Math.abs(parent.val - node.right.val), max);
            run(node.right, parent);
        }
    }

    int res = Integer.MAX_VALUE;

    public int maxAncestorDiffDFS2(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return res;
    }

    public void runDFS2(TreeNode node, int max, int min) {
        if (node == null) {
            return;
        }
        max = Math.max(max, node.val);
        min = Math.min(min, node.val);
        runDFS2(node.left, max, min);
        runDFS2(node.right, max, min);
        res = Math.max(res, Math.abs(max - min));
    }
}
