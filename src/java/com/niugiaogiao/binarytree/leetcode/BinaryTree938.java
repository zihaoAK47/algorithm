package com.niugiaogiao.binarytree.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉搜索树的范围和
 * <p>
 * 给定二叉搜索树的根结点 root，返回值位于范围 [low, high] 之间的所有结点的值的和
 * https://leetcode.cn/problems/range-sum-of-bst/
 *
 * @author zihao
 */

public class BinaryTree938 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public int rangeSumBST1(TreeNode root, int low, int high) {
        int res = 0;
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            res += poll.val >= low && poll.val <= high ? poll.val : 0;
            if (poll.left != null) {
                queue.add(poll.left);
            }
            if (poll.right != null) {
                queue.add(poll.right);
            }
        }

        return res;
    }

    /* 实现方式2 */
    int sum2 = 0;

    public int rangeSumBST2(TreeNode root, int low, int high) {
        run2(root, low, high);
        return sum2;
    }

    public void run2(TreeNode root, int low, int high) {
        if (root == null) return;
        if (root.val >= low && root.val <= high) sum2 += root.val;
        run2(root.left, low, high);
        run2(root.right, low, high);
    }

    /* 实现方式3 */
    int sum3 = 0;

    public int rangeSumBST3(TreeNode root, int low, int high) {
        run3(root, low, high);
        return sum2;
    }

    public void run3(TreeNode root, int low, int high) {
        if (root == null) return;
        if (root.val >= low && root.val <= high) sum2 += root.val;
        if (root.val < low) {
            run3(root.right, low, high);
        } else {
            run3(root.left, low, high);
            run3(root.right, low, high);
        }
    }

    /* 实现方式4 */
    public int rangeSumBST4(TreeNode root, int low, int high) {
        int res = 0;
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            if (poll.val >= low && poll.val <= high) {
                res += poll.val;
            }
            if (poll.val < low) {
                queue.add(poll.right);
            } else {
                queue.add(poll.left);
                queue.add(poll.right);
            }
        }

        return res;
    }

    /* 实现方式5 */
    public int rangeSumBST5(TreeNode root, int low, int high) {
        if (root == null) return 0;
        if (root.val > high) return rangeSumBST5(root.left, low, high);
        if (root.val < low) return rangeSumBST5(root.right, low, high);
        return root.val + rangeSumBST5(root.left, low, high) + rangeSumBST5(root.right, low, high);
    }
}