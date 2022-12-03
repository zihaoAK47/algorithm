package com.niugiaogiao.binarytree.leetcode;

import com.niugiaogiao.binarytree.struct.TreeNode;

/**
 * 面试题 04.05. 合法二叉搜索树
 * 实现一个函数，检查一棵二叉树是否为二叉搜索树
 * https://leetcode.cn/problems/legal-binary-search-tree-lcci/description/
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-12-03 23:18
 */
public class BinaryTree0405 {

    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValidBST(TreeNode root, long min, long max) {
        return root == null || (root.val > min && root.val < max && isValidBST(root.left, min, root.val) && isValidBST(root.right, root.val, max));
    }
}
