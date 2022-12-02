package com.niugiaogiao.binarytree.leetcode;

import com.niugiaogiao.binarytree.struct.TreeNode;

/**
 * 面试题 04.08. 首个共同祖先
 * https://leetcode.cn/problems/first-common-ancestor-lcci/description/
 * <p>
 * 设计并实现一个算法，找出二叉树中某两个节点的第一个共同祖先。不得将其他的节点存储在另外的数据结构中。注意：这不一定是二叉搜索树
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-12-02 22:55
 */
public class BinaryTree0408 {

    TreeNode res = null;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        run(root, p, q);
        return res;
    }

    public boolean run(TreeNode node, TreeNode p, TreeNode q) {
        if (node == null) {
            return false;
        }
        boolean lRes = run(node.left, p, q) || node.val == p.val || node.val == q.val;
        boolean rRes = run(node.right, p, q) || node.val == p.val || node.val == q.val;
        if (lRes && rRes) {
            res = node;
        }
        return lRes || rRes;
    }
}
