package com.niugiaogiao.binarytree.leetcode;

import com.niugiaogiao.binarytree.struct.TreeNode;

import java.util.Stack;

/**
 * 给你两棵二叉树，原始树 original 和克隆树 cloned，以及一个位于原始树 original 中的目标节点 target。
 * <p>
 * 其中，克隆树 cloned 是原始树 original 的一个 副本 。
 * <p>
 * 请找出在树cloned中，与target相同的节点，并返回对该节点的引用（在 C/C++ 等有指针的语言中返回 节点指针，其他语言返回节点本身）。
 * 注意：你 不能 对两棵二叉树，以及 target节点进行更改。只能 返回对克隆树cloned中已有的节点的引用。
 * <p>
 * 链接：https://leetcode.cn/problems/find-a-corresponding-node-of-a-binary-tree-in-a-clone-of-that-tree
 *
 * @author zi hao
 * @version 1.0
 * @date 2022-10-23 14:00
 */
public class BinaryTree1379 {

    TreeNode res = null;
    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        find(cloned, target);
        return res;
    }

    public void find(TreeNode cloned, TreeNode target) {
        if (cloned == null) return;
        if (res != null) return;
        if (cloned.val == target.val) {
            res = cloned;
            return;
        }
        find(cloned.left, target);
        find(cloned.right, target);
    }

    public final TreeNode getTargetCopyStack(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(cloned);
        while (!stack.isEmpty()) {
            TreeNode pollItem = stack.pop();
            if (pollItem.val == target.val) {
                return pollItem;
            }

            if (pollItem.right != null) {
                stack.push(pollItem.right);
            }
            if (pollItem.left != null) {
                stack.push(pollItem.left);
            }
        }
        return null;
    }

}
