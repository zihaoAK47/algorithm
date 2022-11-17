package com.niugiaogiao.binarytree.leetcode;

import com.niugiaogiao.binarytree.struct.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 剑指 Offer 28. 对称的二叉树
 * 请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。
 * <p>
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的
 * <p>
 * https://leetcode.cn/problems/dui-cheng-de-er-cha-shu-lcof/description/
 */

public class BinaryTree28 {

    public boolean isSymmetricDFS(TreeNode root) {
        if (root == null) {
            return true;
        }

        return run(root, root);
    }

    public boolean run(TreeNode lNode, TreeNode rNode) {
        if (lNode == null && rNode == null) {
            return true;
        }
        if (lNode == null || rNode == null || lNode.val != rNode.val) {
            return false;
        }
        return run(lNode.left, rNode.right) && run(lNode.right, rNode.left);
    }

    public boolean isSymmetricBFS(TreeNode root) {
        if (root == null) {
            return true;
        }
        Queue<TreeNode> queue1 = new LinkedList<>();
        queue1.add(root);
        queue1.add(root);
        while (!queue1.isEmpty() ) {
            TreeNode u = queue1.poll();
            TreeNode v = queue1.poll();
            if (u == null && v == null) {
                continue;
            }
            if ((u == null || v == null) || (u.val != v.val)) {
                return false;
            }
            queue1.add(u.left);
            queue1.add(v.right);

            queue1.add(u.right);
            queue1.add(v.left);

        }

        return true;
    }
}