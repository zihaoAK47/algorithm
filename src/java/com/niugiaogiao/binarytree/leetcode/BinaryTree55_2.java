package com.niugiaogiao.binarytree.leetcode;

import com.niugiaogiao.binarytree.struct.TreeNode;

/**
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
