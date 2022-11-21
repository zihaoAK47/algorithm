package com.niugiaogiao.binarytree.leetcode;

import com.niugiaogiao.binarytree.struct.TreeNode;

/**
 * https://leetcode.cn/problems/shu-de-zi-jie-gou-lcof/
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-11-21 23:00
 */
public class BinaryTree26 {

    public boolean isSubStructure(TreeNode A, TreeNode B) {
        return (A != null && B != null) && (run(A,B) || isSubStructure(A.left,B) || isSubStructure(A.right,B));
    }

    public boolean run(TreeNode nodeA, TreeNode nodeB) {
        if (nodeB == null) {
            return true;
        }
        if (nodeA == null || nodeA.val != nodeB.val) {
            return false;
        }
        return run(nodeA.left, nodeB.left) && run(nodeA.right, nodeB.right);
    }
}
