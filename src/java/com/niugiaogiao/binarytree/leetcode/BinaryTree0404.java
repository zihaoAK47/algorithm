package com.niugiaogiao.binarytree.leetcode;

import com.niugiaogiao.binarytree.struct.TreeNode;

/**
 * 面试题 04.04. 检查平衡性
 * 实现一个函数，检查二叉树是否平衡。在这个问题中，平衡树的定义如下：任意一个节点，其两棵子树的高度差不超过 1
 * https://leetcode.cn/problems/check-balance-lcci/
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-11-24 23:05
 */
public class BinaryTree0404 {

    boolean res = true;

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        run(root);
        return res;
    }


    public int run(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int l = run(node.left);
        int r = run(node.right);
        int selfHeight = Math.max(l, r) + 1;
        if (Math.abs(l - r) > 1) {
            res = false;
        }
        return selfHeight;
    }
}
