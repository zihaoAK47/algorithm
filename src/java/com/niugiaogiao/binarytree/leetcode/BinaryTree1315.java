package com.niugiaogiao.binarytree.leetcode;

import com.niugiaogiao.binarytree.struct.TreeNode;

/**
 * 给你一棵二叉树，请你返回满足以下条件的所有节点的值之和：
 * <p>
 * 该节点的祖父节点的值为偶数。（一个节点的祖父节点是指该节点的父节点的父节点。）
 * 如果不存在祖父节点值为偶数的节点，那么返回 0
 * <p>
 * 链接：https://leetcode.cn/problems/sum-of-nodes-with-even-valued-grandparent
 *
 * @author zihao
 */

public class BinaryTree1315 {

    int res = 0;

    public int sumEvenGrandparent(TreeNode root) {
        run(root);
        return res;
    }

    // 从上往下搜索，若当前节点是偶数，检测是否存在子节点
    public void run(TreeNode node) {
        if (node == null) return;
        if (node.val % 2 == 0) {
            if (node.left != null && node.left.left != null) {
                res += node.left.left.val;
            }
            if (node.left != null && node.left.right != null) {
                res += node.left.right.val;
            }
            if (node.right != null && node.right.left != null) {
                res += node.right.left.val;
            }
            if (node.right != null && node.right.right != null) {
                res += node.right.right.val;
            }
        }
        run(node.left);
        run(node.right);
    }
}
