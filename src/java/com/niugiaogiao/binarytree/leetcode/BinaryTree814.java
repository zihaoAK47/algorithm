package com.niugiaogiao.binarytree.leetcode;

import com.niugiaogiao.binarytree.struct.TreeNode;

/**
 * 814. 二叉树剪枝
 * https://leetcode.cn/problems/binary-tree-pruning/
 * <p>
 * 给你二叉树的根结点 root ，此外树的每个结点的值要么是 0 ，要么是 1 。
 * <p>
 * 返回移除了所有不包含 1 的子树的原二叉树。
 * <p>
 * 节点 node 的子树为 node 本身加上所有 node 的后代。
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-12-10 23:23
 */
public class BinaryTree814 {

    public TreeNode pruneTree(TreeNode root) {
        if (root == null)
            return null;

        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        return root.left == null && root.right == null && root.val == 0 ? null : root;
    }

    public TreeNode pruneTree2(TreeNode root) {
        if (root == null) {
            return null;
        }

        run(root);
        if (root.left == null && root.right == null && root.val == 0) {
            return null;
        }

        return root;
    }

    public boolean run(TreeNode node) {
        if (node == null) {
            return true;
        }
        boolean lRes = run(node.left);
        boolean rRes = run(node.right);
        if (lRes) {
            node.left = null;
        }
        if (rRes) {
            node.right = null;
        }
        return node.val == 0 && lRes && rRes;
    }
}
