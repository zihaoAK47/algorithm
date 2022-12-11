package com.niugiaogiao.binarytree.leetcode;

import com.niugiaogiao.binarytree.struct.TreeNode;

/**
 * 剑指 Offer II 047. 二叉树剪枝
 * https://leetcode.cn/problems/pOCWxh/description/
 * <p>
 * 给定一个二叉树 根节点 root ，树的每个节点的值要么是 0，要么是 1。请剪除该二叉树中所有节点的值为 0 的子树。
 * <p>
 * 节点 node 的子树为 node 本身，以及所有 node 的后代
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-12-11 23:11
 */
public class BinaryTree047 {

    public TreeNode pruneTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        return root.left == null && root.right == null && root.val == 0 ? null : root;
    }
}
