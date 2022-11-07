package com.niugiaogiao.binarytree.leetcode;

import com.niugiaogiao.binarytree.struct.TreeNode;

/**
 * 1080. 根到叶路径上的不足节点
 * 给定一棵二叉树的根 root，请你考虑它所有 从根到叶的路径：从根到任何叶的路径。（所谓一个叶子节点，就是一个没有子节点的节点）
 * 假如通过节点 node 的每种可能的 “根-叶” 路径上值的总和全都小于给定的 limit，则该节点被称之为「不足节点」，需要被删除
 * 请你删除所有不足节点，并返回生成的二叉树的根
 * <p>
 * https://leetcode.cn/problems/insufficient-nodes-in-root-to-leaf-paths/
 *
 * @author zihao
 */

public class BinaryTree1080 {

    public  TreeNode sufficientSubset(TreeNode root, int limit) {
        if (root == null) {
            return null;
        }
        if (root.left == null && root.right == null) {
            return root.val < limit ? null : root;
        }
        root.left = sufficientSubset(root.left, limit - root.val);
        root.right = sufficientSubset(root.right, limit - root.val);
        return root.left != null || root.right != null ? root : null;
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(-3);
        TreeNode t4 = new TreeNode(-5);
        TreeNode t5 = new TreeNode(4);
        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t3.left = t5;
    }
}
