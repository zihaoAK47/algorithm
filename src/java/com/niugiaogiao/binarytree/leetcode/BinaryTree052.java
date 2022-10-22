package com.niugiaogiao.binarytree.leetcode;

import com.niugiaogiao.binarytree.struct.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * 剑指 Offer II 052. 展平二叉搜索树
 * 给你一棵二叉搜索树，请 按中序遍历 将其重新排列为一棵递增顺序搜索树，使树中最左边的节点成为树的根节点，并且每个节点没有左子节点，只有一个右子节点。
 * https://leetcode.cn/problems/NYBBNL/
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-10-22 21:17
 */
public class BinaryTree052 {

    TreeNode tRoot = null;
    TreeNode pre = null;
    public TreeNode increasingBST(TreeNode root) {
        run(root);
        return tRoot;
    }

    public void run(TreeNode node) {
        if (node == null) return;
        run(node.left);
        if (tRoot == null) {
            tRoot = node;
        } else {
            pre.right = node;
            node.left = null;
        }
        pre = node;
        run(node.right);
    }


    List<TreeNode> data = new LinkedList<>();
    public TreeNode increasingBST1(TreeNode root) {
        run1(root);
        for (int i = 0 ; i < data.size() - 1 ; ++i) {
            data.get(i).right = data.get(i + 1);
            data.get(i).left = null;
        }
        data.get(data.size() - 1).left = null;
        return tRoot;
    }

    public void run1(TreeNode node) {
        if (node == null) return;
        run1(node.left);
        data.add(node);
        run1(node.right);
    }
}
