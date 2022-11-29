package com.niugiaogiao.binarytree.leetcode;

import com.niugiaogiao.binarytree.struct.TreeNode;

/**
 * 701. 二叉搜索树中的插入操作
 * 给定二叉搜索树（BST）的根节点 root 和要插入树中的值 value ，将值插入二叉搜索树。 返回插入后二叉搜索树的根节点。 输入数据 保证 ，新值和原始二叉搜索树中的任意节点值都不同。
 * <p>
 * 注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回 任意有效的结果
 * https://leetcode.cn/problems/insert-into-a-binary-search-tree/
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-11-29 22:03
 */
public class BinaryTree701 {

    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        TreeNode tRoot = root;
        while (true) {
            if (tRoot.val <= val) {
                if (tRoot.right == null) {
                    tRoot.right = new TreeNode(val);
                    return root;
                } else {
                    tRoot = tRoot.right;
                }
            } else {
                if (tRoot.left == null) {
                    tRoot.left = new TreeNode(val);
                    return root;
                } else {
                    tRoot = tRoot.left;
                }
            }
        }
    }
}
