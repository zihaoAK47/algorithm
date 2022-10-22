package com.niugiaogiao.binarytree.leetcode;

import com.niugiaogiao.binarytree.struct.TreeNode;

/**
 * 剑指 Offer II 053. 二叉搜索树中的中序后继
 * 给定一棵二叉搜索树和其中的一个节点 p ，找到该节点在树中的中序后继。如果节点没有中序后继，请返回 null 。
 * <p>
 * 节点 p 的后继是值比 p.val 大的节点中键值最小的节点，即按中序遍历的顺序节点 p 的下一个节点。
 * <p>
 * 链接：https://leetcode.cn/problems/P5rCT8
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-10-22 21:28
 */
public class BinaryTree053 {

    static boolean isFindTarget = false;
    static TreeNode next = null;
    public static TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        run(root, p);
        return null;
    }

    public static void run(TreeNode node, TreeNode p) {
        if (node == null) return;
        run(node.left, p);
        if (node.val == p.val) {
            isFindTarget = true;
        } else if (isFindTarget && next == null) {
            next = node;
            return;
        }
        run(node.right, p);
    }
}
