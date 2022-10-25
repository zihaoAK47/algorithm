package com.niugiaogiao.binarytree.leetcode;

import com.niugiaogiao.binarytree.struct.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给你一棵二叉树，请你返回满足以下条件的所有节点的值之和：
 * <p>
 * 该节点的祖父节点的值为偶数。（一个节点的祖父节点是指该节点的父节点的父节点。）
 * 如果不存在祖父节点值为偶数的节点，那么返回 0
 * <p>
 * 链接：https://leetcode.cn/problems/sum-of-nodes-with-even-valued-grandparent
 *
 * @author zihao
 */

public class BinaryTree1315 {

    int res = 0;

    public int sumEvenGrandparentDFS(TreeNode root) {
        run(root);
        return res;
    }

    // 从上往下搜索，若当前节点是偶数，检测是否存在子节点
    public void run(TreeNode node) {
        if (node == null) return;
        if (node.val % 2 == 0) {
            if (node.left != null && node.left.left != null) {
                res += node.left.left.val;
            }
            if (node.left != null && node.left.right != null) {
                res += node.left.right.val;
            }
            if (node.right != null && node.right.left != null) {
                res += node.right.left.val;
            }
            if (node.right != null && node.right.right != null) {
                res += node.right.right.val;
            }
        }
        run(node.left);
        run(node.right);
    }

    public int sumEvenGrandparentBFS(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode item = queue.poll();
            boolean isSum = item.val % 2 == 0;
            if (item.left != null) {
                if (isSum && item.left.left != null) res += item.left.left.val;
                if (isSum && item.left.right != null) res += item.left.right.val;
                queue.add(item.left);
            }
            if (item.right != null) {
                if (isSum && item.right.left != null) res += item.right.left.val;
                if (isSum && item.right.right != null) res += item.right.right.val;
                queue.add(item.right);
            }
        }
        return res;
    }
}
