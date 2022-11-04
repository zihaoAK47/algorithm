package com.niugiaogiao.binarytree.leetcode;

import com.niugiaogiao.binarytree.struct.TreeNode;

/**
 * 剑指 Offer 54. 二叉搜索树的第k大节点
 * 给定一棵二叉搜索树，请找出其中第 k 大的节点的值
 * https://leetcode.cn/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof/
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-11-03 23:22
 */
public class BinaryTree54 {

    int res = 0;
    int tempK = 0;

    public int kthLargest(TreeNode root, int k) {
        tempK = k;
        run(root);
        return res;
    }

    public void run(TreeNode node) {
        if (node == null) return;
        run(node.right);
        if (--tempK == 0) {
            res = node.val;
            return;
        }
        run(node.left);
    }
}
