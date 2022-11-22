package com.niugiaogiao.binarytree.leetcode;

import com.niugiaogiao.binarytree.struct.TreeNode;

/**
 * 输入一棵二叉树的根节点，判断该树是不是平衡二叉树。如果某二叉树中任意节点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树
 * https://leetcode.cn/problems/ping-heng-er-cha-shu-lcof/
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-11-14 23:02
 */
public class BinaryTree55_2 {

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

        int lHeight = run(node.left);
        int rHeight = run(node.right);
        if (Math.abs(lHeight - rHeight) > 1) {
            res = false;
        }
        return Math.max(lHeight, rHeight) + 1;
    }
}
