package com.niugiaogiao.binarytree.leetcode;

import com.niugiaogiao.binarytree.struct.TreeNode;

/**
 * 面试题 04.06. 后继者
 * https://leetcode.cn/problems/successor-lcci/description/
 * 设计一个算法，找出二叉搜索树中指定节点的“下一个”节点（也即中序后继）。
 * <p>
 * 如果指定节点没有对应的“下一个”节点，则返回nul
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-12-07 23:26
 */
public class BinaryTree0406 {

    boolean isFind = false;
    TreeNode res = null;
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null) {
            return null;
        }
        run(root, p);
        return res;
    }

    public void run(TreeNode node, TreeNode p) {
        if (node == null || res != null) {
            return;
        }
        run(node.left, p);
        if (node.val == p.val) {
            isFind = true;
        } else if (isFind && res == null) {
            res = node;
        }
        run(node.right, p);
    }
}
